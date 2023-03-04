package com.example.users.util;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Map;

public class ResponseHandlerTest {

    @Test
    public void testErrorResponse(){
        Map<String, Object> map = ResponseHandler.errorResponse(404, "Failure");
        Assertions.assertEquals(404, map.get("errorCode"));
    }
}
