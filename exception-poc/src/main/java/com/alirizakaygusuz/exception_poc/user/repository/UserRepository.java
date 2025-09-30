package com.alirizakaygusuz.exception_poc.user.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.alirizakaygusuz.exception_poc.user.model.User;

@Repository
public interface UserRepository extends JpaRepository<User , Long>{

	boolean existsByUsername(String username);
	
	boolean existsByEmail(String email);
	
	Optional<User> findByUsername(String username);
}
