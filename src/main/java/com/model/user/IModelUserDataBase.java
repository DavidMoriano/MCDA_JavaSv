package com.model.user;

import java.util.List;

import com.entities.Car;
import com.entities.Payment;
import com.entities.PaymentFilter;
import com.entities.User;

public interface IModelUserDataBase {

	boolean addCar(Car c, User u);

	List<Car> getCarList(User user);

	String getOwnerUuid(String string);

	boolean addOwner(String uuidToAddToOwner, int idCar);

	int getCarByPlate(String string);

	boolean deleteCar(int idCarToDelete, User user);

	boolean editCar(Car cEdited);

	boolean addPayment(Payment p);

	List<Payment> getAllPaymentsByUser(int carId, PaymentFilter pF);

}
