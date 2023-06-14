package com.orcrist.facebookcloneserver.util;

import java.util.Date;
import java.util.Map;

public class ValidationErrors {

    private final Date timestamp;
    private final String path;
    private final Map<String, String> errors;

    public ValidationErrors(Date timestamp, String path, Map<String, String> errors) {
        this.timestamp = timestamp;
        this.path = path;
        this.errors = errors;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public String getPath() {
        return path;
    }

    public Map<String, String> getErrors() {
        return errors;
    }
}
