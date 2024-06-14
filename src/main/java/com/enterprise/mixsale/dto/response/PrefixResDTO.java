package com.enterprise.mixsale.dto.response;

import java.sql.Timestamp;

import lombok.Data;

@Data
public class PrefixResDTO {

	private Long id;
	private String prefixNameTh;
	private String prefixNameEng;
	private String prefixDesc;
	private String status;
	private String createBy;
	private Timestamp createDate;
	private String updateBy;
	private Timestamp updateDate;
}
