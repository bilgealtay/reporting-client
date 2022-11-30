package com.ravensoftware.reportingclient.control;

import com.ravensoftware.reportingclient.exception.ReportingApiException;
import com.ravensoftware.reportingclient.model.TransactionListRequest;
import com.ravensoftware.reportingclient.model.TransactionListResponse;
import com.ravensoftware.reportingclient.model.Warning;
import com.ravensoftware.reportingclient.service.TransactionListClient;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * Created by bilga
 */
@Slf4j
@Component
@RequiredArgsConstructor
public class TransactionListControl {
    private final TransactionListClient transactionListClient;

    public TransactionListResponse getTransactionList(TransactionListRequest request){
        TransactionListResponse response = new TransactionListResponse();
        try{
            response = transactionListClient.getTransactionListResponse(request);
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
