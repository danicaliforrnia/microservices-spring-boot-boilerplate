package com.danicalifornia.users.services;

import com.danicalifornia.users.models.dtos.UserDto;
import com.danicalifornia.users.models.dtos.UserPasswordDto;

import java.util.List;

public interface IUsersService {
    List<UserDto> getAll();

    UserPasswordDto getByUsername(String username);

    UserDto create(UserDto user);

    UserDto update(Long id, UserDto user);

    void delete(Long id);
}
