package com.view.user;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import com.controller.user.IUserController;
import com.controller.user.UserController;
import com.entities.Car;
import com.entities.Payment;
import com.entities.PaymentFilter;
import com.entities.User;
import com.utils.TerminalUtils;

public class UserView implements IUserView {
	private IUserController userController;

	public UserView() throws ClassNotFoundException, SQLException, IOException {
		this.userController = new UserController();
	}

	@Override
	public void init(User user) {
		int option = -1;

		do {
			option = menu(user);

			switch (option) {
			case 0:
				TerminalUtils.out("Saliendo al Inicio\n");
				break;
			case 1: // Añadir coche
				String result = addCar(user);
				TerminalUtils.out(result + "\n");
				break;
			case 2: // Añadir propietario
				String result1 = addOwner(user);
				TerminalUtils.out(result1 + "\n");
				break;
			case 3: // Ver coches por usuario
				showCar(user);
				break;
			case 4: // Editar el coche de usuario
				String result2 = editCar(user);
				TerminalUtils.out(result2 + "\n");
				break;
			case 5: // Eliminar el coche del usuario
				String result3 = deleteCar(user);
				TerminalUtils.out(result3 + "\n");
				break;
			case 6: // Insertar gasto
				String result4 = insertPayment(user);
				TerminalUtils.out(result4 + "\n");
				break;
			case 7: // Lista de gastos
				paymentView(user);
				break;
			default:
				TerminalUtils.out("Opción introducida incorrecta");
				break;
			}
		} while (option != 0);
	}

	private String insertPayment(User user) {
	    List<Car> carList = userController.getAllCars(user);
	    Payment p = menuPayments(user, carList);

	    // Math.abs() sirve para no comparar floats con ==
	    if (Math.abs(p.getAmount()) > 0.0001f) {
	        boolean insertado = userController.addPayment(p);
	        return insertado ? "Gasto añadido correctamente" : "Error al añadir gasto";
	    } else {
	        return "Volviendo al inicio";
	    }
	}


	private Payment menuPayments(User user, List<Car> carList) {
	    int option = -1;
	    Payment p = new Payment();

	    if (carList.isEmpty()) {
	        TerminalUtils.out("Lista de coches vacía");
	        return p;
	    }

	    do {
	        TerminalUtils.out("INTRODUCIR GASTOS");
	        TerminalUtils.out("=================");
	        TerminalUtils.out("0. Salir");
	        TerminalUtils.out("1. Echar gasolina");
	        TerminalUtils.out("2. Revisión del coche");
	        TerminalUtils.out("3. ITV");
	        TerminalUtils.out("4. Cambio de aceite");
	        TerminalUtils.out("5. Otros");

	        TerminalUtils.out("Introduce el tipo de gasto");
	        option = TerminalUtils.getInt();

	        switch (option) {
	            case 0:
	                TerminalUtils.out("Saliendo del menú de gastos\n");
	                break;
	            case 1:
	                p.setType("Echar Gasolina");
	                break;
	            case 2:
	                p.setType("Revisión del coche");
	                break;
	            case 3:
	                p.setType("ITV");
	                break;
	            case 4:
	                p.setType("Cambio de aceite");
	                break;
	            case 5:
	                TerminalUtils.out("Introduce el nombre del gasto");
	                p.setType(TerminalUtils.getString());
	                break;
	            default:
	                TerminalUtils.out("Error en la opción introducida");
	                continue;
	        }

	        if (option != 0) {
	            TerminalUtils.out("LISTA DE COCHES DEL USUARIO " + user.getName());
	            TerminalUtils.out("==========================================");
	            TerminalUtils.out(Car.getHeader());
	            for (Car c : carList) {
	                TerminalUtils.out(c.toString());
	            }

	            boolean idCorrecto = false;
	            while (!idCorrecto) {
	                TerminalUtils.out("Introduce a qué coche se le realiza el pago (ID):");
	                int idCarToPay = TerminalUtils.getInt();
	                for (Car c : carList) {
	                    if (c.getId_car() == idCarToPay) {
	                        p.setId_car(idCarToPay);
	                        idCorrecto = true;
	                        break;
	                    }
	                }
	                if (!idCorrecto) {
	                    TerminalUtils.out("ID de coche no válido. Intente de nuevo.");
	                }
	            }

	            p.setUserUuid(user.getuUID());

	            float amount;
	            do {
	                TerminalUtils.out("Introduce el coste:");
	                amount = TerminalUtils.getFloat();
	                if (amount <= 0) {
	                    TerminalUtils.out("El coste debe ser mayor que 0.");
	                }
	            } while (amount <= 0);
	            p.setAmount(amount);

	            TerminalUtils.out("Introduce los kilómetros del coche:");
	            p.setMileage(TerminalUtils.getInt());

	            String fecha = "";
	            TerminalUtils.out("Introduce la fecha de hoy (YYYY-MM-DD):");
	            while (true) {
	                fecha = TerminalUtils.getString();
	                if (TerminalUtils.validarFormatoFecha(fecha)) {
	                    p.setDate(fecha);
	                    break;
	                } else {
	                    TerminalUtils.out("Formato de fecha incorrecto. Intenta de nuevo.");
	                }
	            }

	            TerminalUtils.out("¿Desea introducir algún comentario? (S/N)");
	            String respond = TerminalUtils.getString();
	            if (respond.equalsIgnoreCase("S")) {
	                TerminalUtils.out("Introduce el comentario:");
	                p.setDescription(TerminalUtils.getString());
	            } else {
	                p.setDescription("");
	            }

	            break;
	        }

	    } while (option != 0);

	    return p;
	}


