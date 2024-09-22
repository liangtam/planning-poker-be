package com.planningpoker.exceptions;


public enum ResponseCode {

    RESOURCE_NOT_FOUND      (404, "Resource not found."),
    RESOURCE_INVALID_PARAM  (401, "Invalid parameters.");

    private int code;
    private String message;


    ResponseCode(int code, String message) {
        this.code = code;
        this.message = message;
    }

}
