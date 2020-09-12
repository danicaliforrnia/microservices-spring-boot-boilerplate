package com.danicalifornia.users.services;

import com.danicalifornia.users.errors.RoleNotFoundException;
import com.danicalifornia.users.errors.UserAlreadyExistsException;
import com.danicalifornia.users.errors.UserNotFoundException;
import com.danicalifornia.users.models.dtos.UserDto;
import com.danicalifornia.users.models.dtos.UserPasswordDto;
import com.danicalifornia.users.models.entities.User;
import com.danicalifornia.users.repositories.RolesRepository;
import com.danicalifornia.users.repositories.UsersRepository;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UsersService implements IUsersService {

    private final UsersRepository usersRepository;

    private final RolesRepository rolesRepository;

    private final ModelMapper modelMapper;

    private final BCryptPasswordEncoder passwordEncode;

    public UsersService(UsersRepository usersRepository, RolesRepository rolesRepository, BCryptPasswordEncoder passwordEncode,
                        ModelMapper modelMapper) {
        this.usersRepository = usersRepository;
        this.rolesRepository = rolesRepository;
        this.passwordEncode = passwordEncode;
        this.modelMapper = modelMapper;
    }

    @Transactional(readOnly = true)
    public List<UserDto> getAll() {
        return ((List<User>) usersRepository.findAll()).stream()
                .map(user -> modelMapper.map(user, UserDto.class)).collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public UserPasswordDto getByUsername(String username) {
        return modelMapper.map(usersRepository.findByUsername(username), UserPasswordDto.class);
    }

    @Transactional
    public UserDto create(UserDto userDto) {
        if (usersRepository.findByUsername(userDto.getUsername()) != null) {
            throw new UserAlreadyExistsException();
        }

        User user = modelMapper.map(userDto, User.class);

        user.setPassword(passwordEncode.encode("12345"));

        rolesRepository.findById(user.getRole().getId()).ifPresentOrElse(user::setRole,
                () -> {
                    throw new RoleNotFoundException();
                });

        return modelMapper.map(usersRepository.save(user), UserDto.class);
    }

    @Transactional
    public UserDto update(Long id, UserDto userDto) {
        User user = usersRepository.findById(id).orElseThrow(() -> {
            throw new UserNotFoundException();
        });

        modelMapper.map(userDto, user);

        rolesRepository.findById(userDto.getRole().getId()).ifPresentOrElse(user::setRole,
                () -> {
                    throw new RoleNotFoundException();
                });

        return modelMapper.map(usersRepository.save(user), UserDto.class);
    }

    @Transactional
    public void delete(Long id) {
        usersRepository.findById(id).ifPresentOrElse(usersRepository::delete, () -> {
            throw new UserNotFoundException();
        });
    }
}
