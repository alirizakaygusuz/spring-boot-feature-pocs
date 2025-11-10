package com.alirizakaygusuz.jwt_permission_based_poc.authorization.bootstrap;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import com.alirizakaygusuz.jwt_permission_based_poc.authorization.bootstrap.seeder.MockUserSeederService;
import com.alirizakaygusuz.jwt_permission_based_poc.authorization.bootstrap.seeder.PermissionSeederService;
import com.alirizakaygusuz.jwt_permission_based_poc.authorization.bootstrap.seeder.RolePermissionSeederService;
import com.alirizakaygusuz.jwt_permission_based_poc.authorization.bootstrap.seeder.RoleSeederService;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class AuthorizationBootstrapRunner implements ApplicationRunner {
	
	private final PermissionSeederService permissionSeederService;
	private final RoleSeederService roleSeederService;
	private final RolePermissionSeederService rolePermissionService;
	
	private final MockUserSeederService mockUserSeederService;
	

	@Override
	public void run(ApplicationArguments args) throws Exception {
		permissionSeederService.seed();
		roleSeederService.seed();
		rolePermissionService.seed();
		
		mockUserSeederService.seed();
		
	}

}
