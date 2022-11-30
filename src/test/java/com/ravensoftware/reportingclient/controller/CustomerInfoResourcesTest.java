package com.ravensoftware.reportingclient.controller;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.ravensoftware.reportingclient.control.CustomerInfoControl;
import com.ravensoftware.reportingclient.model.CustomerInfoResponse;
import com.ravensoftware.reportingclient.model.TransactionParam;
import com.ravensoftware.reportingclient.model.Warning;
import com.ravensoftware.reportingclient.model.api.CustomerInfo;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Created by bilga
 */
@RunWith(SpringRunner.class)
@WebMvcTest(CustomerInfoResources.class)
public class CustomerInfoResourcesTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    CustomerInfoControl customerInfoControl;

    ObjectMapper mapper =  new ObjectMapper();

    @Test
    public void shouldReturnBadRequestWhenBodyNull() throws Exception {

        mockMvc.perform(post("/client")
                    .content(mapper.writeValueAsString(null))
                    .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isBadRequest())
            .andReturn();
    }

    @Test
    public void shouldReturnWarningWhenBodyIsEmpty() throws Exception {

        TransactionParam transactionParam = new TransactionParam();
        CustomerInfoResponse customerInfoResponse = CustomerInfoResponse.builder()
                .warning(Warning.builder().code(400).build())
                .build();

        when( customerInfoControl.getClient(transactionParam))
                .thenReturn(customerInfoResponse);

        MvcResult result = mockMvc.perform(post("/client")
                        .content(mapper.writeValueAsString(transactionParam))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();
        CustomerInfoResponse content = mapper.readValue(result.getResponse().getContentAsString(), CustomerInfoResponse.class);

        Assert.assertNotNull(content.getWarning());
        Assert.assertEquals(content.getWarning().getCode(), 400);
    }

    @Test
    public void shouldReturnContent() throws Exception {

        TransactionParam transactionParam = new TransactionParam();
        CustomerInfoResponse customerInfoResponse = CustomerInfoResponse.builder()
                .customerInfo(new CustomerInfo())
                .build();

        when( customerInfoControl.getClient(transactionParam))
                .thenReturn(customerInfoResponse);

        MvcResult result = mockMvc.perform(post("/client")
                        .content(mapper.writeValueAsString(transactionParam))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();
        CustomerInfoResponse content = mapper.readValue(result.getResponse().getContentAsString(), CustomerInfoResponse.class);

        Assert.assertNull(content.getWarning());
        Assert.assertNotNull(content.getCustomerInfo());
    }

}
