package com.orcrist.facebookcloneserver.util;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.Date;

@ResponseStatus(HttpStatus.OK)
public class ApiResponse {

    private Date timestamp;
    private final String message;

    public ApiResponse(Date timestamp, String message) {
        this.timestamp = timestamp;
        this.message = message;
    }

    public ApiResponse(String message) {
        this.message = message;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }

    public String getMessage() {
        return message;
    }
}
