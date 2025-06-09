<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.List"%>
<%@ page import="com.entities.Car"%>
<!DOCTYPE html>
<html lang="es">
<head>
<meta charset="UTF-8">
<title>Mis Coches</title>
<style>
body {
	font-family: Arial, sans-serif;
	padding: 2rem;
	background-color: #f9fafb;
}

h1 {
	text-align: center;
	color: #1e3a8a;
}

.table-container {
	margin-top: 2rem;
}

table {
	width: 100%;
	border-collapse: collapse;
	margin-top: 1rem;
}

th, td {
	padding: 0.75rem;
	border: 1px solid #ccc;
	text-align: center;
}

th {
	background-color: #f3f4f6;
}

button, a.button-link {
	padding: 0.5rem 1rem;
	margin: 0.2rem;
	font-size: 0.95rem;
	background-color: #1e40af;
	color: white;
	border: none;
	border-radius: 5px;
	text-decoration: none;
	cursor: pointer;
	display: inline-block;
}

button:hover, a.button-link:hover {
	background-color: #1d4ed8;
}

.button-top-right {
	text-align: right;
	margin-bottom: 1rem;
}
</style>
</head>
<body>
	<h1>Lista de Coches</h1>

	<div class="button-top-right">
		<a href="${pageContext.request.contextPath}/Car/addCar.jsp" class="button-link">Añadir Coche</a>
	</div>

	<div class="table-container">
		<table>
			<thead>
				<tr>
					<th>ID</th>
					<th>Año</th>
					<th>Marca</th>
					<th>Modelo</th>
					<th>Matrícula</th>
					<th>Acciones</th>
				</tr>
			</thead>
			<tbody>
				<%
				List<Car> carList = (List<Car>) request.getAttribute("carList");
				if (carList != null && !carList.isEmpty()) {
					for (Car car : carList) {
				%>
				<tr>
					<td><%=car.getId_car()%></td>
					<td><%=car.getYear()%></td>
					<td><%=car.getBrand()%></td>
					<td><%=car.getModel()%></td>
					<td><%=car.getPlate()%></td>
					<td>
						<form action="AddOwnerServlet" method="get" style="display: inline;">
							<input type="hidden" name="id_car" value="<%=car.getId_car()%>">
							<button type="submit">Añadir Propietario</button>
						</form>
						<form action="SvEdit" method="get" style="display: inline;">
							<input type="hidden" name="id_car" value="<%=car.getId_car()%>">
							<button type="submit">Editar</button>
						</form>
						<form action="SvDeleteCar" method="post" style="display: inline;"
							onsubmit="return confirm('¿Estás seguro de eliminar este coche?');">
							<input type="hidden" name="id_car" value="<%=car.getId_car()%>">
							<button type="submit">Eliminar</button>
						</form>
						<a class="button-link" href="VerGastosServlet?id_car=<%=car.getId_car()%>">Ver Gastos</a>
					</td>
				</tr>
				<%
				}
				} else {
				%>
				<tr>
					<td colspan="6">No hay coches registrados para este usuario.</td>
				</tr>
				<%
				}
				%>
			</tbody>
		</table>
	</div>
</body>
</html>
