package com.schoolproject.dao;

import com.schoolproject.pojo.UserBean;

public interface UserDao {
	void addUser();
	UserBean login(UserBean userBean);
}
