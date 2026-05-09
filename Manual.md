# TiendaComprasBD

Aplicación monolítica de carrito de compras desarrollada con Spring Boot.

## Descripción

Este proyecto consiste en una aplicación web sencilla de carrito de compras. La aplicación permite ver productos disponibles, agregarlos al carrito, revisar el total de la compra, confirmar un pedido y consultar el historial de compras realizadas.

El sistema fue desarrollado como una aplicación monolítica, ya que todos los componentes se encuentran dentro de un mismo proyecto Spring Boot. En el mismo proyecto están los controladores, modelos, repositorios, servicios, vistas y la configuración de la base de datos.

## Tecnologías utilizadas

- Java 17
- Spring Boot
- Spring Data JPA
- Thymeleaf
- H2 Database
- Maven
- HTML y CSS

## Funcionalidades principales

- Mostrar productos disponibles.
- Agregar productos al carrito de compras.
- Ver los productos agregados al carrito.
- Calcular subtotales y total de la compra.
- Quitar productos del carrito.
- Confirmar una compra.
- Registrar pedidos en la base de datos.
- Consultar historial de pedidos.
- Revisar las tablas desde la consola de H2.

## Base de datos

El proyecto utiliza H2 Database como base de datos relacional embebida. Esto permite que la aplicación pueda ejecutarse sin instalar un servidor externo de base de datos.

La base de datos se crea automáticamente al iniciar el proyecto y se guarda dentro de la carpeta del sistema.

## Cómo ejecutar el proyecto

1. Descargar o clonar el repositorio.
2. Abrir la carpeta principal del proyecto.
3. Ejecutar el archivo:

INICIAR_PROYECTO.bat

4. Esperar a que la aplicación termine de iniciar.
5. Abrir el navegador y entrar a:

http://localhost:8081

También se puede ingresar directamente a la pantalla de productos:

http://localhost:8081/productos

## Cómo revisar la base de datos

Con la aplicación ejecutándose, abrir en el navegador:

http://localhost:8081/h2-console

Luego colocar los siguientes datos:

JDBC URL: jdbc:h2:file:./datos/TiendaComprasBD  
User Name: sa  
Password: dejar vacío

Después presionar el botón Connect.

## Tablas principales

La aplicación maneja las siguientes tablas:

- PRODUCTOS
- PRODUCTOS_CARRITO
- PEDIDOS
- DETALLES_PEDIDO

## Consultas de prueba

Dentro de H2 Console se pueden ejecutar estas consultas:

SELECT * FROM PRODUCTOS;

SELECT * FROM PEDIDOS;

SELECT * FROM DETALLES_PEDIDO;

SELECT * FROM PRODUCTOS_CARRITO;

## Explicación del funcionamiento

Primero, el usuario entra a la pantalla de productos disponibles. Desde ahí puede agregar productos al carrito. Luego puede entrar a la pantalla del carrito para revisar los productos seleccionados, la cantidad, el subtotal y el total de la compra.

Cuando el usuario confirma la compra, el sistema registra el pedido en la base de datos y guarda el detalle de los productos comprados. Después de confirmar la compra, el carrito queda vacío para poder realizar una nueva compra.

## Tipo de aplicación

Esta aplicación es monolítica porque todo el sistema está desarrollado en un solo proyecto Spring Boot. No se separó en microservicios ni en diferentes aplicaciones. Todo el flujo del carrito, productos, pedidos, vistas y base de datos se maneja desde el mismo proyecto.

## Autor

Emersson Steve Alvizures Palma
