package com.view;

import java.io.IOException;
import java.sql.SQLException;
import java.util.UUID;

import com.controller.IMainController;
import com.controller.MainController;
import com.entities.User;
import com.hassPassword.SecurePassword;
import com.utils.TerminalUtils;
import com.view.user.IUserView;
import com.view.user.UserView;

public class MainView implements IMainView {
	private IMainController mainController;
	private IUserView userView;

	public MainView() throws ClassNotFoundException, SQLException, IOException {
		this.mainController = new MainController();
		this.userView = new UserView();
	}

	@Override
	public void init() {
		int option;

		do {
			option = menu();

			switch (option) {
			case 0:
				TerminalUtils.out("Saliendo del programa...");
				break;
			case 1:
				String result = register();
				TerminalUtils.out(result + "\n");
				break;
			case 2:
				User userToLog = login();
				if (userToLog != null) {
					userView.init(userToLog);
					break;
				} else {
					break;
				}
			default:
				TerminalUtils.out("Error en la opción introducida");
				break;
			}
		} while (option != 0);
	}

	private int menu() {
		TerminalUtils.out("PORTAL DE COCHES");
		TerminalUtils.out("=====================\n");
		TerminalUtils.out("0. Salir");
		TerminalUtils.out("1. Registrar usuario");
		TerminalUtils.out("2. Login usuario");
		TerminalUtils.out("=====================\n");

		int opcion;
		do {
			TerminalUtils.out("Introduce la opción requerida: ");
			opcion = TerminalUtils.getInt();
			if (opcion < 0 || opcion > 2) {
				TerminalUtils.out("Opción inválida. Introduce un número entre 0 y 2.");
			}
		} while (opcion < 0 || opcion > 2);

		return opcion;
	}

	private String register() {
		User user = new User();
		TerminalUtils.out("Registrar nuevo usuario");
		TerminalUtils.out("=======================\n");

		while (true) {
			TerminalUtils.out("Introduce el nombre de usuario: ");
			String userName = TerminalUtils.getString().trim();

			if (userName.isEmpty()) {
				TerminalUtils.out("El nombre de usuario no puede estar vacío.\n");
				continue;
			}

			if (!mainController.validUserName(userName)) {
				TerminalUtils.out("Nombre de usuario ya registrado. Intente con otro.\n");
				continue;
			}

			user.setName(userName);
			break;
		}

		while (true) {
			TerminalUtils.out("Introduce la contraseña: ");
			String pass1 = TerminalUtils.getString();

			if (pass1.isEmpty()) {
				TerminalUtils.out("La contraseña no puede estar vacía.\n");
				continue;
			}

			TerminalUtils.out("Introduce de nuevo la misma contraseña: ");
			String pass2 = TerminalUtils.getString();

			if (!pass1.equals(pass2)) {
				TerminalUtils.out("Las contraseñas no coinciden. Inténtalo de nuevo.\n");
				continue;
			}

			user.setPassword(SecurePassword.hashPassword(pass1));
			user.setuUID(UUID.randomUUID().toString());
			break;
		}

		if (mainController.register(user)) {
			return "Usuario registrado";
		} else {
		
		return "Error al registrar";
		}
	}

	private User login() {
		while (true) {
			try {
				TerminalUtils.out("Iniciar sesión");
				TerminalUtils.out("==============");

				TerminalUtils.out("Introduce el nombre de usuario: ");
				String username = TerminalUtils.getString().trim();
				if (username.isEmpty()) {
					TerminalUtils.out("El nombre de usuario no puede estar vacío.\n");
					continue;
				}

				TerminalUtils.out("Introduce la contraseña: ");
				String password = TerminalUtils.getString();
				if (password.isEmpty()) {
					TerminalUtils.out("La contraseña no puede estar vacía.\n");
					continue;
				}

				User user = new User();
				user.setName(username);
				user.setPassword(password);

				User userToLog = mainController.login(user);
				if (userToLog != null) {
					return userToLog;
				} else {
					TerminalUtils.out("Usuario o contraseña incorrectos. Inténtalo de nuevo.\n");
				}
			} catch (Exception e) {
				TerminalUtils.out("Error al iniciar sesión. Inténtalo de nuevo.\n");
				return null;
			}
		}
	}
}
