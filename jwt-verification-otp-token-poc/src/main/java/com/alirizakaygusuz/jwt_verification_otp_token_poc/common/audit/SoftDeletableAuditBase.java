package com.alirizakaygusuz.jwt_verification_otp_token_poc.common.audit;

import java.time.Instant;

import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@MappedSuperclass
@Getter
@Setter
@SuperBuilder
@NoArgsConstructor(force = true)
public abstract class SoftDeletableAuditBase extends AuditBase {
	
	@Builder.Default
	@Column(name = "deleted", nullable = false, columnDefinition = "BOOLEAN DEFAULT FALSE")
	@NotNull(message = "Deleted flag cannot be null")
	private boolean deletedFlag = false;
	
	@Column(name = "deleted_by")
	private String deletedBy;
	
	@Column(name = "deleted_at" , columnDefinition = "datetime(3)")
	private Instant deletedAt;
}
