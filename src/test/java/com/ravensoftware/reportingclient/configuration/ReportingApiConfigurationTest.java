package com.ravensoftware.reportingclient.configuration;

import com.ravensoftware.reportingclient.ReportingClientApplication;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * Created by bilga
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = {ReportingClientApplication.class})
public class ReportingApiConfigurationTest {

    @Autowired
    ReportingApiConfiguration reportingApiConfiguration;

    @Test
    public void shouldCheckReportingApiConfiguration() {
        Assert.assertEquals(reportingApiConfiguration.getEmail(), "demo@financialhouse.io");
        Assert.assertEquals(reportingApiConfiguration.getPassword(), "cjaiU8CV");
        Assert.assertEquals(reportingApiConfiguration.getLoginUrl(), "https://sandbox-reporting.rpdpymnt.com/api/v3/merchant/user/login");
        Assert.assertEquals(reportingApiConfiguration.getReportUrl(), "https://sandbox-reporting.rpdpymnt.com/api/v3/transactions/report");
        Assert.assertEquals(reportingApiConfiguration.getTransactionUrl(), "https://sandbox-reporting.rpdpymnt.com/api/v3/transaction");
        Assert.assertEquals(reportingApiConfiguration.getTransactionListUrl(), "https://sandbox-reporting.rpdpymnt.com/api/v3/transaction/list");
        Assert.assertEquals(reportingApiConfiguration.getClientUrl(), "https://sandbox-reporting.rpdpymnt.com/api/v3/client");
    }
}
