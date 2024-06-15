package com.enterprise.mixsale.controller;

import org.apache.commons.lang3.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.enterprise.mixsale.common.AbstractCommon;
import com.enterprise.mixsale.dto.response.JwtResponseDTO;
import com.enterprise.mixsale.dto.response.RefreshTokenDTO;
import com.enterprise.mixsale.payload.ApiResponse;
import com.enterprise.mixsale.repository.AuthenticationRepository;
import com.enterprise.mixsale.service.AuthenService;
import com.enterprise.mixsale.service.JwtService;
import com.enterprise.mixsale.service.RefreshTokenService;
import com.enterprise.mixsale.util.AppConstants;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping(value = AppConstants.PROJECT_VERSION + "/authen")
@RequiredArgsConstructor
public class AuthenController extends AbstractCommon {

	@Autowired
	private AuthenService authenService;
	
	@Autowired
	RefreshTokenService refreshTokenService;
	
	@Autowired
	AuthenticationRepository authenticationRepository;
	
	@Autowired
    JwtService jwtService;

	
	@GetMapping("/login")
	public ResponseEntity<ApiResponse> login(@RequestParam(name="userName") String userName, @RequestParam(name="password") String password) throws Exception {
		ApiResponse response = new ApiResponse();

		try {
			response = getOkResponseData(authenService.login(userName, password));
			
		} catch (Exception e) {
			// TODO: handle exception
			response = getOkResponse(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage()) ;
		}

		return ResponseEntity.ok(response) ;
	}
	
	@GetMapping("/refreshToken")
	public JwtResponseDTO refreshToken(@RequestParam String refreshToken){
		
		RefreshTokenDTO refreshTokenDTO = refreshTokenService.findByToken(refreshToken);
		
		if(ObjectUtils.isNotEmpty(refreshTokenDTO)) {
			refreshTokenDTO = refreshTokenService.verifyExpiration(refreshTokenDTO);
			if(ObjectUtils.isNotEmpty(refreshTokenDTO)) {
				var user = authenticationRepository.findById(refreshTokenDTO.getUserId()).orElseThrow(() -> new IllegalArgumentException("Invalid email or password."));
				var jwt = jwtService.generateToken(user);
				return JwtResponseDTO.builder()
						.accessToken(jwt)
                        .token(refreshToken).build();
			}
		}else {
			new RuntimeException("Refresh Token is not in DB..!!");
		}
		
		return JwtResponseDTO.builder().build();
	}

	

}
