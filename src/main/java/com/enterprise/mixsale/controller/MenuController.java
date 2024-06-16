package com.enterprise.mixsale.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.enterprise.mixsale.common.AbstractCommon;
import com.enterprise.mixsale.dto.request.MenuReqDTO;
import com.enterprise.mixsale.payload.ApiResponse;
import com.enterprise.mixsale.payload.CustomerUserAttr;
import com.enterprise.mixsale.service.MenuService;
import com.enterprise.mixsale.util.AppConstants;

import jakarta.servlet.http.HttpServletRequest;

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
	
	
	@GetMapping("/getByRoleIdAndDefault")
	public ResponseEntity<ApiResponse> getByRoleIdAndDefault(@RequestParam(name="roleId") Long roleId){
		ApiResponse response = new ApiResponse();
		
		try {
			response = getOkResponseData(menuService.getByRoleIdAndDefault(roleId));
			
		} catch (Exception e) {
			// TODO: handle exception
			response = getOkResponse(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage()) ;
		}

		return ResponseEntity.ok(response) ;
	}
	
	@PostMapping("/save")
	public ResponseEntity<ApiResponse> save(HttpServletRequest request, @RequestBody MenuReqDTO menuReq){
		ApiResponse response = new ApiResponse();
		CustomerUserAttr userAttr = super.getCustomerUserAttr(request);
		try {
			menuService.save(userAttr, menuReq);
			response = getSavedSuccessResponse();
			
		} catch (Exception e) {
			// TODO: handle exception
			response = getOkResponse(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage()) ;
		}

		return ResponseEntity.ok(response) ;
	}
	
	@PutMapping("/update/{menuId}")
	public ResponseEntity<ApiResponse> update(HttpServletRequest request, @RequestBody MenuReqDTO menuReq, @PathVariable(name="menuId") Long menuId){
		ApiResponse response = new ApiResponse();
		CustomerUserAttr userAttr = super.getCustomerUserAttr(request);
		try {
			menuService.update(userAttr, menuReq, menuId);
			response = getSavedSuccessResponse();
			
		} catch (Exception e) {
			// TODO: handle exception
			response = getOkResponse(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage()) ;
		}

		return ResponseEntity.ok(response) ;
	}

}
