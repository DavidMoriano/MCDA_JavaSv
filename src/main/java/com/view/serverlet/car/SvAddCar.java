package com.view.serverlet.car;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.sql.SQLException;

import com.controller.user.UserController;
import com.entities.Car;
import com.entities.User;

/**
 * Servlet implementation class SvAddCar
 */
@WebServlet("/SvAddCar")
public class SvAddCar extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private UserController userController = new UserController();

    public SvAddCar() throws ClassNotFoundException, SQLException, IOException {
        super();
        this.userController = new UserController();
    }
    
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            // Obtener datos del formulario
            String brand = request.getParameter("brand");
            String model = request.getParameter("model");
            String plate = request.getParameter("plate");
            int year = Integer.parseInt(request.getParameter("year"));

            // Crear objeto Car
            Car newCar = new Car();
            newCar.setBrand(brand);
            newCar.setModel(model);
            newCar.setPlate(plate);
            newCar.setYear(year);

            HttpSession session = request.getSession(false);
    		if (session == null) {
    			request.getRequestDispatcher("login").forward(request, response);
    			return;
    		}
    		User user = (User) session.getAttribute("user");

            // Añadir el coche
            boolean success = userController.addCar(newCar, user);

            if (success) {
                response.sendRedirect(request.getContextPath() + "/SvUser");
            } else {
                request.setAttribute("error", "No se pudo añadir el coche.");
                request.getRequestDispatcher("/error.jsp").forward(request, response);
            }

        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("error", "Error al añadir el coche: " + e.getMessage());
            request.getRequestDispatcher("/error.jsp").forward(request, response);
        }
    }
}