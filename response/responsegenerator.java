package com.example.Student.response;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class responsegenerator {

    public static ResponseEntity<Response> successResponse(String message, Object data) {
        Response response = new Response();
        response.setTimestamp(LocalDateTime.now());
        response.setStatus("Success");
        response.setMessage(message);
        response.setData(data);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    public static ResponseEntity<Response> errorResponse(String message) {
        Response response = new Response();
        response.setTimestamp(LocalDateTime.now());
        response.setStatus("Success");
        response.setMessage(message);
        response.setData(null);
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }
}
