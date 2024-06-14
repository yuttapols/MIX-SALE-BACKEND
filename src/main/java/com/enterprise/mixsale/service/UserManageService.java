package com.enterprise.mixsale.service;

import java.security.NoSuchAlgorithmException;

import com.enterprise.mixsale.dto.request.UserManageReqDTO;
import com.enterprise.mixsale.payload.CustomerUserAttr;

public interface UserManageService {

	public void save(CustomerUserAttr userAttr, UserManageReqDTO registerReq) throws NoSuchAlgorithmException;
}
