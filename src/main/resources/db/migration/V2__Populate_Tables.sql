INSERT INTO `usuario` VALUES
	('12345','admin@email.com','Paulo','$2a$16$9qr2tv0HmXbHBsx.TZFjfux742wCZM32a8Wi6iBqwIqaizlHPuxHS',b'1', b'1', b'1', b'1','ADMIN'),
	('2485','bruna@email.com','Bruna Aguiar','$2a$10$kaQhX.VAxlDT5Fk/0bSyueREXwS9GF/B7a5qvn6gSP7JprQviAtNO', b'1', b'1', b'1', b'1','VOLUNTARIO'),
	('3971','marcio@email.com','Márcio Pereira','$2a$10$Ou2RTTwOG18j7Zzjwz5grexUNjCS4MiDDICmZafoED3D7wNqjbMqO', b'1', b'1', b'1', b'1','VOLUNTARIO'),
	('4596','marcela@email.com','Marcela  Torres','$2a$10$sselAC5eqbv.HVNGoY0wOeHNNTBqMLpHX/SAdWtppx1EAh0p78U5S', b'1', b'1', b'1', b'1','VOLUNTARIO'),
	('2729','carlos@email.com','Carlos Henrique Mendes','$2a$10$sselAC5eqbv.HVNGoY0wOeHNNTBqMLpHX/SAdWtppx1EAh0p78U5S', b'1', b'1', b'1', b'1','VOLUNTARIO'),
	('1328','pedro@email.com','Pedro Almeida','$2a$10$sselAC5eqbv.HVNGoY0wOeHNNTBqMLpHX/SAdWtppx1EAh0p78U5S', b'1', b'1', b'1', b'1','VOLUNTARIO'),
	('7391','juliana@email.com','Juliana Guedes','$2a$10$j9B742eYIp21C1p935o2POatM/nhpLM2.ozIkaHkrS2bNeNjsxcCK', b'1', b'1', b'1', b'1','VOLUNTARIO');
	
INSERT INTO `permissao` VALUES 
	(1,'ROLE_ADMIN'),
	(2,'ROLE_COORDENADOR'),
	(3,'ROLE_VOLUNTARIO');

INSERT INTO `usuario_permissao` VALUES 
	('12345',1),
	('12345',2),
	('2485',3),
	('3971',3),
	('4596',3),
	('7391',3);
	
INSERT INTO `municipio` VALUES 
	('1','Apuí'),
	('2','Barcelos'),
	('3','Barreirinha'),
	('4','Borba'),
	('5','Careiro Castanho'),
	('6','Juruti'),('8','Nhamundá'),
	('9','Novo Airão'),('7','Oriximiná'),
	('10','Parintins'),('11','Puranga Conquista'),
	('12','Terra Santa');

INSERT INTO `comunidade` VALUES 
	('8','Comunidade Aliança'),
	('15','Comunidade Ascensão'),
	('7','Comunidade Barreto'),
	('1','Comunidade Bela Vista do Guariba'),
	('9','Comunidade Boca dos Currais'),
	('14','Comunidade Castanhal'),
	('3','Comunidade Granja Ceres'),
	('10','Comunidade Igapó Açu'),
	('5','Comunidade Mamori'),
	('2','Comunidade Nova Esperança'),
	('4','Comunidade Pintado'),
	('11','Comunidade Poço Fundo'),
	('12','Comunidade Salgado I'),
	('6','Comunidade São José'),
	('13','Comunidade Tucumanduba');

	
	
INSERT INTO `ciclo` VALUES 
	(1,'AM','Ciclo Mamori 2022','Comunidade Mamori','Careiro Castanho'),
	(2,'PA','Ciclo Terra Santa 2022','Comunidade Barreto','Terra Santa'),
	(3,'PA','Ciclo Oriximiná 2022','Comunidade Salgado I','Oriximina');


INSERT INTO `viagem` VALUES 	
	(1,'2022-07-11 00:00:00','12345',1),
	(2,'2022-07-16 00:00:00','12345',2),
	(3,'2022-07-22 00:00:00','12345',3),
	(4,'2022-11-10 00:00:00','12345',1),
	(5,'2022-11-20 00:00:00','12345',2),
	(6,'2022-11-09 00:00:00','12345',3),
	(7,'2023-03-15 00:00:00','12345',1),
	(8,'2023-03-28 00:00:00','12345',2),
	(9,'2023-04-09 00:00:00','12345',3);


