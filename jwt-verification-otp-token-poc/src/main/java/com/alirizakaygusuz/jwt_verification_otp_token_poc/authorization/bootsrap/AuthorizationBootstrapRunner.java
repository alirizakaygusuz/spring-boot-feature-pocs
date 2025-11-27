package com.alirizakaygusuz.jwt_verification_otp_token_poc.authorization.bootsrap;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import com.alirizakaygusuz.jwt_verification_otp_token_poc.authorization.bootsrap.seeder.MockUserSeederService;
import com.alirizakaygusuz.jwt_verification_otp_token_poc.authorization.bootsrap.seeder.PermissionSeederService;
import com.alirizakaygusuz.jwt_verification_otp_token_poc.authorization.bootsrap.seeder.RolePermissionSeederService;
import com.alirizakaygusuz.jwt_verification_otp_token_poc.authorization.bootsrap.seeder.RoleSeederService;

import lombok.RequiredArgsConstructor;

@Component
@Profile("dev")
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
