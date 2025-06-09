package com.controller;

import com.entities.User;

public interface IMainController {

	boolean register(User user);

	boolean validUserName(String userName);

	User login(User user);

}
