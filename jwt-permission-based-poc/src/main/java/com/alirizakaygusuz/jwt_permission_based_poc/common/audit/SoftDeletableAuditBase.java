package com.alirizakaygusuz.jwt_permission_based_poc.common.audit;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@MappedSuperclass
@Getter
@Setter
@SuperBuilder
@NoArgsConstructor(force = true)
@EqualsAndHashCode(callSuper = true)
public class SoftDeletableAuditBase extends AuditBase {

	@Builder.Default
	@Column(nullable = false, columnDefinition = "BOOLEAN DEFAULT FALSE")
	@NotNull(message = "Deleted flag cannot be null")
	private Boolean deleted = false;

	@Column(name = "deleted_by")
	String deletedBy;

	@Column(name = "deleted_at")
	private LocalDateTime deletedAt;
}
