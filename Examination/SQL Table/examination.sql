-- phpMyAdmin SQL Dump
-- version 5.2.0
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: May 30, 2023 at 11:28 AM
-- Server version: 10.4.27-MariaDB
-- PHP Version: 8.1.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `examination`
--

-- --------------------------------------------------------

--
-- Table structure for table `answer_options`
--

CREATE TABLE `answer_options` (
  `option_id` int(11) NOT NULL,
  `question_id` int(11) DEFAULT NULL,
  `option_text` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `answer_options`
--

INSERT INTO `answer_options` (`option_id`, `question_id`, `option_text`) VALUES
(1, 1, 'Delhi'),
(2, 1, 'Paris'),
(3, 1, 'Alaska'),
(4, 1, 'Brazil'),
(5, 2, 'Jupiter'),
(6, 2, 'Venus'),
(7, 2, 'Mars'),
(8, 2, 'Pluto'),
(9, 3, 'Pacific Ocean'),
(10, 3, 'Arctic Ocean'),
(11, 3, 'Atlantic Ocean'),
(12, 3, 'Indian Ocean'),
(13, 4, 'Leonardo da Vinci'),
(14, 4, 'Pablo Picasso'),
(15, 4, 'Vincent van Gogh'),
(16, 4, 'Michelangelo'),
(17, 5, 'Au'),
(18, 5, 'Ag'),
(19, 5, 'G'),
(20, 5, 'Go'),
(21, 6, 'Mount Everest'),
(22, 6, 'K2'),
(23, 6, 'Kangchenjunga'),
(24, 6, 'Makalu'),
(25, 7, 'Yen'),
(26, 7, 'Dollar'),
(27, 7, 'Pound'),
(28, 7, 'Euro'),
(29, 8, 'Skin'),
(30, 8, 'Heart'),
(31, 8, 'Liver'),
(32, 8, 'Lungs'),
(33, 9, 'Japan'),
(34, 9, 'China'),
(35, 9, 'South Korea'),
(36, 9, 'Thailand'),
(37, 10, '2005'),
(38, 10, '2006'),
(39, 10, '2007'),
(40, 10, '2008');

-- --------------------------------------------------------

--
-- Table structure for table `login`
--

CREATE TABLE `login` (
  `firstname` varchar(20) NOT NULL,
  `lastname` varchar(20) NOT NULL,
  `username` varchar(20) NOT NULL,
  `password` varchar(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `login`
--

INSERT INTO `login` (`firstname`, `lastname`, `username`, `password`) VALUES
('suraj', 'somani', 'admin', '123456');

-- --------------------------------------------------------

--
-- Table structure for table `questions`
--

CREATE TABLE `questions` (
  `question_id` int(20) NOT NULL,
  `question_text` varchar(255) NOT NULL,
  `correct_answer_index` int(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `questions`
--

INSERT INTO `questions` (`question_id`, `question_text`, `correct_answer_index`) VALUES
(1, 'What is the capital of France?', 2),
(2, 'Which planet is known as the Red Planet?', 3),
(3, 'What is the largest ocean on Earth?', 3),
(4, 'Who painted the Mona Lisa?', 1),
(5, 'What is the chemical symbol for gold?', 1),
(6, 'What is the tallest mountain in the world?', 1),
(7, 'What is the currency of Japan?', 1),
(8, 'What is the largest organ in the human body?', 1),
(9, 'Which country is known as the Land of the Rising Sun?', 1),
(10, 'Which year was the first iPhone released?', 4);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `answer_options`
--
ALTER TABLE `answer_options`
  ADD PRIMARY KEY (`option_id`),
  ADD KEY `question_id` (`question_id`);

--
-- Indexes for table `login`
--
ALTER TABLE `login`
  ADD PRIMARY KEY (`username`);

--
-- Indexes for table `questions`
--
ALTER TABLE `questions`
  ADD PRIMARY KEY (`question_id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `answer_options`
--
ALTER TABLE `answer_options`
  MODIFY `option_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=41;

--
-- AUTO_INCREMENT for table `questions`
--
ALTER TABLE `questions`
  MODIFY `question_id` int(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=11;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `answer_options`
--
ALTER TABLE `answer_options`
  ADD CONSTRAINT `answer_options_ibfk_1` FOREIGN KEY (`question_id`) REFERENCES `questions` (`question_id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
