package com.springproject.dataprovider.repository.entity;


import java.time.LocalDateTime;

import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AuditEntity {
	@CreatedBy
	private Long createdBy;

	@CreatedDate
	private LocalDateTime createdDate;

	@LastModifiedBy
	private Long lastModifiedBy;

	@LastModifiedDate
	private LocalDateTime lastModifiedDate;
}
