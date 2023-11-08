CREATE DATABASE IF NOT EXISTS chess;
USE chess;

CREATE TABLE  IF NOT EXISTS games (
    gameID INT NOT NULL AUTO_INCREMENT,
    game VARCHAR(500) NOT NULL,
    gameName VARCHAR(255) NOT NULL UNIQUE,
    whiteUsername VARCHAR(255),
    blackUsername VARCHAR(255),
    PRIMARY KEY (gameID)
);


CREATE TABLE  IF NOT EXISTS auths (
    authToken VARCHAR(500) NOT NULL,
    username VARCHAR(50) NOT NULL,
    PRIMARY KEY (authToken)
);

CREATE TABLE  IF NOT EXISTS users (
    username VARCHAR(50) NOT NULL,
    password VARCHAR(255) NOT NULL,
    email VARCHAR(255) NOT NULL,
    PRIMARY KEY (username)
);

