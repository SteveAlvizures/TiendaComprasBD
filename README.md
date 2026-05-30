# TiendaComprasBD

Aplicación monolítica de carrito de compras desarrollada con Spring Boot.

## Descripción

Este proyecto permite visualizar productos, agregarlos al carrito, crear una orden de compra y realizar el pago mediante diferentes métodos.

La funcionalidad de pago aplica el patrón de diseño Strategy, permitiendo manejar distintos métodos de pago de forma ordenada y fácil de mantener.

## Funcionalidades principales

- Visualización de productos disponibles.
- Agregar productos al carrito.
- Ver subtotal y total de la compra.
- Crear una orden desde el carrito.
- Seleccionar método de pago.
- Confirmar el pago de la orden.
- Registrar órdenes y pagos en la base de datos.

## Métodos de pago implementados

- Tarjeta
- Efectivo
- Transferencia

## Patrón de diseño utilizado

Se utilizó el patrón Strategy para manejar los métodos de pago.

Este patrón permite separar cada forma de pago en una clase diferente. Así, si en el futuro se desea agregar otro método de pago, solo se debe crear una nueva estrategia sin modificar toda la lógica principal.

## Tecnologías utilizadas

- Java 17
- Spring Boot
- Spring Web
- Spring Data JPA
- Thymeleaf
- H2 Database
- HTML
- CSS
- Maven

## Base de datos

Se utilizó H2 Database como base de datos relacional embebida.

Consola H2:

http://localhost:8081/h2-console

Datos de conexión:

JDBC URL: jdbc:h2:file:./datos/TiendaComprasBD
User Name: sa
Password: dejar vacío

## Consultas de prueba

SELECT * FROM ORDENES;
SELECT * FROM PAGOS;

## Ejecución del proyecto

Para ejecutar el proyecto se puede usar el archivo:

INICIAR_PROYECTO.bat

O ejecutar desde Maven:

spring-boot:run

Luego abrir en el navegador:

http://localhost:8081/productos

## Estructura general

El proyecto está organizado por capas:

- Controladores: reciben las solicitudes del usuario.
- Servicios: manejan la lógica principal del sistema.
- Repositorios: se encargan de guardar y consultar datos.
- Modelos: representan las entidades de la base de datos.
- Vistas: muestran las pantallas HTML con Thymeleaf.
- Pago: contiene las estrategias del patrón Strategy.

## Autor

Emersson Steve Alvizures Palma
