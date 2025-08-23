-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Creato il: Ago 23, 2025 alle 20:13
-- Versione del server: 10.4.32-MariaDB
-- Versione PHP: 8.2.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `allenamenti`
--

-- --------------------------------------------------------

--
-- Struttura della tabella `equipments`
--

CREATE TABLE `equipments` (
  `id` bigint(20) NOT NULL,
  `name` varchar(255) DEFAULT NULL,
  `category` varchar(255) DEFAULT NULL,
  `image_url` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dump dei dati per la tabella `equipments`
--

INSERT INTO `equipments` (`id`, `name`, `category`, `image_url`) VALUES
(1, 'Treadmill', 'Cardio', 'https://example.com/images/treadmill.jpg'),
(2, 'Bench Press', 'Strength', 'https://example.com/images/bench_press.jpg'),
(3, 'Yoga Mat', 'Flexibility', 'https://example.com/images/yoga_mat.jpg'),
(4, 'Elliptical', 'Cardio', 'https://example.com/images/elliptical.jpg'),
(6, 'Letto', 'Strength', ''),
(7, 'Divano', 'Flexibility', ''),
(8, 'poltrona', 'Flexibility', 'https://example.com/images/yoga_mat.jpg');

-- --------------------------------------------------------

--
-- Struttura della tabella `exercises`
--

CREATE TABLE `exercises` (
  `id` bigint(20) NOT NULL,
  `name` varchar(255) DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `color` varchar(255) DEFAULT NULL,
  `image_url` varchar(255) DEFAULT NULL,
  `duration` int(11) DEFAULT NULL CHECK (`duration` >= 0),
  `repetitions` int(11) DEFAULT NULL CHECK (`repetitions` >= 0),
  `sets` int(11) DEFAULT NULL CHECK (`sets` >= 0),
  `rest_time` int(11) DEFAULT NULL CHECK (`rest_time` >= 0),
  `execution_mode` varchar(255) DEFAULT NULL,
  `intensity_level` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dump dei dati per la tabella `exercises`
--

INSERT INTO `exercises` (`id`, `name`, `description`, `color`, `image_url`, `duration`, `repetitions`, `sets`, `rest_time`, `execution_mode`, `intensity_level`) VALUES
(4, 'Plank', 'Core stability exercise', '#FFA500', 'https://example.com/images/plank.jpg', 60, 0, 3, 15, 'Time', 5),
(5, 'Piegamenti', 'roba per petto', 'Blue', '', 60, 10, 3, 60, 'Time', 5);

-- --------------------------------------------------------

--
-- Struttura della tabella `exercises_equipments`
--

CREATE TABLE `exercises_equipments` (
  `id` bigint(20) NOT NULL,
  `exercise_id` bigint(20) NOT NULL,
  `equipment_id` bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dump dei dati per la tabella `exercises_equipments`
--

INSERT INTO `exercises_equipments` (`id`, `exercise_id`, `equipment_id`) VALUES
(2, 4, 1),
(1, 4, 2),
(3, 5, 8);

-- --------------------------------------------------------

--
-- Struttura della tabella `plans`
--

CREATE TABLE `plans` (
  `id` bigint(20) NOT NULL,
  `workout_id` bigint(20) NOT NULL,
  `user_id` bigint(20) NOT NULL,
  `scheduled_date` date NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Struttura della tabella `users`
--

CREATE TABLE `users` (
  `id` bigint(20) NOT NULL,
  `name` varchar(255) DEFAULT NULL,
  `username` varchar(255) DEFAULT NULL,
  `bio` varchar(255) DEFAULT NULL,
  `last_login` datetime DEFAULT NULL,
  `language_preference` varchar(255) DEFAULT NULL,
  `timezone` varchar(255) DEFAULT NULL,
  `is_active` varchar(255) DEFAULT NULL,
  `birth_date` datetime(6) DEFAULT NULL,
  `created_at` datetime DEFAULT current_timestamp(),
  `gender` varchar(255) DEFAULT NULL,
  `weight` float NOT NULL,
  `height` float NOT NULL,
  `waist` float NOT NULL,
  `role` varchar(255) DEFAULT NULL,
  `password_hash` varchar(255) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `phone_number` varchar(255) DEFAULT NULL,
  `street` varchar(255) DEFAULT NULL,
  `street_no` varchar(255) DEFAULT NULL,
  `zip_code` varchar(255) DEFAULT NULL,
  `country` varchar(255) DEFAULT NULL,
  `city` varchar(255) DEFAULT NULL,
  `province` varchar(255) DEFAULT NULL,
  `created_by` varchar(255) DEFAULT NULL,
  `updated_at` datetime DEFAULT current_timestamp() ON UPDATE current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dump dei dati per la tabella `users`
--

INSERT INTO `users` (`id`, `name`, `username`, `bio`, `last_login`, `language_preference`, `timezone`, `is_active`, `birth_date`, `created_at`, `gender`, `weight`, `height`, `waist`, `role`, `password_hash`, `email`, `phone_number`, `street`, `street_no`, `zip_code`, `country`, `city`, `province`, `created_by`, `updated_at`) VALUES
(1, 'PRO55VTTE', 're71', 'Bill foreign read discussion board teacher senior record sign of hour half claim.', '2025-04-24 07:50:09', 'en', 'Europe/Rome', '0', '1960-07-08 00:00:00.000000', '2023-12-23 06:44:54', 'F', 97.3, 198.66, 76.16, 'user', '001de625c87693af95a3a6f500583752db1d8b706a335149b602233ffe8264d3', 'jexample.com', '001469-077-2344x673', 'Sheryl Trail', '132', '61107', 'Holy See (Vatican City State)', 'Lake Juliefort', 'Alabama', '2', '2024-09-07 17:15:20'),
(2, 'Cory Gutierrez', 'shelleyyoung2', 'Amount ability local top scene sport style rise wonder computer skill.', '2025-06-16 22:14:47', 'fr', 'Europe/Berlin', '1', '1980-01-30 00:00:00.000000', '2023-06-25 03:29:10', 'M', 69.32, 194.34, 93.26, 'admin', 'f10d7fa61cd0303ff61eb38a51f3a9fc934d464e2cd582505a5e2bdb804ba1b4', 'shelleyyoung2@example.com', '+1-673-193-8043x8655', 'William River', '127', '65052', 'Marshall Islands', 'Lake Donaldshire', 'Wisconsin', '5', '2025-06-27 22:20:56'),
(3, 'Tammy Robbins', 'lindsay12', 'Resource task institution team magazine ahead above.', '2025-05-13 21:29:46', 'es', 'America/New_York', '1', '1965-06-28 00:00:00.000000', '2023-12-30 05:38:02', 'M', 83.42, 193.3, 103.45, 'admin', '47ed76dce552c268055f2344c017b7d986afc4416d6fef14a4daccaa8b0c93b2', 'lindsay12@example.com', '026.869.7359x19344', 'Kristina Locks', '70', '83938', 'Pakistan', 'West Leslieton', 'Tennessee', '2', '2024-09-01 02:24:26'),
(4, 'Kenneth Henson', 'josephbryan', 'Decide perform public final guy risk blue.', '2024-11-17 02:26:27', 'de', 'Europe/Rome', '0', '1958-07-08 00:00:00.000000', '2023-11-24 11:10:40', 'F', 94.64, 180.94, 71.74, 'user', '93c0211f08db8a7d3f0fbd1b95bdf0d7333d17c96e7c2d3402cf9c1ae2b5420d', 'josephbryan@example.com', '867-352-5084x00848', 'Torres Mountain', '101', '90271', 'Brazil', 'Gonzalesmouth', 'Tennessee', '3', '2025-03-07 16:36:37'),
(5, 'William Mcguire', 'brianperkins', 'Decade tell respond art dream understand.', '2024-10-30 21:10:04', 'fr', 'Asia/Tokyo', '1', '1979-07-28 00:00:00.000000', '2023-11-01 08:43:37', 'M', 85.72, 161.47, 87.79, 'admin', 'f12240cb78a9be3d0cb382e9b58aeea4adf390d03871bb3b0c4377ec5fbb8c07', 'brianperkins@example.com', '+1-334-938-9730x0713', 'Thomas Parkways', '159', '12994', 'South Africa', 'Port Zachary', 'Montana', '2', '2025-01-02 11:28:57'),
(6, 'Lori Owen', 'olivia67', 'Suddenly campaign media direction trouble population loss.', '2024-08-29 18:45:06', 'it', 'Europe/Berlin', '1', '1963-01-09 00:00:00.000000', '2023-10-13 13:01:03', 'F', 55.63, 172.26, 88.59, 'user', 'c268962c5970c22f82e8f04a3143739a7d2cb5d6e5a39cd6d49d2fc92e682b55', 'olivia67@example.com', '648-844-1056x98798', 'Taylor Mount', '67', '86199', 'United Kingdom', 'East Victoria', 'Wisconsin', '5', '2024-10-12 20:48:33'),
(7, 'Gary Glover', 'mariapierce', 'Production growth movie rather again interview save.', '2024-09-24 20:27:36', 'en', 'Europe/Berlin', '0', '1962-11-27 00:00:00.000000', '2023-11-19 21:06:38', 'F', 76.84, 193.73, 79.9, 'admin', 'bdfe7e6f93c33c6e6fc67459a2bc78b5d6657559f30254cbf8e01a17a8781815', 'mariapierce@example.com', '041-365-4941x21076', 'Cook Springs', '95', '87259', 'Paraguay', 'Lake Rebeccaborough', 'New Hampshire', '1', '2025-01-27 23:37:45'),
(8, 'Russell Patterson', 'ronald77', 'Option outside environment want evening economic.', '2024-12-15 04:45:30', 'it', 'Europe/Rome', '1', '1980-09-09 00:00:00.000000', '2023-07-02 22:42:11', 'F', 58.07, 189, 116.94, 'user', '18070eaa9f27673b5c4e8c12734e5f6d66f9cb5c3882db7c3ddc8a5e99f1c5b3', 'ronald77@example.com', '05008039264', 'Erickson Stravenue', '10', '33690', 'Jersey', 'South Tracy', 'Massachusetts', '1', '2025-07-04 06:39:33'),
(9, 'Bobby Holt', 'jamesjohnson', 'War central sure according music.', '2024-11-10 11:20:00', 'es', 'Europe/Berlin', '1', '1973-07-08 00:00:00.000000', '2023-06-16 02:30:34', 'M', 86.61, 160.6, 112.86, 'admin', 'e79b3e6f1e1d17107b4e8d400f7b1ed1e4d2c739b0067e1cb02d894b131ec00c', 'jamesjohnson@example.com', '+1-801-304-7110x3880', 'Lee Run', '140', '28015', 'Uganda', 'Port Destiny', 'Texas', '1', '2025-06-30 02:13:04'),
(10, 'Kristin Garrison', 'scott87', 'Single young training image wind.', '2025-04-20 11:34:18', 'de', 'Asia/Tokyo', '1', '1978-10-06 00:00:00.000000', '2023-07-24 15:56:16', 'F', 77.8, 180.01, 83.86, 'user', 'c6f869accf8c9f47e008a188bcd474f90a94dbd82f8f6b75917e37720225b154', 'scott87@example.com', '001-827-017-0960x084', 'Tanya Turnpike', '135', '00588', 'Cameroon', 'North Raymondstad', 'South Dakota', '2', '2024-12-18 06:14:04'),
(12, 'Bhhrett Lam', 'matteo271', 'Bill foreign read discussion board teacher senior record sign of hour half claim.', '2025-04-24 07:50:09', 'en', 'Europe/Rome', '0', '1960-07-08 00:00:00.000000', '2023-12-23 06:44:54', 'F', 97.3, 198.66, 76.16, 'user', '001de625c87693af95a3a6f500583752db1d8b706a335149b602233ffe8264d3', 'rebecca2k71@example.com', '001-469-077-2344x673', 'Sheryl Trail', '132', '61107', 'Holy See (Vatican City State)', 'Lake Juliefort', 'Alabama', '2', '2024-09-07 17:15:20'),
(15, 'PRO55VTTE', 'reca271', 'Bill foreign read discussion board teacher senior record sign of hour half claim.', '2025-04-24 07:50:09', 'en', 'Europe/Rome', '0', '1960-07-08 00:00:00.000000', '2023-12-23 06:44:54', 'F', 97.3, 198.66, 76.16, 'user', '001de625c87693af95a3a6f500583752db1d8b706a335149b602233ffe8264d3', 'rebeccaexample.com', '001-469-077-2344x673', 'Sheryl Trail', '132', '61107', 'Holy See (Vatican City State)', 'Lake Juliefort', 'Alabama', '2', '2024-09-07 17:15:20'),
(16, 'PRO55VTTE', 'rec271', 'Bill foreign read discussion board teacher senior record sign of hour half claim.', '2025-04-24 07:50:09', 'en', 'Europe/Rome', '0', '1960-07-08 00:00:00.000000', '2023-12-23 06:44:54', 'F', 97.3, 198.66, 76.16, 'user', '001de625c87693af95a3a6f500583752db1d8b706a335149b602233ffe8264d3', 'rebeccaexamphle.com', '001-469-077-2344x673', 'Sheryl Trail', '132', '61107', 'Holy See (Vatican City State)', 'Lake Juliefort', 'Alabama', '2', '2024-09-07 17:15:20');

-- --------------------------------------------------------

--
-- Struttura della tabella `workouts`
--

CREATE TABLE `workouts` (
  `id` bigint(20) NOT NULL,
  `name` varchar(255) DEFAULT NULL,
  `color` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dump dei dati per la tabella `workouts`
--

INSERT INTO `workouts` (`id`, `name`, `color`) VALUES
(1, 'Morning Cardio', '#FF5733'),
(2, 'Full Body Strength', '#33B5FF');

-- --------------------------------------------------------

--
-- Struttura della tabella `workouts_exercises`
--

CREATE TABLE `workouts_exercises` (
  `id` bigint(20) NOT NULL,
  `workout_id` bigint(20) NOT NULL,
  `exercise_id` bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dump dei dati per la tabella `workouts_exercises`
--

INSERT INTO `workouts_exercises` (`id`, `workout_id`, `exercise_id`) VALUES
(1, 1, 4),
(2, 2, 4);

--
-- Indici per le tabelle scaricate
--

--
-- Indici per le tabelle `equipments`
--
ALTER TABLE `equipments`
  ADD PRIMARY KEY (`id`);

--
-- Indici per le tabelle `exercises`
--
ALTER TABLE `exercises`
  ADD PRIMARY KEY (`id`);

--
-- Indici per le tabelle `exercises_equipments`
--
ALTER TABLE `exercises_equipments`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `exercise_id equipment_id` (`exercise_id`,`equipment_id`),
  ADD KEY `FKltwetl0a5r5i3tbjv6f4rfugy` (`equipment_id`);

--
-- Indici per le tabelle `plans`
--
ALTER TABLE `plans`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `uq_schedule` (`workout_id`,`user_id`,`scheduled_date`),
  ADD UNIQUE KEY `UKdksl760huwllq7wlnw8vqo65y` (`workout_id`,`user_id`,`scheduled_date`),
  ADD KEY `fk_schedules_user` (`user_id`);

--
-- Indici per le tabelle `users`
--
ALTER TABLE `users`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `username` (`username`),
  ADD UNIQUE KEY `email` (`email`);

--
-- Indici per le tabelle `workouts`
--
ALTER TABLE `workouts`
  ADD PRIMARY KEY (`id`);

--
-- Indici per le tabelle `workouts_exercises`
--
ALTER TABLE `workouts_exercises`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `workout_id exercise_id` (`workout_id`,`exercise_id`),
  ADD KEY `FKi7264u1fw298c0ysf28x39hs6` (`exercise_id`);

--
-- AUTO_INCREMENT per le tabelle scaricate
--

--
-- AUTO_INCREMENT per la tabella `equipments`
--
ALTER TABLE `equipments`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=9;

--
-- AUTO_INCREMENT per la tabella `exercises`
--
ALTER TABLE `exercises`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- AUTO_INCREMENT per la tabella `exercises_equipments`
--
ALTER TABLE `exercises_equipments`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT per la tabella `plans`
--
ALTER TABLE `plans`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT per la tabella `users`
--
ALTER TABLE `users`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=17;

--
-- AUTO_INCREMENT per la tabella `workouts`
--
ALTER TABLE `workouts`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT per la tabella `workouts_exercises`
--
ALTER TABLE `workouts_exercises`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- Limiti per le tabelle scaricate
--

--
-- Limiti per la tabella `exercises_equipments`
--
ALTER TABLE `exercises_equipments`
  ADD CONSTRAINT `FKltwetl0a5r5i3tbjv6f4rfugy` FOREIGN KEY (`equipment_id`) REFERENCES `equipments` (`id`),
  ADD CONSTRAINT `exercises_equipments_ibfk_1` FOREIGN KEY (`exercise_id`) REFERENCES `exercises` (`id`) ON DELETE CASCADE,
  ADD CONSTRAINT `exercises_equipments_ibfk_2` FOREIGN KEY (`equipment_id`) REFERENCES `equipments` (`id`) ON DELETE CASCADE;

--
-- Limiti per la tabella `plans`
--
ALTER TABLE `plans`
  ADD CONSTRAINT `fk_schedules_user` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`) ON DELETE CASCADE,
  ADD CONSTRAINT `fk_schedules_workout` FOREIGN KEY (`workout_id`) REFERENCES `workouts` (`id`) ON DELETE CASCADE;

--
-- Limiti per la tabella `workouts_exercises`
--
ALTER TABLE `workouts_exercises`
  ADD CONSTRAINT `FKi7264u1fw298c0ysf28x39hs6` FOREIGN KEY (`exercise_id`) REFERENCES `exercises` (`id`),
  ADD CONSTRAINT `workouts_exercises_ibfk_1` FOREIGN KEY (`workout_id`) REFERENCES `workouts` (`id`) ON DELETE CASCADE,
  ADD CONSTRAINT `workouts_exercises_ibfk_2` FOREIGN KEY (`exercise_id`) REFERENCES `exercises` (`id`) ON DELETE CASCADE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
