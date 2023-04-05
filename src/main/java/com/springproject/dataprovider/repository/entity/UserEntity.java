package com.springproject.dataprovider.repository.entity;

import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "USER_ACCOUNT")
@Getter
@Setter
public class UserEntity extends BaseEntity {

    @Column(name = "USERNAME", nullable = false, length = 200)
    private String username;

    @Column(name = "PASSWORD", nullable = false, length = 200)
    private String password;
}
