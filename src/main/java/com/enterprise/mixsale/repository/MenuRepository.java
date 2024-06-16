package com.enterprise.mixsale.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.enterprise.mixsale.entity.MenuEntity;

public interface MenuRepository extends JpaRepository<MenuEntity, Long>{

	@Query("select t from MenuEntity t where t.roleId in (?1, 0) ")
	public List<MenuEntity> findByRoleIdAndDefault(Long roleId);
}
