package com.ravensoftware.reportingclient.service;

import com.ravensoftware.reportingclient.configuration.ReportingApiConfiguration;
import com.ravensoftware.reportingclient.exception.ReportingApiException;
import com.ravensoftware.reportingclient.model.ReportRequest;
import com.ravensoftware.reportingclient.model.TransactionReportResponse;
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
public class ReportClient {

    private final RestTemplate restTemplate;
    private final ReportingApiConfiguration reportingApiConfiguration;
    private final LoginClient loginClient;

    public TransactionReportResponse getTransactionReportResponse(ReportRequest request) throws ReportingApiException{
        log.info("ReportingApiClient report call started: {}", request);
        try {
            String accessToken = loginClient.login().getToken();

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            headers.set("Authorization", accessToken);

            HttpEntity<ReportRequest> entity = new HttpEntity<>(request, headers);
            return Optional.of(restTemplate.postForEntity(reportingApiConfiguration.getReportUrl(), entity, TransactionReportResponse.class).getBody()).get();
        } catch (Exception e) {
            log.error("Check report service ", e);
            throw new ReportingApiException(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
}