	private String deleteCar(User user) {
		List<Car> carList = userController.getAllCars(user);
		TerminalUtils.out("LISTA DE COCHES DEL USUARIO " + user.getName());
		TerminalUtils.out("==============================================");
		TerminalUtils.out(Car.getHeader());
		for (Car car : carList) {
			TerminalUtils.out(car.toString());
		}

		TerminalUtils.out("\nIntroduce el coche a eliminar");
		int idCarToDelete = TerminalUtils.getInt();

		if (userController.deleteCar(idCarToDelete, user)) {
			return "Coche eliminado correctamente";
		} else {
			return "Error al eliminar el coche";
		}

	}

	private String editCar(User user) {
		List<Car> carList = userController.getAllCars(user);
		TerminalUtils.out("LISTA DE COCHES DEL USUARIO " + user.getName());
		TerminalUtils.out("==============================================");
		TerminalUtils.out(Car.getHeader());
		for (Car car : carList) {
			TerminalUtils.out(car.toString());
		}

		TerminalUtils.out("Introduce el coche a editar: ");
		int idCar = TerminalUtils.getInt();

		Car carToEdit = new Car();
		for (Car car : carList) {
			if (car.getId_car() == idCar) {
				carToEdit.setId_car(car.getId_car());
				carToEdit.setBrand(car.getBrand());
				carToEdit.setModel(car.getModel());
				carToEdit.setPlate(car.getPlate());
				carToEdit.setYear(car.getYear());
			}
		}

		Car cEdited = menuToEdit(carToEdit);
		if (userController.editCar(cEdited)) {
			return "Coche editado correctamente";
		} else {
			return "Error al editar un coche";
		}

	}

	private String addOwner(User user) {
		TerminalUtils.out("Introduce el nombre del usuario que quiere añadir de propietario : ");
		String uuidUserToAdd = userController.getUserToAddCarOwner(TerminalUtils.getString());
		TerminalUtils.out("Introduce la matrícula del coche al que quiere introducir el nuevo propietario : ");
		int carIdByPlate = userController.getCarByPlate(TerminalUtils.getString());
		if ((!uuidUserToAdd.isBlank()) && (carIdByPlate > 0)) {
			if (userController.addOnwerToCar(uuidUserToAdd, carIdByPlate)) {
				return "Propieatrio añadido";
			} else {
				return "Error al añadir al propietario";
			}
		} else {
			return "Error al encontrar al propietario";
		}
	}

