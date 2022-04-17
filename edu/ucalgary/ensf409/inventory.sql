/* ENSF 409 W22 Project Example Database
 2022 Barcomb and Marasco
 
 Each time this file is executed, it will reset the database to the original state defined below.
 
 */

DROP DATABASE IF EXISTS FOOD_INVENTORY;
CREATE DATABASE FOOD_INVENTORY; 
USE FOOD_INVENTORY;


DROP TABLE IF EXISTS DAILY_CLIENT_NEEDS;
CREATE TABLE DAILY_CLIENT_NEEDS (
	ClientID		int not null AUTO_INCREMENT,
	Client			varchar(25),
	WholeGrains		int,
	FruitVeggies	int,
	Protein			int,
	Other			int,
	Calories		int,
	primary key (ClientID)
);

INSERT INTO DAILY_CLIENT_NEEDS (Client, WholeGrains, FruitVeggies, Protein, Other, Calories)
VALUES
('Adult Male',	16,	28,	26,	30,	2500),
('Adult Female', 16, 28, 26, 30, 2000),
('Child over 8', 21, 33, 31, 15, 2200),
('Child under 8', 21, 33, 31, 15, 1400);

DROP TABLE IF EXISTS AVAILABLE_FOOD;
CREATE TABLE AVAILABLE_FOOD (
	ItemID			int not null AUTO_INCREMENT,
	Name			varchar(50),
	GrainContent	int,
	FVContent		int,
	ProContent		int,
	Other			int,
	Calories		int,
	primary key (ItemID)
);

INSERT INTO AVAILABLE_FOOD (Name, GrainContent, FVContent, ProContent, Other, Calories)
VALUES
('Apple, dozen', 0, 100, 0, 0, 624), 
('Apple, dozen', 0, 100, 0, 0, 624), 
('Granola Bar, box', 71, 0, 6, 23, 936), 
('Chicken broth, can', 0, 0, 14, 86, 95), 
('Broccoli, 3 bunches', 0, 92, 8, 0, 621), 
('Wheat bread, loaf', 96, 0, 4, 0, 2192), 
('Orange, dozen', 0, 100, 0, 0, 864), 
('Orange, dozen', 0, 100, 0, 0, 864), 
('Eggs, dozen', 0, 0, 9, 91, 864), 
('Chicken breast, pound', 0, 0, 30, 70, 730), 
('Pasta, dry, pound', 75, 0, 13, 12, 1683), 
('Cheddar cheese, pound', 0, 0, 23, 77, 1851), 
('Quinoa, pound', 87, 0, 13, 0, 1397), 
('Pearl barley, 1 pound', 0, 0, 44, 56, 1814),
('Bananas, bunch', 0, 100, 0, 0, 672), 
('Milk, 1%, 4 L', 0, 0, 3, 97, 1785), 
('Avocado, dozen', 0, 100, 0, 0, 2880), 
('Soy burger, 20', 21, 0, 41, 38, 4800), 
('Whey powder, large jar', 16, 0, 77, 7, 3900), 
('Roasted seaweed, 250 g', 0, 26, 26, 48, 1500);
