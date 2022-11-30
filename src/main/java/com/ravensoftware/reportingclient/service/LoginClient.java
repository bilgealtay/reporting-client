package com.ravensoftware.reportingclient.service;

import com.ravensoftware.reportingclient.configuration.ReportingApiConfiguration;
import com.ravensoftware.reportingclient.exception.ReportingApiException;
import com.ravensoftware.reportingclient.model.LoginRequest;
import com.ravensoftware.reportingclient.model.LoginResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Optional;

/**
 * Created by bilga
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class LoginClient {

    private final RestTemplate restTemplate;
    private final ReportingApiConfiguration reportingApiConfiguration;

    public LoginResponse login() throws ReportingApiException{
        try {
            LoginRequest request = new LoginRequest();
            request.setEmail(reportingApiConfiguration.getEmail());
            request.setPassword(reportingApiConfiguration.getPassword());
            log.info("ReportingApiClient login ok");
            return Optional.of(restTemplate.postForEntity(reportingApiConfiguration.getLoginUrl(), request, LoginResponse.class).getBody()).get();
        } catch (Exception e) {
            log.error("Check login info ", e);
            throw new ReportingApiException("Check login info", HttpStatus.BAD_REQUEST);
        }
    }
}
