package com.ravensoftware.reportingclient.controller;

import com.ravensoftware.reportingclient.control.TransactionListControl;
import com.ravensoftware.reportingclient.model.TransactionListRequest;
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
@RequestMapping(value = "/transaction-list")
public class TransactionListResources {

    private final TransactionListControl transactionListControl;

    @PostMapping
    public ResponseEntity transactionList(@NotNull @RequestBody TransactionListRequest request) {
        return ResponseEntity.ok(transactionListControl.getTransactionList(request));
    }
}
