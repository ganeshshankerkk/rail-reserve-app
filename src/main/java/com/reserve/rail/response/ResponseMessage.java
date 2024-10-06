package com.reserve.rail.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.reserve.rail.constants.RailReserveConstants;

public class ResponseMessage {
	
	private boolean success = true;
	
    @JsonProperty("success-message")
    private String successMessage = RailReserveConstants.BLANK;

    @JsonProperty("error-message")
    private String errorMessage = RailReserveConstants.BLANK;

    private Object data = RailReserveConstants.BLANK;
    
    public boolean isSuccess() {
        return success;
    }

    public String getSuccessMessage() {
        return successMessage;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public void setSuccessMessage(String successMessage) {
        this.successMessage = successMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public Object getData() {
        return this.data;
    }

    @Override
    public String toString() {
        return "ResponseMessage{" + "success="
                + success + ", successMessage='"
                + successMessage + '\'' + ", errorMessage='"
                + errorMessage + '\'' + ", data="
                + data + '}';
    }

}
