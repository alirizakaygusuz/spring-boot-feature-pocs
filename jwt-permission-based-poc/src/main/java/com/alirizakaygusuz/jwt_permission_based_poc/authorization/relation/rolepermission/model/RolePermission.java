package com.alirizakaygusuz.jwt_permission_based_poc.authorization.relation.rolepermission.model;

import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.SQLRestriction;

import com.alirizakaygusuz.jwt_permission_based_poc.authorization.permission.model.Permission;
import com.alirizakaygusuz.jwt_permission_based_poc.authorization.role.model.Role;
import com.alirizakaygusuz.jwt_permission_based_poc.common.audit.SoftDeletableAuditBase;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;


@Entity
@Table(
	    name = "role_permissions",
	    uniqueConstraints = @UniqueConstraint(columnNames = {"role_id", "permission_id"})
	)
@Getter
@Setter
@SuperBuilder
@NoArgsConstructor(force = true)
@EqualsAndHashCode(callSuper = true , onlyExplicitlyIncluded = true)
@SQLDelete(sql = "UPDATE role_permissions SET deleted = true, deleted_at = now() WHERE id = ?")
@SQLRestriction("deleted = false")
public class RolePermission extends SoftDeletableAuditBase {
	
	@ManyToOne(fetch = FetchType.LAZY ,optional = false)
	@JoinColumn(name = "role_id" , nullable = false)
	private Role role;
	
	@ManyToOne(fetch = FetchType.LAZY ,optional = false)
	@JoinColumn(name ="permission_id" ,nullable = false)
	private Permission permission;
	
	@Builder.Default
	@Column(nullable = false)
	private String grantedBy = "system";
	

}
