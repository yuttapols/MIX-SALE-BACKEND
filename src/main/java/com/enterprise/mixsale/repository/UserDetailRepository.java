package com.enterprise.mixsale.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.enterprise.mixsale.entity.UserDetailEntity;

public interface UserDetailRepository extends JpaRepository<UserDetailEntity, Long>{

	@Query("select t from UserDetailEntity t where t.userId = ?1 ")
	public List<UserDetailEntity> findByUserId(Long userId);
}
