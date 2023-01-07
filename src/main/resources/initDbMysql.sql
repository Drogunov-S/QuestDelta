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
                                               `role` VARCHAR(45) NOT NULL,
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
                                                 CONSTRAINT `fk_answers_questions1`
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


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
