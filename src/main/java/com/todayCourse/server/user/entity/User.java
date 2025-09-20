package com.todayCourse.server.user.entity;

import com.todayCourse.server.audit.Auditable;
import com.todayCourse.server.constant.type.ActiveStatus;
import com.todayCourse.server.constant.type.LoginType;
import com.todayCourse.server.constant.type.Role;
import jakarta.persistence.*;
import lombok.*;

import java.util.Set;

@Getter
@Setter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "Users")
public class User extends Auditable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;

    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    private String password;

    @Column
    private String loginId;

    @Column
    private String nickname;

    @Column
    @Enumerated(EnumType.STRING)
    private LoginType loginType;

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "user_roles", joinColumns = @JoinColumn(name = "user_id"))
    @Enumerated(EnumType.STRING)
    @Column(name = "role")
    private Set<Role> roles;

    @Column
    @Enumerated(EnumType.STRING)
    private ActiveStatus activeStatus;
}
