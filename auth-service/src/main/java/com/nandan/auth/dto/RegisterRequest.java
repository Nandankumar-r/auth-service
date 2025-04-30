package com.nandan.auth.dto;

import com.nandan.auth.entity.Role;
import lombok.Data;

@Data
public class RegisterRequest {
    private String username;
    private String password;
    private Role role;
}
