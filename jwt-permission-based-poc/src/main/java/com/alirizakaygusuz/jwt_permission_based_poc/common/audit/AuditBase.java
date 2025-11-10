package com.alirizakaygusuz.jwt_permission_based_poc.common.audit;

import java.time.LocalDateTime;

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


@Getter
@MappedSuperclass
@SuperBuilder
@EntityListeners(AuditingEntityListener.class)
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@NoArgsConstructor(force = true)
public class AuditBase {
	
	
	@Id
	@EqualsAndHashCode.Include
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	protected Long id;
	
	
	@CreatedDate
	@Column(updatable = false ,nullable = false)
	private LocalDateTime createdTime;
	
	@LastModifiedDate
	@Column
	private LocalDateTime updatedDateTime;
	
	@CreatedBy
	@Column(updatable = false , nullable = false)
	private String createdBy;
	
	@LastModifiedBy
	@Column
	private String updatedBy;
	

}
