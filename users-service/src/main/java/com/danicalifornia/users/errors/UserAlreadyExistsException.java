package com.danicalifornia.users.errors;

import me.alidg.errors.annotation.ExceptionMapping;
import org.springframework.http.HttpStatus;

@ExceptionMapping(statusCode = HttpStatus.FORBIDDEN, errorCode = "users.user_exists")
public class UserAlreadyExistsException extends RuntimeException {
}
