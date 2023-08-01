CREATE TABLE `Capability` (
`ID` SMALLINT unsigned AUTO_INCREMENT,
`Name` VARCHAR(50)
PRIMARY KEY (`ID`)
);


CREATE TABLE `BandLevel` (
`ID` SMALLINT unsigned AUTO_INCREMENT,
`Name` VARCHAR(50)
PRIMARY KEY (`ID`)
);

CREATE TABLE `JobRole` (
`ID` SMALLINT unsigned AUTO_INCREMENT,
`Name` VARCHAR(50),
`Specification` VARCHAR(100),
`BandID` VARCHAR(30),
`CapabilityID` SMALLINT unsigned,
`UrlLink` VARCHAR(100),
PRIMARY KEY (`ID`),
FOREIGN KEY `CapabilityID` REFERENCES `Capability`.`ID`,
FOREIGN KEY `CapabilityID` REFERENCES `Capability`.`ID`
);