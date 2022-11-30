package com.ravensoftware.reportingclient.controller;

import com.ravensoftware.reportingclient.control.LoginControl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
/**
 * Created by bilga
 */
@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/login")
public class LoginResources {

    private final LoginControl loginControl;

    @GetMapping
    public ResponseEntity login() {
        return ResponseEntity.ok(loginControl.login());
    }
}
