package com.enterprise.mixsale.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.enterprise.mixsale.entity.DistrictsEntity;

@Repository
public interface DistrictsRepository extends JpaRepository<DistrictsEntity, Long>{

}
