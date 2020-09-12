package com.danicalifornia.users.models.dtos;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.sun.istack.NotNull;
import lombok.*;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
@NoArgsConstructor(access = AccessLevel.PUBLIC)
@AllArgsConstructor(access = AccessLevel.PUBLIC)
public class UserDto {
    private Long id;
    @NotNull
    private String username;
    @NotNull
    private String fullName;
    @NotNull
    private boolean active;
    @NotNull
    private RoleDto role;
}
