package com.alirizakaygusuz.jwt_permission_based_poc.authorization.bootstrap.seeder;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.alirizakaygusuz.jwt_permission_based_poc.authorization.role.model.Role;
import com.alirizakaygusuz.jwt_permission_based_poc.authorization.role.repository.RoleRepository;
import com.alirizakaygusuz.jwt_permission_based_poc.authorization.role.type.PredefinedRole;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
@RequiredArgsConstructor
public class RoleSeederService {

	private final RoleRepository roleRepository;
	
	@Transactional
	public void seed() {
		
		log.info("Seeding Roles");
		
		List<Role> roles = new ArrayList<>();
		
		for(PredefinedRole predef: PredefinedRole.values()){
			boolean exists = roleRepository.existsByName(predef.getName());
			
			if(!exists) {
			
				Role role = Role.builder()
						.name(predef.getName())
						.description(predef.getDescription())
						.build();
			
				roles.add(role);
			}
		}
		
		if(!roles.isEmpty()) {
			roleRepository.saveAll(roles);

		}
		
		
		
		log.info("Seeded {} predefined roles into db",roles.size());

	}
}
