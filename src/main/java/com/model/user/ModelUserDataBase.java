package com.model.user;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.entities.Car;
import com.entities.Payment;
import com.entities.PaymentFilter;
import com.entities.User;
import com.model.config.DatabaseConnection;

public class ModelUserDataBase implements IModelUserDataBase {
	private Connection connection;

	public ModelUserDataBase() throws ClassNotFoundException, SQLException, IOException {
		this.connection = DatabaseConnection.getConnection();
	}

	@Override
	public boolean addCar(Car c, User u) {
		String query = "INSERT INTO cars (brand, model, plate, year) VALUES (?, ?, ?, ?)";
		String query2 = "INSERT INTO car_owners (car_id, user_uuid) VALUES (?, ?)";

		try {
			PreparedStatement ps = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
			// el statement.return... es necesario para recuperar valores que se obtienen en
			// la ejecucion de la consulta
			ps.setString(1, c.getBrand());
			ps.setString(2, c.getModel());
			ps.setString(3, c.getPlate());
			ps.setInt(4, c.getYear());

			int affectedRows = ps.executeUpdate(); // Comprobacion de la consulta
			if (affectedRows == 0) {
				return false;
			}

			ResultSet generatedKeys = ps.getGeneratedKeys(); // es necesario para obtener el id del coche, ya que es
																// autoIncrementado
			if (generatedKeys.next()) {
				int newCarId = generatedKeys.getInt(1); // 1 porque es la primera key que se obtiene

				PreparedStatement ps1 = connection.prepareStatement(query2);
				ps1.setInt(1, newCarId);
				ps1.setString(2, u.getuUID());
				int res = ps1.executeUpdate();

				return res > 0;
			} else {
				return false;
			}

		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public List<Car> getCarList(User user) {
		String query = "SELECT id, brand, model, plate, year FROM cars where id in (SELECT car_id FROM car_owners WHERE user_uuid = (select uuid from users where id = ?))";

		List<Car> list = new ArrayList<>();
		try {
			PreparedStatement ps = connection.prepareStatement(query);
			ps.setInt(1, user.getId_user());
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				Car c = new Car();
				c.setId_car(rs.getInt(1));
				c.setBrand(rs.getString(2));
				c.setModel(rs.getString(3));
				c.setPlate(rs.getString(4));
				c.setYear(rs.getInt(5));
				list.add(c);
			}

			rs.close();
			ps.close();
			return list;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public String getOwnerUuid(String name) {
		String queryCount = "SELECT count(*) FROM users WHERE name = ?";
		String queryUuid = "SELECT uuid FROM users WHERE name = ?";
		String uUIDUser = "";
		try {
			PreparedStatement psCount = connection.prepareStatement(queryCount);
			psCount.setString(1, name);
			ResultSet rsCount = psCount.executeQuery();

			if (rsCount.next()) {
				int count = rsCount.getInt(1);
				if (count == 1) {
					PreparedStatement psUuid = connection.prepareStatement(queryUuid);
					psUuid.setString(1, name);
					ResultSet rsUuid = psUuid.executeQuery();
					if (rsUuid.next()) {
						uUIDUser = rsUuid.getString("uuid");
					}
					rsUuid.close();
					psUuid.close();
				}
			}
			rsCount.close();
			psCount.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return uUIDUser;
	}

	@Override
	public boolean addOwner(String uuidToAddToOwner, int idCar) {
		String query = "INSERT INTO car_owners (car_id, user_uuid) values (?, ?)";
		try {
			PreparedStatement ps = connection.prepareStatement(query);
			ps.setInt(1, idCar);
			ps.setString(2, uuidToAddToOwner);
			ps.execute();
		} catch (Exception e) {
			return false;
		}
		return true;
	}

	@Override
	public int getCarByPlate(String string) {
		String query = "SELECT id FROM cars WHERE plate like ?";
		int idCar = -1;
		try {
			PreparedStatement ps = connection.prepareStatement(query);
			ps.setString(1, string);

			ResultSet rs = ps.executeQuery();

			if (rs.next()) {
				idCar = rs.getInt(1);
			}
		} catch (Exception e) {
			return idCar;
		}
		return idCar;
	}

	@Override
	public boolean deleteCar(int idCarToDelete, User user) {
		String query1 = "DELETE FROM car_owners WHERE car_id = ? AND user_uuid = ?";
		String query2 = "DELETE FROM cars WHERE id = ?";

		try {
			PreparedStatement ps1 = connection.prepareStatement(query1);
			ps1.setInt(1, idCarToDelete);
			ps1.setString(2, user.getuUID());
			int affectedRows1 = ps1.executeUpdate();// Comprueba si se ha eliminado el uuid del car_owners
			ps1.close();

			if (affectedRows1 > 0) {
				PreparedStatement ps2 = connection.prepareStatement(query2);
				ps2.setInt(1, idCarToDelete);
				int affectedRows2 = ps2.executeUpdate();
				ps2.close();

				return affectedRows2 > 0;
			} else {
				return false;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public boolean editCar(Car cEdited) {
		String query = "UPDATE cars SET brand = ?, model = ?, plate = ?, year = ? WHERE id = ?";

		try {
			PreparedStatement ps = connection.prepareStatement(query);
			ps.setString(1, cEdited.getBrand());
			ps.setString(2, cEdited.getModel());
			ps.setString(3, cEdited.getPlate());
			ps.setInt(4, cEdited.getYear());
			ps.setInt(5, cEdited.getId_car());

			int affectedRows = ps.executeUpdate();
			ps.close();

			return affectedRows > 0;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	public boolean addPayment(Payment payment) {
		String query = "INSERT INTO payments (car_id, type, date, amount, description, mileage, user_uuid) VALUES (?, ?, ?, ?, ?, ?, ?)";

		try {
			PreparedStatement ps = connection.prepareStatement(query);
			ps.setInt(1, payment.getId_car());
			ps.setString(2, payment.getType());
			ps.setDate(3, java.sql.Date.valueOf(payment.getDate()));
			ps.setFloat(4, payment.getAmount());
			ps.setString(5, payment.getDescription());
			ps.setInt(6, payment.getMileage());
			ps.setString(7, payment.getUserUuid());

			int rowsInserted = ps.executeUpdate();
			ps.close();
			return rowsInserted > 0;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public List<Payment> getAllPaymentsByUser(int carId, PaymentFilter pF) {
	    List<Payment> payments = new ArrayList<>();
	    
	    StringBuilder sql = new StringBuilder("SELECT id, type, date, car_id, amount, description, mileage, user_uuid "
	                                        + "FROM payments WHERE car_id = ?"); //query dinámico, ya que no siempre es el mismo
	    
	    List<Object> parameters = new ArrayList<>(); //Necesario para construir el query
	    parameters.add(carId); //Añade el carId porque es obligatorio

	    if (pF != null) {
	        if (pF.hasType()) {
	            sql.append(" AND type = ?");
	            parameters.add(pF.getType());
	        }
	        if (pF.hasDate()) {
	            sql.append(" AND date = ?");
	            parameters.add(pF.getDate());
	        }
	        if (pF.hasMileage()) {
	            sql.append(" AND mileage = ?");
	            parameters.add(pF.getMileage());
	        }
	    }

	    try (PreparedStatement ps = connection.prepareStatement(sql.toString())) { //Recorre el array de parameters y va añadiendo valores.
	        for (int i = 0; i < parameters.size(); i++) {
	            ps.setObject(i + 1, parameters.get(i)); //i + 1 es porque empieza por 1 el PreparedStatement
	        }

	        try (ResultSet rs = ps.executeQuery()) {
	            while (rs.next()) {
	                Payment p = new Payment();
	                p.setId(rs.getInt("id"));
	                p.setId_car(rs.getInt("car_id"));
	                p.setType(rs.getString("type"));
	                p.setDate(rs.getString("date"));
	                p.setAmount(rs.getFloat("amount"));
	                p.setDescription(rs.getString("description"));
	                p.setUserUuid(rs.getString("user_uuid"));
	                p.setMileage(rs.getInt("mileage"));
	                payments.add(p);
	            }
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }

	    return payments;
	}


}
