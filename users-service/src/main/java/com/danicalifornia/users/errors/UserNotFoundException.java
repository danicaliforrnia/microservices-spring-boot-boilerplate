package com.danicalifornia.users.errors;

import me.alidg.errors.annotation.ExceptionMapping;
import org.springframework.http.HttpStatus;

@ExceptionMapping(statusCode = HttpStatus.NOT_FOUND, errorCode = "users.not_found")
public class UserNotFoundException extends RuntimeException {
}
