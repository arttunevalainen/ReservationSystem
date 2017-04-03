WWW-Ohjelmointi harjoitustyö tietokanta:


CREATE TABLE Users (
Id SERIAL,
Username VARCHAR(40),
Password VARCHAR(40) NOT NULL,
Name VARCHAR(50) NOT NULL,
Role VARCHAR(10) NOT NULL,
PRIMARY KEY (Id));


CREATE TABLE Reservable (
Id SERIAL,
Name VARCHAR(40),
Info VARCHAR(400),
OwnerId INT NOT NULL,
PRIMARY KEY (Id),
FOREIGN KEY (OwnerId) REFERENCES Users(Id));


CREATE TABLE Reservations (
Id SERIAL,
ReservableId INT NOT NULL,
ReserverId INT NOT NULL,
StartTime TIMESTAMP NOT NULL,
EndTime TIMESTAMP NOT NULL,
PRIMARY KEY (Id),
FOREIGN KEY (ReserverId) REFERENCES Users(Id),
FOREIGN KEY (ReservableId) REFERENCES Reservable(Id));


CREATE TABLE Privileges (
Id SERIAL,
UserId INT NOT NULL,
ReservableId INT NOT NULL,
PRIMARY KEY (Id),
FOREIGN KEY (UserId) REFERENCES Users(Id),
FOREIGN KEY (ReservableId) REFERENCES Reservable(Id));


//TESTIDATAAA
INSERT INTO Users (Username, password, name, role) VALUES ('admin', 'admin', 'admin', 'admin');

INSERT INTO Reservable(Name, info, ownerId) VALUES ('Paku', 'varaa paku', 1);

INSERT INTO Reservations (reservableid, reserverid, starttime, endtime) VALUES (1, 1, '2017-04-01 09:00:00', '2017-04-01 12:00:00');

