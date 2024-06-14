package com.enterprise.mixsale.dto.response;

import lombok.Data;

@Data
public class DistrictsResDTO {
	private Long id;
	private Long zipCode;
	private String name_th;
	private String name_en;
	private Long amphureId;
}
