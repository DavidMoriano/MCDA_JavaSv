package com.view.serverlet.car;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
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

            // Suponemos que el usuario ya está autenticado o es fijo
            User user = new User();
            user.setId_user(5); // Ajusta esto según tu lógica real
            user.setName("manu");
            user.setuUID("b47b60f8-6c94-4529-8ba9-9baedb238e55");
            user.setPassword("$2a$10$pDAqjIlxZQuwVGYlAgr1/e/PRvXQsZF/s.hIhMkaZNlYWrlgbKdj6");

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