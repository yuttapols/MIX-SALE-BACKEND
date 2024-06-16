package com.enterprise.mixsale.dto.request;

import java.util.List;

import lombok.Data;

@Data
public class MenuReqDTO {

	private Long roleId;
	private String menuNameTh;
	private String menuNameEng;
	private String menuClassId;
	private String menuIconName;
	private String menuPathRouterLink;
	private String isDropdown;
	
	private List<SubMenuReqDTO> subMenu;
}
