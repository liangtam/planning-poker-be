package com.planningpoker.exceptions;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GeneralException extends RuntimeException {
    protected ResponseCode code;
    protected String message;
}
