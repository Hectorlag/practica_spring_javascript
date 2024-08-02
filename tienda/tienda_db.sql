-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 02-08-2024 a las 03:43:56
-- Versión del servidor: 10.4.32-MariaDB
-- Versión de PHP: 8.0.30

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `tienda_db`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `empleado`
--

CREATE TABLE `empleado` (
  `id` bigint(20) NOT NULL,
  `apellido` varchar(255) NOT NULL,
  `celular` varchar(255) DEFAULT NULL,
  `deleted` bit(1) NOT NULL,
  `genero` varchar(255) NOT NULL,
  `nombre` varchar(255) NOT NULL,
  `rol_id` bigint(20) DEFAULT NULL,
  `tutor_id` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;

--
-- Volcado de datos para la tabla `empleado`
--

INSERT INTO `empleado` (`id`, `apellido`, `celular`, `deleted`, `genero`, `nombre`, `rol_id`, `tutor_id`) VALUES
(2, 'Pérez', '12344440', b'0', 'Masculino', 'Juan', 1, NULL),
(8, 'Corbo', '5039330', b'0', 'Femenino', 'Mariana', 1, 2),
(18, 'ovelar', '222999', b'0', 'Femenino', 'Carla', 2, NULL),
(19, 'Fernan', '888830', b'1', 'Masculino', 'Esteban', 2, NULL),
(20, 'Messi', '22211666', b'0', 'femenino', 'Lionel', NULL, NULL),
(21, 'garcia', '111000', b'0', 'masculino', 'abel', NULL, NULL),
(22, 'Diaz', '1245900', b'0', 'masculino', 'Nestor', NULL, NULL),
(23, 'pepe', '1111', b'0', 'masculino', 'Horacio', NULL, NULL),
(24, 'qwerty', '6655443', b'0', 'femenino', 'Juana', NULL, NULL),
(25, 'Gregores', '09091212', b'0', 'Femenino', 'Amanda', 2, NULL),
(26, 'Estigarribia', '109283', b'0', 'Masculino', 'Arnaldo', 2, NULL);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `empleado_tareas`
--

CREATE TABLE `empleado_tareas` (
  `empleado_id` bigint(20) NOT NULL,
  `tarea_id` bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;

--
-- Volcado de datos para la tabla `empleado_tareas`
--

INSERT INTO `empleado_tareas` (`empleado_id`, `tarea_id`) VALUES
(25, 19),
(25, 22),
(26, 25),
(26, 26);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `rol`
--

CREATE TABLE `rol` (
  `id` bigint(20) NOT NULL,
  `deleted` bit(1) NOT NULL,
  `nombre` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;

--
-- Volcado de datos para la tabla `rol`
--

INSERT INTO `rol` (`id`, `deleted`, `nombre`) VALUES
(1, b'0', 'Supervisor'),
(2, b'0', 'Empleado');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `tarea`
--

CREATE TABLE `tarea` (
  `id` bigint(20) NOT NULL,
  `deleted` bit(1) NOT NULL,
  `description` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;

--
-- Volcado de datos para la tabla `tarea`
--

INSERT INTO `tarea` (`id`, `deleted`, `description`) VALUES
(19, b'0', 'Formateo e instalación de SO'),
(20, b'0', 'Limpieza física'),
(21, b'0', 'Desinfección de virus'),
(22, b'0', 'Diagnosticar'),
(23, b'0', 'Cambio de partes'),
(24, b'0', 'Flasheo'),
(25, b'0', 'Cambio de bateria'),
(26, b'0', 'Cambio de pantalla'),
(27, b'0', 'Cambio de vidrio templado'),
(28, b'0', 'Recibir nuevos clientes'),
(29, b'0', 'Presupuesto de equipos'),
(30, b'0', 'Venta de equipos'),
(31, b'0', 'Compra a proveedores');

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `empleado`
--
ALTER TABLE `empleado`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FKqudsrbctju4p60b7n0cdalf6d` (`rol_id`),
  ADD KEY `FKqt0gy3wbc15x7h45ekajhd1le` (`tutor_id`);

--
-- Indices de la tabla `empleado_tareas`
--
ALTER TABLE `empleado_tareas`
  ADD PRIMARY KEY (`empleado_id`,`tarea_id`),
  ADD KEY `FKrdavaiph53g0jn0if9hpljc20` (`tarea_id`);

--
-- Indices de la tabla `rol`
--
ALTER TABLE `rol`
  ADD PRIMARY KEY (`id`);

--
-- Indices de la tabla `tarea`
--
ALTER TABLE `tarea`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `empleado`
--
ALTER TABLE `empleado`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=27;

--
-- AUTO_INCREMENT de la tabla `rol`
--
ALTER TABLE `rol`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT de la tabla `tarea`
--
ALTER TABLE `tarea`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=32;

--
-- Restricciones para tablas volcadas
--

--
-- Filtros para la tabla `empleado`
--
ALTER TABLE `empleado`
  ADD CONSTRAINT `FKqt0gy3wbc15x7h45ekajhd1le` FOREIGN KEY (`tutor_id`) REFERENCES `empleado` (`id`),
  ADD CONSTRAINT `FKqudsrbctju4p60b7n0cdalf6d` FOREIGN KEY (`rol_id`) REFERENCES `rol` (`id`);

--
-- Filtros para la tabla `empleado_tareas`
--
ALTER TABLE `empleado_tareas`
  ADD CONSTRAINT `FKfuywhb55b8i4w3mcigean0h77` FOREIGN KEY (`empleado_id`) REFERENCES `empleado` (`id`),
  ADD CONSTRAINT `FKrdavaiph53g0jn0if9hpljc20` FOREIGN KEY (`tarea_id`) REFERENCES `tarea` (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
