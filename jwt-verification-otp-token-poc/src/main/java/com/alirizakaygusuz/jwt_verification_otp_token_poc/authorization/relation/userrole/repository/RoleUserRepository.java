package com.alirizakaygusuz.jwt_verification_otp_token_poc.authorization.relation.userrole.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.alirizakaygusuz.jwt_verification_otp_token_poc.authorization.relation.userrole.model.UserRole;

@Repository
public interface RoleUserRepository extends JpaRepository<UserRole , Long> {

}
