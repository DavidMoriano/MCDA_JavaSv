<%@ page language="java" contentType="text/html; charset=UTF-8" 
	pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="es">
<head>
  <meta charset="UTF-8" />
  <meta name="viewport" content="width=device-width, initial-scale=1" />
  <link rel="stylesheet" href="./Styles.css" />
  <title>Registrar Usuario - Portal de Coches</title>
</head>
<body>
  <main class="container" role="main" aria-labelledby="registerTitle">
    <h1 id="registerTitle">Registrar nuevo usuario</h1>

    <form id="registerForm" action="register" method="post" novalidate>
      <label for="username">Nombre de usuario</label>
      <input type="text" id="username" name="username" autocomplete="username" required />

      <label for="password">Contraseña</label>
      <input type="password" id="password" name="password" autocomplete="new-password" required />

      <label for="confirmPassword">Confirmar contraseña</label>
      <input type="password" id="confirmPassword" name="confirmPassword" autocomplete="new-password" required />

      <button type="submit">Registrar</button>
    </form>
    <%
      String mensaje = (String) request.getAttribute("mensaje");
      if (mensaje != null && !mensaje.isEmpty()) {
    %>
      <p class="message" id="message" aria-live="polite"><%= mensaje %></p>
    <% } else { %>
      <p class="message" id="message" aria-live="polite"></p>
    <% } %>

    <p class="nav-link">
      ¿Ya tienes cuenta? <a href="login.jsp">Inicia sesión</a>
    </p>
  </main>

  <script>
    const form = document.getElementById('registerForm');
    const msgEl = document.getElementById('message');

    form.addEventListener('submit', (e) => {
      const username = form.username.value.trim();
      const password = form.password.value;
      const confirmPassword = form.confirmPassword.value;

      msgEl.textContent = '';

      if (!username) {
        e.preventDefault();
        msgEl.textContent = 'El nombre de usuario no puede estar vacío.';
        return;
      }

      if (!password) {
        e.preventDefault();
        msgEl.textContent = 'La contraseña no puede estar vacía.';
        return;
      }

      if (password !== confirmPassword) {
        e.preventDefault();
        msgEl.textContent = 'Las contraseñas no coinciden.';
        return;
      }
    });
  </script>
</body>
</html>
