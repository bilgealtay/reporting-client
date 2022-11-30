package com.ravensoftware.reportingclient.controller;

import com.ravensoftware.reportingclient.control.TransactionControl;
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
@RequestMapping(value = "/transaction")
public class TransactionResources {

    private final TransactionControl transactionControl;

    @PostMapping
    public ResponseEntity transactionList(@NotNull @RequestBody TransactionParam request) {
        return ResponseEntity.ok(transactionControl.getTransaction(request));
    }
}
