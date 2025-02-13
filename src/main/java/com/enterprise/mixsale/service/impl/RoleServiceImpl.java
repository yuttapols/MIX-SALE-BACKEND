package com.enterprise.mixsale.service.impl;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.enterprise.mixsale.dto.response.MenuResDTO;
import com.enterprise.mixsale.dto.response.RoleResDTO;
import com.enterprise.mixsale.repository.MenuRoleRepository;
import com.enterprise.mixsale.repository.RoleRepository;
import com.enterprise.mixsale.service.RoleService;

@Service
public class RoleServiceImpl implements RoleService{

	@Autowired
	RoleRepository roleRepository;
	
	@Autowired
	MenuRoleRepository menuRoleRepository;
	
	@Autowired
	private ModelMapper mapper;
	
	@Override
	@Transactional(readOnly = true)
	public List<RoleResDTO> getAll() {
		// TODO Auto-generated method stub
		return mapper.map(roleRepository.findByStatusIsActive(), new TypeToken<List<RoleResDTO>>() {}.getType());
	}

	@Override
	public List<MenuResDTO> getAllMenuRole() {
		// TODO Auto-generated method stub
		return mapper.map(menuRoleRepository.findAll(), new TypeToken<List<MenuResDTO>>() {}.getType());
	}

}
