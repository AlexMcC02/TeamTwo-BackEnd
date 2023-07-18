CREATE TABLE `JobRole` (
`ID` SMALLINT unsigned AUTO_INCREMENT,
`Name` VARCHAR(50),
`Specification` VARCHAR(100),
`BandLevel` VARCHAR(30),
`Capability` VARCHAR(30),
PRIMARY KEY (`ID`)
);


CREATE TABLE `Capability` (
`ID` SMALLINT unsigned AUTO_INCREMENT,
`Name` VARCHAR(50),
`Capability` VARCHAR(50),
PRIMARY KEY (`ID`)
);


CREATE TABLE `BandLevel` (
`ID` SMALLINT unsigned AUTO_INCREMENT,
`Name` VARCHAR(50),
`BandLevel` VARCHAR(50),
PRIMARY KEY (`ID`)
);