package com.ravensoftware.reportingclient.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.io.Serializable;

/**
 * Created by bilga
 */
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class TransactionResponse implements Serializable {

    private com.ravensoftware.reportingclient.model.api.TransactionResponse transaction;
    private Warning warning;
}
