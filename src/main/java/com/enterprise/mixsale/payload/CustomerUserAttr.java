package com.enterprise.mixsale.payload;

import lombok.Data;

@Data
public class CustomerUserAttr {

	private String empNo;
	private String empName;
	private Long roleId;
	private String roleName;
	private String ipAddress;
}
