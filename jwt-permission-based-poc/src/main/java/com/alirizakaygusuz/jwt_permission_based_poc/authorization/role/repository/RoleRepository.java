package com.alirizakaygusuz.jwt_permission_based_poc.authorization.role.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.alirizakaygusuz.jwt_permission_based_poc.authorization.role.model.Role;

@Repository
public interface RoleRepository extends JpaRepository<Role , Long> {

	boolean existsByName(String name);

	Optional<Role> findByName(String string);

}
