package com.enterprise.mixsale.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.enterprise.mixsale.entity.AuthenticationEntity;

@Repository
public interface AuthenticationRepository extends JpaRepository<AuthenticationEntity, Long>{

	@Query("select t from AuthenticationEntity t where t.userName =?1 and t.password = ?2")
	public AuthenticationEntity findByUserNameAndPassword(String userName, String password);
	
	@Query("select t from AuthenticationEntity t where t.userName =?1")
	Optional<AuthenticationEntity> findByUserName(String userName);
}
