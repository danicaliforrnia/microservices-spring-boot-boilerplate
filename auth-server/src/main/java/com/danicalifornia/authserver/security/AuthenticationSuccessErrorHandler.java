package com.danicalifornia.authserver.security;

import com.danicalifornia.authserver.services.IUsersService;
import org.springframework.security.authentication.AuthenticationEventPublisher;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

@Component("authenticationSuccessErrorHandler")
public class AuthenticationSuccessErrorHandler implements AuthenticationEventPublisher {

    private final IUsersService usersService;

    public AuthenticationSuccessErrorHandler(IUsersService usersService) {
        this.usersService = usersService;
    }

    @Override
    public void publishAuthenticationSuccess(Authentication authentication) {

    }

    @Override
    public void publishAuthenticationFailure(AuthenticationException exception, Authentication authentication) {

    }

}
