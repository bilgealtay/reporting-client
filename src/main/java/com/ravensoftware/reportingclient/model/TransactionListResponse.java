package com.ravensoftware.reportingclient.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.ravensoftware.reportingclient.model.api.TransactionResponse;
import lombok.Data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by bilga
 */
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class TransactionListResponse implements Serializable {
    private int per_page;
    private int current_page;
    private String next_page_url;
    private String prev_page_url;
    private int from;
    private int to;
    private List<TransactionResponse> data = new ArrayList<>();
    private Warning warning;
}
