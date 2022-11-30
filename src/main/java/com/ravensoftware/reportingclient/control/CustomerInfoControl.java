package com.ravensoftware.reportingclient.control;

import com.ravensoftware.reportingclient.exception.ReportingApiException;
import com.ravensoftware.reportingclient.model.CustomerInfoResponse;
import com.ravensoftware.reportingclient.model.TransactionParam;
import com.ravensoftware.reportingclient.model.Warning;
import com.ravensoftware.reportingclient.service.CustomerInfoClient;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * Created by bilga
 */
@Slf4j
@Component
@RequiredArgsConstructor
public class CustomerInfoControl {
    private final CustomerInfoClient customerInfoClient;

    public CustomerInfoResponse getClient(TransactionParam request){
        try{
            return CustomerInfoResponse.builder()
                    .customerInfo(customerInfoClient.getCustomerInfo(request).getCustomerInfo())
                    .build();
        } catch (ReportingApiException e){
            return CustomerInfoResponse.builder()
                    .warning(Warning.builder()
                            .code(e.getErrorCode().value())
                            .description(e.getMessage())
                            .message("Client service error.")
                            .build())
                    .build();
        }
    }
}
