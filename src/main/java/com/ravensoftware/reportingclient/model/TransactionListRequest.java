package com.ravensoftware.reportingclient.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDate;

/**
 * Created by bilga
 */
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class TransactionListRequest implements Serializable {

    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate fromDate;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate toDate;
    private String status;
    private String operation;
    private Integer merchantId;
    private Integer acquirerId;
    private String paymentMethod;
    private String errorCode;
    private String filterField;
    private String filterValue;
    private Integer page;
}
