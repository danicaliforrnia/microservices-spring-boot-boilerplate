package com.danicalifornia.users.services;

import com.danicalifornia.users.models.dtos.RoleDto;
import com.danicalifornia.users.models.entities.Role;
import com.danicalifornia.users.repositories.RolesRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class RolesService implements IRolesService {

    private final RolesRepository rolesRepository;

    private final ModelMapper modelMapper;

    public RolesService(RolesRepository rolesRepository, ModelMapper modelMapper) {
        this.rolesRepository = rolesRepository;
        this.modelMapper = modelMapper;
    }

    @Transactional(readOnly = true)
    public List<RoleDto> getRoles() {
        return ((List<Role>) rolesRepository.findAll()).stream()
                .map(role -> modelMapper.map(role, RoleDto.class)).collect(Collectors.toList());
    }
}
