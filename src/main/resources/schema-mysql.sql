CREATE TABLE IF NOT EXISTS `head` (
  `staff_number` int NOT NULL AUTO_INCREMENT,
  `staff_id` varchar(20) NOT NULL,
  `staff_name` varchar(20) DEFAULT NULL,
  `job` varchar(20) DEFAULT NULL,
  `phone` varchar(20) DEFAULT NULL,
  `email` varchar(40) DEFAULT NULL,
  PRIMARY KEY (`staff_number`,`staff_id`)
);
