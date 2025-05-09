-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 09-05-2025 a las 14:49:56
-- Versión del servidor: 10.4.32-MariaDB
-- Versión de PHP: 8.2.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `coco_chat_bd`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `amistad`
--

CREATE TABLE `amistad` (
  `id_amistad` int(11) NOT NULL,
  `amigo1` int(11) DEFAULT NULL,
  `amigo2` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `grupo`
--

CREATE TABLE `grupo` (
  `id_grupo` int(11) NOT NULL,
  `nombre_grupo` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `invitacion_grupo`
--

CREATE TABLE `invitacion_grupo` (
  `id_invitacion_grupo` int(11) NOT NULL,
  `remitente_grupo` int(11) DEFAULT NULL,
  `destinatario_invitacion_grupo` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `mensaje_amigo`
--

CREATE TABLE `mensaje_amigo` (
  `id_mensaje_amigo` int(11) NOT NULL,
  `remitente_amigo` int(11) DEFAULT NULL,
  `destinatario_amigo` int(11) DEFAULT NULL,
  `fecha_mensaje_amigo` datetime DEFAULT NULL,
  `mensaje_amigo` longtext DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `mensaje_grupo`
--

CREATE TABLE `mensaje_grupo` (
  `id_mensaje_grupo` int(11) NOT NULL,
  `remitente_grupo` int(11) DEFAULT NULL,
  `destinatario_grupo` int(11) DEFAULT NULL,
  `fecha_mensaje_grupo` datetime DEFAULT NULL,
  `mensaje_grupo` longtext DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `mensaje_usuario`
--

CREATE TABLE `mensaje_usuario` (
  `id_mensaje_usuario` int(11) NOT NULL,
  `remitente_usuario` int(11) DEFAULT NULL,
  `destinatario_usuario` int(11) DEFAULT NULL,
  `fecha_mensaje_usuario` datetime DEFAULT NULL,
  `mensaje_usuario` longtext DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `pertenencias_grupo`
--

CREATE TABLE `pertenencias_grupo` (
  `id_pertenencias_grupo` int(11) NOT NULL,
  `grupo` int(11) DEFAULT NULL,
  `usuario_perteneciente` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `solicitud_amistad`
--

CREATE TABLE `solicitud_amistad` (
  `id_solicitud_amistad` int(11) NOT NULL,
  `remitente_solicitud_amistad` int(11) DEFAULT NULL,
  `destinatario_solicitud_amistad` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `usuario`
--

CREATE TABLE `usuario` (
  `id_usuario` int(11) NOT NULL,
  `nombre` varchar(255) DEFAULT NULL,
  `username` varchar(100) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `correo` varchar(255) DEFAULT NULL,
  `pregunta_respaldo` varchar(100) DEFAULT NULL,
  `estado` tinyint(1) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `amistad`
--
ALTER TABLE `amistad`
  ADD PRIMARY KEY (`id_amistad`),
  ADD KEY `amigo1` (`amigo1`),
  ADD KEY `amigo2` (`amigo2`);

--
-- Indices de la tabla `grupo`
--
ALTER TABLE `grupo`
  ADD PRIMARY KEY (`id_grupo`);

--
-- Indices de la tabla `invitacion_grupo`
--
ALTER TABLE `invitacion_grupo`
  ADD PRIMARY KEY (`id_invitacion_grupo`),
  ADD KEY `destinatario_invitacion_grupo` (`destinatario_invitacion_grupo`),
  ADD KEY `remitente_grupo` (`remitente_grupo`);

--
-- Indices de la tabla `mensaje_amigo`
--
ALTER TABLE `mensaje_amigo`
  ADD PRIMARY KEY (`id_mensaje_amigo`),
  ADD KEY `remitente_amigo` (`remitente_amigo`),
  ADD KEY `destinatario_amigo` (`destinatario_amigo`);

--
-- Indices de la tabla `mensaje_grupo`
--
ALTER TABLE `mensaje_grupo`
  ADD PRIMARY KEY (`id_mensaje_grupo`),
  ADD KEY `remitente_grupo` (`remitente_grupo`),
  ADD KEY `destinatario_grupo` (`destinatario_grupo`);

--
-- Indices de la tabla `mensaje_usuario`
--
ALTER TABLE `mensaje_usuario`
  ADD PRIMARY KEY (`id_mensaje_usuario`),
  ADD KEY `remitente_usuario` (`remitente_usuario`),
  ADD KEY `destinatario_usuario` (`destinatario_usuario`);

--
-- Indices de la tabla `pertenencias_grupo`
--
ALTER TABLE `pertenencias_grupo`
  ADD PRIMARY KEY (`id_pertenencias_grupo`),
  ADD KEY `grupo` (`grupo`),
  ADD KEY `usuario_perteneciente` (`usuario_perteneciente`);

--
-- Indices de la tabla `solicitud_amistad`
--
ALTER TABLE `solicitud_amistad`
  ADD PRIMARY KEY (`id_solicitud_amistad`),
  ADD KEY `remitente_solicitud_amistad` (`remitente_solicitud_amistad`),
  ADD KEY `destinatario_solicitud_amistad` (`destinatario_solicitud_amistad`);

--
-- Indices de la tabla `usuario`
--
ALTER TABLE `usuario`
  ADD PRIMARY KEY (`id_usuario`);

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `amistad`
--
ALTER TABLE `amistad`
  MODIFY `id_amistad` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `grupo`
--
ALTER TABLE `grupo`
  MODIFY `id_grupo` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `invitacion_grupo`
--
ALTER TABLE `invitacion_grupo`
  MODIFY `id_invitacion_grupo` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `mensaje_amigo`
--
ALTER TABLE `mensaje_amigo`
  MODIFY `id_mensaje_amigo` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `mensaje_grupo`
--
ALTER TABLE `mensaje_grupo`
  MODIFY `id_mensaje_grupo` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `mensaje_usuario`
--
ALTER TABLE `mensaje_usuario`
  MODIFY `id_mensaje_usuario` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `pertenencias_grupo`
--
ALTER TABLE `pertenencias_grupo`
  MODIFY `id_pertenencias_grupo` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `solicitud_amistad`
--
ALTER TABLE `solicitud_amistad`
  MODIFY `id_solicitud_amistad` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `usuario`
--
ALTER TABLE `usuario`
  MODIFY `id_usuario` int(11) NOT NULL AUTO_INCREMENT;

--
-- Restricciones para tablas volcadas
--

--
-- Filtros para la tabla `amistad`
--
ALTER TABLE `amistad`
  ADD CONSTRAINT `amistad_ibfk_1` FOREIGN KEY (`amigo1`) REFERENCES `usuario` (`id_usuario`),
  ADD CONSTRAINT `amistad_ibfk_2` FOREIGN KEY (`amigo2`) REFERENCES `usuario` (`id_usuario`);

--
-- Filtros para la tabla `invitacion_grupo`
--
ALTER TABLE `invitacion_grupo`
  ADD CONSTRAINT `invitacion_grupo_ibfk_2` FOREIGN KEY (`destinatario_invitacion_grupo`) REFERENCES `usuario` (`id_usuario`),
  ADD CONSTRAINT `invitacion_grupo_ibfk_3` FOREIGN KEY (`remitente_grupo`) REFERENCES `grupo` (`id_grupo`);

--
-- Filtros para la tabla `mensaje_amigo`
--
ALTER TABLE `mensaje_amigo`
  ADD CONSTRAINT `mensaje_amigo_ibfk_1` FOREIGN KEY (`remitente_amigo`) REFERENCES `usuario` (`id_usuario`),
  ADD CONSTRAINT `mensaje_amigo_ibfk_2` FOREIGN KEY (`destinatario_amigo`) REFERENCES `usuario` (`id_usuario`);

--
-- Filtros para la tabla `mensaje_grupo`
--
ALTER TABLE `mensaje_grupo`
  ADD CONSTRAINT `mensaje_grupo_ibfk_1` FOREIGN KEY (`remitente_grupo`) REFERENCES `usuario` (`id_usuario`),
  ADD CONSTRAINT `mensaje_grupo_ibfk_2` FOREIGN KEY (`destinatario_grupo`) REFERENCES `grupo` (`id_grupo`);

--
-- Filtros para la tabla `mensaje_usuario`
--
ALTER TABLE `mensaje_usuario`
  ADD CONSTRAINT `mensaje_usuario_ibfk_1` FOREIGN KEY (`remitente_usuario`) REFERENCES `usuario` (`id_usuario`),
  ADD CONSTRAINT `mensaje_usuario_ibfk_2` FOREIGN KEY (`destinatario_usuario`) REFERENCES `usuario` (`id_usuario`);

--
-- Filtros para la tabla `pertenencias_grupo`
--
ALTER TABLE `pertenencias_grupo`
  ADD CONSTRAINT `pertenencias_grupo_ibfk_1` FOREIGN KEY (`grupo`) REFERENCES `grupo` (`id_grupo`),
  ADD CONSTRAINT `pertenencias_grupo_ibfk_2` FOREIGN KEY (`usuario_perteneciente`) REFERENCES `usuario` (`id_usuario`);

--
-- Filtros para la tabla `solicitud_amistad`
--
ALTER TABLE `solicitud_amistad`
  ADD CONSTRAINT `solicitud_amistad_ibfk_1` FOREIGN KEY (`remitente_solicitud_amistad`) REFERENCES `usuario` (`id_usuario`),
  ADD CONSTRAINT `solicitud_amistad_ibfk_2` FOREIGN KEY (`destinatario_solicitud_amistad`) REFERENCES `usuario` (`id_usuario`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
