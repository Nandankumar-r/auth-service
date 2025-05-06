package com.nandan.auth.dto;

import com.nandan.auth.entity.Role;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RegisterRequest {
    @NotBlank(message = "UserName must not be blank")
    @Size(min = 3, max = 20, message = "Username must be between 5 and 20 characters")
    private String username;

    @NotBlank(message = "Password must not be blank")
    @Pattern(
            regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[!@#$%^&*~`()])[A-Za-z\\d!@#$%^&*~`()]{8,}$",
            message = "Password must be at least 8 characters and include upper, lower, number, and special character"
    )
    private String password;

    @NotBlank(message = "Role must be specified")
    private String role;

    @Override
    public String toString() {
        return "RegisterRequest(username=" + username + ", role=" + role + ")";
    }
}
