package com.ravensoftware.reportingclient.controller;

import com.ravensoftware.reportingclient.control.ReportControl;
import com.ravensoftware.reportingclient.model.ReportRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.NotNull;

/**
 * Created by bilga
 */
@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/report")
public class ReportResources {

    private final ReportControl reportControl;

    @PostMapping
    public ResponseEntity report(@NotNull @RequestBody ReportRequest request) {
        return ResponseEntity.ok(reportControl.getReport(request));
    }
}
