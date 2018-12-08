package com.apap.silabor.service;

import com.apap.silabor.model.UserRoleModel;

public interface UserRoleService {
	UserRoleModel addUser(UserRoleModel user);
	public String encrypt(String password);
	UserRoleModel findUserByUsername(String username);
	boolean isMatch(String passwordLama, String hashedPassword);
}
