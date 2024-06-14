package com.enterprise.mixsale.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.enterprise.mixsale.entity.GeographiesEntity;

@Repository
public interface GeographiesRepository extends JpaRepository<GeographiesEntity, Long>{

}
