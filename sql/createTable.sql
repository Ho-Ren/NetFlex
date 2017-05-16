DROP DATABASE IF EXISTS moviedb;
CREATE DATABASE moviedb;
USE moviedb;

Create table `employees`(
`email` varchar(50) not null,
`password` varchar(20) not null,
`fullname` varchar(100) not null,
primary key (`email`)
);

CREATE TABLE `movies` (
  `id` int(11) NOT NULL auto_increment,
  `title` varchar(100) NOT NULL,
  `year` int(11) NOT NULL,
  `director` varchar(200) NOT NULL,
  `banner_url` varchar(200) DEFAULT NULL,
  `trailer_url` varchar(200) DEFAULT NULL,
  PRIMARY KEY (`id`)
);

CREATE TABLE `stars` (
  `id` int(11) NOT NULL auto_increment,
  `first_name` varchar(50) NOT NULL,
  `last_name` varchar(50) NOT NULL,
  `dob` DATE DEFAULT NULL,
  `photo_url` varchar(200) DEFAULT NULL,
  PRIMARY KEY (`id`)
);

CREATE TABLE `stars_in_movies` (
  `star_id` int(11) NOT NULL,
  `movie_id` int(11) NOT NULL,
  PRIMARY KEY (`star_id`, `movie_id`),
  FOREIGN KEY (`star_id`) REFERENCES `stars` (`id`) ON DELETE CASCADE,
  FOREIGN KEY (`movie_id`) REFERENCES `movies` (`id`) ON DELETE CASCADE

);

CREATE TABLE `genres` (
  `id` int(11) NOT NULL auto_increment,
  `name` varchar(32) NOT NULL,
  PRIMARY KEY (`id`)
);

CREATE TABLE `genres_in_movies` (
  `genre_id` int(11) NOT NULL,
  `movie_id` int(11) NOT NULL,
  PRIMARY KEY (`genre_id`, `movie_id`),
  FOREIGN KEY (`genre_id`) REFERENCES `genres` (`id`) ON DELETE CASCADE,
  FOREIGN KEY (`movie_id`) REFERENCES `movies` (`id`) ON DELETE CASCADE
);

CREATE TABLE `creditcards` (
  `id` varchar(20) NOT NULL,
  `first_name` varchar(50) NOT NULL,
  `last_name` varchar(50) NOT NULL,
  `expiration` DATE NOT NULL,
  PRIMARY KEY (`id`)
);

CREATE TABLE `customers` (
  `id` int(11) NOT NULL auto_increment,
  `first_name` varchar(50) NOT NULL,
  `last_name` varchar(50) NOT NULL,
  `cc_id` varchar(20) NOT NULL,
  `address` varchar(200) NOT NULL,
  `email` varchar(50) NOT NULL,
  `password` varchar(20) NOT NULL,
  PRIMARY KEY (`id`),
  FOREIGN KEY (`cc_id`) REFERENCES `creditcards` (`id`) ON DELETE CASCADE
);

CREATE TABLE `sales` (
  `id` int(11) NOT NULL auto_increment,
  `customer_id` int(11) NOT NULL,
  `movie_id` int(11) NOT NULL,
  `sale_date` DATE NOT NULL,
  PRIMARY KEY (`id`),
  FOREIGN KEY (`customer_id`) REFERENCES `customers` (`id`) ON DELETE CASCADE,
  FOREIGN KEY (`movie_id`) REFERENCES `movies` (`id`) ON DELETE CASCADE
);

