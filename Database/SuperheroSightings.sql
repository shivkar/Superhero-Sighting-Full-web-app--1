DROP DATABASE IF EXISTS Superherosightings;
CREATE DATABASE Superherosightings;

USE Superherosightings;

CREATE TABLE Superheros (
	superhero_id INT PRIMARY KEY AUTO_INCREMENT,
    super_name VARCHAR(100) NOT NULL,
	super_description VARCHAR(500) NOT NULL,
    is_superhero BOOLEAN NOT NULL
   
);
    
CREATE TABLE Powers (
	power_id INT PRIMARY KEY AUTO_INCREMENT,
    power_name VARCHAR(50) NOT NULL,
    power_description VARCHAR(500) NOT NULL
);
    
CREATE TABLE Superhero_Power (
    superhero_id INT NOT NULL,
    power_id INT NOT NULL,
    PRIMARY KEY pk_Superhero_Power (superhero_id , power_id),
    FOREIGN KEY (superhero_id)
        REFERENCES Superheros (superhero_id),
    FOREIGN KEY (power_id)
        REFERENCES Powers (power_id)
);    

CREATE TABLE Locations (
	loc_id INT PRIMARY KEY AUTO_INCREMENT,
    loc_name VARCHAR(50) NOT NULL,
    loc_description VARCHAR(500) NOT NULL,
    loc_street VARCHAR(100) NOT NULL,
    loc_city VARCHAR(50) NOT NULL,
    loc_state CHAR(2) NOT NULL,
    loc_zip CHAR(6) NOT NULL,
    loc_latitude VARCHAR(12) NOT NULL,
    loc_longitude VARCHAR(12) NOT NULL
);

CREATE TABLE Sightings (
	sighting_id INT PRIMARY KEY AUTO_INCREMENT,
    superhero_id INT NOT NULL,
    loc_id INT NOT NULL,
    sighting_time DATE,
    FOREIGN KEY (superhero_id) REFERENCES Superheros (superhero_id),
    FOREIGN KEY (loc_id) REFERENCES Locations (loc_id)
);

CREATE TABLE Organizations (
	org_id INT PRIMARY KEY AUTO_INCREMENT,
    org_name VARCHAR(100) NOT NULL,
    org_description VARCHAR(500) NOT NULL,
    org_hotline CHAR(14) NOT NULL
);

CREATE TABLE Super_Organization (
	superhero_id INT NOT NULL,
    org_id INT NOT NULL,
    PRIMARY KEY pk_Super_Organization (superhero_id , org_id),
    FOREIGN KEY (superhero_id) REFERENCES Superheros (superhero_id),
    FOREIGN KEY (org_id) REFERENCES Organizations (org_id)
);

SELECT * FROM superheros ;

SELECT * FROM Locations ;
SELECT * FROM Sightings ;
SELECT * FROM Powers ;
SELECT * FROM Organizations ;
