package com.alirizakaygusuz.jwt_permission_based_poc.authentication.authuser.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.alirizakaygusuz.jwt_permission_based_poc.authentication.authuser.dto.AuthUserResponse;
import com.alirizakaygusuz.jwt_permission_based_poc.authentication.authuser.dto.AuthUserUpdateRequest;
import com.alirizakaygusuz.jwt_permission_based_poc.authentication.authuser.dto.LoginRequest;
import com.alirizakaygusuz.jwt_permission_based_poc.authentication.authuser.dto.LoginResponse;
import com.alirizakaygusuz.jwt_permission_based_poc.authentication.authuser.service.AuthUserService;
import com.alirizakaygusuz.jwt_permission_based_poc.common.controller.BaseResponseController;
import com.alirizakaygusuz.jwt_permission_based_poc.common.response.StandardApiResponse;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthUserController extends BaseResponseController {

	private final AuthUserService authUserService;

	@PostMapping("/login")
	public ResponseEntity<StandardApiResponse<LoginResponse>> login(@RequestBody @Valid LoginRequest request,
			HttpServletRequest httpServletRequest) {
		request.injectClientMetaData(httpServletRequest);

		return ok(authUserService.login(request));
	}

	@GetMapping("/admin/me")
	@PreAuthorize("hasAuthority('admin:user:view')")
	public ResponseEntity<StandardApiResponse<AuthUserResponse>> getAdmin(
		@AuthenticationPrincipal String value) {
		

		return ok(authUserService.getAdminByUsername(value));

	}

	@GetMapping("/users/{id}")
	@PreAuthorize("hasAuthority('admin:user:view')")
	public ResponseEntity<StandardApiResponse<AuthUserResponse>> getUserById(@PathVariable Long id) {

		return ok(authUserService.getUserById(id));

	}

	@GetMapping("/users/by-username")
	@PreAuthorize("hasAuthority('admin:user:view')")
	public ResponseEntity<StandardApiResponse<AuthUserResponse>> getUserByUsernameOrEmail(
			@RequestParam("value") String usernameOrEmail) {

		return ok(authUserService.getUserByUsernameOrEmail(usernameOrEmail));

	}

	@GetMapping("/users")
	@PreAuthorize("hasAuthority('admin:user:view_all')")
	public ResponseEntity<StandardApiResponse<List<AuthUserResponse>>> getAllUsers() {

		return ok(authUserService.getAllUsers());

	}

	@PatchMapping("/users/{id}")
	@PreAuthorize("hasAuthority('admin:user:update')")
	public ResponseEntity<StandardApiResponse<AuthUserResponse>> updateUserById(
			@RequestBody @Valid AuthUserUpdateRequest updateRequest, @PathVariable Long id) {

		return ok(authUserService.updateUserById(updateRequest, id));

	}

	@PatchMapping("/users/by-username")
	@PreAuthorize("hasAuthority('admin:user:update')")
	public ResponseEntity<StandardApiResponse<AuthUserResponse>> updateUserByUsernameOrEmail(
			@RequestBody @Valid AuthUserUpdateRequest updateRequest, @RequestParam("value") String usernameOrEmail) {

		return ok(authUserService.updateUserByUsernameOrEmail(updateRequest, usernameOrEmail));

	}

	@DeleteMapping("/admin/me")
	@PreAuthorize("hasAuthority('admin:user:delete')")
	public ResponseEntity<StandardApiResponse<Void>> deleteAdmin(
			@AuthenticationPrincipal String value) {

		authUserService.deleteAdminByUsername(value);
		return noContent();

	}

	@DeleteMapping("/users/{id}")
	@PreAuthorize("hasAuthority('admin:user:delete')")
	public ResponseEntity<StandardApiResponse<Void>> deleteUserById(@PathVariable Long id) {

		authUserService.deleteUserById(id);
		return noContent();

	}

	@DeleteMapping("/users/by-username")
	@PreAuthorize("hasAuthority('admin:user:delete')")
	public ResponseEntity<StandardApiResponse<Void>> deleteUserByUsernameOrEmail(
			@RequestParam("value") String usernameOrEmail) {

		authUserService.deleteUserByUsernameOrEmail(usernameOrEmail);
		return noContent();
	}

	@GetMapping("/users/me")
	@PreAuthorize("hasAuthority('user:self:view')")
	public ResponseEntity<StandardApiResponse<AuthUserResponse>> getCurrentUser(
			@AuthenticationPrincipal String value) {

		return ok(authUserService.getCurrentUser(value));

	}

	@PatchMapping("/users/me")
	@PreAuthorize("hasAuthority('user:self:update')")
	public ResponseEntity<StandardApiResponse<AuthUserResponse>> updateCurrentUser(
			@RequestBody @Valid AuthUserUpdateRequest updateRequest,
			@AuthenticationPrincipal String value) {

		return ok(authUserService.updateCurrentUser(updateRequest, value));

	}

	@DeleteMapping("/users/me")
	@PreAuthorize("hasAuthority('user:self:delete')")
	public ResponseEntity<StandardApiResponse<Void>> deleteCurrentUser(
			@AuthenticationPrincipal String value) {
		authUserService.deleteCurrentUser(value);

		return noContent();

	}
}
