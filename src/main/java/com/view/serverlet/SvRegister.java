package com.view.serverlet;

import java.io.IOException;
import java.util.UUID;

import com.controller.IMainController;
import com.controller.MainController;
import com.entities.User;
import com.hassPassword.SecurePassword;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/register")
public class SvRegister extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private IMainController mainController;

    public SvRegister() {
        super();
        try {
            this.mainController = new MainController();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("register.jsp");
        requestDispatcher.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String mensaje;

        if (username == null || username.trim().isEmpty() || password == null || password.trim().isEmpty()) {
            mensaje = "Usuario y contraseña son obligatorios.";
            request.setAttribute("mensaje", mensaje);
            request.getRequestDispatcher("register.jsp").forward(request, response);
            return;
        }

        User user = new User();
        user.setName(username);
        user.setPassword(SecurePassword.hashPassword(password));  
        user.setuUID(UUID.randomUUID().toString());

        try {
            boolean registrado = mainController.register(user);
            if (registrado) {
                mensaje = "Usuario registrado con éxito.";
            } else {
                mensaje = "No se pudo registrar el usuario.";
            }
        } catch (Exception e) {
            e.printStackTrace();
            mensaje = "Error en la base de datos: " + e.getMessage();
        }

        request.setAttribute("mensaje", mensaje);
        request.getRequestDispatcher("register.jsp").forward(request, response);
    }
}
