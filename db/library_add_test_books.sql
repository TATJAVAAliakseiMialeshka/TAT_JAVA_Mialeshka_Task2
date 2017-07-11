-- --------------------------------------------------------
-- Host:                         127.0.0.1
-- Server version:               5.7.16-log - MySQL Community Server (GPL)
-- Server OS:                    Win64
-- HeidiSQL Version:             9.4.0.5125
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!50503 SET NAMES utf8mb4 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;


-- Dumping database structure for library
CREATE DATABASE IF NOT EXISTS `library` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `library`;

-- Dumping structure for table library.authors
CREATE TABLE IF NOT EXISTS `authors` (
  `a_id` int(10) NOT NULL AUTO_INCREMENT,
  `a_name` varchar(150) DEFAULT NULL,
  PRIMARY KEY (`a_id`),
  UNIQUE KEY `a_name` (`a_name`)
) ENGINE=InnoDB AUTO_INCREMENT=25 DEFAULT CHARSET=utf8;

-- Dumping data for table library.authors: ~19 rows (approximately)
DELETE FROM `authors`;
/*!40000 ALTER TABLE `authors` DISABLE KEYS */;
INSERT INTO `authors` (`a_id`, `a_name`) VALUES
	(6, 'Agatha Christie'),
	(4, 'Antoine de Saint-ExupГ©ry'),
	(18, 'Arthur Conan Doyle'),
	(9, 'C. S. Lewis'),
	(7, 'Cao Xueqin'),
	(2, 'Charles Dickens'),
	(13, 'Dan Brown'),
	(20, 'Gabriel GarcГ­a MГЎrquez'),
	(12, 'H. Rider Haggard'),
	(16, 'J. D. Salinger'),
	(5, 'J. K. Rowling'),
	(3, 'J. R. R. Tolkien'),
	(19, 'Jules Verne'),
	(8, 'Lewis Carroll'),
	(1, 'Miguel de Cervantes'),
	(14, 'Napoleon Hill'),
	(17, 'Robert James Waller'),
	(24, 'someAuthor'),
	(21, 'Vladimir Nabokov');
/*!40000 ALTER TABLE `authors` ENABLE KEYS */;

-- Dumping structure for table library.books
CREATE TABLE IF NOT EXISTS `books` (
  `b_id` int(10) NOT NULL AUTO_INCREMENT,
  `b_name` varchar(150) DEFAULT NULL,
  `b_year` smallint(5) DEFAULT NULL,
  `b_description` text,
  `b_quantity` smallint(5) DEFAULT NULL,
  `b_is_available` enum('Y','N') DEFAULT 'Y',
  PRIMARY KEY (`b_id`)
) ENGINE=InnoDB AUTO_INCREMENT=113 DEFAULT CHARSET=utf8;

