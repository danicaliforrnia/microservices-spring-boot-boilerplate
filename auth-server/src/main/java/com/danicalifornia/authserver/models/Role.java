package com.danicalifornia.authserver.models;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.sun.istack.NotNull;
import lombok.*;

import java.util.List;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
@NoArgsConstructor(access = AccessLevel.PUBLIC)
@AllArgsConstructor(access = AccessLevel.PUBLIC)
public class Role {
    @NotNull
    private Integer id;
    @NotNull
    private String name;
    @NotNull
    private String code;
    @NotNull
    private List<Permission> permissions;
}
