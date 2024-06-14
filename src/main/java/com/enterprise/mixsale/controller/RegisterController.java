package com.enterprise.mixsale.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.enterprise.mixsale.common.AbstractCommon;
import com.enterprise.mixsale.payload.ApiResponse;
import com.enterprise.mixsale.payload.CustomerUserAttr;
import com.enterprise.mixsale.util.AppConstants;

import jakarta.servlet.http.HttpServletRequest;

@RestController
@RequestMapping(value = AppConstants.PROJECT_VERSION + "/register")
public class RegisterController extends AbstractCommon{

	@GetMapping("/getById")
	public ResponseEntity<ApiResponse> getById(HttpServletRequest request, String value) {
		
		CustomerUserAttr cutomerUserAttr = super.getCustomerUserAttr(request);
		
		ApiResponse response = new ApiResponse();

		try {
			response = getOkResponseData(cutomerUserAttr);
			
		} catch (Exception e) {
			// TODO: handle exception
			response = getOkResponse(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage()) ;
		}

		return ResponseEntity.ok(response) ;
	}
	
}
