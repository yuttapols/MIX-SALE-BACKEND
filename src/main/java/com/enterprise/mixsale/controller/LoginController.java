package com.enterprise.mixsale.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.enterprise.mixsale.common.AbstractCommon;
import com.enterprise.mixsale.payload.ApiResponse;
import com.enterprise.mixsale.util.Constants;

import jakarta.servlet.http.HttpServletRequest;

@RestController
@RequestMapping(value = Constants.PROJECT_VERSION + "/login")
public class LoginController extends AbstractCommon{

	@GetMapping("/testFunction")
	public ResponseEntity<ApiResponse> testFunction(HttpServletRequest request, String value) {
		
		ApiResponse response = new ApiResponse();

		try {
			response = getOkResponseData(value);
			
		} catch (Exception e) {
			// TODO: handle exception
			response = getOkResponse(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage()) ;
		}

		return ResponseEntity.ok(response) ;
	}
	
}
