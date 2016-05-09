SET SESSION FOREIGN_KEY_CHECKS=0;

/* Drop Tables */

DROP TABLE IF EXISTS udgift;
DROP TABLE IF EXISTS bilag;
DROP TABLE IF EXISTS rejsedag;
DROP TABLE IF EXISTS rejseafregning;
DROP TABLE IF EXISTS godtgoerelse;
DROP TABLE IF EXISTS medarbejder;
DROP TABLE IF EXISTS projekt;
DROP TABLE IF EXISTS opgave;




/* Create Tables */

CREATE TABLE bilag
(
	bilag_ID int NOT NULL,
	billede blob NOT NULL,
	PRIMARY KEY (bilag_ID)
);


CREATE TABLE godtgoerelse
(
	Land varchar(20) NOT NULL,
	Dagpengesats double(5,2) NOT NULL,
	Timepengesats double(5,2) NOT NULL,
	Hoteldisposition int NOT NULL,
	PRIMARY KEY (Land)
);


CREATE TABLE medarbejder
(
	brugernavn varchar(10) NOT NULL,
	navn varchar(50) NOT NULL,
	email varchar(50) NOT NULL,
	adgangskode varchar(20) NOT NULL,
	afdeling varchar(30) NOT NULL,
	postnr varchar(4),
	vejnavn varchar(50),
	husnr varchar(4),
	etage varchar(4),
	doer varchar(3),
	PRIMARY KEY (brugernavn)
);


CREATE TABLE opgave
(
	nameOp varchar(50) NOT NULL,
	PRIMARY KEY (nameOp)
);


CREATE TABLE projekt
(
	nameProjekt varchar(50) NOT NULL,
	nameOp varchar(50) NOT NULL,
	PRIMARY KEY (nameProjekt)
);


CREATE TABLE rejseafregning
(
	rejseafregning_ID int NOT NULL AUTO_INCREMENT,
	brugernavn varchar(10) NOT NULL,
	nameProjekt varchar(50) NOT NULL,
	land varchar(20) NOT NULL,
	status varchar(20) NOT NULL,
	datoStart date NOT NULL,
	datoSlut date NOT NULL,
	city varchar(20) NOT NULL,
	anledning varchar(100) NOT NULL,
	anviser varchar(35) NOT NULL,
	godkender varchar(35) NOT NULL,
	forklaring varchar(1000),
	PRIMARY KEY (rejseafregning_ID)
);


CREATE TABLE rejsedag
(
	rejsedag_ID int NOT NULL AUTO_INCREMENT,
	rejseafregning_ID int NOT NULL,
	dato date NOT NULL,
	start time NOT NULL,
	slut time NOT NULL,
	morgenmad boolean NOT NULL,
	frokost boolean NOT NULL,
	aftensmad boolean NOT NULL,
	PRIMARY KEY (rejsedag_ID)
);


CREATE TABLE udgift
(
	udgift_ID int NOT NULL AUTO_INCREMENT,
	rejseafregning_ID int NOT NULL,
	bilag_ID int NOT NULL,
	udgiftType varchar(50) NOT NULL,
	betalingsType varchar(50) NOT NULL,
	forklaring varchar(300) NOT NULL,
	dato date NOT NULL,
	beloeb double(8,2) NOT NULL,
	PRIMARY KEY (udgift_ID)
);



/* Create Foreign Keys */

ALTER TABLE udgift
	ADD FOREIGN KEY (bilag_ID)
	REFERENCES bilag (bilag_ID)
	ON UPDATE RESTRICT
	ON DELETE RESTRICT
;


ALTER TABLE rejseafregning
	ADD FOREIGN KEY (land)
	REFERENCES godtgoerelse (Land)
	ON UPDATE RESTRICT
	ON DELETE RESTRICT
;


ALTER TABLE rejseafregning
	ADD FOREIGN KEY (brugernavn)
	REFERENCES medarbejder (brugernavn)
	ON UPDATE RESTRICT
	ON DELETE RESTRICT
;


ALTER TABLE projekt
	ADD FOREIGN KEY (nameOp)
	REFERENCES opgave (nameOp)
	ON UPDATE RESTRICT
	ON DELETE RESTRICT
;


ALTER TABLE rejseafregning
	ADD FOREIGN KEY (nameProjekt)
	REFERENCES projekt (nameProjekt)
	ON UPDATE RESTRICT
	ON DELETE RESTRICT
;


ALTER TABLE rejsedag
	ADD FOREIGN KEY (rejseafregning_ID)
	REFERENCES rejseafregning (rejseafregning_ID)
	ON UPDATE RESTRICT
	ON DELETE RESTRICT
;


ALTER TABLE udgift
	ADD FOREIGN KEY (rejseafregning_ID)
	REFERENCES rejseafregning (rejseafregning_ID)
	ON UPDATE RESTRICT
	ON DELETE RESTRICT
;



