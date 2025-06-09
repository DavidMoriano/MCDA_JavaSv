package com.view.serverlet;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.List;

import com.controller.user.IUserController;
import com.controller.user.UserController;
import com.entities.Car;
import com.entities.User;

@WebServlet("/SvUser")
public class SvUser extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private IUserController userController;

	public SvUser() {
		super();
		try {
			this.userController = new UserController();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession(false);
		if (session == null) {
			request.getRequestDispatcher("SvLogin").forward(request, response);
			return;
		}
		User user = (User) session.getAttribute("user");

		try {
			List<Car> carList = userController.getAllCars(user);
			request.setAttribute("carList", carList);
			RequestDispatcher dispatcher = request.getRequestDispatcher("userView.jsp");
			dispatcher.forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("mensaje", "Error al obtener los coches: " + e.getMessage());
			request.getRequestDispatcher("error.jsp").forward(request, response);
		}
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}
}
