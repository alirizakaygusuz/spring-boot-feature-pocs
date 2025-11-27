package com.alirizakaygusuz.jwt_verification_otp_token_poc.authorization.relation.userrole.model;

import com.alirizakaygusuz.jwt_verification_otp_token_poc.authentication.authuser.model.AuthUser;
import com.alirizakaygusuz.jwt_verification_otp_token_poc.authorization.role.model.Role;
import com.alirizakaygusuz.jwt_verification_otp_token_poc.common.audit.SoftDeletableAuditBase;

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
@Table(name = "user_roles")
@Getter
@Setter
@SuperBuilder
@NoArgsConstructor(force = true)
public class UserRole extends SoftDeletableAuditBase {
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "auth_user_id" , nullable = false)
	private AuthUser authUser; 
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "role_id" , nullable = false)
	private Role role;
	
	
	

}