-- Dumping data for table library.books: ~20 rows (approximately)
DELETE FROM `books`;
/*!40000 ALTER TABLE `books` DISABLE KEYS */;
INSERT INTO `books` (`b_id`, `b_name`, `b_year`, `b_description`, `b_quantity`, `b_is_available`) VALUES
	(1, 'Don Quixote', 1612, 'The story follows the adventures of a hidalgo named Mr. Alonso Quixano who reads so many chivalric romances that he loses his sanity and decides to set out to revive chivalry, undo wrongs, and bring justice to the world, under the name Don Quixote de la Mancha. He recruits a simple farmer, Sancho Panza, as his squire, who often employs a unique, earthy wit in dealing with Don Quixote\'s rhetorical orations on antiquated knighthood. ', 5, 'Y'),
	(2, 'A Tale of Two Cities', 1859, 'The novel tells the story of the French Doctor Manette imprisoned 18 years in the Bastille in Paris, his release from prison and into life in London with his daughter Lucie, whom he had never met, her marriage and the collision between her beloved husband and the people who decades earlier caused her father to be imprisoned. Monsieur and Madame Defarge, sellers of wine in a poor suburb of Paris also figure in the Doctor\'s story. The story is set against the conditions that led up to the French Revolution, and the worst year of it, the Reign of Terror.', 3, 'Y'),
	(3, 'The Lord of the Rings', 1954, 'The title of the novel refers to the story\'s main antagonist, the Dark Lord Sauron, who had in an earlier age created the One Ring to rule the other Rings of Power as the ultimate weapon in his campaign to conquer and rule all of Middle-earth. ', 1, 'Y'),
	(4, 'Le Petit Prince (The Little Prince)', 1943, 'The Little Prince is a poetic tale, with watercolour illustrations by the author, in which a pilot stranded in the desert meets a young prince fallen to Earth from a tiny asteroid.', 10, 'Y'),
	(5, 'Harry Potter and the Philosopher\'s Stone', 1997, 'The plot follows Harry Potter, a young wizard who discovers his magical heritage as he makes close friends and a few enemies in his first year at the Hogwarts School of Witchcraft and Wizardry.', 2, 'Y'),
	(6, 'The Hobbit', 1937, 'The Hobbit is set in a time "between the Dawn of FГ¦rie and the Dominion of Men", and follows the quest of home-loving hobbit Bilbo Baggins to win a share of the treasure guarded by Smaug the dragon. Bilbo\'s journey takes him from light-hearted, rural surroundings into more sinister territory.', 1, 'Y'),
	(7, 'And Then There Were None', 1939, 'In the novel, a group of people are lured into coming to an island under different pretexts, e.g., offers of employment, to enjoy a late summer holiday, or to meet old friends. All have been complicit in the deaths of other human beings, but either escaped justice or committed an act that was not subject to legal sanction. The guests and two servants who are present are "charged" with their respective "crimes" by a gramophone recording after dinner the first night, and informed that they have been brought to the island to pay for their actions. They are the only people on the island, and cannot escape due to the distance from the mainland and the inclement weather, and gradually all ten are killed in turn, each in a manner that seems to parallel the deaths in the nursery rhyme. Nobody else seems to be left alive on the island by the time of the apparent last death. A confession, in the form of a postscript to the novel, unveils how the killings took place and who was responsible.', 1, 'Y'),
	(8, 'Dream of the Red Chamber', 1754, 'The novel provides a detailed, episodic record of life in the two branches of the wealthy, aristocratic Jia clanвЂ”the Rongguo House and the Ningguo House вЂ”who reside in two large, adjacent family compounds in the capital. Their ancestors were made Dukes and given imperial titles, and as the novel begins the two houses are among the most illustrious families in the city. One of the clanвЂ™s offspring is made a Royal Consort, and a lush landscaped garden is built to receive her visit. The novel describes the JiasвЂ™ wealth and influence in great naturalistic detail, and charts the JiasвЂ™ fall from the height of their prestige, following some thirty main characters and over four hundred minor ones. Eventually the Jia clan falls into disfavor with the Emperor, and their mansions are raided and confiscated.', 3, 'Y'),
	(9, 'Alice in Wonderland', 1865, ' It tells of a girl named Alice falling through a rabbit hole into a fantasy world populated by peculiar, anthropomorphic creatures. The tale plays with logic, giving the story lasting popularity with adults as well as with children.', 6, 'Y'),
	(10, 'The Lion, the Witch and the Wardrobe', 1950, 'Most of the novel is set in Narnia, a land of talking animals and mythical creatures that one White Witch has ruled for 100 years of deep winter. In the frame story, four English children are relocated to a large, old country house following a wartime evacuation. The youngest visits Narnia three times via the magic of a wardrobe in a spare room. All four children are together on her third visit, which verifies her fantastic claims and comprises the subsequent 12 of 17 chapters except for a brief conclusion. In Narnia, the siblings seem fit to fulfill an old prophecy and so are soon adventuring both to save Narnia and their lives.', 1, 'Y'),
	(13, 'She: A History of Adventure', 1887, 'The story is a first-person narrative that follows the journey of Horace Holly and his ward Leo Vincey to a lost kingdom in the African interior. There they encounter a primitive race of natives and a mysterious white queen named Ayesha who reigns as the all-powerful "She", or "She-who-must-be-obeyed". In this work, Rider Haggard developed the conventions of the Lost World subgenre, which many later authors emulated.', 2, 'N'),
	(14, 'The Da Vinci Code', 2003, 'The novel explores an alternative religious history, whose central plot point is that the Merovingian kings of France were descended from the bloodline of Jesus Christ and Mary Magdalene, ideas derived from Clive Prince\'s The Templar Revelation (1997) and books by Margaret Starbird. The book also refers to The Holy Blood and the Holy Grail (1982) though Dan Brown has stated that it was not used as research material.', 5, 'Y'),
	(15, 'Think and Grow Rich', 1937, 'The text of Think and Grow Rich is based on Hill\'s earlier work The Law of Success, said to be the result of more than twenty years of study of many individuals who had amassed personal fortunes.', 1, 'Y'),
	(16, 'Harry Potter and the Half-Blood Prince', 2005, 'Harry Potter and the Half-Blood Prince is the sixth and penultimate novel in the Harry Potter series, written by British author J. K. Rowling. Set during protagonist Harry Potter\'s sixth year at Hogwarts, the novel explores the past of Harry\'s nemesis, Lord Voldemort, and Harry\'s preparations for the final battle against Voldemort alongside his headmaster and mentor Albus Dumbledore.', 0, 'Y'),
	(17, 'The Catcher in the Rye', 1951, 'Holden Caulfield, a teenager from New York City, describes events that took place in December 1949 from an unspecified California institution one year later.', 3, 'Y'),
	(18, 'The Bridges of Madison County', 1992, 'The Bridges of Madison County is a 1992 best-selling novel by Robert James Waller that tells the story of a married but lonely Italian-American woman (war bride) living on a 1960s Madison County, Iowa farm. While her husband and children are away at the State Fair, she engages in an affair with a National Geographic photographer from Bellingham, Washington, who is visiting Madison County to create a photographic essay on the covered bridges in the area. The novel is presented as a novelization of a true story, but it is in fact entirely fictional. However, the author stated in an interview that there were strong similarities between the main character and himself.', 3, 'Y'),
	(19, 'The Adventures of Sherlock Holmes', 1887, 'The Adventures of Sherlock Holmes is a collection of twelve short stories by Arthur Conan Doyle, featuring his fictional detective Sherlock Holmes. It was first published on 14 October 1892; the individual stories had been serialised in The Strand Magazine between June 1891 and July 1892. The stories are not in chronological order, and the only characters common to all twelve are Holmes and Dr. Watson. The stories are related in first-person narrative from Watson\'s point of view.', 5, 'Y'),
	(20, '20,000 Leagues under the Sea', 1870, 'The title refers to the distance traveled while under the sea and not to a depth, as 20,000 leagues is over six times the diameter, and nearly twice the circumference of the Earth.[2] The greatest depth mentioned in the book is four leagues. The book uses metric leagues, which are four kilometres each.[3] A literal translation of the French title would end in the plural "seas", thus implying the "seven seas" through which the characters of the novel travel; however, the early English translations of the title used "sea", meaning the ocean in general.', 10, 'Y'),
	(21, 'One Hundred Years of Solitude', 1967, 'One Hundred Years of Solitude is the story of seven generations of the BuendГ­a Family in the town of Macondo. The founding patriarch of Macondo, JosГ© Arcadio BuendГ­a, and Гљrsula IguarГЎn, his wife (and first cousin), leave Riohacha, Colombia, to find a better life and a new home. One night of their emigration journey, while camping on a riverbank, JosГ© Arcadio BuendГ­a dreams of "Macondo", a city of mirrors that reflected the world in and about it. Upon awakening, he decides to establish Macondo at the river side; after days of wandering the jungle, JosГ© Arcadio BuendГ­a\'s founding of Macondo is utopic.', 0, 'Y'),
	(22, 'Lolita', 1955, 'The novel is notable for its controversial subject: the protagonist and unreliable narratorвЂ”a middle-aged literature professor called Humbert HumbertвЂ”is obsessed with the 12-year-old Dolores Haze, with whom he becomes sexually involved after he becomes her stepfather. "Lolita" is his private nickname for Dolores.', 2, 'Y'),
	(113, 'b_name', 9999, 'b_descr', 100, 'Y'),
	(114, 'b_name', 9999, 'b_descr', 0, 'N');
