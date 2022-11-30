package com.ravensoftware.reportingclient.control;

import com.ravensoftware.reportingclient.exception.ReportingApiException;
import com.ravensoftware.reportingclient.model.TransactionParam;
import com.ravensoftware.reportingclient.model.TransactionResponse;
import com.ravensoftware.reportingclient.model.Warning;
import com.ravensoftware.reportingclient.service.TransactionClient;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * Created by bilga
 */
@Slf4j
@Component
@RequiredArgsConstructor
public class TransactionControl {
    private final TransactionClient transactionClient;

    public TransactionResponse getTransaction(TransactionParam request){
        TransactionResponse response = new TransactionResponse();
        try{
            response.setTransaction(transactionClient.getTransactionResponse(request));
        } catch (ReportingApiException e){
            response.setWarning(Warning.builder()
                    .code(e.getErrorCode().value())
                    .description(e.getMessage())
                    .message("Transaction List error.")
                    .build());
        }
        return response;
    }
}
