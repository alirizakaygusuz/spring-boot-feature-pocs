package com.alirizakaygusuz.jwt_permission_based_poc.authentication.authuser.model;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.SQLRestriction;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.alirizakaygusuz.jwt_permission_based_poc.authorization.relation.roleuser.model.RoleUser;
import com.alirizakaygusuz.jwt_permission_based_poc.common.audit.SoftDeletableAuditBase;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;


@Entity
@Table(name = "auth_users")
@Getter
@Setter
@SuperBuilder
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor(force = true)
@SQLDelete(sql = "UPDATE auth_users SET deleted = true, deleted_at = now() WHERE id = ?")
@SQLRestriction("deleted = false")
public class AuthUser extends SoftDeletableAuditBase implements UserDetails{
	
	@Column(nullable = false , unique = true)
	private String username;
	
	@Column(nullable = false , unique = true)
	private String email;
	
	
	@Column(nullable = false )
	private String firstName;
	
	
	@Column(nullable = false )
	private String lastName;
	
	
	@Column(nullable = false)
	private String password;
	
	@OneToMany(mappedBy = "authUser" , cascade = CascadeType.ALL , orphanRemoval = true)
	private List<RoleUser> roleUsers;

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return roleUsers.stream().flatMap(roleUser -> roleUser.getRole().getRolePermissions().stream())
				.map(rolePermission -> new SimpleGrantedAuthority(rolePermission.getPermission().getPermissionKey()))
				.collect(Collectors.toSet());
	}

}
