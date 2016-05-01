package edu.miamioh.cse.finalproject.core;

/**
 * Created by mac on 5/1/16.
 */
public class TranslateMessage {
    String message;
    Boolean isSuccess;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Boolean getSuccess() {
        return isSuccess;
    }

    public void setSuccess(Boolean success) {
        isSuccess = success;
    }
}
