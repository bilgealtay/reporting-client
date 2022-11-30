package com.ravensoftware.reportingclient.service;

import com.ravensoftware.reportingclient.configuration.ReportingApiConfiguration;
import com.ravensoftware.reportingclient.exception.ReportingApiException;
import com.ravensoftware.reportingclient.model.TransactionListRequest;
import com.ravensoftware.reportingclient.model.TransactionListResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Optional;

/**
 * Created by bilga
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class TransactionListClient {

    private final RestTemplate restTemplate;
    private final ReportingApiConfiguration reportingApiConfiguration;
    private final LoginClient loginClient;

    public TransactionListResponse getTransactionListResponse(TransactionListRequest request) throws ReportingApiException{
        log.info("ReportingApiClient transaction list call started: {}", request);
        try {
            // TODO: add a cache mechanism to avoid login call before every request
            String accessToken = loginClient.login().getToken();

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            headers.set("Authorization", accessToken);

            HttpEntity<TransactionListRequest> entity = new HttpEntity<>(request, headers);
            return Optional.of(restTemplate.postForEntity(reportingApiConfiguration.getTransactionListUrl(), entity, TransactionListResponse.class).getBody()).get();
        } catch (Exception e) {
            log.error("Check transaction list service ", e);
            throw new ReportingApiException(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
}
