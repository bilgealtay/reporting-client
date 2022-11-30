package com.ravensoftware.reportingclient.configuration;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * Created by bilga
 */
@Component
@ConfigurationProperties(prefix = "services")
@Data
public class ReportingApiConfiguration {
    private String email;
    private String password;
    private String loginUrl;
    private String reportUrl;
    private String transactionListUrl;
    private String transactionUrl;
    private String clientUrl;
}
