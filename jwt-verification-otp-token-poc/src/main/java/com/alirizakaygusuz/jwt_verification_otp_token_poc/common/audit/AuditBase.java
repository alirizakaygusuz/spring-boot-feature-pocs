package com.alirizakaygusuz.jwt_verification_otp_token_poc.common.audit;

import java.time.Instant;

import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;


@MappedSuperclass
@Getter
@EntityListeners(AuditingEntityListener.class) 
@SuperBuilder
@NoArgsConstructor(force = true)
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public abstract class AuditBase {

	@Id
	@EqualsAndHashCode.Include
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	protected Long id;
	
	@CreatedDate
	@Column(updatable = false , nullable = false ,  columnDefinition = "datetime(3)")
	private Instant createdAt;
	
	@CreatedBy
	@Column(updatable = false , nullable =  false )
	private String createdBy;
	
	@LastModifiedDate
	@Column(name = "updated_at" ,columnDefinition = "datetime(3)")
	private Instant updatedAt;
	
	@LastModifiedBy
	@Column(name = "updated_by")
	private String updatedBy;
	
}
