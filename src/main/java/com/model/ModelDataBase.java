package com.model;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.entities.User;
import com.model.config.DatabaseConnection;

public class ModelDataBase implements IModelDatabase {
	private Connection connection;

	public ModelDataBase() throws ClassNotFoundException, SQLException, IOException {
		this.connection = DatabaseConnection.getConnection();
	}

	@Override
	public boolean register(User user) {

		try {
			String query = "INSERT INTO users (name, password, uuid) value (?, ?, ?)";
			PreparedStatement ps1 = connection.prepareStatement(query);

			ps1.setString(1, user.getName());
			ps1.setString(2, user.getPassword());
			ps1.setString(3, user.getuUID());

			ps1.executeUpdate();
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	@Override
	public boolean correctUserName(String userName) {
		String query = "SELECT count(*) FROM users where name like ?"; 

		try {
			PreparedStatement ps2 = connection.prepareStatement(query);
			ps2.setString(1, userName);

			ResultSet rs = ps2.executeQuery();
			if (rs.next()) {
				int count = rs.getInt(1);
				if (count == 0) {
					return true;
				}
			}
			rs.close();
			ps2.close();
			return false;
		} catch (Exception e) {
			return false;
		}

	}

	@Override
	public User login(User user) {
		String query = "SELECT id, name, password, uuid FROM users WHERE name like ?";
		try {
			PreparedStatement ps2 = connection.prepareStatement(query);

			ps2.setString(1, user.getName());

			ResultSet rs = ps2.executeQuery();

			if (rs.next()) {
				int idDb = rs.getInt(1);
				String nameDb = rs.getString(2);
				String password = rs.getString(3);
				String uuid = rs.getString(4);
				User userDB = new User();

				userDB.setId_user(idDb);
				userDB.setName(nameDb);
				userDB.setPassword(password);
				userDB.setuUID(uuid);

				return userDB;
			} else {
				return null;
			}
		} catch (Exception e) {
			return null;
		}

	}
}
