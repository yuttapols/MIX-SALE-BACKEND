package com.enterprise.mixsale.dto.response;

import java.sql.Timestamp;

import lombok.Data;

@Data
public class SubMenuDTO {

	private Long id;
	private Long menuId;
	private String menuNameTh;
	private String menuNameEng;
	private String menuIconName;
	private String menuPathRouterLink;
	private String status;
	private String createBy;
	private Timestamp createDate;
	private String updateBy;
	private Timestamp updateDate;
}
