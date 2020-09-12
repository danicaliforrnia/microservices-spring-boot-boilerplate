package com.danicalifornia.users.repositories;

import com.danicalifornia.users.models.entities.User;
import org.springframework.data.repository.CrudRepository;

public interface UsersRepository extends CrudRepository<User, Long> {
    User findByUsername(String username);
}
