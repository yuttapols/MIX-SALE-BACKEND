package com.enterprise.mixsale.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.enterprise.mixsale.entity.PrefixEntity;

@Repository
public interface PrefixRepository extends JpaRepository<PrefixEntity, Long>{

}
