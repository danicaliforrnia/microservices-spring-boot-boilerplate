package com.danicalifornia.users.models.entities;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "per_permission")
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PUBLIC)
public class Permission implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "per_id")
    private Integer id;

    @Column(name = "per_code", nullable = false, length = 10)
    private String code;

    @Column(name = "per_description", nullable = false, length = 100)
    private String description;
}
