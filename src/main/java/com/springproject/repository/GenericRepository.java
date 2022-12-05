package com.springproject.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.springproject.entity.BaseEntity;

public interface GenericRepository<T extends BaseEntity> extends JpaRepository<T, Long> {
}
