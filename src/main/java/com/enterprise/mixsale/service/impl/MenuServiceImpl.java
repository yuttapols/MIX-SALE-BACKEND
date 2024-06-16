package com.enterprise.mixsale.service.impl;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.enterprise.mixsale.dto.response.MenuDTO;
import com.enterprise.mixsale.repository.MenuRepository;
import com.enterprise.mixsale.service.MenuService;

@Service
public class MenuServiceImpl implements MenuService{

	@Autowired
	MenuRepository menuRepository;
	
	@Autowired
	private ModelMapper mapper;
	
	@Override
	public List<MenuDTO> getAll() {
		// TODO Auto-generated method stub
		
		return mapper.map(menuRepository.findAll(), new TypeToken<List<MenuDTO>>() {}.getType());
	}

}
