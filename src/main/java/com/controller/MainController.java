package com.controller;

import java.io.IOException;
import java.sql.SQLException;

import com.entities.User;
import com.hassPassword.SecurePassword;
import com.model.IModelDatabase;
import com.model.ModelDataBase;

public class MainController implements IMainController {
	private IModelDatabase modelDataBase;

	public MainController() throws ClassNotFoundException, SQLException, IOException {
		this.modelDataBase = new ModelDataBase();
	}

	@Override
	public boolean validUserName(String userName) {
		if (modelDataBase.correctUserName(userName)) {
			return true;
		} else {
			return false;
		}
	}

	@Override
	public boolean register(User user) {
		if (modelDataBase.register(user)) {
			return true;
		} else {
			return false;
		}
	}

	@Override
	public User login(User user) {
		User userDB = modelDataBase.login(user);
		if (SecurePassword.checkPassword(user.getPassword(), userDB.getPassword())) {
			return userDB;
		} else {
			return null;
		}
	}
}
