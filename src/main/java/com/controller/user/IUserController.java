package com.controller.user;

import java.util.List;

import com.entities.Car;
import com.entities.Payment;
import com.entities.PaymentFilter;
import com.entities.User;

public interface IUserController {

	boolean addCar(Car c, User u);

	List<Car> getAllCars(User user);

	String getUserToAddCarOwner(String string);

	boolean addOnwerToCar(String uuidToAddToOwner, int idCar);

	int getCarByPlate(String string);

	boolean deleteCar(int idCarToDelete, User user);

	boolean editCar(Car cEdited);

	boolean addPayment(Payment p);

	List<Payment> getAllPayments(String plate, List<Car> carOwnerList, PaymentFilter pF);

}
