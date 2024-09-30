package com.planningpoker.exceptions;

public class InvalidParamException extends GeneralException{

    public InvalidParamException(ResponseCode resourceInvalidParam, String message) {
        super(resourceInvalidParam, message);
    }
}
