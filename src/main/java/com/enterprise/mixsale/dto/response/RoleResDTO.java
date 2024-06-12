package com.enterprise.mixsale.dto.response;

import java.sql.Timestamp;

import lombok.Data;

@Data
public class RoleResDTO {

	private Long id;
	private String roleName;
	private String roleDesc;
	private String status;
	private String createBy;
	private Timestamp createDate;
	private String updateBy;
	private Timestamp updateDate;
}
