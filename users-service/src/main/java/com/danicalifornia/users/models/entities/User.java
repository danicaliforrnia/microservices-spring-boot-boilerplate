package com.danicalifornia.users.models.entities;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@Table(name = "usr_user")
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PUBLIC)
public class User implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "usr_id")
    private Long id;

    @Column(name = "usr_username", nullable = false, length = 50)
    private String username;

    @Column(name = "usr_full_name", nullable = false, length = 100)
    private String fullName;

    @Column(name = "usr_password", nullable = false, length = 60)
    private String password;

    @Column(name = "usr_is_active", nullable = false)
    private boolean active;

    @Column(name = "usr_password_active", nullable = false)
    private boolean passwordActive;

    @Column(name = "usr_account_locked", nullable = false)
    private boolean accountLocked;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "rol_id", referencedColumnName = "rol_id", nullable = false)
    private Role role;

    @Column(name = "usr_is_signed_in", nullable = false)
    private boolean signedIn = false;

    @Column(name = "usr_sign_in_at")
    private LocalDateTime signInAt;
}
