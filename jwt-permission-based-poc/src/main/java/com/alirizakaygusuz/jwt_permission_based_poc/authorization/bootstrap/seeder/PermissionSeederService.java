package com.alirizakaygusuz.jwt_permission_based_poc.authorization.bootstrap.seeder;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.alirizakaygusuz.jwt_permission_based_poc.authorization.permission.model.Permission;
import com.alirizakaygusuz.jwt_permission_based_poc.authorization.permission.repository.PermissionRepository;
import com.alirizakaygusuz.jwt_permission_based_poc.authorization.permission.type.PredefinedPermission;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
@RequiredArgsConstructor
public class PermissionSeederService {

	private final PermissionRepository permissionRepository;
	
	
	@Transactional
	public void seed() {
		
		log.info("Seeding Permissions!!!");
		
		List<Permission> permissions = new ArrayList<>();
		
		for(PredefinedPermission predef: PredefinedPermission.values()) {
			
			boolean exists = permissionRepository.existsByPermissionKey(predef.getKey());
			
			if(!exists) {
				Permission permission = Permission.builder()
						.permissionKey(predef.getKey())
						.description(predef.getDescription())
						.category(predef.getCategory())
						.build();
			
				permissions.add(permission);
			
			}
			
		}
		
		if(!permissions.isEmpty()) {
			permissionRepository.saveAll(permissions);
		}
		
		log.info("Seeded {} predefined permissons into db",permissions.size());


	}
}
