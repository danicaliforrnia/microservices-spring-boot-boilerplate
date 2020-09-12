package com.danicalifornia.users.controllers;

import com.danicalifornia.users.models.dtos.RoleDto;
import com.danicalifornia.users.services.IRolesService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("roles")
public class RolesController {

    private final IRolesService rolesService;

    public RolesController(IRolesService rolesService) {
        this.rolesService = rolesService;
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public List<RoleDto> getAll() {
        return rolesService.getRoles();
    }

}
