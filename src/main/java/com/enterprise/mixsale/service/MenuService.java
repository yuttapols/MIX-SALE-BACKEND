package com.enterprise.mixsale.service;

import java.util.List;

import com.enterprise.mixsale.dto.request.MenuReqDTO;
import com.enterprise.mixsale.dto.response.MenuResDTO;
import com.enterprise.mixsale.payload.CustomerUserAttr;

public interface MenuService {

	public List<MenuResDTO> getAll();
	public List<MenuResDTO> getByRoleIdAndDefault(Long roleId);
	public void save(CustomerUserAttr userAttr, MenuReqDTO menuReq);
	public void update(CustomerUserAttr userAttr, MenuReqDTO menuReq, Long menuId);
}
