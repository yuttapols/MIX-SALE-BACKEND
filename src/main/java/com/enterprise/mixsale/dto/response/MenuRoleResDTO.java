package com.enterprise.mixsale.dto.response;

import java.sql.Timestamp;

import lombok.Data;

@Data
public class MenuRoleResDTO {

	private Long id;
	private String menuRoleNameEng;
	private String menuR;
	private String status;
	private String createBy;
	private Timestamp createDate;
	private String updateBy;
	private Timestamp updateDate;
}
