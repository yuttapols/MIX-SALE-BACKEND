package com.enterprise.mixsale.service;

import java.util.List;

import com.enterprise.mixsale.dto.response.MenuResDTO;
import com.enterprise.mixsale.dto.response.RoleResDTO;

public interface RoleService {

	public List<RoleResDTO> getAll();
	
	public List<MenuResDTO> getAllMenuRole();
}
