package com.danicalifornia.authserver.models;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.sun.istack.NotNull;
import lombok.*;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
@NoArgsConstructor(access = AccessLevel.PUBLIC)
@AllArgsConstructor(access = AccessLevel.PUBLIC)
public class User {
    @NotNull
    private Long id;
    @NotNull
    private String username;
    @NotNull
    private String fullName;
    @NotNull
    private boolean active;
    @NotNull
    private boolean passwordActive;
    @NotNull
    private boolean accountLocked;
    @NotNull
    private String password;
    @NotNull
    private Role role;
}
