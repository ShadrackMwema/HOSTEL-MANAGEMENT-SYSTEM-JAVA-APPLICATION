-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: localhost
-- Generation Time: Sep 07, 2024 at 02:50 PM
-- Server version: 10.4.28-MariaDB
-- PHP Version: 8.2.4

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
(23, 'dfhvh', 'cbjvj'),
(25, 'Denno', 'denno'),
(26, 'shady', 'shady');

-- --------------------------------------------------------

--
-- Table structure for table `attendance`
--

CREATE TABLE `attendance` (
  `Name` varchar(255) NOT NULL,
  `RegNo` int(255) NOT NULL,
  `PhoneNumber` int(255) NOT NULL,
  `ParentPhoneNumber` int(255) NOT NULL,
  `Room` int(255) NOT NULL,
  `Block` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `attendance`
--

INSERT INTO `attendance` (`Name`, `RegNo`, `PhoneNumber`, `ParentPhoneNumber`, `Room`, `Block`) VALUES
('Hezron', 35543, 798354723, 735626765, 23, 'C'),
('Jona', 87654, 7346679, 76842347, 67, 'A'),
('Tom', 34566, 75654345, 78645534, 45, 'Q');

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
('jONA', '074514756', 98000.00, 45, 'Z', 21435),
('David', '0735620873', 6700.00, 6, 'B', 907974),
('Mutisya', '0753465723', 59000.00, 23, 'C', 3547675);

-- --------------------------------------------------------

--
-- Table structure for table `Complaints`
--

CREATE TABLE `Complaints` (
  `Name` varchar(255) NOT NULL,
  `PhoneNumber` int(255) NOT NULL,
  `Date` int(255) NOT NULL,
  `Complaints` int(255) NOT NULL,
  `Status` varchar(255) NOT NULL,
  `RegNo` int(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `Complaints`
--

INSERT INTO `Complaints` (`Name`, `PhoneNumber`, `Date`, `Complaints`, `Status`, `RegNo`) VALUES
('Water Insufficiency', 60, 7000, 1002, 'Unattended', 75),
('Electricity', 45, 5000, 2343, 'Attended', 75764372);

-- --------------------------------------------------------

--
-- Table structure for table `Facilities`
--

CREATE TABLE `Facilities` (
  `Name` varchar(255) NOT NULL,
  `FacilityID` int(255) NOT NULL,
  `Maintenaces` int(255) NOT NULL,
  `CaretakerID` int(255) NOT NULL,
  `Rooms` int(255) NOT NULL,
  `Block` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `Facilities`
--

INSERT INTO `Facilities` (`Name`, `FacilityID`, `Maintenaces`, `CaretakerID`, `Rooms`, `Block`) VALUES
('Cinema', 6, 7, 234, 43, '5'),
('Mess', 3, 8, 21, 50, '7');

-- --------------------------------------------------------

--
-- Table structure for table `leaverrequests`
--

CREATE TABLE `leaverrequests` (
  `RegNo` varchar(255) NOT NULL,
  `Name` varchar(255) NOT NULL,
  `PhoneNumber` int(255) NOT NULL,
  `Date` date NOT NULL,
  `Reason` varchar(255) NOT NULL,
  `Status` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `leaverrequests`
--

INSERT INTO `leaverrequests` (`RegNo`, `Name`, `PhoneNumber`, `Date`, `Reason`, `Status`) VALUES
('2334', 'Shadrack', 75774434, '0008-05-23', 'Sick', 'Active');

-- --------------------------------------------------------

--
-- Table structure for table `messmenu`
--

CREATE TABLE `messmenu` (
  `FoodName` varchar(255) NOT NULL,
  `FoodId` varchar(255) NOT NULL,
  `SerialNumber` decimal(65,0) NOT NULL,
  `Price` int(255) NOT NULL,
  `Quantity` varchar(255) NOT NULL,
  `Times` int(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `messmenu`
--

INSERT INTO `messmenu` (`FoodName`, `FoodId`, `SerialNumber`, `Price`, `Quantity`, `Times`) VALUES
('Jona', '0756064543', 743234534, 12, 'A', 123321),
('Japheth', '56345', 32, 23, '5', 45342);

-- --------------------------------------------------------

--
-- Table structure for table `Rooms`
--

CREATE TABLE `Rooms` (
  `Block` varchar(255) NOT NULL,
  `RoomID` int(255) NOT NULL,
  `NoOfStudents` int(255) NOT NULL,
  `AccomodationFee` int(255) NOT NULL,
  `CareTakerID` int(255) NOT NULL,
  `Gents_Ladies` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `Rooms`
--

INSERT INTO `Rooms` (`Block`, `RoomID`, `NoOfStudents`, `AccomodationFee`, `CareTakerID`, `Gents_Ladies`) VALUES
('A', 743768576, 39486, 6, 9, '735532463'),
('B', 65000, 53685, 7, 2, '98'),
('V', 704344, 32423, 4, 2, '23');

-- --------------------------------------------------------

--
-- Table structure for table `Security`
--

CREATE TABLE `Security` (
  `Name` varchar(255) NOT NULL,
  `ID_No` int(255) NOT NULL,
  `PhoneNumber` int(255) NOT NULL,
  `Salary` int(255) NOT NULL,
  `No_Of_Rooms` int(255) NOT NULL,
  `Block` varchar(255) NOT NULL
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
(3456145, 'Stev', '072474567', '076256789', '78', 'a'),
(24358956, 'DEnnis', '0736472873', '0723647298', '9', 'A'),
(45576845, 'Dante', '074434546', '074348763', '34', 'Q'),
(56235540, 'Jonathan', '07545342', '0743314346', '54', 'A'),
(87654769, 'Tom', '07743447567', '074435578567', '34', 'C'),
(94867543, 'Shad', '0746487627', '0746287839', '1', 'A');

-- --------------------------------------------------------

--
-- Table structure for table `Visitorlog`
--

CREATE TABLE `Visitorlog` (
  `ID_No` int(255) NOT NULL,
  `Name` varchar(255) NOT NULL,
  `PhoneNumber` int(255) NOT NULL,
  `Date` date NOT NULL,
  `Reason` varchar(255) NOT NULL,
  `Room` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `Visitorlog`
--

INSERT INTO `Visitorlog` (`ID_No`, `Name`, `PhoneNumber`, `Date`, `Reason`, `Room`) VALUES
(3654284, 'Shimita', 712745891, '0002-12-23', 'Visiting a son', 'B'),
(24364, 'Jona', 3454353, '2023-12-09', 'To see a neighbour', 'A');

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
-- Indexes for table `students`
--
ALTER TABLE `students`
  ADD PRIMARY KEY (`RegNo`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `accounts`
--
ALTER TABLE `accounts`
  MODIFY `account_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=28;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
