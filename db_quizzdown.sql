-- --------------------------------------------------------
-- Hôte:                         127.0.0.1
-- Version du serveur:           11.1.2-MariaDB - mariadb.org binary distribution
-- SE du serveur:                Win64
-- HeidiSQL Version:             12.3.0.6589
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!50503 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;


-- Listage de la structure de la base pour quizzdown
CREATE DATABASE IF NOT EXISTS `quizzdown` /*!40100 DEFAULT CHARACTER SET latin1 COLLATE latin1_swedish_ci */;
USE `quizzdown`;

-- Listage de la structure de table quizzdown. question
CREATE TABLE IF NOT EXISTS `question` (
  `id` int(11) NOT NULL,
  `énoncé` varchar(200) NOT NULL,
  `choix_1` varchar(50) NOT NULL,
  `choix_2` varchar(50) NOT NULL,
  `choix_3` varchar(50) NOT NULL,
  `choix_4` varchar(50) NOT NULL,
  `réponse_correcte` varchar(50) NOT NULL,
  `thème` varchar(20) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `thème` (`thème`),
  CONSTRAINT `question_ibfk_1` FOREIGN KEY (`thème`) REFERENCES `thème` (`nom`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_swedish_ci;

-- Listage des données de la table quizzdown.question : ~3 rows (environ)
INSERT INTO `question` (`id`, `énoncé`, `choix_1`, `choix_2`, `choix_3`, `choix_4`, `réponse_correcte`, `thème`) VALUES
	(1, 'Quelle est la capitale de la France ?', 'Paris', 'Berlin', 'Rome', 'Londres', 'Paris', 'Géographie'),
	(2, 'Quel est le plus grand pays du monde en superficie ?', 'Russie', 'Canada', 'Chine', 'États-Unis', 'Russie', 'Géographie'),
	(3, 'Quel est le nom du plus long fleuve du monde ?', 'Nil', 'Amazone', 'Yangzi Jiang', 'Mississippi', 'Nil', 'Géographie');

-- Listage de la structure de table quizzdown. session
CREATE TABLE IF NOT EXISTS `session` (
  `id` int(11) NOT NULL,
  `date` date NOT NULL,
  `thème` varchar(20) NOT NULL,
  `points` int(11) NOT NULL,
  `utilisateur` varchar(20) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `thème` (`thème`),
  KEY `utilisateur` (`utilisateur`),
  CONSTRAINT `session_ibfk_1` FOREIGN KEY (`thème`) REFERENCES `thème` (`nom`),
  CONSTRAINT `session_ibfk_2` FOREIGN KEY (`utilisateur`) REFERENCES `utilisateur` (`login`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_swedish_ci;

-- Listage des données de la table quizzdown.session : ~0 rows (environ)

-- Listage de la structure de table quizzdown. thème
CREATE TABLE IF NOT EXISTS `thème` (
  `nom` varchar(20) NOT NULL,
  PRIMARY KEY (`nom`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_swedish_ci;

-- Listage des données de la table quizzdown.thème : ~3 rows (environ)
INSERT INTO `thème` (`nom`) VALUES
	('Cinéma'),
	('Culture générale'),
	('Géographie');

-- Listage de la structure de table quizzdown. utilisateur
CREATE TABLE IF NOT EXISTS `utilisateur` (
  `login` varchar(20) NOT NULL,
  `mot_de_passe` varchar(20) NOT NULL,
  `score` int(11) DEFAULT 0,
  PRIMARY KEY (`login`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_swedish_ci;

-- Listage des données de la table quizzdown.utilisateur : ~0 rows (environ)

/*!40103 SET TIME_ZONE=IFNULL(@OLD_TIME_ZONE, 'system') */;
/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IFNULL(@OLD_FOREIGN_KEY_CHECKS, 1) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40111 SET SQL_NOTES=IFNULL(@OLD_SQL_NOTES, 1) */;
