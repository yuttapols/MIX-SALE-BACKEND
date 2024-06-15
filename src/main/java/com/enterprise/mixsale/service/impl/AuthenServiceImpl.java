package com.enterprise.mixsale.service.impl;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.enterprise.mixsale.dto.response.AuthenticationResDTO;
import com.enterprise.mixsale.dto.response.RefreshTokenDTO;
import com.enterprise.mixsale.dto.response.UserDetailResDTO;
import com.enterprise.mixsale.repository.AuthenticationRepository;
import com.enterprise.mixsale.service.AuthenService;
import com.enterprise.mixsale.service.JwtService;
import com.enterprise.mixsale.service.RefreshTokenService;
import com.enterprise.mixsale.util.Md5Util;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthenServiceImpl implements AuthenService{

	@Autowired
	AuthenticationRepository authenticationRepository;

	@Autowired
    PasswordEncoder passwordEncoder;
	
	@Autowired
    JwtService jwtService;
	
	@Autowired
    AuthenticationManager authenticationManager;
	
	@Autowired
	RefreshTokenService refreshTokenService;
	
	@Autowired
	private ModelMapper mapper;

    @Override
    public AuthenticationResDTO login(String userName, String password) {
    	
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(userName,passwordEncoder.encode(Md5Util.checkMd5(password))));
        var user = authenticationRepository.findByUserName(userName).orElseThrow(() -> new IllegalArgumentException("Invalid email or password."));
        var jwt = jwtService.generateToken(user);

        RefreshTokenDTO refreshTokenDTO = refreshTokenService.createRefreshToken(userName);
        
        return AuthenticationResDTO.builder()
            	.id(user.getId())
            	.userName(user.getUsername())
            	.status(user.getStatus())
            	.role(user.getRole())
            	.createBy(user.getCreateBy())
            	.createDate(user.getCreateDate())
            	.updateBy(user.getUpdateBy())
            	.updateDate(user.getUpdateDate())
            	.accessToken(jwt)
            	.token(refreshTokenDTO.getToken())
            	.userDetail(mapper.map(user.getUserDetail(), new TypeToken<UserDetailResDTO>() {}.getType()))
            	.build();
    }


}
