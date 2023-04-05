package com.springproject.dataprovider.repository.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "person")
@Getter
@Setter
public class PersonEntity extends BaseEntity {

	@Column(name = "EMAIL", nullable = false, length = 200)
	private String email;

}
