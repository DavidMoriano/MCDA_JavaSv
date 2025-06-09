# MCDA_JavaSv
# 🚗✨ Gestor de Coches - Tu garaje digital en Java ✨🚗

¡Bienvenido a **Gestor de Coches**, la aplicación web que convierte la gestión de tus vehículos en una experiencia sencilla, ágil y moderna! Construida con Java Servlets y JSP, esta herramienta es perfecta para quienes quieren tener el control total de sus coches desde cualquier lugar.

---

## 🚀 ¿Qué puedes hacer con esta app?

- **Ver tu flota de coches**: Consulta todos tus vehículos en una lista clara y fácil de navegar.
- **Añadir coches nuevos**: Agrega tus vehículos con todos sus detalles, desde la matrícula hasta el año.
- **Editar coches existentes**: Cambia la información de tus coches al instante sin complicaciones.
- **Eliminar coches**: ¿Vendes o regalas uno? ¡Elimínalo con un solo clic y sin miedo!
- **Gestionar propietarios**: Añade y administra propietarios para cada coche.
- **Revisar gastos**: Consulta los gastos asociados para mantener tus finanzas bajo control.

---

## 🛠 Tecnologías y herramientas

- **Java 17+**: Potente y robusto para aplicaciones empresariales.
- **Jakarta Servlet & JSP**: Clásicos para crear aplicaciones web dinámicas.
- **Servidor Apache Tomcat**: El contenedor de servlets confiable.
- **HTML5 + CSS3**: Interfaces limpias y amigables.
- **IDE recomendado**: IntelliJ IDEA, Eclipse o NetBeans.

---

## 🏗 Estructura del proyecto

```plaintext
/src
  └── com/
        ├── controller/
        │   ├── MainController.java
        │   └── user/
        │       └── UserController.java
        │
        ├── entities/
        │   ├── Car.java
        │   ├── Payment.java
        │   ├── PaymentFilter.java
        │   └── User.java
        │
        ├── hashPassword/
        │   └── SecurePassword.java
        │
        ├── main/
        │   └── Main.java
        │
        ├── model/
        │   ├── config/
        │   │   └── DatabaseConnection.java
        │   └── user/
        │       └── ModelUserDataBase.java
        │
        ├── utils/
        │   ├── FileUtils.java
        │   ├── TerminalUtils.java
        │   └── configs/
        │       ├── Configurations.java
        │       └── DatabaseConfigurations.java
        │
        └── view/
            ├── MainView.java
            ├── servlet/
            │   ├── SvLogin.java
            │   ├── SVRegister.java
            │   ├── SvUser.java
            │   └── car/
            │       ├── SVAddCar.java
            │       ├── SVDeleteCar.java
            │       └── SvEdit.java
            └── user/
                └── UserView.java
webapp/
├── META-INF/
├── WEB-INF/
│   └── web.xml
├── Car/
│   ├── addCar.jsp
│   └── editCar.jsp
├── Styles.css
├── error.jsp
├── index.html
├── login.jsp
├── register.jsp
└── userView.jsp