	private void showCar(User user) {
		List<Car> carList = userController.getAllCars(user);
		if (carList == null) {
			TerminalUtils.out("Lista de coches vacía");
		} else {
			TerminalUtils.out("LISTA DE COCHES DEL USUARIO " + user.getName());
			TerminalUtils.out("==========================================");
			TerminalUtils.out(Car.getHeader());
			for (Car c : carList) {
				TerminalUtils.out(c.toString());
			}
		}
	}

	private String addCar(User user) {
		TerminalUtils.out("CREACIÓN DE UN COCHE");
		TerminalUtils.out("====================");
		Car c = new Car();
		TerminalUtils.out("Introduce el año de fabricación : ");
		c.setYear(TerminalUtils.getInt());
		TerminalUtils.out("Introduce la marca del coche : ");
		c.setBrand(TerminalUtils.getString());
		TerminalUtils.out("Introduce el model de coche : ");
		c.setModel(TerminalUtils.getString());
		TerminalUtils.out("Introduce la matrícula del coche : (AAA1111)");
		c.setPlate(TerminalUtils.getString());

		if(userController.addCar(c, user)) {
			return "Coche añadido correctamente";
		} else {
			return "Error al añadir coche";
		}
	}

	private int menu(User user) {
		TerminalUtils.out("\nVISTA DE USUARIO " + user.getName());
		TerminalUtils.out("=====================================");
		TerminalUtils.out("0. Salir");
		TerminalUtils.out("1. Crear un coche"); // Se hace propietario del mismo
		TerminalUtils.out("2. Añadir propietaros al coche"); // Se necesita el UUID de los usuarios a añadir
		TerminalUtils.out("3. Ver información de los coches");
		TerminalUtils.out("4. Editar información de los coches");
		TerminalUtils.out("5. Eliminar coches");
		TerminalUtils.out("6. Añadir gastos del coche"); // Entablado %-10s, filtrando por año, fecha y kilometraje.
		TerminalUtils.out("7. Lista de gastos"); // Para poder ver los coches en función del UUID.
		TerminalUtils.out("=====================================\n");

		TerminalUtils.out("Introduce la opción requerida: ");
		int opcion = TerminalUtils.getInt();
		return opcion;
	}

	private Car menuToEdit(Car car) {
		int option = -1;
		TerminalUtils.out("MENÚ DE CAMPOS A EDITAR");
		TerminalUtils.out("1. Marca");
		TerminalUtils.out("2. Modelo");
		TerminalUtils.out("3. Matrícula");
		TerminalUtils.out("4. Año de matriculación");
		TerminalUtils.out("Introduce la opción que quiera: ");
		option = TerminalUtils.getInt();
		switch (option) {
		case 1:
			TerminalUtils.out("Antigua marca : " + car.getBrand());
			TerminalUtils.out("Introduce la nueva marca : ");
			String newBrand = TerminalUtils.getString();
			car.setBrand(newBrand);
			break;
		case 2:
			TerminalUtils.out("Antiguo modelo : " + car.getModel());
			TerminalUtils.out("Introduce el nuevo modelo : ");
			String newModel = TerminalUtils.getString();
			car.setModel(newModel);
			break;
		case 3:
			TerminalUtils.out("Antigua matrícula : " + car.getPlate());
			TerminalUtils.out("Introduce la nueva matrícula : ");
			String newPlate = TerminalUtils.getString();
			car.setPlate(newPlate);
			break;
		case 4:
			TerminalUtils.out("Antigua año de matriculación : " + car.getYear());
			TerminalUtils.out("Introduce el nuevo año de matriculación : ");
			int newYear = TerminalUtils.getInt();
			car.setYear(newYear);
			break;

		default:
			TerminalUtils.out("Opción incorrecta");
			break;
		}

		return car;
	}

