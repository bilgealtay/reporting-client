package com.ravensoftware.reportingclient.model;

import lombok.Builder;
import lombok.Value;
import lombok.extern.jackson.Jacksonized;

import java.io.Serializable;

/**
 * Created by bilga
 */
@Builder
@Value
@Jacksonized
public class Warning implements Serializable {
    private int code;
    private String description;
    private String message;
}
