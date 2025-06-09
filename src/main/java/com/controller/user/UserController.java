package com.controller.user;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import com.entities.Car;
import com.entities.Payment;
import com.entities.PaymentFilter;
import com.entities.User;
import com.model.user.IModelUserDataBase;
import com.model.user.ModelUserDataBase;

public class UserController implements IUserController {
	private IModelUserDataBase userDataBase;

	public UserController() throws ClassNotFoundException, SQLException, IOException {
		this.userDataBase = new ModelUserDataBase();
	}

	@Override
	public boolean addCar(Car c, User u) {
		if (userDataBase.addCar(c, u)) {
			return true;
		} else {
			return false;

		}
	}

	@Override
	public List<Car> getAllCars(User user) {
		List<Car> list = userDataBase.getCarList(user);
		if (list != null) {
			return list;
		} else {
			return null;
		}
	}

	@Override
	public String getUserToAddCarOwner(String string) {
		String uuid = userDataBase.getOwnerUuid(string);
		if (!uuid.isBlank()) {
			return uuid;
		} else {
			return "";
		}
	}

	@Override
	public boolean addOnwerToCar(String uuidToAddToOwner, int idCar) {
		if (userDataBase.addOwner(uuidToAddToOwner, idCar)) {
			return true;
		} else {
			return false;
		}
	}

	@Override
	public int getCarByPlate(String string) {
		int idCar = userDataBase.getCarByPlate(string);
		if (idCar > 0) {
			return idCar;
		} else {
			return -1;
		}
	}

	@Override
	public boolean deleteCar(int idCarToDelete, User user) {
		if (userDataBase.deleteCar(idCarToDelete, user)) {
			return true;
		} else {
			return false;
		}
	}

	@Override
	public boolean editCar(Car cEdited) {
		if (userDataBase.editCar(cEdited)) {
			return true;
		} else {
			return false;
		}
	}

	@Override
	public boolean addPayment(Payment p) {
		if (userDataBase.addPayment(p)) {
			return true;
		} else {
			return false;

		}
	}

	@Override
	public List<Payment> getAllPayments(String plate, List<Car> carOwnerList, PaymentFilter pF) {
		int carId = -1;

		for (Car c : carOwnerList) {
			if (c.getPlate().equalsIgnoreCase(plate)) {
				carId = c.getId_car();
			}
		}

		if (carId < 0) {
			return null;
		} else {
			List<Payment> paymentList = userDataBase.getAllPaymentsByUser(carId, pF);
			return paymentList;
		}
	}
}
