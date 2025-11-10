package com.alirizakaygusuz.jwt_permission_based_poc.authorization.permission.model;

import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.SQLRestriction;

import com.alirizakaygusuz.jwt_permission_based_poc.authorization.permission.type.PermissionCategory;
import com.alirizakaygusuz.jwt_permission_based_poc.common.audit.SoftDeletableAuditBase;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Entity
@Table(name = "permissions" , uniqueConstraints = {@UniqueConstraint(columnNames = "permission_key")})
@Getter
@Setter
@SuperBuilder
@NoArgsConstructor(force = true)
@EqualsAndHashCode(callSuper = true, onlyExplicitlyIncluded = true)
@SQLDelete(sql = "UPDATE permissions SET deleted = true, deleted_at = now() WHERE id = ?")
@SQLRestriction("deleted = false")
public class Permission extends SoftDeletableAuditBase {
	
    @EqualsAndHashCode.Include
    @Column(name = "permission_key",nullable = false, unique = true)
	private String permissionKey;
	
    @Column(nullable = false)
	private String description;
	
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
	private PermissionCategory category;

}
