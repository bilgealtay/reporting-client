package com.ravensoftware.reportingclient.model.api;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.ravensoftware.reportingclient.model.enums.Status;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * Created by bilga
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Merchant implements Serializable {

    private Long id;
    private String name;
    private String referenceNo;
    private Status status;
    private String operation;
    private String message;
    @JsonProperty("created_at")
    private String createdAt;
    private String transactionId;
    private Boolean allowPartialCapture;
    private Boolean allowPartialRefund;
    private String  type;
    private BigDecimal amount;
    private String  chainId;
    private String  code;
    private BigDecimal  convertedAmount;
    private String  convertedCurrency;
    private String  currency;
    private String  ipnType;
    private String  paymentType;
    private String  token;

}
