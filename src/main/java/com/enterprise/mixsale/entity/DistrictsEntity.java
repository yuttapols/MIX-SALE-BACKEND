package com.enterprise.mixsale.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name = "districts")
public class DistrictsEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;
	
	@Column(name = "zip_code")
	private Long zipCode;
	
	@Column(name = "name_th")
	private String name_th;
	
	@Column(name = "name_en")
	private String name_en;
	
	@Column(name = "amphure_id")
	private Long amphureId;
	
}
