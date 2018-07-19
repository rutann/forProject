DROP TABLE if exists Orders;
DROP TABLE if exists Customers;
DROP TABLE if exists Salespeople;

CREATE TABLE Salespeople
(
	snum  INTEGER NOT NULL PRIMARY KEY AUTO_INCREMENT, 
	sname char(10) NOT NULL, 
	city  char(10), 
	comm  decimal(10,5)
); 

CREATE TABLE Customers (
    cnum INTEGER NOT NULL PRIMARY KEY AUTO_INCREMENT,
    cname CHAR(10) NOT NULL,
    city CHAR(10),
    rating INTEGER,
    snum INTEGER,
    FOREIGN KEY (snum) REFERENCES Salespeople ( snum),
    UNIQUE (cnum, snum)
); 

CREATE TABLE Orders
(
	onum  integer NOT NULL PRIMARY KEY AUTO_INCREMENT, 
	amt   decimal(10,5), 
	odate date NOT NULL,
	cnum  integer NOT NULL,
	snum  INTEGER NOT NULL,
    FOREIGN KEY (cnum, snum) REFERENCES Customers ( cnum, snum)
); 