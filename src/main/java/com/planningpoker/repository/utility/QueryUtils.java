package com.planningpoker.repository.utility;

import com.querydsl.core.types.Path;

public class QueryUtils {

    /**
     * @param path QueryDsl path
     * @return the property string of the path
     */
    public static String parseFieldName(Path path) {
        return path.getMetadata().getName();
    }
}
