package com.enterprise.mixsale.service.impl;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

import org.apache.commons.lang3.ObjectUtils;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.enterprise.mixsale.dto.response.RefreshTokenDTO;
import com.enterprise.mixsale.entity.AuthenticationEntity;
import com.enterprise.mixsale.entity.RefreshTokenEntity;
import com.enterprise.mixsale.exception.TokenRefreshException;
import com.enterprise.mixsale.repository.AuthenticationRepository;
import com.enterprise.mixsale.repository.RefreshTokenRepository;
import com.enterprise.mixsale.service.RefreshTokenService;

import jakarta.transaction.Transactional;

@Service
public class RefreshTokenServiceImpl implements RefreshTokenService{

	@Autowired
	private RefreshTokenRepository  refreshTokenRepository;
	
	@Autowired
	private AuthenticationRepository authenticationRepository;
	
	@Autowired
	private ModelMapper mapper;
	
	@Value("${token.expiry.minutes}")
	private Integer tokenExpiryMinutes;
	
	@Override
	public RefreshTokenDTO findByToken(String token) {
		
		RefreshTokenDTO response = null;
		RefreshTokenEntity entity = refreshTokenRepository.findByToken(token);
		if(ObjectUtils.isNotEmpty(entity)) {
			response = mapper.map(entity, new TypeToken<RefreshTokenDTO>() {}.getType());
		}
		return response;
	}

	@Override
	@Transactional
	public RefreshTokenDTO createRefreshToken(String userName) {

		RefreshTokenDTO response = null;
		
		AuthenticationEntity authenEntity = authenticationRepository.findByUserName(userName).get();
		
		if(ObjectUtils.isNotEmpty(authenEntity)) {
			
			refreshTokenRepository.deleteByUserId(authenEntity.getId());
			
			response = new RefreshTokenDTO();
			RefreshTokenEntity tokenEntity = RefreshTokenEntity.builder()
					.token(UUID.randomUUID().toString())
					.userId(authenEntity.getId())
					.expiryDate(Timestamp.from(Instant.now().plusMillis(TimeUnit.MINUTES.toMillis(tokenExpiryMinutes))))
					.build();
			tokenEntity = refreshTokenRepository.save(tokenEntity);
			
			response = mapper.map(tokenEntity, new TypeToken<RefreshTokenDTO>() {}.getType());
			
			return response;
		}
		
		
		return response;
	}

	@Override
	public RefreshTokenDTO verifyExpiration(RefreshTokenDTO token) {
        
		if (token.getExpiryDate().compareTo( new Timestamp(System.currentTimeMillis()) ) < 0) {
            refreshTokenRepository.delete(mapper.map(token, new TypeToken<RefreshTokenEntity>() {}.getType()));
            throw new TokenRefreshException(token.getToken(), "Refresh token was expired. Please make a new signin request");
        }

        return token;
	}

}
