--
-- Database: `ff`
--

-- --------------------------------------------------------

--
-- Table structure for table `Orders`
--

CREATE TABLE IF NOT EXISTS `Orders` (
  `id` bigint(11) NOT NULL auto_increment,
  `supplier` varchar(255) character set latin1 default NULL,
  `productlist` text character set latin1,
  `finished` text character set latin1 NOT NULL,
  `approved` text character set latin1 NOT NULL,
  `date` date DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Table structure for table `Sales`
--

CREATE TABLE IF NOT EXISTS `Sales` (
  `id` bigint(11) NOT NULL auto_increment,
  `customer` varchar(255) character set latin1 default NULL,
  `productlist` text character set latin1,
  `date` date DEFAULT NULL,
  `price` decimal(5,2) DEFAULT NULL,
  `finished` text character set latin1 NOT NULL
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `Staff`
--

CREATE TABLE IF NOT EXISTS `Staff` (
  `employeeName` varchar(255) DEFAULT NULL,
  `username` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL
  `permission` tinyint(4) unsigned DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Dumping data for table `Staff`
--

INSERT INTO `Staff` (`employeeName`, `username`, `password`, `permission`) VALUES
(Fernando Navarro, 'admin', 'admin', 1);

--
-- Table structure for table `Stock`
--

CREATE TABLE IF NOT EXISTS `Stock` (
  `id` int(11) NOT NULL,
  `name` varchar(255) DEFAULT NULL,
  `tag` varchar(255) DEFAULT NULL,
  `amount` tinyint(3) unsigned DEFAULT NULL,
  `price` decimal(5,2) DEFAULT NULL,
  PRIMARY KEY (`Id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1

-- --------------------------------------------------------

--
-- Dumping data for table `Stock`
--

INSERT INTO `Stock` (`id`, `name`, `tag`, `amount`, `price`) VALUES
(0, 'Hamburguesa de carne', 'HAMBURGUESAS' , 39, 6.3),
(3, 'Pan de pita', 'POSTRE' , 35, 2.31),
(12, 'Patatas fritas', 'COMPLEMENTOS', 55, 1.0),
(23, 'Ensalada César', 'ENSALADAS', 59, 5.7),
(30, 'Pepitos', 'POSTRE', 40, 1.5),
(40, 'Manolitos', 'POSTRE', 32, 3.4),
(123, 'Filetes de ternera' 'CARNE', 42, 10.3),
(1232, 'Coca-cola grande', 'BEBIDAS' , 17, 1.40),
(9939, 'Botella de agua pequeña', 'BEBIDAS', 17, 1.2),
(4353, 'Botella de agua grande', 'BEBIDAS', 30, 2.4);
