package com.model;

import com.entities.User;

public interface IModelDatabase {

	boolean register(User user);

	boolean correctUserName(String userName);

	User login(User user);

}
