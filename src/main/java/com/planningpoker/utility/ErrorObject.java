package com.planningpoker.utility;

import com.mongodb.lang.Nullable;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ErrorObject {
    private String message;
    @Nullable
    private String type;

    public ErrorObject(String message) {
        this.message = message;
    }
}
