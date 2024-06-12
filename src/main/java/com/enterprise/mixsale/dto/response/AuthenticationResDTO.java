package com.enterprise.mixsale.dto.response;

import java.sql.Timestamp;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AuthenticationResDTO {

	private Long id;
	private String userName;
	private String password;
	private Long roleId;
	private String status;
	private String createBy;
	private Timestamp createDate;
	private String updateBy;
	private Timestamp updateDate;
	
	// token
	private String accessToken;
    private String token;
}
