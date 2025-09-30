package com.alirizakaygusuz.exception_poc.user.model;

import com.alirizakaygusuz.exception_poc.common.audit.AuditBase;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Entity
@Table(name = "users")
@NoArgsConstructor
@SuperBuilder
@Getter
@Setter
public class User extends AuditBase {

	@Column(name = "username", nullable = false, unique = true, length = 50)
	private String username;

	@Column(name = "email", nullable = false, unique = true, length = 120)
	private String email;

	@Column(name = "password", nullable = false)
	private String password;

	@Column(name = "first_name", nullable = false)
	private String firstName;

	@Column(name = "last_name", nullable = false)
	private String lastName;
	

}
