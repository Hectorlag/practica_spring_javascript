# Sistema de Registro de Empleados

Este proyecto es una aplicación para la gestión de empleados, desarrollada utilizando **Spring Boot** para el backend y **JavaScript (vanilla)** para el frontend. La aplicación permite registrar empleados, asignarles tareas y gestionar su información personal.

## Contenidos del Proyecto

- **Backend**: Implementado con Spring Boot para manejar la lógica de negocio y la persistencia de datos.
- **Frontend**: Desarrollado con JavaScript (vanilla) para la interacción del usuario y el manejo de formularios.

## Instalación y Configuración

### Requisitos Previos

- [Java 19](https://www.oracle.com/java/technologies/javase-downloads.html)
- [Maven](https://maven.apache.org/download.cgi)
- [MySQL](https://dev.mysql.com/downloads/mysql/)

### Configuración del Backend

1. **Clona el repositorio:**

   ```bash
   git clone https://github.com/Hectorlag/practica_spring_javascript.git
   
2.Accede al directorio del proyecto desde la consola:
    tienda_test\tienda
3.Configura el archivo de propiedades:

Edita el archivo src/main/resources/application.properties para configurar tu conexión a la base de datos MySQL:

spring.datasource.url=jdbc:mysql://localhost:3306/tu_base_de_datos
spring.datasource.username=tu_usuario
spring.datasource.password=tu_contraseña

4.Construye y ejecuta la aplicación
   mvn clean install
   mvn spring-boot:run
   
Uso
Accede a la aplicación:

Abre un navegador y navega a http://localhost:8080 para ver la aplicación en funcionamiento.

Registrar un Empleado:

Llena el formulario en el frontend para registrar un nuevo empleado. Los datos se enviarán al backend y se guardarán en la base de datos.

Diagrama de Clases
Incluye aquí el diagrama de clases de la aplicación en formato de imagen o proporciona un enlace al archivo del diagrama.

Colección de Postman
La colección de Postman utilizada para probar la API se encuentra en el archivo postman_collection.json en el directorio raíz del proyecto.
