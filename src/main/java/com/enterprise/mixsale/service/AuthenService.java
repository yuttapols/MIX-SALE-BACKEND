package com.enterprise.mixsale.service;

import org.springframework.stereotype.Service;

import com.enterprise.mixsale.dto.response.AuthenticationResDTO;

@Service
public interface AuthenService {
	
	public AuthenticationResDTO login(String userName, String password) throws Exception;

}
