package com.danicalifornia.authserver.services;

import com.danicalifornia.authserver.clients.UsersFeignClient;
import com.danicalifornia.authserver.models.Role;
import com.danicalifornia.authserver.models.User;
import feign.FeignException;
import org.springframework.cache.annotation.CachePut;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service("usersService")
public class UsersService implements IUsersService, UserDetailsService {

    private final UsersFeignClient usersFeignClient;

    public UsersService(UsersFeignClient usersFeignClient) {
        this.usersFeignClient = usersFeignClient;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        try {
            User user = usersFeignClient.findByUsername(username);

            return this.loadUser(user.getUsername(), user.getRole(), user.getPassword(), user.isActive(),
                    user.isPasswordActive(), user.isAccountLocked());

        } catch (FeignException e) {
            throw new UsernameNotFoundException("Username not found");
        }
    }

    @Override
    @CachePut(value = "user", key = "#username")
    public User findEmployerUserByUsername(String username) {
        return usersFeignClient.findByUsername(username);
    }

    private UserDetails loadUser(String username, Role role, String password, boolean isActive, boolean isPasswordActive,
                                 boolean isAccountLocked) {
        List<GrantedAuthority> authorities = new ArrayList<>();

        if (role != null) {
            authorities = role.getPermissions().stream()
                    .map(permission -> new SimpleGrantedAuthority(permission.getCode()))
                    .collect(Collectors.toList());

        }

        if (isAccountLocked) {
            throw new InternalAuthenticationServiceException("Cuenta bloqueada o suspendida");
        }

        if (!isPasswordActive) {
            throw new InternalAuthenticationServiceException("No puede iniciar sesión");
        }

        if (!isActive) {
            throw new InternalAuthenticationServiceException("Usuario no encontrado");
        }

        return new org.springframework.security.core.userdetails.User(username, password, true,
                true, true, true, authorities);
    }

}
