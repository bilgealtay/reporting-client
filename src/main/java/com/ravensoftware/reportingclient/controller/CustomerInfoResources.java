package com.ravensoftware.reportingclient.controller;

import com.ravensoftware.reportingclient.control.CustomerInfoControl;
import com.ravensoftware.reportingclient.model.TransactionParam;
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
@RequestMapping(value = "/client")
public class CustomerInfoResources {

    private final CustomerInfoControl customerInfoControl;

    @PostMapping
    public ResponseEntity transactionList(@NotNull @RequestBody TransactionParam request) {
        return ResponseEntity.ok(customerInfoControl.getClient(request));
    }
}
