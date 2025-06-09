<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.entities.Car" %>
<%
    Car car = (Car) request.getAttribute("car");
%>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <title>Editar Coche</title>
    <style>
        form {
            max-width: 500px;
            margin: auto;
            padding: 1.5rem;
            border: 1px solid #ccc;
            border-radius: 8px;
            background-color: #f9f9f9;
        }
        label {
            display: block;
            margin-top: 1rem;
            font-weight: bold;
        }
        input[type="text"], input[type="number"] {
            width: 100%;
            padding: 0.5rem;
            margin-top: 0.3rem;
            box-sizing: border-box;
        }
        button {
            margin-top: 1.5rem;
            padding: 0.6rem 1.2rem;
            background-color: #2563eb;
            color: white;
            border: none;
            border-radius: 6px;
            cursor: pointer;
        }
        button:hover {
            background-color: #1d4ed8;
        }
    </style>
</head>
<body>

    <h1 style="text-align:center;">Editar Coche</h1>

    <form action="${pageContext.request.contextPath}/SvEdit" method="post">
    <input type="hidden" name="id_car" value="<%= car.getId_car() %>">

    <label for="brand">Marca</label>
    <input type="text" name="brand" id="brand" value="<%= car.getBrand() %>" required>

    <label for="model">Modelo</label>
    <input type="text" name="model" id="model" value="<%= car.getModel() %>" required>

    <label for="plate">Matrícula</label>
    <input type="text" name="plate" id="plate" value="<%= car.getPlate() %>" required>

    <label for="year">Año</label>
    <input type="number" name="year" id="year" value="<%= car.getYear() %>" required>

    <button type="submit">Guardar Cambios</button>

    <a href="${pageContext.request.contextPath}/SvUser" style="margin-left: 1rem; padding: 0.6rem 1.2rem; background-color: #9ca3af; color: white; text-decoration: none; border-radius: 6px;">Cancelar</a>
</form>


</body>
</html>
