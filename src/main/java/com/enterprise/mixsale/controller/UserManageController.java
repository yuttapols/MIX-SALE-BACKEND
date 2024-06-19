package com.enterprise.mixsale.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.enterprise.mixsale.common.AbstractCommon;
import com.enterprise.mixsale.dto.request.UserManageReqDTO;
import com.enterprise.mixsale.payload.ApiResponse;
import com.enterprise.mixsale.payload.CustomerUserAttr;
import com.enterprise.mixsale.service.UserManageService;
import com.enterprise.mixsale.util.AppConstants;

import jakarta.servlet.http.HttpServletRequest;

@RestController
@RequestMapping(value = AppConstants.PROJECT_VERSION + "/user/manage")
public class UserManageController extends AbstractCommon{

	@Autowired
	UserManageService manageService;
	
	@PostMapping("/save")
	public ResponseEntity<ApiResponse> save(HttpServletRequest request, @RequestBody UserManageReqDTO registerReq) {
		ApiResponse response = new ApiResponse();
		
		CustomerUserAttr userAttr = super.getCustomerUserAttr(request);

		try {
			manageService.save(userAttr, registerReq);
			response = getSavedSuccessResponse();
		} catch (Exception e) {
			response = getOkResponse(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage()) ;
		}

		return ResponseEntity.ok(response) ;
	}
	
	@PutMapping("/update/{userId}")
	public ResponseEntity<ApiResponse> update(HttpServletRequest request, @RequestBody UserManageReqDTO registerReq, @PathVariable(name="userId") Long userId) {
		ApiResponse response = new ApiResponse();
		
		CustomerUserAttr userAttr = super.getCustomerUserAttr(request);

		try {
			manageService.update(userAttr, registerReq, userId);
			response = getSavedSuccessResponse();
		} catch (Exception e) {
			response = getOkResponse(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage()) ;
		}

		return ResponseEntity.ok(response) ;
	}
	
}
