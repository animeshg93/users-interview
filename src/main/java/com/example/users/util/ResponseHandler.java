package com.example.users.util;

import com.example.users.model.User;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ResponseHandler {

    public static Map<String, Object> errorResponse(int errorCode, String errorDesc){
        Map<String, Object> map = new HashMap<>();
        map.put("errorCode", errorCode);
        map.put("errorDesc",errorDesc);
        return map;
    }

    public static Map<String, Object> successResponse(String message){
        Map<String, Object> map = new HashMap<>();
        map.put("message", message);
        return map;
    }

    public static Map<String, Object> getAllResponse(List<User> list){
        Map<String, Object> map = new HashMap<>();
        map.put("users", list);
        return map;
    }
}
