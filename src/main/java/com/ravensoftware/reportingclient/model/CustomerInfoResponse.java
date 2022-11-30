package com.ravensoftware.reportingclient.model;

import com.ravensoftware.reportingclient.model.api.CustomerInfo;
import lombok.Builder;
import lombok.Value;
import lombok.extern.jackson.Jacksonized;

/**
 * Created by bilga
 */
@Builder
@Jacksonized
@Value
public class CustomerInfoResponse {
    private CustomerInfo customerInfo;
    private Warning warning;
}
