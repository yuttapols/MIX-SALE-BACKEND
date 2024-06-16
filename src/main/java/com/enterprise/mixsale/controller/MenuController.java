package com.enterprise.mixsale.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.enterprise.mixsale.common.AbstractCommon;
import com.enterprise.mixsale.payload.ApiResponse;
import com.enterprise.mixsale.service.MenuService;
import com.enterprise.mixsale.util.AppConstants;

@RestController
@RequestMapping(value = AppConstants.PROJECT_VERSION + "/menu")
public class MenuController extends AbstractCommon{
	
	@Autowired
	MenuService menuService;
	
	@GetMapping("/getAll")
	public ResponseEntity<ApiResponse> getAll(){
		ApiResponse response = new ApiResponse();
		
		try {
			response = getOkResponseData(menuService.getAll());
			
		} catch (Exception e) {
			// TODO: handle exception
			response = getOkResponse(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage()) ;
		}

		return ResponseEntity.ok(response) ;
	}

}
