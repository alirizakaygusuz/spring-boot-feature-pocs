package com.alirizakaygusuz.jwt_poc.auth.authuser.model;

import java.util.Collection;
import java.util.Collections;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.alirizakaygusuz.jwt_poc.common.audit.AuditBase;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Entity
@Table(name = "auth_users")
@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
public class AuthUser extends AuditBase  implements UserDetails{

	@Column(nullable = false , unique = true)
	private String username;
	
	@Column(nullable = false , unique = true)
	private String email;
	
	@Column(nullable = false )
	private String firstName;
	
	@Column(nullable = false )
	private String lastName;
	
	@Column(nullable = false )	
	private String password;

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		
		return Collections.emptyList();
	}
	
}
