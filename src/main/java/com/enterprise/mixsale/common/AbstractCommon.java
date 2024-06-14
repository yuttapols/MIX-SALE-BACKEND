package com.enterprise.mixsale.common;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.util.StringUtils;

import com.enterprise.mixsale.entity.AuthenticationEntity;
import com.enterprise.mixsale.payload.ApiResponse;
import com.enterprise.mixsale.payload.CustomerUserAttr;
import com.enterprise.mixsale.repository.AuthenticationRepository;
import com.enterprise.mixsale.service.JwtService;
import com.enterprise.mixsale.util.AppConstants;
import com.enterprise.mixsale.util.Constants;

import jakarta.servlet.http.HttpServletRequest;


public abstract class AbstractCommon {

	@Autowired
	JwtService jwtService;
	
	@Autowired
	AuthenticationRepository authenticationRepository;
	
	protected final static ApiResponse getSavedSuccessResponse() {
        return ApiResponse.builder()
                .success(true)
                .code(Integer.toString(HttpStatus.OK.value()))
                .message(Constants.MESSAGE_KEYS_SAVE_ENG)
                .messageTh(Constants.MESSAGE_KEYS_SAVE_TH)
                .build();
    }
	
	protected final static ApiResponse getOkResponse(HttpStatus code, String message) {
        return ApiResponse.builder()
                .success(true)
                .code(Integer.toString(code.value()))
                .message(message)
                .build();
    }
	
	protected final static ApiResponse getOkResponse() {
        
        return ApiResponse.builder()
                .success(true)
                .code(Integer.toString(HttpStatus.OK.value()))
                .message(Constants.MESSAGE_KEY_ENG)
                .messageTh(Constants.MESSAGE_KEY_TH)
                .build();
    }
	
	protected final static <T> ApiResponse getOkResponseData(T body) {
        
        return ApiResponse.builder()
                .success(true)
                .code(Integer.toString(HttpStatus.OK.value()))
                .data(body)
                .message(Constants.MESSAGE_KEY_ENG)
                .messageTh(Constants.MESSAGE_KEY_TH)
                .build();
    }
	
	
	public CustomerUserAttr getCustomerUserAttr(HttpServletRequest request) {
		CustomerUserAttr customerUserAttr = new CustomerUserAttr();
		UserDetails userDetail = (UserDetails) jwtService.getAuthentication(getToken(request)).getPrincipal();
		
		if(org.apache.commons.lang3.StringUtils.isNotBlank(userDetail.getUsername())) {
			Optional<AuthenticationEntity> dataList = authenticationRepository.findByUserName(userDetail.getUsername());
			if(dataList.isPresent()) {
				customerUserAttr.setEmpNo(dataList.get().getUserDetail().getEmpNo());
				customerUserAttr.setEmpName(dataList.get().getUserDetail().getFristName() + "-" + dataList.get().getUserDetail().getLastName());
				customerUserAttr.setRoleId(dataList.get().getRoleId());
				customerUserAttr.setRoleName(dataList.get().getRole().getRoleName());
			}
		}
		
		customerUserAttr.setIpAddress(request.getRemoteAddr());
		
		return customerUserAttr;
	}
	
	public String getToken(HttpServletRequest request) {
        String token = null;
        String bearerToken = request.getHeader(AppConstants.AUTHORIZATION_HEADER);
        if (StringUtils.hasText(bearerToken) && bearerToken.startsWith("Bearer ")) {
            token = bearerToken.substring(7);
        }
        return token;
    }
}
