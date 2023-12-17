-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Nov 30, 2023 at 09:48 AM
-- Server version: 10.4.28-MariaDB
-- PHP Version: 8.0.28

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `hostel_management_system`
--

-- --------------------------------------------------------

--
-- Table structure for table `accounts`
--

CREATE TABLE `accounts` (
  `account_id` int(11) NOT NULL,
  `username` varchar(50) NOT NULL,
  `password` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `accounts`
--

INSERT INTO `accounts` (`account_id`, `username`, `password`) VALUES
(14, 'Tom', '12345'),
(17, 'qwerty', '9000'),
(22, ' ', ' '),
(23, 'dfhvh', 'cbjvj');

-- --------------------------------------------------------

--
-- Table structure for table `caretakers`
--

CREATE TABLE `caretakers` (
  `Names` varchar(255) NOT NULL,
  `Phone_Number` varchar(20) NOT NULL,
  `Salary` decimal(10,2) NOT NULL,
  `No_of_Room` int(11) NOT NULL,
  `Block` varchar(10) NOT NULL,
  `ID_No` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `caretakers`
--

INSERT INTO `caretakers` (`Names`, `Phone_Number`, `Salary`, `No_of_Room`, `Block`, `ID_No`) VALUES
('Edward', '0736254382', 54000.00, 34, 'C', 56789),
('David', '0735620873', 6700.00, 6, 'B', 907974);

-- --------------------------------------------------------

--
-- Table structure for table `complaints`
--

CREATE TABLE `complaints` (
  `ComplaintID` int(11) NOT NULL,
  `StudentID` int(11) DEFAULT NULL,
  `Date` date DEFAULT NULL,
  `Description` varchar(255) DEFAULT NULL,
  `Status` varchar(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Table structure for table `facilities`
--

CREATE TABLE `facilities` (
  `FacilityID` int(11) NOT NULL,
  `FacilityName` varchar(50) DEFAULT NULL,
  `Description` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Table structure for table `hostelfees`
--

CREATE TABLE `hostelfees` (
  `FeeID` int(11) NOT NULL,
  `StudentID` int(11) DEFAULT NULL,
  `RoomID` int(11) DEFAULT NULL,
  `AdmissionFee` decimal(10,2) DEFAULT NULL,
  `TuitionFee` decimal(10,2) DEFAULT NULL,
  `MessFee` decimal(10,2) DEFAULT NULL,
  `TotalFee` decimal(10,2) DEFAULT NULL,
  `PaymentStatus` varchar(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Table structure for table `leaverequests`
--

CREATE TABLE `leaverequests` (
  `RequestID` int(11) NOT NULL,
  `StudentID` int(11) DEFAULT NULL,
  `DateFrom` date DEFAULT NULL,
  `DateTo` date DEFAULT NULL,
  `Reason` varchar(255) DEFAULT NULL,
  `Status` varchar(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Table structure for table `messmenu`
--

CREATE TABLE `messmenu` (
  `MenuID` int(11) NOT NULL,
  `Date` date DEFAULT NULL,
  `Breakfast` varchar(255) DEFAULT NULL,
  `Lunch` varchar(255) DEFAULT NULL,
  `Dinner` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Table structure for table `rooms`
--

CREATE TABLE `rooms` (
  `BlockNo` varchar(255) DEFAULT NULL,
  `RoomNo` varchar(255) DEFAULT NULL,
  `NoOfStudents` int(11) DEFAULT NULL,
  `Beds` int(11) DEFAULT NULL,
  `Chairs` int(11) DEFAULT NULL,
  `Curtains` varchar(3) DEFAULT NULL,
  `AccommodationFee` decimal(10,2) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Table structure for table `students`
--

CREATE TABLE `students` (
  `RegNo` int(11) NOT NULL,
  `Name` varchar(255) NOT NULL,
  `PhoneNumber` varchar(20) NOT NULL,
  `ParentPhoneNumber` varchar(20) NOT NULL,
  `Room` varchar(10) NOT NULL,
  `Block` varchar(10) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `students`
--

INSERT INTO `students` (`RegNo`, `Name`, `PhoneNumber`, `ParentPhoneNumber`, `Room`, `Block`) VALUES
(345, 'Shadrack Muema', '0755512934', '0728352378', '10', 'A'),
(5678, 'Jona', '0747264582', '0726543728', '90', 'C'),
(65235, 'Calvins', '073276528', '073857628', '98', 'D'),
(67890, 'Tom Naurori', '073634321', '076558765', '02', 'B');

-- --------------------------------------------------------

--
-- Table structure for table `visitorlog`
--

CREATE TABLE `visitorlog` (
  `LogID` int(11) NOT NULL,
  `StudentID` int(11) DEFAULT NULL,
  `VisitorName` varchar(100) DEFAULT NULL,
  `Date` date DEFAULT NULL,
  `Purpose` varchar(255) DEFAULT NULL,
  `InTime` time DEFAULT NULL,
  `OutTime` time DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Indexes for dumped tables
--

--
-- Indexes for table `accounts`
--
ALTER TABLE `accounts`
  ADD PRIMARY KEY (`account_id`),
  ADD UNIQUE KEY `username` (`username`);

--
-- Indexes for table `caretakers`
--
ALTER TABLE `caretakers`
  ADD PRIMARY KEY (`ID_No`);

--
-- Indexes for table `complaints`
--
ALTER TABLE `complaints`
  ADD PRIMARY KEY (`ComplaintID`);

--
-- Indexes for table `facilities`
--
ALTER TABLE `facilities`
  ADD PRIMARY KEY (`FacilityID`);

--
-- Indexes for table `hostelfees`
--
ALTER TABLE `hostelfees`
  ADD PRIMARY KEY (`FeeID`);

--
-- Indexes for table `leaverequests`
--
ALTER TABLE `leaverequests`
  ADD PRIMARY KEY (`RequestID`);

--
-- Indexes for table `messmenu`
--
ALTER TABLE `messmenu`
  ADD PRIMARY KEY (`MenuID`);

--
-- Indexes for table `students`
--
ALTER TABLE `students`
  ADD PRIMARY KEY (`RegNo`);

--
-- Indexes for table `visitorlog`
--
ALTER TABLE `visitorlog`
  ADD PRIMARY KEY (`LogID`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `accounts`
--
ALTER TABLE `accounts`
  MODIFY `account_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=24;

--
-- AUTO_INCREMENT for table `complaints`
--
ALTER TABLE `complaints`
  MODIFY `ComplaintID` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `facilities`
--
ALTER TABLE `facilities`
  MODIFY `FacilityID` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `leaverequests`
--
ALTER TABLE `leaverequests`
  MODIFY `RequestID` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `messmenu`
--
ALTER TABLE `messmenu`
  MODIFY `MenuID` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `visitorlog`
--
ALTER TABLE `visitorlog`
  MODIFY `LogID` int(11) NOT NULL AUTO_INCREMENT;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
