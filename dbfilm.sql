-- phpMyAdmin SQL Dump
-- version 5.1.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Nov 21, 2021 at 01:49 PM
-- Server version: 10.4.21-MariaDB
-- PHP Version: 8.0.11

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `dbfilm`
--

-- --------------------------------------------------------

--
-- Table structure for table `anggota`
--

CREATE TABLE `anggota` (
  `IDAnggota` varchar(8) NOT NULL,
  `nama` varchar(40) NOT NULL,
  `tempat_lahir` varchar(15) NOT NULL,
  `tgl_lahir` date NOT NULL,
  `jkl` varchar(15) NOT NULL,
  `status` varchar(15) NOT NULL,
  `alamat` varchar(50) NOT NULL,
  `telp` varchar(15) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `anggota`
--

INSERT INTO `anggota` (`IDAnggota`, `nama`, `tempat_lahir`, `tgl_lahir`, `jkl`, `status`, `alamat`, `telp`) VALUES
('001', 'Ahmad', 'Pelaihari', '2021-11-04', 'Pria', 'Pelajar', 'Jl.Kenanga No 22', '08586743221'),
('002', 'annisa', 'anjir', '2017-11-10', 'Wanita', 'mahasiswa', 'jl.mahakam', '082234567788'),
('003', 'Yusuf', 'Kapuas', '1999-02-16', 'Pria', 'Mahasiswa', 'Jl.Sultan Adam No 22', '082252245056');

-- --------------------------------------------------------

--
-- Table structure for table `film`
--

CREATE TABLE `film` (
  `KodeFilm` varchar(5) NOT NULL,
  `Judul` varchar(30) NOT NULL,
  `Genre` varchar(20) NOT NULL,
  `Tahun` int(4) NOT NULL,
  `Asal` varchar(15) NOT NULL,
  `Stok` int(3) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `film`
--

INSERT INTO `film` (`KodeFilm`, `Judul`, `Genre`, `Tahun`, `Asal`, `Stok`) VALUES
('01', 'Captain', 'Actions', 2010, 'ID', 22),
('02', 'The Iron Man', 'Action', 2019, 'USA', 20),
('03', 'Impossible', 'Action', 2018, 'USA', 22),
('04', 'Naruto', 'anime', 2000, 'Jepun', 30);

-- --------------------------------------------------------

--
-- Table structure for table `pinjam`
--

CREATE TABLE `pinjam` (
  `IDPinjam` varchar(8) NOT NULL,
  `IDAnggota` varchar(8) NOT NULL,
  `TotalDenda` int(6) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `pinjam`
--

INSERT INTO `pinjam` (`IDPinjam`, `IDAnggota`, `TotalDenda`) VALUES
('1', '001', 25000),
('2', '002', 15000);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `anggota`
--
ALTER TABLE `anggota`
  ADD PRIMARY KEY (`IDAnggota`);

--
-- Indexes for table `film`
--
ALTER TABLE `film`
  ADD PRIMARY KEY (`KodeFilm`);

--
-- Indexes for table `pinjam`
--
ALTER TABLE `pinjam`
  ADD PRIMARY KEY (`IDPinjam`),
  ADD KEY `IDAnggota` (`IDAnggota`);

--
-- Constraints for dumped tables
--

--
-- Constraints for table `pinjam`
--
ALTER TABLE `pinjam`
  ADD CONSTRAINT `pinjam_ibfk_1` FOREIGN KEY (`IDAnggota`) REFERENCES `anggota` (`IDAnggota`) ON DELETE CASCADE ON UPDATE CASCADE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
