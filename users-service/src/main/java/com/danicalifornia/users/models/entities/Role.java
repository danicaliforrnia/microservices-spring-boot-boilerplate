package com.danicalifornia.users.models.entities;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "rol_role")
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PUBLIC)
public class Role implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "rol_id")
    private Integer id;

    @Column(name = "rol_name", nullable = false, length = 50)
    private String name;

    @Column(name = "rol_code", nullable = false, length = 20)
    private String code;

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(name = "rtp_role_to_permission",
            joinColumns = @JoinColumn(name = "rol_id"),
            inverseJoinColumns = @JoinColumn(name = "per_id"))
    private List<Permission> permissions = new ArrayList<>();
}
