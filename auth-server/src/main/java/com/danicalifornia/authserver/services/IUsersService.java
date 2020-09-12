package com.danicalifornia.authserver.services;

import com.danicalifornia.authserver.models.User;

public interface IUsersService {

    User findEmployerUserByUsername(String username);

}
