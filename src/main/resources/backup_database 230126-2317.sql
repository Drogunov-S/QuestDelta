-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema quest
-- -----------------------------------------------------
DROP SCHEMA IF EXISTS `quest` ;

-- -----------------------------------------------------
-- Schema quest
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `quest` DEFAULT CHARACTER SET utf8 ;
USE `quest` ;

-- -----------------------------------------------------
-- Table `quest`.`users`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `quest`.`users` (
  `id` BIGINT NOT NULL AUTO_INCREMENT,
  `login` VARCHAR(45) NOT NULL,
  `password` VARCHAR(45) NOT NULL,
  `role` ENUM('GUEST', 'USER', 'REDACTOR', 'ADMIN') NOT NULL,
  `create_date` TIMESTAMP NULL,
  `update_date` TIMESTAMP NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `quest`.`quests`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `quest`.`quests` (
  `id` BIGINT NOT NULL AUTO_INCREMENT,
  `author_id` BIGINT NOT NULL,
  `name` VARCHAR(45) NOT NULL,
  `description` TEXT NOT NULL,
  `create_date` TIMESTAMP NULL,
  `update_date` TIMESTAMP NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_quests_users1_idx` (`author_id` ASC) VISIBLE,
  CONSTRAINT `fk_quests_author_id`
    FOREIGN KEY (`author_id`)
    REFERENCES `quest`.`users` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `quest`.`answers`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `quest`.`answers` (
  `id` BIGINT NOT NULL AUTO_INCREMENT,
  `question_id` BIGINT NULL,
  `answer` TEXT(1000) NOT NULL,
  `create_date` TIMESTAMP NULL,
  `update_date` TIMESTAMP NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_answers_questions1_idx` (`question_id` ASC) VISIBLE,
  CONSTRAINT `fk_answers_questions`
    FOREIGN KEY (`question_id`)
    REFERENCES `quest`.`questions` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `quest`.`questions`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `quest`.`questions` (
  `id` BIGINT NOT NULL AUTO_INCREMENT COMMENT '	',
  `quest_id` BIGINT NULL,
  `question` TEXT(1000) NOT NULL,
  `true_answer_id` BIGINT NOT NULL,
  `create_date` TIMESTAMP NULL,
  `update_date` TIMESTAMP NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_questions_true_answer_idx` (`true_answer_id` ASC) VISIBLE,
  INDEX `fk_questions_quest_idx` (`quest_id` ASC) VISIBLE,
  CONSTRAINT `fk_questions_quest`
    FOREIGN KEY (`quest_id`)
    REFERENCES `quest`.`quests` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_questions_true_answer`
    FOREIGN KEY (`true_answer_id`)
    REFERENCES `quest`.`answers` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `quest`.`games`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `quest`.`games` (
  `id` BIGINT NOT NULL AUTO_INCREMENT,
  `user_id` BIGINT NULL,
  `quest_id` BIGINT NULL,
  `last_question_id` BIGINT NULL,
  `game_state` ENUM('START', 'PLAY', 'END') NULL DEFAULT 'START',
  `create_date` TIMESTAMP NULL,
  `update_date` TIMESTAMP NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_games_quest_idx` (`quest_id` ASC) VISIBLE,
  INDEX `fk_games_questions_idx` (`last_question_id` ASC) VISIBLE,
  INDEX `fk_games_users1_idx` (`user_id` ASC) VISIBLE,
  CONSTRAINT `fk_games_quest`
    FOREIGN KEY (`quest_id`)
    REFERENCES `quest`.`quests` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_games_question`
    FOREIGN KEY (`last_question_id`)
    REFERENCES `quest`.`questions` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_games_user`
    FOREIGN KEY (`user_id`)
    REFERENCES `quest`.`users` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Data for table `quest`.`users`
-- -----------------------------------------------------
START TRANSACTION;
USE `quest`;
INSERT INTO `quest`.`users` (`id`, `login`, `password`, `role`, `create_date`, `update_date`) VALUES (1, 'guest', 'guestPass', 'GUEST', '2023-01-01 00:00:00', '2023-01-01 00:00:00');
INSERT INTO `quest`.`users` (`id`, `login`, `password`, `role`, `create_date`, `update_date`) VALUES (2, 'ivan', '1234', 'USER', '2023-01-06 02:23:33', '2023-01-08 02:23:33');
INSERT INTO `quest`.`users` (`id`, `login`, `password`, `role`, `create_date`, `update_date`) VALUES (3, 'sergey', 'qwerty', 'ADMIN', '2023-01-07 02:23:33', '2023-01-08 02:23:33');

COMMIT;


-- -----------------------------------------------------
-- Data for table `quest`.`quests`
-- -----------------------------------------------------
START TRANSACTION;
USE `quest`;
INSERT INTO `quest`.`quests` (`id`, `author_id`, `name`, `description`, `create_date`, `update_date`) VALUES (1, 2, 'Java Quest', 'Тест на знание Java', '2023-01-01 02:23:33', '2023-01-08 02:23:12');
INSERT INTO `quest`.`quests` (`id`, `author_id`, `name`, `description`, `create_date`, `update_date`) VALUES (2, 1, 'Find User Quest', 'Your gaming for user', '2023-01-02 02:23:33', '2023-01-08 02:23:55');

COMMIT;


-- -----------------------------------------------------
-- Data for table `quest`.`answers`
-- -----------------------------------------------------
START TRANSACTION;
USE `quest`;
INSERT INTO `quest`.`answers` (`id`, `question_id`, `answer`, `create_date`, `update_date`) VALUES (1, 1, 'String', '2023-01-08 02:23:33', '2023-01-08 02:23:33');
INSERT INTO `quest`.`answers` (`id`, `question_id`, `answer`, `create_date`, `update_date`) VALUES (2, 1, 'int', '2023-01-08 02:23:33', '2023-01-08 02:23:33');
INSERT INTO `quest`.`answers` (`id`, `question_id`, `answer`, `create_date`, `update_date`) VALUES (3, 1, 'long', '2023-01-08 02:23:33', '2023-01-08 02:23:33');
INSERT INTO `quest`.`answers` (`id`, `question_id`, `answer`, `create_date`, `update_date`) VALUES (4, 2, 'HashMap', '2023-01-08 02:23:33', '2023-01-08 02:23:33');
INSERT INTO `quest`.`answers` (`id`, `question_id`, `answer`, `create_date`, `update_date`) VALUES (5, 2, 'ArrayList', '2023-01-08 02:23:33', '2023-01-08 02:23:33');
INSERT INTO `quest`.`answers` (`id`, `question_id`, `answer`, `create_date`, `update_date`) VALUES (6, 2, 'LinkedList', '2023-01-08 02:23:33', '2023-01-08 02:23:33');
INSERT INTO `quest`.`answers` (`id`, `question_id`, `answer`, `create_date`, `update_date`) VALUES (7, 3, 'Seven answer', '2023-01-08 02:28:42', '2023-01-08 02:28:42');
INSERT INTO `quest`.`answers` (`id`, `question_id`, `answer`, `create_date`, `update_date`) VALUES (8, 3, 'Eight answer', '2023-01-08 02:28:42', '2023-01-08 02:28:42');
INSERT INTO `quest`.`answers` (`id`, `question_id`, `answer`, `create_date`, `update_date`) VALUES (9, 3, 'Nine answer', '2023-01-08 02:28:42', '2023-01-08 02:28:42');
INSERT INTO `quest`.`answers` (`id`, `question_id`, `answer`, `create_date`, `update_date`) VALUES (10, 4, 'Ten answer', '2023-01-08 02:28:42', '2023-01-08 02:28:42');
INSERT INTO `quest`.`answers` (`id`, `question_id`, `answer`, `create_date`, `update_date`) VALUES (11, 4, 'Eleven answer', '2023-01-08 02:28:42', '2023-01-08 02:28:42');
INSERT INTO `quest`.`answers` (`id`, `question_id`, `answer`, `create_date`, `update_date`) VALUES (12, 4, 'Twelw answer', '2023-01-08 02:28:42', '2023-01-08 02:28:42');
INSERT INTO `quest`.`answers` (`id`, `question_id`, `answer`, `create_date`, `update_date`) VALUES (13, 5, 'При помощи цикла for', '2023-01-08 02:28:42', NULL);
INSERT INTO `quest`.`answers` (`id`, `question_id`, `answer`, `create_date`, `update_date`) VALUES (14, 5, 'При помощи цикла while', NULL, NULL);
INSERT INTO `quest`.`answers` (`id`, `question_id`, `answer`, `create_date`, `update_date`) VALUES (15, 5, 'Через stream API', NULL, NULL);
INSERT INTO `quest`.`answers` (`id`, `question_id`, `answer`, `create_date`, `update_date`) VALUES (16, 5, 'Через forEach', NULL, NULL);

COMMIT;


-- -----------------------------------------------------
-- Data for table `quest`.`questions`
-- -----------------------------------------------------
START TRANSACTION;
USE `quest`;
INSERT INTO `quest`.`questions` (`id`, `quest_id`, `question`, `true_answer_id`, `create_date`, `update_date`) VALUES (1, 1, 'Что не является примитивом?', 1, '2023-01-08 02:23:33', '2023-01-08 02:23:33');
INSERT INTO `quest`.`questions` (`id`, `quest_id`, `question`, `true_answer_id`, `create_date`, `update_date`) VALUES (2, 1, 'Что не имплементирует интерфейс Collection?', 4, '2023-01-08 02:23:33', '2023-01-08 02:23:33');
INSERT INTO `quest`.`questions` (`id`, `quest_id`, `question`, `true_answer_id`, `create_date`, `update_date`) VALUES (3, 2, 'Who User?', 7, '2023-01-08 02:23:33', '2023-01-08 02:23:33');
INSERT INTO `quest`.`questions` (`id`, `quest_id`, `question`, `true_answer_id`, `create_date`, `update_date`) VALUES (4, 2, 'Two answer user', 10, '2023-01-08 02:23:33', '2023-01-08 02:23:33');
INSERT INTO `quest`.`questions` (`id`, `quest_id`, `question`, `true_answer_id`, `create_date`, `update_date`) VALUES (5, 1, 'Чем нельзя изменять коллекции?', 16, NULL, NULL);

COMMIT;


-- -----------------------------------------------------
-- Data for table `quest`.`games`
-- -----------------------------------------------------
START TRANSACTION;
USE `quest`;
INSERT INTO `quest`.`games` (`id`, `user_id`, `quest_id`, `last_question_id`, `game_state`, `create_date`, `update_date`) VALUES (1, 1, 1, 1, 'START', '2023-01-08 02:23:33', '2023-01-08 02:23:33');
INSERT INTO `quest`.`games` (`id`, `user_id`, `quest_id`, `last_question_id`, `game_state`, `create_date`, `update_date`) VALUES (2, 2, 2, 2, 'START', '2023-01-08 02:28:42', '2023-01-08 02:28:42');

COMMIT;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
