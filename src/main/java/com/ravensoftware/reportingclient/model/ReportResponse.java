package com.ravensoftware.reportingclient.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.io.Serializable;

/**
 * Created by bilga
 */
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class ReportResponse implements Serializable {
    private TransactionReportResponse response;
    private Warning warning;
}
