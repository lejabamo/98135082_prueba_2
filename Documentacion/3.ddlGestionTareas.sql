-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

-- -----------------------------------------------------
-- Schema 98135082_prueba2
-- -----------------------------------------------------
-- Modelo Relacional Adminstrador de tareas.

-- -----------------------------------------------------
-- Schema 98135082_prueba2
--
-- Modelo Relacional Adminstrador de tareas.
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `98135082_prueba2` DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci ;
USE `98135082_prueba2` ;

-- -----------------------------------------------------
-- Table `98135082_prueba2`.`responsable`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `98135082_prueba2`.`responsable` (
  `idresponsable` INT NOT NULL,
  `nombreresponsable` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`idresponsable`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `98135082_prueba2`.`estado`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `98135082_prueba2`.`estado` (
  `idestado` INT NOT NULL,
  `estadoTarea` INT NOT NULL COMMENT 'estadoTarea: \n1. Backlog\n2. En progreso\n3. Suspendido\n4.Cancelado\n5. Terminado',
  PRIMARY KEY (`idestado`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `98135082_prueba2`.`tarea`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `98135082_prueba2`.`tarea` (
  `idtarea` INT NOT NULL,
  `nombre` VARCHAR(45) NOT NULL,
  `descripcion` VARCHAR(45) NOT NULL,
  `fechaCreacion` DATE NOT NULL,
  `estado` INT NOT NULL,
  `responsable_idresponsable` INT NOT NULL,
  PRIMARY KEY (`idtarea`),
  INDEX `fk_tarea_responsable_idx` (`responsable_idresponsable` ASC),
  INDEX `fk_tarea_estado1_idx` (`estado` ASC),
  CONSTRAINT `fk_tarea_responsable`
    FOREIGN KEY (`responsable_idresponsable`)
    REFERENCES `98135082_prueba2`.`responsable` (`idresponsable`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_tarea_estado1`
    FOREIGN KEY (`estado`)
    REFERENCES `98135082_prueba2`.`estado` (`idestado`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
