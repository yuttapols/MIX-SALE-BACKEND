package com.enterprise.mixsale.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.ObjectUtils;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.enterprise.mixsale.dto.request.MenuReqDTO;
import com.enterprise.mixsale.dto.request.SubMenuReqDTO;
import com.enterprise.mixsale.dto.response.MenuResDTO;
import com.enterprise.mixsale.entity.MenuEntity;
import com.enterprise.mixsale.entity.SubMenuEntity;
import com.enterprise.mixsale.payload.CustomerUserAttr;
import com.enterprise.mixsale.repository.MenuRepository;
import com.enterprise.mixsale.repository.SubMenuRepository;
import com.enterprise.mixsale.service.MenuService;
import com.enterprise.mixsale.util.Constants;
import com.enterprise.mixsale.util.DateUtil;

@Service
public class MenuServiceImpl implements MenuService{

	@Autowired
	MenuRepository menuRepository;
	
	@Autowired
	SubMenuRepository subMenuRepository;
	 
	@Autowired
	private ModelMapper mapper;
	
	@Override
	@Transactional(readOnly = true)
	public List<MenuResDTO> getAll() {
		// TODO Auto-generated method stub
		
		return mapper.map(menuRepository.findAll(), new TypeToken<List<MenuResDTO>>() {}.getType());
	}

	@Override
	@Transactional(readOnly = true)
	public List<MenuResDTO> getByRoleIdAndDefault(Long roleId) {
		// TODO Auto-generated method stub
		return mapper.map(menuRepository.findByRoleIdAndDefault(roleId), new TypeToken<List<MenuResDTO>>() {}.getType());
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void save(CustomerUserAttr userAttr, MenuReqDTO menuReq) {
		// TODO Auto-generated method stub
		
		if(ObjectUtils.isNotEmpty(menuReq)) {
			
			MenuEntity menuEntity = MenuEntity.builder()
			.menuNameEng(menuReq.getMenuNameEng())
			.menuNameTh(menuReq.getMenuNameTh())
			.menuClassId(menuReq.getMenuClassId())
			.menuIconName(menuReq.getMenuIconName())
			.menuPathRouterLink(menuReq.getMenuPathRouterLink())
			.isDropdown(menuReq.getIsDropdown())
			.roleId(menuReq.getRoleId())
			.status(Constants.STATUS_NORMAL)
			.createBy(userAttr.getCustomerNo())
			.createDate(DateUtil.createTimestmapNow()).build();
			
			menuEntity = menuRepository.save(menuEntity);
			
			if(ObjectUtils.isNotEmpty(menuEntity)) {
				if(CollectionUtils.isNotEmpty(menuReq.getSubMenu())) {
					List<SubMenuEntity> subMenuList = new ArrayList<>();
					for(SubMenuReqDTO subMenu : menuReq.getSubMenu()) {
						SubMenuEntity subMenuEntity = SubMenuEntity.builder()
								.menuNameEng(subMenu.getMenuNameEng())
								.menuNameTh(subMenu.getMenuNameTh())
								.menuIconName(subMenu.getMenuIconName())
								.menuPathRouterLink(subMenu.getMenuPathRouterLink())
								.menuId(menuEntity.getId())
								.status(Constants.STATUS_NORMAL)
								.createBy(userAttr.getCustomerNo())
								.createDate(DateUtil.createTimestmapNow())
								.build();

						subMenuList.add(subMenuEntity);
					}
					subMenuRepository.saveAll(subMenuList);
				}
			}
			
		}
		
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void update(CustomerUserAttr userAttr, MenuReqDTO menuReq, Long menuId) {
		// TODO Auto-generated method stub
		Optional<MenuEntity> menuEntityOpt = menuRepository.findById(menuId);
		if(menuEntityOpt.isPresent()) {
			MenuEntity menuEntity = menuEntityOpt.get();
			menuEntity.setMenuNameEng(menuReq.getMenuNameEng());
			menuEntity.setMenuNameTh(menuReq.getMenuNameTh());
			menuEntity.setMenuClassId(menuReq.getMenuClassId());
			menuEntity.setMenuIconName(menuReq.getMenuIconName());
			menuEntity.setMenuPathRouterLink(menuReq.getMenuPathRouterLink());
			menuEntity.setIsDropdown(menuReq.getIsDropdown());
			menuEntity.setRoleId(menuReq.getRoleId());
			menuEntity.setUpdateBy(userAttr.getCustomerNo());
			menuEntity.setUpdateDate(DateUtil.createTimestmapNow());
			menuEntity = menuRepository.save(menuEntity);

			subMenuRepository.deleteByMenuId(menuId);
			if(ObjectUtils.isNotEmpty(menuEntity)) {
				if(CollectionUtils.isNotEmpty(menuReq.getSubMenu())) {
					List<SubMenuEntity> subMenuList = new ArrayList<>();
					for(SubMenuReqDTO subMenu : menuReq.getSubMenu()) {
						SubMenuEntity subMenuEntity = SubMenuEntity.builder()
								.menuNameEng(subMenu.getMenuNameEng())
								.menuNameTh(subMenu.getMenuNameTh())
								.menuIconName(subMenu.getMenuIconName())
								.menuPathRouterLink(subMenu.getMenuPathRouterLink())
								.menuId(menuEntity.getId())
								.status(Constants.STATUS_NORMAL)
								.createBy(userAttr.getCustomerNo())
								.createDate(DateUtil.createTimestmapNow())
								.build();

						subMenuList.add(subMenuEntity);
					}
					subMenuRepository.saveAll(subMenuList);
				}
			}
		}
	}

}
