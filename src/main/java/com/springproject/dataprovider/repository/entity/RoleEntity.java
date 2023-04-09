package com.springproject.dataprovider.repository.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.springproject.core.type.YesNoEnum;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "Role")
@Getter
@Setter
public class RoleEntity extends AuditEntity{
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID")
	private Long id;

	@Column(name = "NAME", nullable = false, length = 200)
	private String name;

	@Enumerated(EnumType.STRING)
	@Column(name = "IS_ACTIVE", nullable = false, length = 200)
	private YesNoEnum isActive;

	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "fk_role_id", referencedColumnName = "id")
	private List<UserRoleEntity> userRole;
}
