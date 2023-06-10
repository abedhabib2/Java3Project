-- phpMyAdmin SQL Dump
-- version 5.2.0
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Jun 10, 2023 at 07:55 PM
-- Server version: 10.4.27-MariaDB
-- PHP Version: 8.0.25

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `clinic_appointment`
--

-- --------------------------------------------------------

--
-- Table structure for table `appointments`
--

CREATE TABLE `appointments` (
  `appo_id` int(11) NOT NULL,
  `appo_date` date DEFAULT NULL,
  `appo_day` varchar(50) DEFAULT NULL,
  `appo_time` varchar(50) DEFAULT NULL,
  `appo_status` enum('free','booked') DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `appointments`
--

INSERT INTO `appointments` (`appo_id`, `appo_date`, `appo_day`, `appo_time`, `appo_status`) VALUES
(7, '2023-06-05', 'Saturday', '1:00', 'free'),
(8, '2023-06-23', 'Thursday', '1:38', 'free'),
(9, '2023-06-10', 'Wednesday', '10:11', 'free');

-- --------------------------------------------------------

--
-- Table structure for table `booked_appointments`
--

CREATE TABLE `booked_appointments` (
  `booked_id` int(11) NOT NULL,
  `appo_id` int(11) DEFAULT NULL,
  `user_id` int(11) DEFAULT NULL,
  `booked_status` enum('waiting','finished') DEFAULT NULL,
  `doctor_comment` varchar(50) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `booked_appointments`
--

INSERT INTO `booked_appointments` (`booked_id`, `appo_id`, `user_id`, `booked_status`, `doctor_comment`) VALUES
(12, 9, 7, 'waiting', 'No Comment'),
(13, 8, 7, 'finished', 'Finished Appointment');

-- --------------------------------------------------------

--
-- Table structure for table `users`
--

CREATE TABLE `users` (
  `user_id` int(11) NOT NULL,
  `username` varchar(50) DEFAULT NULL,
  `password` varchar(50) DEFAULT NULL,
  `firstname` varchar(50) DEFAULT NULL,
  `lastname` varchar(50) DEFAULT NULL,
  `age` int(3) DEFAULT NULL,
  `email` varchar(50) DEFAULT NULL,
  `phone` int(10) DEFAULT NULL,
  `gender` enum('male','female') DEFAULT NULL,
  `role` enum('admin','patient') DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `users`
--

INSERT INTO `users` (`user_id`, `username`, `password`, `firstname`, `lastname`, `age`, `email`, `phone`, `gender`, `role`) VALUES
(1, 'abed', '12345', 'Abed Al Rahman ', 'Habib', 20, 'abed@gmail.com', 4395473, 'male', 'admin'),
(2, 'moRabed', '12345', 'Mohammed', 'Abed', 19, 'morabed@gmail.com', 0, 'male', 'patient'),
(3, 'ahmed', '1234', 'Ahmed', 'Habib', 20, 'ahmed@gmail.com', 555555, 'male', 'admin'),
(5, 'ad', '12', 'New', 'Admin', 19, 'admin@gmail.com', 59474, 'male', 'admin'),
(6, 'ptien', '123456', 'NEW', 'patient', 11, 'p@gmail.com', 123, 'male', 'patient'),
(7, 'moabedh', '111', 'Mohammed', 'Abdo', 21, 'ab@gm.com', 299181, 'male', 'patient');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `appointments`
--
ALTER TABLE `appointments`
  ADD PRIMARY KEY (`appo_id`);

--
-- Indexes for table `booked_appointments`
--
ALTER TABLE `booked_appointments`
  ADD PRIMARY KEY (`booked_id`),
  ADD KEY `appo_id` (`appo_id`),
  ADD KEY `user_id` (`user_id`);

--
-- Indexes for table `users`
--
ALTER TABLE `users`
  ADD PRIMARY KEY (`user_id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `appointments`
--
ALTER TABLE `appointments`
  MODIFY `appo_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=10;

--
-- AUTO_INCREMENT for table `booked_appointments`
--
ALTER TABLE `booked_appointments`
  MODIFY `booked_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=14;

--
-- AUTO_INCREMENT for table `users`
--
ALTER TABLE `users`
  MODIFY `user_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=8;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `booked_appointments`
--
ALTER TABLE `booked_appointments`
  ADD CONSTRAINT `booked_appointments_ibfk_1` FOREIGN KEY (`appo_id`) REFERENCES `appointments` (`appo_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `booked_appointments_ibfk_2` FOREIGN KEY (`user_id`) REFERENCES `users` (`user_id`) ON DELETE CASCADE ON UPDATE CASCADE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
