package com.view.serverlet;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

import com.controller.IMainController;
import com.controller.MainController;
import com.entities.User;
import com.utils.TerminalUtils;

/**
 * Servlet implementation class SvLogin
 */
@WebServlet("/login")
public class SvLogin extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private IMainController mainController;

	/**
	 * @throws IOException
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 * @see HttpServlet#HttpServlet()
	 */
	public SvLogin() throws ClassNotFoundException, SQLException, IOException {
		super();
		this.mainController = new MainController();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		RequestDispatcher requestDispatcher = request.getRequestDispatcher("login.jsp");
		requestDispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			String name = request.getParameter("username");
			String password = request.getParameter("password");
			User user = new User();
			user.setName(name);
			user.setPassword(password);

			User userToLog = mainController.login(user);
			if (userToLog != null) {
				RequestDispatcher requestDispatcher = request.getRequestDispatcher("SvUser");
				requestDispatcher.forward(request, response);
				return;
			}
		} catch (Exception e) {
			TerminalUtils.out("Error al iniciar sesión. Inténtalo de nuevo.\n");

		}
		request.setAttribute("error", "No se ha podido loguear.");
		doGet(request, response);
	}

}
