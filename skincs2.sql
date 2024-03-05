-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Oct 08, 2023 at 05:13 PM
-- Server version: 10.4.28-MariaDB
-- PHP Version: 8.1.17

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `shopping`
--

-- --------------------------------------------------------

--
-- Table structure for table `categories`
--

CREATE TABLE `categories` (
  `id` int(11) NOT NULL,
  `name` varchar(250) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_swedish_ci;

--
-- Dumping data for table `categories`
--

INSERT INTO `categories` (`id`, `name`) VALUES
(1, 'Smart Phone'),
(2, 'Laptop'),
(3, 'Smart TV'),
(4, 'Watch');

-- --------------------------------------------------------

--
-- Table structure for table `products`
--

CREATE TABLE `products` (
  `id` int(11) NOT NULL,
  `name` varchar(250) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `image` varchar(250) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `price` double NOT NULL DEFAULT 0,
  `quantity` int(6) NOT NULL DEFAULT 0,
  `status` tinyint(1) NOT NULL DEFAULT 0,
  `id_category` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_swedish_ci;

--
-- Dumping data for table `products`
--

INSERT INTO `products` (`id`, `name`, `image`, `price`, `quantity`, `status`, `id_category`) VALUES
(1, 'Apple iPhone 12 Pro 6.1 inches RAM 6GB 512GB Unlocked', 'Apple-iPhone-12-Pro-6.1-inches-RAM-6GB-512GB-Unlocked.png', 500, 10, 0, 1),
(2, 'Apple iPhone 13 - 128GB', 'Apple-iPhone-13-128GB.png', 550, 5, 1, 1),
(3, 'Apple iPhone 13 (256GB)', 'Apple iPhone 13 (256GB).png', 610, 10, 0, 1),
(4, 'Apple iPhone 13 Pro (512GB)', 'Apple iPhone 13 Pro (512GB).png', 650, 10, 1, 1),
(5, 'Apple iPhone 14 (256 GB)', 'Apple iPhone 14 (256 GB).png', 800, 10, 1, 1),
(6, 'Apple iPhone 14 (512GB)', 'Apple iPhone 14 (512GB).png', 850, 20, 0, 1),
(7, 'Apple iMac 2021 M1 24 inches 7 Core GPU - 256GB', 'Apple-iMac-2021-M1-24-inches-7-Core-GPU-256GB.png', 1500, 5, 0, 2),
(8, 'Apple iMac 2021 M1 24 inches 8 Core GPU - 512GB', 'Apple-iMac-2021-M1-24-inches-8-Core-GPU-512GB.png', 1600, 10, 1, 2),
(9, 'Laptop Asus ExpertBook P2451FA-EK3299T, i3-10110U_4GB - 256GB-SSD', 'Laptop Asus ExpertBook P2451FA-EK3299T, i3-10110U_4GB - 256GB-SSD.png', 200, 5, 0, 2),
(10, 'Laptop ASUS Gaming TUF Dash F15 FX517ZE-HN045W', 'Laptop ASUS Gaming TUF Dash F15 FX517ZE-HN045W.png', 300, 10, 1, 2),
(11, 'Smart Tivi Casper 4K 43 inches (43UGS611)', 'Smart Tivi Casper 4K 43 inches (43UGS611).png', 350, 10, 1, 3),
(12, 'Smart Tivi Casper HD 32 inches (32HGS610)', 'Smart Tivi Casper HD 32 inches (32HGS610).png', 250, 10, 0, 3),
(13, 'Smart Tivi Itel 4K 50 inches (G5057)', 'Smart Tivi Itel 4K 50 inches (G5057).png', 500, 5, 1, 3),
(14, 'Smart Tivi Itel FHD 43 inches (G4357)', 'Smart Tivi Itel FHD 43 inches (G4357).png', 370, 10, 0, 3),
(15, 'Apple Watch SE 2022 GPS + Cellular, 40mm', 'Apple Watch SE 2022 GPS + Cellular, 40mm.png', 100, 10, 0, 4),
(16, 'Apple Watch SE GPS, 44mm Aluminum Case with Sport Band', 'Apple Watch SE GPS, 44mm Aluminum Case with Sport Band.png', 110, 5, 1, 4),
(17, 'Apple Watch Series 7 GPS, 41mm', 'Apple Watch Series 7 GPS, 41mm.png', 95, 10, 0, 4),
(18, 'Apple Watch Series 8 GPS + Cellular, 41mm', 'Apple Watch Series 8 GPS + Cellular, 41mm.png', 120, 20, 1, 4),
(19, 'Laptop Asus VivoBook A415EA-EB1474W - (i5 1135G7 - 8GB RAM - 512GB)', 'Laptop Asus VivoBook A415EA-EB1474W - (i5 1135G7 - 8GB RAM - 512GB).png', 650, 10, 1, 2),
(20, 'Laptop HUAWEI MateBook D 16 (i5-12450H-16GB 512GB)', 'Laptop HUAWEI MateBook D 16 (i5-12450H-16GB 512GB).png', 700, 5, 1, 2),
(21, 'Laptop Lenovo IdeaPad 3 14IAU7-82RJ001BVN- i5-1235U - 8GB - 512GB', 'Laptop Lenovo IdeaPad 3 14IAU7-82RJ001BVN- i5-1235U - 8GB - 512GB.png', 350, 10, 0, 2),
(22, 'Laptop LG Gram 2022 16ZD90Q-GAX72A5 (Core i7-1260P_16GB 256GB )', 'Laptop LG Gram 2022 16ZD90Q-GAX72A5 (Core i7-1260P_16GB 256GB ).png', 720, 5, 1, 2),
(23, 'Laptop Microsoft Surface Pro 7 12.3 inches - i5_8GB 128GB', 'Laptop Microsoft Surface Pro 7 12.3 inches - i5_8GB 128GB.png', 510, 10, 0, 2),
(24, 'Laptop MSI Alpha 17 B5EEK-031VN, R7-5800H - 8GB Ram- 512GB - RX6600M 17.3FHD', 'Laptop MSI Alpha 17 B5EEK-031VN, R7-5800H - 8GB Ram- 512GB - RX6600M 17.3FHD.png', 660, 5, 1, 2),
(25, 'Laptop MSI Modern 15 A5M - 237VN - R7-5700U-8GB - 512GB', 'Laptop MSI Modern 15 A5M - 237VN - R7-5700U-8GB - 512GB.png', 420, 5, 0, 2),
(26, 'Laptop MSI Modern 15 A11M - 1024VN - i5 - 1155G7 - 8GB - 512GB SSD', 'Laptop MSI Modern 15 A11M - 1024VN - i5 - 1155G7 - 8GB - 512GB SSD.png', 520, 10, 1, 2),
(27, 'Apple IPhone 14 Pro max 512GB Dual SIM Nano original unlocked', 'Apple IPhone 14 Pro max 512GB Dual SIM Nano original unlocked.png', 1020, 10, 1, 1),
(28, 'Apple Iphone 14 Promax, 6.7 inch 256GB,8GB, Unlocked Cell Phone', 'Apple Iphone 14 Promax, 6.7 inch 256GB,8GB, Unlocked Cell Phone.png', 1150, 5, 1, 1),
(29, 'OPPO A55', 'OPPO A55.png', 210, 10, 0, 1),
(30, 'Realme C33 4GB, store 64GB', 'Realme C33 4GB, store 64GB.png', 260, 5, 1, 1),
(31, 'Smart Tivi Samsung Crystal UHD 4K 50 inch UA50AU8000KXXV', 'Smart Tivi Samsung Crystal UHD 4K 50 inch UA50AU8000KXXV.png', 400, 10, 1, 3),
(32, 'Smart Tivi Xiaomi A2 43 inch', 'Smart Tivi Xiaomi A2 43 inch.png', 220, 10, 0, 3),
(33, 'Smart TV Samsung 4K Neo QLED 65 inch QA65QN85AAKXXV 2021', 'Smart TV Samsung 4K Neo QLED 65 inch QA65QN85AAKXXV 2021.png', 510, 5, 1, 3),
(34, 'Smart TV Samsung 4K QLED 50 inch QA50Q60AAKXXV', 'Smart TV Samsung 4K QLED 50 inch QA50Q60AAKXXV.png', 340, 5, 1, 3),
(35, 'Smart TV Xiaomi 4K P1 55 inch', 'Smart TV Xiaomi 4K P1 55 inch.png', 410, 10, 1, 3),
(36, 'Tivi Smart Tivi Itel HD 32 inches (G3257)', 'Tivi Smart Tivi Itel HD 32 inches (G3257).png', 130, 10, 0, 3),
(37, 'Apple Watch Series 8 GPS 41mm', 'Apple Watch Series 8 GPS 41mm.png', 160, 10, 1, 4),
(38, 'Forerunner 255S watch', 'Forerunner 255S watch.png', 250, 10, 1, 4),
(39, 'Garmin Fenix 7S Sapphire Solar', 'Garmin Fenix 7S Sapphire Solar.png', 310, 10, 1, 4),
(40, 'Garmin Forerunner 45', 'Garmin Forerunner 45.png', 160, 10, 0, 4),
(41, 'Garmin Forerunner 255', 'Garmin Forerunner 255.png', 190, 10, 0, 4),
(42, 'Garmin Forerunner 955', 'Garmin Forerunner 955.png', 290, 10, 1, 4),
(43, 'Garmin MARQ Athlete (Gen 2)', 'Garmin MARQ Athlete (Gen 2).png', 230, 10, 1, 4),
(44, 'Garmin MARQ Captain (Gen 2)', 'Garmin MARQ Captain (Gen 2).png', 270, 10, 0, 4);

-- --------------------------------------------------------

--
-- Table structure for table `users`
--

CREATE TABLE `users` (
  `id` int(11) NOT NULL,
  `name` varchar(250) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `email` varchar(250) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `phone` varchar(15) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `password` varchar(250) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_swedish_ci;

--
-- Indexes for dumped tables
--

--
-- Indexes for table `categories`
--
ALTER TABLE `categories`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `products`
--
ALTER TABLE `products`
  ADD PRIMARY KEY (`id`),
  ADD KEY `id_category` (`id_category`);

--
-- Indexes for table `users`
--
ALTER TABLE `users`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `categories`
--
ALTER TABLE `categories`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT for table `products`
--
ALTER TABLE `products`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=45;

--
-- AUTO_INCREMENT for table `users`
--
ALTER TABLE `users`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `products`
--
ALTER TABLE `products`
  ADD CONSTRAINT `products_ibfk_1` FOREIGN KEY (`id_category`) REFERENCES `categories` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