/*!40000 ALTER TABLE `books` ENABLE KEYS */;

-- Dumping structure for table library.books_has_authors
CREATE TABLE IF NOT EXISTS `books_has_authors` (
  `b_id` int(10) NOT NULL,
  `a_id` int(10) NOT NULL,
  PRIMARY KEY (`b_id`,`a_id`),
  KEY `fk_books_has_authors_authors1_idx` (`a_id`),
  KEY `fk_books_has_authors_books1_idx` (`b_id`),
  CONSTRAINT `fk_books_has_authors_authors1` FOREIGN KEY (`a_id`) REFERENCES `authors` (`a_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `fk_books_has_authors_books1` FOREIGN KEY (`b_id`) REFERENCES `books` (`b_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- Dumping data for table library.books_has_authors: ~20 rows (approximately)
DELETE FROM `books_has_authors`;
/*!40000 ALTER TABLE `books_has_authors` DISABLE KEYS */;
INSERT INTO `books_has_authors` (`b_id`, `a_id`) VALUES
	(1, 1),
	(2, 2),
	(3, 3),
	(6, 3),
	(4, 4),
	(5, 5),
	(16, 5),
	(7, 6),
	(8, 7),
	(9, 8),
	(10, 9),
	(13, 12),
	(14, 13),
	(15, 14),
	(17, 16),
	(18, 17),
	(19, 18),
	(20, 19),
	(21, 20),
	(22, 21);
/*!40000 ALTER TABLE `books_has_authors` ENABLE KEYS */;

-- Dumping structure for table library.books_has_genres
CREATE TABLE IF NOT EXISTS `books_has_genres` (
  `b_id` int(10) NOT NULL,
  `g_id` int(10) NOT NULL,
  PRIMARY KEY (`b_id`,`g_id`),
  KEY `fk_books_has_genres_genres1_idx` (`g_id`),
  KEY `fk_books_has_genres_books1_idx` (`b_id`),
  CONSTRAINT `fk_books_has_genres_books1` FOREIGN KEY (`b_id`) REFERENCES `books` (`b_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `fk_books_has_genres_genres1` FOREIGN KEY (`g_id`) REFERENCES `genres` (`g_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- Dumping data for table library.books_has_genres: ~20 rows (approximately)
DELETE FROM `books_has_genres`;
/*!40000 ALTER TABLE `books_has_genres` DISABLE KEYS */;
INSERT INTO `books_has_genres` (`b_id`, `g_id`) VALUES
	(1, 1),
	(4, 1),
	(10, 1),
	(18, 1),
	(21, 1),
	(22, 1),
	(2, 2),
	(3, 3),
	(5, 3),
	(6, 3),
	(9, 3),
	(13, 3),
	(16, 3),
	(17, 3),
	(7, 4),
	(8, 5),
	(14, 6),
	(19, 6),
	(15, 8),
	(20, 9);
/*!40000 ALTER TABLE `books_has_genres` ENABLE KEYS */;

-- Dumping structure for table library.genres
CREATE TABLE IF NOT EXISTS `genres` (
  `g_id` int(10) NOT NULL AUTO_INCREMENT,
  `g_name` varchar(150) DEFAULT NULL,
  PRIMARY KEY (`g_id`),
  UNIQUE KEY `g_name_UNIQUE` (`g_name`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8;

-- Dumping data for table library.genres: ~9 rows (approximately)
DELETE FROM `genres`;
/*!40000 ALTER TABLE `genres` DISABLE KEYS */;
INSERT INTO `genres` (`g_id`, `g_name`) VALUES
	(9, 'Adventure'),
	(6, 'Detective fiction'),
	(5, 'Family saga'),
	(3, 'Fantasy'),
	(2, 'Historical fiction'),
	(4, 'Mystery'),
	(7, 'Non-fiction'),
	(1, 'Novel'),
	(8, 'Realistic fiction');
/*!40000 ALTER TABLE `genres` ENABLE KEYS */;

-- Dumping structure for table library.role
CREATE TABLE IF NOT EXISTS `role` (
  `r_id` int(11) NOT NULL AUTO_INCREMENT,
  `r_name` varchar(45) DEFAULT NULL,
  `r_authority` enum('ROLE_SUPERADMIN','ROLE_ADMIN','ROLE_USER') DEFAULT NULL,
  PRIMARY KEY (`r_id`),
  UNIQUE KEY `r_name_UNIQUE` (`r_name`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

-- Dumping data for table library.role: ~3 rows (approximately)
DELETE FROM `role`;
/*!40000 ALTER TABLE `role` DISABLE KEYS */;
INSERT INTO `role` (`r_id`, `r_name`, `r_authority`) VALUES
	(1, 'Superadmin', 'ROLE_SUPERADMIN'),
	(2, 'Admin', 'ROLE_ADMIN'),
	(3, 'User', 'ROLE_USER');
/*!40000 ALTER TABLE `role` ENABLE KEYS */;

-- Dumping structure for table library.subscriptions
CREATE TABLE IF NOT EXISTS `subscriptions` (
  `sb_id` int(10) NOT NULL AUTO_INCREMENT,
  `u_id` int(10) NOT NULL,
  `b_id` int(10) NOT NULL,
  `sb_start` date DEFAULT NULL,
  `sb_finish` date DEFAULT NULL,
  `sb_status` enum('P','S','E') DEFAULT 'P',
  PRIMARY KEY (`sb_id`),
  KEY `fk_subscriptions_books1_idx` (`b_id`),
  KEY `fk_subscriptions_users1_idx` (`u_id`),
  CONSTRAINT `fk_subscriptions_books1` FOREIGN KEY (`b_id`) REFERENCES `books` (`b_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `fk_subscriptions_users1` FOREIGN KEY (`u_id`) REFERENCES `users` (`u_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=32 DEFAULT CHARSET=utf8;

-- Dumping data for table library.subscriptions: ~3 rows (approximately)
DELETE FROM `subscriptions`;
/*!40000 ALTER TABLE `subscriptions` DISABLE KEYS */;
INSERT INTO `subscriptions` (`sb_id`, `u_id`, `b_id`, `sb_start`, `sb_finish`, `sb_status`) VALUES
	(1, 4, 9, '2017-10-01', '2018-10-01', 'S'),
	(2, 7, 2, '2017-10-01', '2018-10-01', 'S'),
	(31, 6, 9, '2017-06-30', '2017-07-04', 'E');
/*!40000 ALTER TABLE `subscriptions` ENABLE KEYS */;

-- Dumping structure for table library.users
CREATE TABLE IF NOT EXISTS `users` (
  `u_id` int(10) NOT NULL AUTO_INCREMENT,
  `u_name` varchar(150) DEFAULT NULL,
  `u_password` varchar(150) DEFAULT NULL,
  `u_status` enum('G','R','A','B') DEFAULT 'R',
  PRIMARY KEY (`u_id`),
  UNIQUE KEY `u_name` (`u_name`)
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=utf8;

-- Dumping data for table library.users: ~10 rows (approximately)
DELETE FROM `users`;
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` (`u_id`, `u_name`, `u_password`, `u_status`) VALUES
	(1, 'first_superadmin', 'first', 'A'),
	(2, 'second_admin', 'second', 'A'),
	(3, 'third_admin', 'third', 'A'),
	(4, 'fourth', 'fourth', 'R'),
	(5, 'fifth', 'fifth', 'R'),
	(6, 'sixth', 'sixth', 'R'),
	(7, 'seventh', 'seventh', 'R'),
	(8, 'eighth', 'eighth', 'R'),
	(9, 'nineth', 'nineth', 'R'),
	(10, 'tenth', '4758950de9a04905d6268b3f396f66cb', 'R');
/*!40000 ALTER TABLE `users` ENABLE KEYS */;

-- Dumping structure for table library.users_has_role
CREATE TABLE IF NOT EXISTS `users_has_role` (
  `u_id` int(10) NOT NULL,
  `r_id` int(11) NOT NULL,
  PRIMARY KEY (`u_id`,`r_id`),
  KEY `fk_users_has_role_role1_idx` (`r_id`),
  KEY `fk_users_has_role_users1_idx` (`u_id`),
  CONSTRAINT `fk_users_has_role_role1` FOREIGN KEY (`r_id`) REFERENCES `role` (`r_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `fk_users_has_role_users1` FOREIGN KEY (`u_id`) REFERENCES `users` (`u_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- Dumping data for table library.users_has_role: ~12 rows (approximately)
DELETE FROM `users_has_role`;
/*!40000 ALTER TABLE `users_has_role` DISABLE KEYS */;
INSERT INTO `users_has_role` (`u_id`, `r_id`) VALUES
	(1, 1),
	(1, 2),
	(2, 2),
	(3, 2),
	(4, 2),
	(4, 3),
	(5, 3),
	(6, 3),
	(7, 3),
	(8, 3),
	(9, 3),
	(10, 3);
/*!40000 ALTER TABLE `users_has_role` ENABLE KEYS */;

/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IF(@OLD_FOREIGN_KEY_CHECKS IS NULL, 1, @OLD_FOREIGN_KEY_CHECKS) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
