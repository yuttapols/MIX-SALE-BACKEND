package com.enterprise.mixsale.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.enterprise.mixsale.entity.AmphuresEntity;

@Repository
public interface AmphuresRepository extends JpaRepository<AmphuresEntity, Long>{

}
