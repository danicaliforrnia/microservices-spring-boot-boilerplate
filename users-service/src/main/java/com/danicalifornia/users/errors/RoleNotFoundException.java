package com.danicalifornia.users.errors;

import me.alidg.errors.annotation.ExceptionMapping;
import org.springframework.http.HttpStatus;

@ExceptionMapping(statusCode = HttpStatus.NOT_FOUND, errorCode = "roles.not_found")
public class RoleNotFoundException extends RuntimeException {
}
