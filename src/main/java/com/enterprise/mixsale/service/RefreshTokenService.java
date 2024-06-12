package com.enterprise.mixsale.service;

import com.enterprise.mixsale.dto.response.RefreshTokenDTO;

public interface RefreshTokenService {

	public RefreshTokenDTO findByToken(String token) ;
	public RefreshTokenDTO createRefreshToken(String userName);
	public RefreshTokenDTO verifyExpiration(RefreshTokenDTO token) ;
}
