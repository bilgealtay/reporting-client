package com.ravensoftware.reportingclient.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;

/**
 * Created by bilga
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class LoginRequest implements Serializable {
    @Email
    @NotBlank
    private String email;

    @NotBlank
    private String password;
}
