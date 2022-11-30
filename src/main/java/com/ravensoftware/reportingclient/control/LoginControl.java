package com.ravensoftware.reportingclient.control;

import com.ravensoftware.reportingclient.exception.ReportingApiException;
import com.ravensoftware.reportingclient.model.LoginResponse;
import com.ravensoftware.reportingclient.model.Warning;
import com.ravensoftware.reportingclient.service.LoginClient;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * Created by bilga
 */
@Slf4j
@Component
@RequiredArgsConstructor
public class LoginControl {
    public static final String LOGIN_IS_NOT_SUCCESSFUL = "Login is not successful.";
    private final LoginClient loginClient;

    public LoginResponse login(){
        LoginResponse loginResponse = new LoginResponse();
        try{
            loginResponse = loginClient.login();
        } catch (ReportingApiException e){
            loginResponse.setWarning(Warning.builder()
                    .code(e.getErrorCode().value())
                    .description(e.getMessage())
                    .message(LOGIN_IS_NOT_SUCCESSFUL)
                    .build());
        }
        return loginResponse;
    }
}
