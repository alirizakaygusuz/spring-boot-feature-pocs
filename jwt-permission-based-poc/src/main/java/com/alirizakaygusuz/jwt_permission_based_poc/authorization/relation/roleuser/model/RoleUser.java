package com.alirizakaygusuz.jwt_permission_based_poc.authorization.relation.roleuser.model;

import com.alirizakaygusuz.jwt_permission_based_poc.authentication.authuser.model.AuthUser;
import com.alirizakaygusuz.jwt_permission_based_poc.authorization.role.model.Role;
import com.alirizakaygusuz.jwt_permission_based_poc.common.audit.SoftDeletableAuditBase;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Entity
@Table(name = "role_users")
@Getter
@Setter
@SuperBuilder
@NoArgsConstructor(force = true)
public class RoleUser extends SoftDeletableAuditBase {

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "role_id", nullable = false)
	private Role role;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "auth_user_id", nullable = false)
	private AuthUser authUser;

}
