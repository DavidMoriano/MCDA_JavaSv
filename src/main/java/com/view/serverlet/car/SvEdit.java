package com.view.serverlet.car;

import com.controller.user.IUserController;
import com.controller.user.UserController;
import com.entities.Car;
import com.entities.User;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet("/SvEdit")
public class SvEdit extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private IUserController userController;

    public SvEdit() throws ClassNotFoundException, SQLException, IOException {
        super();
        this.userController = new UserController();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            int id_car = Integer.parseInt(request.getParameter("id_car"));
            HttpSession session = request.getSession(false);
    		if (session == null) {
    			request.getRequestDispatcher("login").forward(request, response);
    			return;
    		}
    		User user = (User) session.getAttribute("user");

            List<Car> carList = userController.getAllCars(user);

            Car carToEdit = null;
            for (Car car : carList) {
                if (car.getId_car() == id_car) {
                    carToEdit = car;
                    break;
                }
            }

            if (carToEdit != null) {
                request.setAttribute("car", carToEdit);
                request.getRequestDispatcher("/Car/editCar.jsp").forward(request, response);
                return;
            } else {
                request.setAttribute("errorMessage", "No se encontró el coche con ID: " + id_car);
                request.getRequestDispatcher("/error.jsp").forward(request, response);
            }
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("errorMessage", "Error al obtener el coche: " + e.getMessage());
            request.getRequestDispatcher("/error.jsp").forward(request, response);
        }
    }


    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            int id_car = Integer.parseInt(request.getParameter("id_car"));
            String brand = request.getParameter("brand");
            String model = request.getParameter("model");
            String plate = request.getParameter("plate");
            int year = Integer.parseInt(request.getParameter("year"));

            Car carToEdit = new Car();
            carToEdit.setId_car(id_car);
            carToEdit.setBrand(brand);
            carToEdit.setModel(model);
            carToEdit.setPlate(plate);
            carToEdit.setYear(year);

            boolean success = userController.editCar(carToEdit);

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
