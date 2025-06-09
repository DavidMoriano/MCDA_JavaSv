<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="es">
<head>
<meta charset="UTF-8" />
<meta name="viewport" content="width=device-width, initial-scale=1" />
<link rel="stylesheet" href="./Styles.css" />
<title>Iniciar Sesión - Portal de Coches</title>
</head>

<body>
	<main class="container" role="main" aria-labelledby="loginTitle">
		<h1 id="loginTitle">Iniciar sesión</h1>
		<form id="loginForm" action="login" method="post">
			<label for="username">Nombre de usuario</label> <input type="text"
				id="username" name="username" autocomplete="username" required /> <label
				for="password">Contraseña</label> <input type="password"
				id="password" name="password" autocomplete="current-password"
				required />

			<button type="submit">Iniciar sesión</button>
		</form>

		<p class="message" id="message" aria-live="polite"></p>
		<p class="nav-link">
			¿No tienes cuenta? <a href="register.html">Regístrate aquí</a>
		</p>
	</main>

	<%
	String error = (String) request.getAttribute("error");
	%>
	<%
	if (error != null && !error.isEmpty()) {
	%>
	<div class="modal" id="errorModal">
		<div class="modal-content">
			<p>
				<strong>Error:</strong>
				<%=error%></p>
			<button
				onclick="document.getElementById('errorModal').style.display='none'">Cerrar</button>
		</div>
	</div>
	<%
	}
	%>
</body>
</html>
