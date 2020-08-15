SET FOREIGN_KEY_CHECKS = 0;

DROP TABLE `game`;
DROP TABLE `unused_letter`;
DROP TABLE `game_statistic`;
DROP TABLE `ranking`;

CREATE TABLE `game` (
`id` CHAR(36),
`attempts_left` INT,
`word` VARCHAR(11) DEFAULT NULL,
`word_reveal` TINYINT(1),
`word_in_progress` VARCHAR(11) DEFAULT NULL,
`game_statistic_id` INT DEFAULT NULL,

PRIMARY KEY (`id`),
KEY `FK_STATISTIC_idx` (`game_statistic_id`),
CONSTRAINT `FK_STATISTIC`
FOREIGN KEY (`game_statistic_id`)
REFERENCES `game_statistic` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;


CREATE TABLE `unused_letter` (
`id` INT NOT NULL AUTO_INCREMENT,
`letter` VARCHAR(1) DEFAULT NULL,
`game_id` CHAR(36),

PRIMARY KEY(`id`),
KEY `FK_GAME_idx` (`game_id`),

FOREIGN KEY (`game_id`)
REFERENCES `game` (`id`)

ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;


CREATE TABLE `game_statistic` (
`id` INT NOT NULL AUTO_INCREMENT,
`hint` TINYINT(1),
`game_date` TIMESTAMP,
`score` INT DEFAULT NULL,
`ranking_alias` VARCHAR(15) DEFAULT NULL,

PRIMARY KEY (`id`),
KEY `FK_RANKING_idx` (`ranking_alias`),
CONSTRAINT `FK_RANKING`
FOREIGN KEY (`ranking_alias`)
REFERENCES `ranking` (`alias`)  
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;


CREATE TABLE `ranking` (
`alias` VARCHAR(15) NOT NULL PRIMARY KEY,
`high_score` INT
) ENGINE=InnoDB DEFAULT CHARSET=latin1;