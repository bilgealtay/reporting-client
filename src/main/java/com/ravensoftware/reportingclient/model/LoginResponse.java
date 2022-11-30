package com.ravensoftware.reportingclient.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * Created by bilga
 */
@Data
@NoArgsConstructor
public class LoginResponse implements Serializable {
    private String token;
    private String status;
    private Warning warning;
}
