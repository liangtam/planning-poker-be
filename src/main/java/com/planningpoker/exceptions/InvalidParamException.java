package com.planningpoker.exceptions;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
public class InvalidParamException extends GeneralException{

    public InvalidParamException(ResponseCode resourceInvalidParam, String message) {
        super(resourceInvalidParam, message);
    }
}
