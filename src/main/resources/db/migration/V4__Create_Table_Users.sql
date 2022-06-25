CREATE TABLE IF NOT EXISTS `usuario` (
  `matricula` varchar(255) NOT NULL,
  `user_name` varchar(255) DEFAULT NULL,
  `nome` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `account_non_expired` bit(1) DEFAULT NULL,
  `account_non_locked` bit(1) DEFAULT NULL,
  `credentials_non_expired` bit(1) DEFAULT NULL,
  `enabled` bit(1) DEFAULT NULL,
  PRIMARY KEY (`matricula`),
  UNIQUE KEY `uk_user_name` (`user_name`)
) ENGINE=InnoDB;