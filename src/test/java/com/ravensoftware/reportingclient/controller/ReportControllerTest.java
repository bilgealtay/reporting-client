package com.ravensoftware.reportingclient.controller;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.ravensoftware.reportingclient.control.ReportControl;
import com.ravensoftware.reportingclient.model.*;
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
@WebMvcTest(ReportController.class)
public class ReportControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    ReportControl reportControl;

    ObjectMapper mapper =  new ObjectMapper();

    @Test
    public void shouldReturnBadRequestWhenBodyIsNull() throws Exception {

        mockMvc.perform(post("/report")
                    .content(mapper.writeValueAsString(null))
                    .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isBadRequest())
            .andReturn();
    }

    @Test
    public void shouldReturnWarningWhenBodyIsEmpty() throws Exception {
        // given
        ReportRequest reportRequest = new ReportRequest();

        ReportResponse reportResponse = new ReportResponse();
        reportResponse.setWarning(Warning.builder().code(400).build());

        when( reportControl.getReport(reportRequest))
                .thenReturn(reportResponse);

        // when
        MvcResult result = mockMvc.perform(post("/report")
                        .content(mapper.writeValueAsString(reportRequest))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();
        ReportResponse content = mapper.readValue(result.getResponse().getContentAsString(), ReportResponse.class);

        // then
        Assert.assertNotNull(content.getWarning());
        Assert.assertEquals(content.getWarning().getCode(), 400);
    }

    @Test
    public void shouldReturnContent() throws Exception {
        // gıven
        ReportRequest reportRequest = new ReportRequest();

        ReportResponse reportResponse = new ReportResponse();
        reportResponse.setResponse(new TransactionReportResponse());

        when( reportControl.getReport(reportRequest))
                .thenReturn(reportResponse);

        // when
        MvcResult result = mockMvc.perform(post("/report")
                        .content(mapper.writeValueAsString(reportRequest))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();
        ReportResponse content = mapper.readValue(result.getResponse().getContentAsString(), ReportResponse.class);

        // then
        Assert.assertNull(content.getWarning());
        Assert.assertNotNull(content.getResponse());
    }

}
