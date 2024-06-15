package com.enterprise.mixsale.dto.response;

import java.sql.Timestamp;

import com.enterprise.mixsale.entity.RoleEntity;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AuthenticationResDTO {

	private Long id;
	private String userName;
	private String password;
	private String status;
	private String createBy;
	private Timestamp createDate;
	private String updateBy;
	private Timestamp updateDate;
	private RoleEntity role;
	
	// token
	private String accessToken;
    private String token;
    
    //user
    private UserDetailResDTO userDetail;
}
