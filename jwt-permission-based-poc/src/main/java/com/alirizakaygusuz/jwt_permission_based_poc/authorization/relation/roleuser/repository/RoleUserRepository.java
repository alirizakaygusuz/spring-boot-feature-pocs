package com.alirizakaygusuz.jwt_permission_based_poc.authorization.relation.roleuser.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.alirizakaygusuz.jwt_permission_based_poc.authorization.relation.roleuser.model.RoleUser;


@Repository
public interface RoleUserRepository extends JpaRepository<RoleUser , Long> {

}
