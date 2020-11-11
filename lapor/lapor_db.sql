-- phpMyAdmin SQL Dump
-- version 4.9.0.1
-- https://www.phpmyadmin.net/
--
-- Host: localhost
-- Generation Time: Nov 11, 2020 at 02:24 PM
-- Server version: 10.4.6-MariaDB
-- PHP Version: 7.1.31

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `lapor_db`
--

-- --------------------------------------------------------

--
-- Table structure for table `foto_wisata`
--

CREATE TABLE `foto_wisata` (
  `id` int(10) NOT NULL,
  `nama_foto` varchar(200) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Dumping data for table `foto_wisata`
--

INSERT INTO `foto_wisata` (`id`, `nama_foto`) VALUES
(1, 'Screenshot_20200707-185239.png'),
(2, 'IMG-20200707-WA0008.jpg'),
(3, 'Screenshot_20200707-185206.png'),
(4, 'IMG-20200707-WA0006.jpg'),
(5, 'IMG-20200707-WA0005.jpg'),
(6, 'Screenshot_20200707-115924.png'),
(7, 'IMG-20200706-WA0004.jpg'),
(8, 'IMG_20200707_212321.jpg'),
(9, 'IMG_20200526_134648.jpg'),
(18, 'IMG-20201110-WA0005.jpg'),
(17, 'faq');

-- --------------------------------------------------------

--
-- Table structure for table `tb_lapor`
--

CREATE TABLE `tb_lapor` (
  `id` int(5) NOT NULL,
  `nama_pelapor` varchar(200) NOT NULL,
  `lokasi` varchar(200) NOT NULL,
  `deksripsi_lapor` varchar(200) NOT NULL,
  `gambar` varchar(200) NOT NULL,
  `tanggal_lapor` varchar(200) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `tb_lapor`
--

INSERT INTO `tb_lapor` (`id`, `nama_pelapor`, `lokasi`, `deksripsi_lapor`, `gambar`, `tanggal_lapor`) VALUES
(1, '', '', '', '', ''),
(2, 'basri', 'a', 'a', 'a', '12'),
(3, 'basri Umar', 'a', 'a', 'a', '12'),
(4, 'basri 2', 'a', 'a', 'a', '12'),
(5, 'basri 2ad', 'a', 'a', 'a', '12'),
(6, 'basri 2ad', 'a', 'a', 'a', '12'),
(7, 'saya', 'jl syahdan', 'kecelekaan', 'path gambar', '10 November'),
(8, 'saya 2', 'jl syahdan', 'banjir', 'ahahah', '10 November'),
(9, 'ahahha', 'ahahaj', 'ahahah', 'ababa', 'Babab'),
(10, 'ahahah', 'ahahah', 'ahahah', 'ababa', 'ababab'),
(11, 'basri 2ad', 'a', 'a', 'a', '12');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `foto_wisata`
--
ALTER TABLE `foto_wisata`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `tb_lapor`
--
ALTER TABLE `tb_lapor`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `foto_wisata`
--
ALTER TABLE `foto_wisata`
  MODIFY `id` int(10) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=19;

--
-- AUTO_INCREMENT for table `tb_lapor`
--
ALTER TABLE `tb_lapor`
  MODIFY `id` int(5) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=12;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
