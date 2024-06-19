package com.enterprise.mixsale.service.impl;

import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.Optional;

import org.apache.commons.collections4.CollectionUtils;
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
import com.enterprise.mixsale.util.DateUtil;
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
			String empNo = userAttr.getCustomerNo();
			AuthenticationEntity authenEntity = new AuthenticationEntity();
			authenEntity.setStatus(Constants.STATUS_NORMAL);
			authenEntity.setCreateBy(empNo);
			authenEntity.setCreateDate(DateUtil.createTimestmapNow());
			authenEntity.setUserName(registerReq.getUserName());
			authenEntity.setPassword(Md5Util.genarateMd5(registerReq.getPassword()));
			authenEntity.setRoleId(registerReq.getRoleId());
			authenEntity = authenticationRepository.save(authenEntity);
			
			if(ObjectUtils.isNotEmpty(authenEntity)) {
				UserDetailEntity userDetailEntity = new UserDetailEntity();
				userDetailEntity.setUserId(authenEntity.getId());
				userDetailEntity.setCustomerNo(FunctionUtil.genarateCustomerNo(authenEntity.getId(), authenEntity.getId()));
				userDetailEntity.setEmail(registerReq.getEmail());
				userDetailEntity.setTelephone(registerReq.getTelephone());
				userDetailEntity.setPrefixId(registerReq.getPrefixId());
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
				userDetailEntity.setCreateDate(DateUtil.createTimestmapNow());
				userDetailRepository.save(userDetailEntity);
			}
		}
		
	}

	@Override
	public void update(CustomerUserAttr userAttr, UserManageReqDTO registerReq, Long userId) {
		
		Optional<AuthenticationEntity> authenOpt = authenticationRepository.findById(userId);
		if(authenOpt.isPresent()) {
			List<UserDetailEntity> userDetailList = userDetailRepository.findByUserId(userId);
			if(CollectionUtils.isNotEmpty(userDetailList)) {
				for(UserDetailEntity userDtEntity : userDetailList) {
					userDtEntity.setFristName(registerReq.getFristName());
					userDtEntity.setLastName(registerReq.getLastName());
					userDtEntity.setEmail(registerReq.getEmail());
					userDtEntity.setTelephone(registerReq.getTelephone());
					userDtEntity.setPrefixId(registerReq.getPrefixId());
					userDtEntity.setHouseNo(registerReq.getHouseNo());
					userDtEntity.setNickName(registerReq.getNickName());
					userDtEntity.setMiddleName(registerReq.getMiddleName());
					userDtEntity.setUpdateBy(userAttr.getCustomerName());
					userDtEntity.setUpdateDate(DateUtil.createTimestmapNow());
					userDetailRepository.save(userDtEntity);
				}
			}
		}
	}

}
