package com.main;
import java.io.IOException;
import java.sql.SQLException;

import com.view.IMainView;
import com.view.MainView;

public class Main {

	public static void main(String[] args) throws ClassNotFoundException, SQLException, IOException {
		IMainView mainView = new MainView();
		mainView.init(); 
	}

}
