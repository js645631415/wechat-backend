package com.dark.monitor.json;


import lombok.Getter;
import lombok.Setter;

import java.util.LinkedHashMap;

public class Response extends LinkedHashMap<String, Object> {
    /**
     * 成功标示
     */
    private @Getter boolean success;
    /**
     * 错误码
     */
    private @Getter @Setter String errorCode;
    /**
     * 错误信息
     */
    private @Getter @Setter String errorMessage;

    private Response(boolean success) {
        this.success = success;
        this.put("success", success);
    }

    public static Response Success() {
        return new Response(true);
    }

    public static Response Error() {
        return new Response(false);
    }

    public static Response Error(String message) {
        Response result = new Response(false);
        result.setErrorMessage(message);
        result.put("errorMessage", message);
        result.put("message", message);
        return result;
    }

    public static Response Success(String message) {
        Response result = new Response(true);
        result.put("message", message);
        return result;
    }
}
