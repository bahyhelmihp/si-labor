package com.apap.silabor.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.apap.silabor.model.UserRoleModel;

@Repository
public interface UserRoleDb extends JpaRepository<UserRoleModel, Long> {
	UserRoleModel findByUsername(String username);
}
