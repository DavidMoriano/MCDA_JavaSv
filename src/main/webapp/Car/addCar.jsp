<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="es">
<head>
<meta charset="UTF-8">
<title>Añadir Coche</title>
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
    .button-group {
        margin-top: 1.5rem;
        display: flex;
        justify-content: space-between;
    }
    button, .cancel-link {
        padding: 0.6rem 1.2rem;
        background-color: #2563eb;
        color: white;
        border: none;
        border-radius: 6px;
        cursor: pointer;
        text-decoration: none;
        text-align: center;
    }
    .cancel-link {
        background-color: #6b7280;
    }
    button:hover {
        background-color: #1d4ed8;
    }
    .cancel-link:hover {
        background-color: #4b5563;
    }
</style>
</head>
<body>

<h1 style="text-align:center;">Añadir Coche</h1>

<form action="${pageContext.request.contextPath}/SvAddCar" method="post">

    <label for="brand">Marca</label>
    <input type="text" name="brand" id="brand" required>

    <label for="model">Modelo</label>
    <input type="text" name="model" id="model" required>

    <label for="plate">Matrícula</label>
    <input type="text" name="plate" id="plate" required>

    <label for="year">Año</label>
    <input type="number" name="year" id="year" required>

    <div class="button-group">
        <button type="submit">Añadir</button>
        <a href="${pageContext.request.contextPath}/SvUser" class="cancel-link">Cancelar</a>
    </div>
</form>

</body>
</html>
