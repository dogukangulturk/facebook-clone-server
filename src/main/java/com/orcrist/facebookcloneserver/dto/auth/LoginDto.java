package com.orcrist.facebookcloneserver.dto.auth;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import org.hibernate.validator.constraints.Length;

public class LoginDto {
    @NotEmpty(message = "Email is required!")
    @Pattern(regexp = "[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,4}", message = "Please write correct email!")
    private String email;

    @NotEmpty(message = "Password is required!")
    @Length(min = 6, message = "Minimum 6 characters")
    @Pattern(regexp = "^(?=.*?[A-Z])(?=.*?[a-z])(?=.*?[0-9]).{6,}$",
            message = "Minimum 6 characters, at least one upper case, one lower case, one number!")
    private String password;

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setEmail(String email) {
        this.email = email.toLowerCase();
    }
}