INSERT INTO `coleta` VALUES
	(1,'2022-07-12 00:00:00',2,5,'tracajá',9,15,6,'Praia do Manelão',1,30,45,39,1,'2485'),
	(2,'2022-07-12 00:00:00',1.5,7,'tracajá',7,19,5,'Praia do Manelão',2,30,43,48,1,'2485'),
	(3,'2022-07-12 00:00:00',1.5,3,'tracajá',7,17,5,'Praia do Manelão',3,30,43,30,1,'2485'),
	(4,'2022-07-13 00:00:00',1.4,5,'tracajá',7,19,5,'Praia da Cida',4,30,43,50,1,'3971'),
	(5,'2022-07-17 00:00:00',1.7,4,'tracajá',7,15,5,'Praia da Cida',5,30,43,61,2,'3971'),
	(6,'2022-07-17 00:00:00',1.3,7,'tracajá',7,19,5,'Praia da Cida',6,30,43,42,2,'3971'),
	(7,'2022-07-18 00:00:00',1.8,7,'tracajá',7,16,5,'Praia do Boto',7,30,43,24,2,'1328'),
	(8,'2022-07-19 00:00:00',3.5,6,'tracajá',7,18,5,'Praia do Boto',8,27,38,24,2,'1328'),
	(9,'2022-07-22 00:00:00',3.7,9,'tracajá',7,17,5,'Praia do Boto',9,27,38,45,3,'1328'),
	(10,'2022-07-23 00:00:00',3.3,6,'tracajá',7,15,5,'Praia da Neide',10,27,38,38,3,'4596'),
	(11,'2022-07-23 00:00:00',3.4,13,'tracajá',7,15,5,'Praia da Neide',10,27,38,38,3,'4596'),
	(12,'2022-07-25 00:00:00',3.6,6,'tracajá',7,19,5,'Praia da Neide',10,27,38,38,3,'4596');

INSERT INTO `eclosao` VALUES
	(1,'2022-11-05 00:00:00','Tracajá',1,0,3,3,30,0,3,4,'2729'),
	(2,'2022-11-06 00:00:00','Tracajá',2,0,0,3,45,0,0,4,'2729'),
	(3,'2022-11-08 00:00:00','Tracajá',3,0,5,0,20,0,5,4,'7391'),
	(4,'2022-11-10 00:00:00','Tracajá',4,0,0,0,45,5,0,4,'7391'),
	(5,'2022-11-15 00:00:00','Tracajá',5,10,0,0,50,1,0,5,'4596'),
	(6,'2022-11-15 00:00:00','Tracajá',6,0,0,0,42,0,0,5,'4596'),
	(7,'2022-11-16 00:00:00','Tracajá',7,0,0,4,15,0,5,5,'1328'),
	(8,'2022-11-18 00:00:00','Tracajá',8,0,2,0,45,3,3,5,'1328'),
	(9,'2022-11-05 00:00:00','Tracajá',9,0,0,4,40,1,0,6,'3971'),
	(10,'2022-11-05 00:00:00','Tracajá',10,0,0,0,33,5,0,6,'3971'),
	(11,'2022-11-08 00:00:00','Tracajá',10,0,0,0,33,5,0,6,'2485'),
	(12,'2022-11-08 00:00:00','Tracajá',10,0,0,0,33,5,0,6,'2485');
	
INSERT INTO `soltura` VALUES 
	(1,12,5,3,'2023-03-25 00:00:00','Tracajá',1,16,4,5,7,'4596'),
	(2,12,3,3,'2023-03-25 00:00:00','Tracajá',2,16,5,5,7,'4596'),
	(3,11,3,3,'2023-03-25 00:00:00','Tracajá',3,16,4,5,7,'2485'),
	(4,11,3,3,'2023-03-25 00:00:00','Tracajá',4,16,4,5,7,'2485'),
	(5,10,3,3,'2023-03-25 00:00:00','Tracajá',5,16,5,5,8,'1328'),
	(6,12,3,3,'2023-03-25 00:00:00','Tracajá',6,16,5,5,8,'1328'),
	(7,10,3,3,'2023-03-25 00:00:00','Tracajá',7,16,6,5,8,'7391'),
	(8,11,3,3,'2023-03-25 00:00:00','Tracajá',8,16,5,5,8,'7391'),
	(9,12,3,3,'2023-03-25 00:00:00','Tracajá',9,16,6,5,9,'2729'),
	(10,11,3,3,'2023-03-25 00:00:00','Tracajá',10,16,5,5,9,'2729'),
	(11,11,6,4,'2023-03-25 00:00:00','Tracajá',11,18,5,6,9,'3971'),
	(12,12,6,4,'2023-03-25 00:00:00','Tracajá',12,18,5,6,9,'3971');