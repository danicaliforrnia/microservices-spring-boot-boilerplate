package com.danicalifornia.users.controllers;

import com.danicalifornia.users.models.dtos.UserDto;
import com.danicalifornia.users.models.dtos.UserPasswordDto;
import com.danicalifornia.users.services.IUsersService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("users")
public class UsersController {

    private final IUsersService usersService;

    public UsersController(IUsersService usersService) {
        this.usersService = usersService;
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public List<UserDto> getAll() {
        return usersService.getAll();
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE, params = "username")
    @ResponseStatus(HttpStatus.OK)
    public UserPasswordDto getByUsername(@RequestParam String username) {
        return usersService.getByUsername(username);
    }

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public UserDto create(@RequestBody UserDto user) {
        return usersService.create(user);
    }

    @PutMapping(produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE,
            value = "{id}")
    @ResponseStatus(HttpStatus.OK)
    public UserDto create(@RequestBody UserDto user, @PathVariable Long id) {
        return usersService.update(id, user);
    }

    @DeleteMapping(value = "{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void create(@PathVariable Long id) {
        usersService.delete(id);
    }
}
