package com.enterprise.mixsale.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.enterprise.mixsale.entity.SubMenuEntity;

@Repository
public interface SubMenuRepository extends JpaRepository<SubMenuEntity, Long>{

	@Modifying
	@Query("delete from SubMenuEntity t where t.menuId = ?1")
	void deleteByMenuId(Long menuId);
}