	public void paymentView(User user) {
		int valid = -1;
		PaymentFilter pF = new PaymentFilter();
		do {

			TerminalUtils.out("\nLista de gastos");
			TerminalUtils.out("=====================\n");

			List<Car> carOwnerList = userController.getAllCars(user);

			if (carOwnerList.isEmpty()) {
				TerminalUtils.out("Este usuario no tiene coches");
			} else {

				TerminalUtils.out("Coches del propietario " + user.getName());
				TerminalUtils.out("=========================================\n");
				TerminalUtils.out(Car.getHeader());
				for (Car c : carOwnerList) {
					TerminalUtils.out(c.toString());
				}
				String plate = "";
				boolean plateExists = false;
				TerminalUtils.out("Introduce la matrícula del coche para el que quiere ver los gastos");

				while (!plateExists) {
					plate = TerminalUtils.getString().trim(); // .trim() elimina espacios en blanco

					if (plate.isEmpty()) {
						TerminalUtils.out("La matrícula no puede estar vacía. Inténtalo de nuevo.");
						continue;
					}

					plateExists = false;
					for (Car car : carOwnerList) {
						if (car.getPlate().equalsIgnoreCase(plate)) {
							plateExists = true;
							break;
						}
					}

					if (!plateExists) {
						TerminalUtils.out("La matrícula introducida no existe. Por favor, inténtalo de nuevo.");
					}
				}

				menuFilterPayments();

				TerminalUtils.out("Filtros de búsqueda");
				TerminalUtils.out("===================");
				TerminalUtils.out("Introduce el nombre de gasto para buscar ((Si no quiere filtrar presione ENTER)");
				String typeFilterPayments = TerminalUtils.getString();

				TerminalUtils.out("Introduce la fecha para buscar (Si no quiere filtrar presione Enter)");
				String dateFilterPayments = TerminalUtils.getString();

				TerminalUtils.out("Introduce el kilometraje del coche para buscar (ENTER para omitir):");
				String mileageStr = TerminalUtils.getString();
				Integer mileageFilterPayments = null;
				try {
					mileageFilterPayments = mileageStr.isBlank() ? null : Integer.parseInt(mileageStr);
				} catch (NumberFormatException e) {
					TerminalUtils.out("Kilometraje no válido. Ignorando filtro.");
				}
				pF.setDate(dateFilterPayments);
				pF.setMileage(mileageFilterPayments);
				pF.setType(typeFilterPayments);

				List<Payment> paymentsList = userController.getAllPayments(plate, carOwnerList, pF);

				if (paymentsList == null) {
					TerminalUtils.out("Matrícula incorrecta");
				} else if (paymentsList.isEmpty()) {
					TerminalUtils.out("Lista de gastos vacía");

				} else {

					TerminalUtils.out("Gastos del coche ");
					TerminalUtils.out(Payment.getHeader());
					for (Payment p : paymentsList) {
						TerminalUtils.out(p.toString());

					}
				}
				String respond = "";
				int salir = -1;
				while ((respond.isEmpty()) && (salir == -1)) {
					TerminalUtils.out("Desea salir? (S\\N)");
					respond = TerminalUtils.getString();
					if (respond.equalsIgnoreCase("s")) {
						salir = 1;
						valid = 1;
					} else if (respond.equalsIgnoreCase("n")) {
						valid = 0;
					}
				}
				
			}

		} while (valid == 0);
	}

	private void menuFilterPayments() {
		TerminalUtils.out("Tipos de categorías");
		TerminalUtils.out("Echar gasolina, \nRevisión del coche, \nITV, \nCambio de aceite, \nOtros");
	}
}
