package com.danicalifornia.users.models.dtos;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.sun.istack.NotNull;
import lombok.*;

import java.util.List;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
@NoArgsConstructor(access = AccessLevel.PUBLIC)
@AllArgsConstructor(access = AccessLevel.PUBLIC)
public class RoleDto {
    @NotNull
    private Integer id;
    private String name;
    private String code;
    private List<PermissionDto> permissions;
}
