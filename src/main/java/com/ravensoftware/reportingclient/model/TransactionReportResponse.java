package com.ravensoftware.reportingclient.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by bilga
 */
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class TransactionReportResponse implements Serializable {

    private String status;
    private List<TransactionReport> response = new ArrayList<>();

}
