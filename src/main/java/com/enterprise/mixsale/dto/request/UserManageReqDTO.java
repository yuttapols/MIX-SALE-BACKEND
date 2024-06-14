package com.enterprise.mixsale.dto.request;

import lombok.Data;

@Data
public class UserManageReqDTO {

	private String userName;
	private String password;
	private Long roleId;
	private Long prefixId;
	private Long departmentId;
	private String fristName;
	private String middleName;
	private String lastName;
	private String nickName;
	private String email;
	private String telephone;
	private String houseNo;
	private String villageNo;
	private String alley;
	private String lane;
	private String road;
	private Long geographiesId;
	private Long districts;
	private Long amphuresId;
	private Long provincesId;
}
