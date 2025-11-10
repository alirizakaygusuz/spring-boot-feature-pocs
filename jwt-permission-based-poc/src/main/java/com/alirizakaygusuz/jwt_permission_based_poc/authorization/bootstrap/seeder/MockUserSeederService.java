package com.alirizakaygusuz.jwt_permission_based_poc.authorization.bootstrap.seeder;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.alirizakaygusuz.jwt_permission_based_poc.authentication.authuser.model.AuthUser;
import com.alirizakaygusuz.jwt_permission_based_poc.authentication.authuser.repository.AuthUserRepository;
import com.alirizakaygusuz.jwt_permission_based_poc.authorization.relation.roleuser.model.RoleUser;
import com.alirizakaygusuz.jwt_permission_based_poc.authorization.relation.roleuser.repository.RoleUserRepository;
import com.alirizakaygusuz.jwt_permission_based_poc.authorization.role.exception.RoleException;
import com.alirizakaygusuz.jwt_permission_based_poc.authorization.role.exception.type.RoleErrorType;
import com.alirizakaygusuz.jwt_permission_based_poc.authorization.role.model.Role;
import com.alirizakaygusuz.jwt_permission_based_poc.authorization.role.repository.RoleRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
@RequiredArgsConstructor
public class MockUserSeederService {

	private final AuthUserRepository authUserRepository;
	private final RoleRepository roleRepository;
    private final RoleUserRepository roleUserRepository;
	private final PasswordEncoder passwordEncoder;
	
	
	
    public void seed(){
        if (authUserRepository.count() == 0) {
            Role adminRole = roleRepository.findByName("SUPER_ADMIN")
                    .orElseThrow(() -> new RoleException(RoleErrorType.ADMIN_ROLE_NOT_FOUND));
            Role userRole = roleRepository.findByName("USER")
                    .orElseThrow(() -> new RoleException(RoleErrorType.USER_ROLE_NOT_FOUND));

            AuthUser admin = AuthUser.builder()
            		.username("admin")
            		.firstName("admin")
            		.lastName("admin")
                    .email("admin@example.com")
                    .password(passwordEncoder.encode("admin123"))
                    .build();

            AuthUser user = AuthUser.builder()
            		.username("user")
            		.firstName("user")
            		.lastName("user")
                    .email("user@example.com")
                    .password(passwordEncoder.encode("user123"))
                    .build();
            
            authUserRepository.save(admin);
            authUserRepository.save(user);
            
            RoleUser adminRelation =RoleUser.builder()
            		.authUser(admin)
            		.role(adminRole)               
            		.build();
            
            RoleUser userRelation=RoleUser.builder()
            		.authUser(user)
            		.role(userRole)
            		.build();

           roleUserRepository.save(adminRelation);
           roleUserRepository.save(userRelation);

            log.info("Mock users and relations are created by MockUserSeederService for: admin@example.com, user@example.com");
        } else {
            log.info("Mock users already exist.");
        }
    }
}
