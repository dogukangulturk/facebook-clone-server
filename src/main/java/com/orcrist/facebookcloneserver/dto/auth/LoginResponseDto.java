package com.orcrist.facebookcloneserver.dto.auth;

import com.orcrist.facebookcloneserver.dto.user.UserDto;

public class LoginResponseDto {

    private UserDto user;
    private String accessToken;

    public LoginResponseDto(UserDto user, String accessToken) {
        this.user = user;
        this.accessToken = accessToken;
    }

    public UserDto getUser() {
        return user;
    }

    public void setUser(UserDto user) {
        this.user = user;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }
}
