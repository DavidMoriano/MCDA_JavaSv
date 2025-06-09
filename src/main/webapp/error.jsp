<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <title>Error</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #fef2f2;
            color: #991b1b;
            text-align: center;
            padding: 4rem;
        }
        .error-container {
            background-color: #fee2e2;
            padding: 2rem;
            margin: auto;
            max-width: 600px;
            border: 1px solid #fca5a5;
            border-radius: 8px;
        }
        h1 {
            font-size: 2rem;
            margin-bottom: 1rem;
        }
        p {
            font-size: 1.1rem;
        }
        a {
            display: inline-block;
            margin-top: 1.5rem;
            padding: 0.5rem 1rem;
            background-color: #dc2626;
            color: white;
            text-decoration: none;
            border-radius: 5px;
        }
        a:hover {
            background-color: #b91c1c;
        }
    </style>
</head>
<body>
    <div class="error-container">
        <h1>Ha ocurrido un error</h1>
        <p><%= request.getAttribute("errorMessage") != null 
                ? request.getAttribute("errorMessage") 
                : "Se produjo un error inesperado. Inténtalo más tarde." %></p>
        <a href="<%= request.getContextPath() %>/SvUser">Volver al inicio</a>
    </div>
</body>
</html>
