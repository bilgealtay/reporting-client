package com.ravensoftware.reportingclient.control;

import com.ravensoftware.reportingclient.exception.ReportingApiException;
import com.ravensoftware.reportingclient.model.CustomerInfoResponse;
import com.ravensoftware.reportingclient.model.api.CustomerInfo;
import com.ravensoftware.reportingclient.model.api.TransactionResponse;
import com.ravensoftware.reportingclient.service.CustomerInfoClient;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

/**
 * Created by bilga
 */
@RunWith(MockitoJUnitRunner.class)
public class CustomerInfoControlTest {
    @Mock
    private CustomerInfoClient customerInfoClient;
    @InjectMocks
    private CustomerInfoControl underTest ;

    @Test
    public void shouldReturnResponseWithWarningWhenServiceThrowException() {
        // given
        when(customerInfoClient.getCustomerInfo(any())).thenThrow(new ReportingApiException("e.getMessage()", HttpStatus.BAD_REQUEST));
        // when
        CustomerInfoResponse result = underTest.getClient(any());
        // then
        Assert.assertNotNull(result.getWarning());
        Assert.assertEquals(result.getWarning().getCode(), 400);
    }

    @Test
    public void shouldReturnResponseWithResponse() {
        // given
        TransactionResponse transactionResponse = new TransactionResponse();
        transactionResponse.setCustomerInfo(new CustomerInfo());

        when(customerInfoClient.getCustomerInfo(any())).thenReturn(transactionResponse);

        // when
        CustomerInfoResponse result = underTest.getClient(any());

        // then
        Assert.assertNull(result.getWarning());
        Assert.assertNotNull(result.getCustomerInfo());

    }
}
