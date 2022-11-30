package com.ravensoftware.reportingclient.control;

import com.ravensoftware.reportingclient.exception.ReportingApiException;
import com.ravensoftware.reportingclient.model.ReportRequest;
import com.ravensoftware.reportingclient.model.ReportResponse;
import com.ravensoftware.reportingclient.model.Warning;
import com.ravensoftware.reportingclient.service.ReportClient;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * Created by bilga
 */
@Slf4j
@Component
@RequiredArgsConstructor
public class ReportControl {
    public static final String REPORT_ERROR = "Report error.";
    private final ReportClient reportClient;

    public ReportResponse getReport(ReportRequest reportRequest){
        ReportResponse reportResponse = new ReportResponse();
        try{
            reportResponse.setResponse( reportClient.getTransactionReportResponse(reportRequest));
        } catch (ReportingApiException e){
            reportResponse.setWarning(Warning.builder()
                    .code(e.getErrorCode().value())
                    .description(e.getMessage())
                    .message(REPORT_ERROR)
                    .build());
        }
        return reportResponse;
    }
}
