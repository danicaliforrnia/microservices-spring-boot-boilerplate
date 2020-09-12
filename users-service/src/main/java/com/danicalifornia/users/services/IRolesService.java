package com.danicalifornia.users.services;

import com.danicalifornia.users.models.dtos.RoleDto;

import java.util.List;

public interface IRolesService {
    List<RoleDto> getRoles();
}
