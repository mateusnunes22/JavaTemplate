package com.springproject.dataprovider.repository.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
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
@Table(name = "USER_ACCOUNT")
@Getter
@Setter
public class UserEntity extends AuditEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID")
	private Long id;

	@Enumerated(EnumType.STRING)
	@Column(name = "IS_ACTIVE", nullable = false, length = 200)
	private YesNoEnum isActive;

	@Column(name = "USERNAME", nullable = false, length = 200)
	private String username;

	@Column(name = "PASSWORD", nullable = false, length = 200)
	private String password;
	
	@Column(name = "LAST_USED_TOKEN", nullable = true, length = 200)
	private String lastUsedToken;

	@OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinColumn(name = "fk_user_id", referencedColumnName = "id")
	private List<UserRoleEntity> userRoles = new ArrayList<>();

}
