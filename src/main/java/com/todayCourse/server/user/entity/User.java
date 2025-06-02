package com.todayCourse.server.user.entity;

import com.todayCourse.server.audit.Auditable;
import com.todayCourse.server.constant.ActiveStatus;
import com.todayCourse.server.constant.LoginType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity
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
    private String userName;

    @Column
    private String nickname;

    @Column
    private String phone;

    @Column
    @Enumerated(EnumType.STRING)
    private LoginType loginType;

    @Column
    private String role;

    @Column
    @Enumerated(EnumType.STRING)
    private ActiveStatus activeStatus;

    @Column
    private String regUserId;

    @Column
    private String mdfcUserId;
}
