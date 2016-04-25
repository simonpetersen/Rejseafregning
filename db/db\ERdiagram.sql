SET SESSION FOREIGN_KEY_CHECKS=0;

/* Drop Tables */

DROP TABLE IF EXISTS Udgift;
DROP TABLE IF EXISTS Bilag;
DROP TABLE IF EXISTS Rejsedag;
DROP TABLE IF EXISTS Rejseafregning;
DROP TABLE IF EXISTS Medarbejder;
DROP TABLE IF EXISTS Projekt;
DROP TABLE IF EXISTS Opgave;




/* Create Tables */

CREATE TABLE Bilag
(
	bilag_ID int NOT NULL,
	billede blob NOT NULL,
	PRIMARY KEY (bilag_ID)
);


CREATE TABLE Medarbejder
(
	brugernavn varchar(10) NOT NULL,
	navn varchar(50) NOT NULL,
	email varchar(50) NOT NULL,
	adgangskode varchar(20) NOT NULL,
	afdeling varchar(30) NOT NULL,
	administrator boolean NOT NULL,
	PRIMARY KEY (brugernavn)
);


CREATE TABLE Opgave
(
	nameOp varchar(50) NOT NULL,
	PRIMARY KEY (nameOp)
);


CREATE TABLE Projekt
(
	nameProjekt varchar(50) NOT NULL,
	nameOp varchar(50) NOT NULL,
	PRIMARY KEY (nameProjekt)
);


CREATE TABLE Rejseafregning
(
	rejseafregning_ID int NOT NULL,
	brugernavn varchar(10) NOT NULL,
	nameProjekt varchar(50) NOT NULL,
	status varchar(20) NOT NULL,
	datoStart date NOT NULL,
	datoSlut date NOT NULL,
	land varchar(20) NOT NULL,
	city varchar(20) NOT NULL,
	anledning varchar(100) NOT NULL,
	sum double(9,2) NOT NULL,
	anviser varchar(35) NOT NULL,
	godkender varchar(35) NOT NULL,
	PRIMARY KEY (rejseafregning_ID)
);


CREATE TABLE Rejsedag
(
	rejsedag_ID int NOT NULL,
	rejseafregning_ID int NOT NULL,
	start time NOT NULL,
	slut time NOT NULL,
	morgenmad boolean NOT NULL,
	frokost boolean NOT NULL,
	aftensmad boolean NOT NULL,
	PRIMARY KEY (rejsedag_ID)
);


CREATE TABLE Udgift
(
	udgift_ID int NOT NULL,
	rejseafregning_ID int NOT NULL,
	bilag_ID int NOT NULL,
	udgiftType varchar(50) NOT NULL,
	betalingsType varchar(50) NOT NULL,
	forklaring varchar(300) NOT NULL,
	dato date NOT NULL,
	PRIMARY KEY (udgift_ID)
);



/* Create Foreign Keys! */

ALTER TABLE Udgift
	ADD FOREIGN KEY (bilag_ID)
	REFERENCES Bilag (bilag_ID)
	ON UPDATE RESTRICT
	ON DELETE RESTRICT
;


ALTER TABLE Rejseafregning
	ADD FOREIGN KEY (brugernavn)
	REFERENCES Medarbejder (brugernavn)
	ON UPDATE RESTRICT
	ON DELETE RESTRICT
;


ALTER TABLE Projekt
	ADD FOREIGN KEY (nameOp)
	REFERENCES Opgave (nameOp)
	ON UPDATE RESTRICT
	ON DELETE RESTRICT
;


ALTER TABLE Rejseafregning
	ADD FOREIGN KEY (nameProjekt)
	REFERENCES Projekt (nameProjekt)
	ON UPDATE RESTRICT
	ON DELETE RESTRICT
;


ALTER TABLE Rejsedag
	ADD FOREIGN KEY (rejseafregning_ID)
	REFERENCES Rejseafregning (rejseafregning_ID)
	ON UPDATE RESTRICT
	ON DELETE RESTRICT
;


ALTER TABLE Udgift
	ADD FOREIGN KEY (rejseafregning_ID)
	REFERENCES Rejseafregning (rejseafregning_ID)
	ON UPDATE RESTRICT
	ON DELETE RESTRICT
;



