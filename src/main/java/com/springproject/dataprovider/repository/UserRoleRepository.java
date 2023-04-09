package com.springproject.dataprovider.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.springproject.dataprovider.repository.entity.UserRoleEntity;

public interface UserRoleRepository extends JpaRepository<UserRoleEntity, Long> {

}
