package com.alpha.momentum.model;

import com.alpha.momentum.enums.Operations;
import com.alpha.momentum.enums.Status;

public class CommonResponse {
    private Operations operation;
    private Status status;
    private String message;
    private String additionalInfo;

    public CommonResponse() {
    }

    public CommonResponse(Operations operation, Status status, String message, String additionalInfo) {
        this.operation = operation;
        this.status = status;
        this.message = message;
        this.additionalInfo = additionalInfo;
    }

    public Operations getOperation() {
        return operation;
    }

    public void setOperation(Operations operation) {
        this.operation = operation;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getAdditionalInfo() {
        return additionalInfo;
    }

    public void setAdditionalInfo(String additionalInfo) {
        this.additionalInfo = additionalInfo;
    }
}
