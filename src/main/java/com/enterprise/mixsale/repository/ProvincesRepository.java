package com.enterprise.mixsale.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.enterprise.mixsale.entity.ProvincesEntity;

@Repository
public interface ProvincesRepository extends JpaRepository<ProvincesEntity, Long>{

}
