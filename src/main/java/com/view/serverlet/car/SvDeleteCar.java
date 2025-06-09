package com.view.serverlet.car;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

import com.controller.user.IUserController;
import com.controller.user.UserController;
import com.entities.User;

/**
 * Servlet implementation class SvDelete
 */
@WebServlet("/SvDeleteCar")
public class SvDeleteCar extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private IUserController userController;

	public SvDeleteCar() {
		super();
		try {
			this.userController = new UserController(); 
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 try {
	            int id_car = Integer.parseInt(request.getParameter("id_car"));

	            User user = new User();
	            user.setId_user(5);
	            user.setName("manu");
	            user.setuUID("b47b60f8-6c94-4529-8ba9-9baedb238e55");
	            user.setPassword("$2a$10$pDAqjIlxZQuwVGYlAgr1/e/PRvXQsZF/s.hIhMkaZNlYWrlgbKdj6");

	            boolean success = userController.deleteCar(id_car, user);

	            if (success) {
	                response.sendRedirect(request.getContextPath() + "/SvUser");
	            } else {
	                request.setAttribute("errorMessage", "No se pudo editar el coche.");
	                request.getRequestDispatcher("/error.jsp").forward(request, response);
	            }

	        } catch (Exception e) {
	            e.printStackTrace();
	            request.setAttribute("errorMessage", "Error al procesar los datos: " + e.getMessage());
	            request.getRequestDispatcher("/error.jsp").forward(request, response);
	        }
	}

}
