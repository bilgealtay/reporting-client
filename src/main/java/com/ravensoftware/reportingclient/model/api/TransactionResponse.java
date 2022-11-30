package com.ravensoftware.reportingclient.model.api;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * Created by bilga
 */
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class TransactionResponse implements Serializable {

    @JsonProperty("created_at")
    private String createdAt;
    @JsonProperty("updated_at")
    private String updatedAt;
    private Boolean refundable;
    private CustomerInfo customerInfo;
    private FX fx;
    private Transaction transaction;
    private Merchant merchant;
    private Acquirer acquirer;
    private Ipn ipn;
}
