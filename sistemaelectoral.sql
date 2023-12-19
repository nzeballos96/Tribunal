-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 19-12-2023 a las 18:29:58
-- Versión del servidor: 10.4.28-MariaDB
-- Versión de PHP: 8.2.4

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `sistemaelectoral`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `candidato`
--

CREATE TABLE `candidato` (
  `DU` int(10) NOT NULL,
  `APELLIDO` varchar(20) NOT NULL,
  `NOMBRE` varchar(20) NOT NULL,
  `edad` int(3) NOT NULL,
  `GENERO` varchar(10) NOT NULL,
  `DOMICILIO` varchar(30) NOT NULL,
  `PARTIDO` varchar(50) NOT NULL,
  `LEMA` varchar(100) NOT NULL,
  `VOTOREC` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `candidato_eleccion`
--

CREATE TABLE `candidato_eleccion` (
  `DU_CANDIDATO` int(10) NOT NULL,
  `APELLIDO` varchar(20) NOT NULL,
  `NOMBRE` varchar(20) NOT NULL,
  `EDAD` int(3) NOT NULL,
  `GENERO` varchar(10) NOT NULL,
  `domicilio` varchar(50) NOT NULL,
  `PARTIDO` varchar(50) NOT NULL,
  `Lema` varchar(100) NOT NULL,
  `VOTOREC` int(11) NOT NULL,
  `ID_ELECCION` int(10) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `eleccion`
--

CREATE TABLE `eleccion` (
  `Id` int(10) NOT NULL,
  `DIA` int(2) NOT NULL,
  `MES` int(2) NOT NULL,
  `ANIO` int(4) NOT NULL,
  `DETALLE` varchar(50) NOT NULL,
  `STATUS` int(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `sufragante`
--

CREATE TABLE `sufragante` (
  `DU` int(10) NOT NULL,
  `APELLIDO` varchar(20) NOT NULL,
  `edad` int(3) NOT NULL,
  `NOMBRE` varchar(20) NOT NULL,
  `GENERO` varchar(10) NOT NULL,
  `DOMICILIO` varchar(30) NOT NULL,
  `CHECKIN` int(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `sufragante_eleccion`
--

CREATE TABLE `sufragante_eleccion` (
  `CANTIDA_SUFRAGANTE` int(10) NOT NULL,
  `CANTIDA_VOTOS` int(11) NOT NULL,
  `SUFRAGANTES_FALTANTES` int(11) NOT NULL,
  `ID_ELECCION` int(10) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `votacion`
--

CREATE TABLE `votacion` (
  `IDVOTO` int(11) NOT NULL,
  `DU_CANDIDATO` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `candidato`
--
ALTER TABLE `candidato`
  ADD PRIMARY KEY (`DU`);

--
-- Indices de la tabla `eleccion`
--
ALTER TABLE `eleccion`
  ADD PRIMARY KEY (`Id`);

--
-- Indices de la tabla `sufragante`
--
ALTER TABLE `sufragante`
  ADD PRIMARY KEY (`DU`);

--
-- Indices de la tabla `votacion`
--
ALTER TABLE `votacion`
  ADD PRIMARY KEY (`IDVOTO`);

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `eleccion`
--
ALTER TABLE `eleccion`
  MODIFY `Id` int(10) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `votacion`
--
ALTER TABLE `votacion`
  MODIFY `IDVOTO` int(11) NOT NULL AUTO_INCREMENT;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
