package com.ravensoftware.reportingclient.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.io.Serializable;

/**
 * Created by bilga
 */
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class TransactionReport implements Serializable {

    private Integer count;
    private Integer total;
    private String currency;

}
