CREATE TABLE `municipio` (
  `id_municipio` varchar(255) NOT NULL,
  `nome_municipio` varchar(255) NOT NULL,
  PRIMARY KEY (`id_municipio`),
  UNIQUE KEY `UK_2nrawpuq5h5ia875lmpfq8r1h` (`nome_municipio`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `comunidade` (
  `id_comunidade` varchar(255) NOT NULL,
  `nome_comunidade` varchar(255) NOT NULL,
  PRIMARY KEY (`id_comunidade`),
  UNIQUE KEY `UK_67l1u1i5ttw91o3b5s02kim9v` (`nome_comunidade`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `ciclo` (
  `id_ciclo` int NOT NULL AUTO_INCREMENT,
  `uf` enum('AM','PA') DEFAULT NULL,
  `nome_ciclo` varchar(255) DEFAULT NULL,
  `nome_comunidade_ciclo` varchar(255) DEFAULT NULL,
  `nome_municipio_ciclo` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id_ciclo`),
  KEY `FKaipruux4oe7aissvxpgx9cuj` (`nome_comunidade_ciclo`),
  KEY `FK11pdmfqbn44usecbdnfk82yhb` (`nome_municipio_ciclo`),
  CONSTRAINT `FK11pdmfqbn44usecbdnfk82yhb` FOREIGN KEY (`nome_municipio_ciclo`) REFERENCES `municipio` (`nome_municipio`),
  CONSTRAINT `FKaipruux4oe7aissvxpgx9cuj` FOREIGN KEY (`nome_comunidade_ciclo`) REFERENCES `comunidade` (`nome_comunidade`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `usuario` (
  `matricula` varchar(255) NOT NULL,
  `user_name` varchar(255) DEFAULT NULL,
  `nome` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `account_non_expired` bit(1) DEFAULT NULL,
  `account_non_locked` bit(1) DEFAULT NULL,
  `credentials_non_expired` bit(1) DEFAULT NULL,
  `enabled` bit(1) DEFAULT NULL,
  `tipo_usuario` enum('ADMIN','COORDENADOR','VOLUNTARIO') DEFAULT NULL,
  PRIMARY KEY (`matricula`),
  UNIQUE KEY `uk_user_name` (`user_name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `permissao` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `description` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB;

CREATE TABLE `usuario_permissao` (
  `id_usuario` varchar(255) NOT NULL,
  `id_permissao` bigint NOT NULL,
  PRIMARY KEY (`id_usuario`,`id_permissao`),
  KEY `fk_usuario_permissao_permissao` (`id_permissao`),
  CONSTRAINT `fk_usuario_permissao` FOREIGN KEY (`id_usuario`) REFERENCES `usuario` (`matricula`),
  CONSTRAINT `fk_usuario_permissao_permissao` FOREIGN KEY (`id_permissao`) REFERENCES `permissao` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `viagem` (
  `id_viagem` int NOT NULL AUTO_INCREMENT,
  `data_viagem` datetime DEFAULT NULL,
  `matricula_coordenador` varchar(255) DEFAULT NULL,
  `id_ciclo_viagem` int DEFAULT NULL,
  PRIMARY KEY (`id_viagem`),
  KEY `FKjuotfe5tfvxf14mw63j3bq39j` (`matricula_coordenador`),
  KEY `FK75qkmg3dxmhelte03xwm1qg1b` (`id_ciclo_viagem`),
  CONSTRAINT `FK75qkmg3dxmhelte03xwm1qg1b` FOREIGN KEY (`id_ciclo_viagem`) REFERENCES `ciclo` (`id_ciclo`),
  CONSTRAINT `FKjuotfe5tfvxf14mw63j3bq39j` FOREIGN KEY (`matricula_coordenador`) REFERENCES `usuario` (`matricula`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `coleta` (
  `id_coleta` int NOT NULL AUTO_INCREMENT,
  `data_coleta` datetime DEFAULT NULL,
  `distancia_agua` double DEFAULT NULL,
  `distancia_vegetacao` double DEFAULT NULL,
  `especie` varchar(255) DEFAULT NULL,
  `largura_entre_patas` float DEFAULT NULL,
  `largura_ninho` float DEFAULT NULL,
  `largura_patas` float DEFAULT NULL,
  `nome_praia_tabuleiro` varchar(255) DEFAULT NULL,
  `numero_cova` int DEFAULT NULL,
  `profundidade_primeiro_ovo` float DEFAULT NULL,
  `profundidade_total` float DEFAULT NULL,
  `quantidade_ovos` int DEFAULT NULL,
  `id_viagem` int DEFAULT NULL,
  `matricula_voluntario` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id_coleta`),
  KEY `FK4ebjpbf5a79rwhurefpixp9g` (`id_viagem`),
  KEY `FK4vv5hl9eokujyq0p199l1qk0a` (`matricula_voluntario`),
  CONSTRAINT `FK4ebjpbf5a79rwhurefpixp9g` FOREIGN KEY (`id_viagem`) REFERENCES `viagem` (`id_viagem`),
  CONSTRAINT `FK4vv5hl9eokujyq0p199l1qk0a` FOREIGN KEY (`matricula_voluntario`) REFERENCES `usuario` (`matricula`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `eclosao` (
  `id_eclosao` int NOT NULL AUTO_INCREMENT,
  `data_nascimento` datetime DEFAULT NULL,
  `especie` varchar(255) DEFAULT NULL,
  `numero_cova` int DEFAULT NULL,
  `quantidade_filhote_morto_bicheira` int DEFAULT NULL,
  `quantidade_filhote_morto_formiga` int DEFAULT NULL,
  `quantidade_filhote_morto_outros` int DEFAULT NULL,
  `quantidade_filhote_vivo` int DEFAULT NULL,
  `quantidade_ovo_infertil` int DEFAULT NULL,
  `quantidade_ovo_inviavel` int DEFAULT NULL,
  `id_viagem` int DEFAULT NULL,
  `matricula_voluntario` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id_eclosao`),
  KEY `FKp945lsqnw879rib80954dxxim` (`id_viagem`),
  KEY `FK1fdm0basighfabo2k5p36gcx4` (`matricula_voluntario`),
  CONSTRAINT `FK1fdm0basighfabo2k5p36gcx4` FOREIGN KEY (`matricula_voluntario`) REFERENCES `usuario` (`matricula`),
  CONSTRAINT `FKp945lsqnw879rib80954dxxim` FOREIGN KEY (`id_viagem`) REFERENCES `viagem` (`id_viagem`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;


CREATE TABLE `soltura` (
  `id_soltura` int NOT NULL AUTO_INCREMENT,
  `altura` float DEFAULT NULL,
  `carapaca_comprimento` float DEFAULT NULL,
  `carapaca_largura` float DEFAULT NULL,
  `data_soltura` datetime DEFAULT NULL,
  `especie` varchar(255) DEFAULT NULL,
  `numero_animal` int DEFAULT NULL,
  `peso` float DEFAULT NULL,
  `plastrao_comprimento` float DEFAULT NULL,
  `plastrao_largura` float DEFAULT NULL,
  `id_viagem` int DEFAULT NULL,
  `matricula_voluntario` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id_soltura`),
  KEY `FK5p1wycquun8pyvd4qnsiw3v4c` (`id_viagem`),
  KEY `FKk1a0cg1jlb17toaympuc4enjk` (`matricula_voluntario`),
  CONSTRAINT `FK5p1wycquun8pyvd4qnsiw3v4c` FOREIGN KEY (`id_viagem`) REFERENCES `viagem` (`id_viagem`),
  CONSTRAINT `FKk1a0cg1jlb17toaympuc4enjk` FOREIGN KEY (`matricula_voluntario`) REFERENCES `usuario` (`matricula`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;


