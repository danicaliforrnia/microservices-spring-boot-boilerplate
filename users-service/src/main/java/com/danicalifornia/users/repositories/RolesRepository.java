package com.danicalifornia.users.repositories;

import com.danicalifornia.users.models.entities.Role;
import org.springframework.data.repository.CrudRepository;

public interface RolesRepository extends CrudRepository<Role, Integer> {
}
