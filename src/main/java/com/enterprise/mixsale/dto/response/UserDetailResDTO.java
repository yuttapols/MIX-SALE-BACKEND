package com.enterprise.mixsale.dto.response;

import java.sql.Timestamp;

import com.enterprise.mixsale.entity.PrefixEntity;

import lombok.Data;

@Data
public class UserDetailResDTO {

	private Long id;
	private Long userId;
	private String empNo;
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
	private String amphuresId;
	private String provincesId;
	private String status;
	private String createBy;
	private Timestamp createDate;
	private String updateBy;
	private Timestamp updateDate;
	
	//prefix
	private PrefixEntity prefix;
}
