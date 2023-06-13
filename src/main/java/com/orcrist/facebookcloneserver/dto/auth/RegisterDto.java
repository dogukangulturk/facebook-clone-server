package com.orcrist.facebookcloneserver.dto.auth;

import com.orcrist.facebookcloneserver.util.GenderEnum;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import org.hibernate.validator.constraints.Length;

public class RegisterDto {

    @NotEmpty(message = "Name is required!")
    private String name;

    @NotEmpty(message = "Lastname is required!")
    private String lastname;

    @NotEmpty(message = "Email is required!")
    @Pattern(regexp = "[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,4}", message = "Please write correct email!")
    private String email;

    @NotEmpty(message = "Password is required!")
    @Length(min = 6, message = "Minimum 6 characters")
    @Pattern(regexp = "^(?=.*?[A-Z])(?=.*?[a-z])(?=.*?[0-9]).{6,}$",
            message = "Minimum 6 characters, at least one upper case, one lower case, one number!")
    private String password;

    @Enumerated(EnumType.STRING)
    private GenderEnum gender;
    private String birthday;

    public String getName() {
        return name;
    }

    public String getLastname() {
        return lastname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public GenderEnum getGender() {
        return gender;
    }

    public void setGender(GenderEnum gender) {
        this.gender = gender;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public void setName(String name) {
        char firstChar = Character.toUpperCase(name.charAt(0));
        this.name = (firstChar + name.substring(1)).trim();
    }

    public void setLastname(String lastname) {
        char firstChar = Character.toUpperCase(lastname.charAt(0));
        this.lastname = (firstChar + lastname .substring(1)).trim();
    }
}
