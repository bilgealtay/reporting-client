package com.ravensoftware.reportingclient.service;

import com.ravensoftware.reportingclient.configuration.ReportingApiConfiguration;
import com.ravensoftware.reportingclient.exception.ReportingApiException;
import com.ravensoftware.reportingclient.model.LoginResponse;
import com.ravensoftware.reportingclient.model.TransactionParam;
import com.ravensoftware.reportingclient.model.Warning;
import com.ravensoftware.reportingclient.model.api.CustomerInfo;
import com.ravensoftware.reportingclient.model.api.TransactionResponse;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;

/**
 * Created by bilga
 */
@RunWith(MockitoJUnitRunner.class)
public class CustomerInfoClientTest {

    @Mock
    private RestTemplate restTemplate;
    @Mock
    private ReportingApiConfiguration reportingApiConfiguration;
    @Mock
    private LoginClient loginClient;
    @InjectMocks
    private CustomerInfoClient underTest ;

    @Test
    public void shouldThrowReportingApiExceptionWhenNotLogin(){
        // given
        LoginResponse loginResponse = new LoginResponse();
        loginResponse.setWarning(Warning.builder().code(400).build());
        when(loginClient.login()).thenReturn(loginResponse);
        // when
        ReportingApiException reportingApiException = Assert.assertThrows(ReportingApiException.class, () -> underTest.getCustomerInfo(new TransactionParam()));

        // then
        Assert.assertNotNull(reportingApiException.getErrorCode());
    }

    @Test
    public void shouldThrowReportingApiExceptionWhenRestCallHasException(){
        // given
        when(loginClient.login()).thenReturn(new LoginResponse());
        when(restTemplate.postForEntity(anyString(), anyMap(), any())).thenThrow(new RuntimeException());
        // when
        ReportingApiException reportingApiException = Assert.assertThrows(ReportingApiException.class, () -> underTest.getCustomerInfo(new TransactionParam()));

        // then
        Assert.assertNotNull(reportingApiException.getErrorCode());
    }

    @Test
    public void shouldReturnTransactionResponse(){
        // given
        TransactionParam transactionParam = new TransactionParam();
        transactionParam.setTransactionId("transactionId");

        TransactionResponse transactionResponse = new TransactionResponse();
        CustomerInfo customerInfo = new CustomerInfo();
        customerInfo.setEmail("email");
        transactionResponse.setCustomerInfo(new CustomerInfo());

        LoginResponse loginResponse = new LoginResponse();
        loginResponse.setToken("token");

        when(loginClient.login()).thenReturn(loginResponse);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("Authorization", loginResponse.getToken());

        HttpEntity<TransactionParam> entity = new HttpEntity<>(transactionParam, headers);

        when(restTemplate.postForEntity(reportingApiConfiguration.getClientUrl(), entity, TransactionResponse.class))
                .thenReturn(new ResponseEntity(transactionResponse, HttpStatus.OK));

        // when
        TransactionResponse result = underTest.getCustomerInfo(transactionParam);

        // then
        Assert.assertNotNull(result.getCustomerInfo());
    }
}
