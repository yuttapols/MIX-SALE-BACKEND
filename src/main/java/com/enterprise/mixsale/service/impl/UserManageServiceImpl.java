package com.enterprise.mixsale.service.impl;

import java.security.NoSuchAlgorithmException;
import java.sql.Timestamp;

import org.apache.commons.lang3.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.enterprise.mixsale.dto.request.UserManageReqDTO;
import com.enterprise.mixsale.entity.AuthenticationEntity;
import com.enterprise.mixsale.entity.UserDetailEntity;
import com.enterprise.mixsale.payload.CustomerUserAttr;
import com.enterprise.mixsale.repository.AuthenticationRepository;
import com.enterprise.mixsale.repository.UserDetailRepository;
import com.enterprise.mixsale.service.UserManageService;
import com.enterprise.mixsale.util.Constants;
import com.enterprise.mixsale.util.FunctionUtil;
import com.enterprise.mixsale.util.Md5Util;

@Service
public class UserManageServiceImpl implements UserManageService{

	
	@Autowired
	private AuthenticationRepository authenticationRepository;
	
	@Autowired
	private UserDetailRepository userDetailRepository;
	
	@Override
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void save(CustomerUserAttr userAttr, UserManageReqDTO registerReq) throws NoSuchAlgorithmException {
		
		if(ObjectUtils.isNotEmpty(registerReq)) {
			String empNo = userAttr.getEmpNo();
			AuthenticationEntity authenEntity = new AuthenticationEntity();
			authenEntity.setStatus(Constants.STATUS_NORMAL);
			authenEntity.setCreateBy(empNo);
			authenEntity.setCreateDate(new Timestamp(System.currentTimeMillis()));
			authenEntity.setUserName(registerReq.getUserName());
			authenEntity.setPassword(Md5Util.genarateMd5(registerReq.getPassword()));
			authenEntity.setRoleId(registerReq.getRoleId());
			authenEntity = authenticationRepository.save(authenEntity);
			
			if(ObjectUtils.isNotEmpty(authenEntity)) {
				UserDetailEntity userDetailEntity = new UserDetailEntity();
				userDetailEntity.setUserId(authenEntity.getId());
				userDetailEntity.setEmpNo(FunctionUtil.genarateEmpNo(registerReq.getDepartmentId(), authenEntity.getId()));
				userDetailEntity.setEmail(registerReq.getEmail());
				userDetailEntity.setTelephone(registerReq.getTelephone());
				userDetailEntity.setPrefixId(registerReq.getPrefixId());
				userDetailEntity.setDepartmentId(registerReq.getDepartmentId());
				userDetailEntity.setFristName(registerReq.getFristName());
				userDetailEntity.setMiddleName(registerReq.getMiddleName());
				userDetailEntity.setLastName(registerReq.getLastName());
				userDetailEntity.setNickName(registerReq.getNickName());
				userDetailEntity.setHouseNo(registerReq.getHouseNo());
				userDetailEntity.setVillageNo(registerReq.getVillageNo());
				userDetailEntity.setAlley(registerReq.getAlley());
				userDetailEntity.setLane(registerReq.getLane());
				userDetailEntity.setRoad(registerReq.getRoad());
				userDetailEntity.setGeographiesId(registerReq.getGeographiesId());
				userDetailEntity.setDistricts(registerReq.getDistricts());
				userDetailEntity.setAmphuresId(registerReq.getAmphuresId());
				userDetailEntity.setProvincesId(registerReq.getProvincesId());
				userDetailEntity.setStatus(Constants.STATUS_NORMAL);
				userDetailEntity.setCreateBy(empNo);
				userDetailEntity.setCreateDate(new Timestamp(System.currentTimeMillis()));
				userDetailRepository.save(userDetailEntity);
			}
		}
		
	}

}
