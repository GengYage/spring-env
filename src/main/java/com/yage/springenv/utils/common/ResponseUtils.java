package com.yage.springenv.utils.common;

import java.util.HashMap;
import java.util.Map;

import static com.yage.springenv.common.Constant.OK;
import static com.yage.springenv.common.Constant.RESULT;

public class ResponseUtils {

    public static Map<String, String> ok() {
        Map<String, String> response = new HashMap<>(2);
        response.put(RESULT, OK);
        return response;
    }
}
