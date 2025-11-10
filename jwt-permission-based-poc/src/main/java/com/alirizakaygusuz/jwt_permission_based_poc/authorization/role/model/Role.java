package com.alirizakaygusuz.jwt_permission_based_poc.authorization.role.model;

import java.util.HashSet;
import java.util.Set;

import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.SQLRestriction;

import com.alirizakaygusuz.jwt_permission_based_poc.authorization.relation.rolepermission.model.RolePermission;
import com.alirizakaygusuz.jwt_permission_based_poc.common.audit.SoftDeletableAuditBase;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Entity
@Table(name = "roles", uniqueConstraints = {@UniqueConstraint(columnNames = "name")})
@Getter
@Setter
@SuperBuilder
@NoArgsConstructor(force = true)
@EqualsAndHashCode(callSuper = true , onlyExplicitlyIncluded = true )
@SQLDelete(sql = "UPDATE roles SET deleted = true, deleted_at = now() WHERE id = ?")
@SQLRestriction("deleted = false")
public class Role extends SoftDeletableAuditBase {

	@EqualsAndHashCode.Include
	@Column(nullable = false,unique = true)
	private String name;
	
	@Column(nullable = false)
	private String description;
	
	
	@Builder.Default
	@OneToMany(mappedBy = "role" , fetch = FetchType.LAZY , cascade = CascadeType.ALL)
	private Set<RolePermission> rolePermissions = new HashSet<>();
}
