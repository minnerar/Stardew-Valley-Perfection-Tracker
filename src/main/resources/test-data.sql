--ROLLBACK; 

START TRANSACTION;

-- Drop tables if they exist
DROP TABLE IF EXISTS item_classification, achievement_item, item, villager, achievement, classification CASCADE;
DROP SEQUENCE IF EXISTS achievement_serial, item_serial, villager_serial, classification_serial;

-- Create serials
CREATE SEQUENCE achievement_serial;
CREATE SEQUENCE item_serial;
CREATE SEQUENCE villager_serial;
CREATE SEQUENCE classification_serial;

-- Create Achievement Table
CREATE TABLE achievement (
	achievement_id int NOT NULL DEFAULT nextval('achievement_serial'),
	name varchar(80) NOT NULL,          -- Name of the achievement
	progress int NOT NULL DEFAULT 0,    -- Current completion progress
	current int NOT NULL DEFAULT 0,		-- Progress / total_needed 
	description text NOT NULL,	        -- Description of the achievement
	total_needed int NOT NULL,		    -- Total amount of things needed to complete the achievement 
	CONSTRAINT pk_achievement PRIMARY KEY (achievement_id)
);

-- Create Classification Table
CREATE TABLE classification (
	classification_id int NOT NULL DEFAULT nextval('classification_serial'),			
	name varchar NOT NULL,	-- classification name of the item
	CONSTRAINT pk_classification PRIMARY KEY (classification_id)
);

-- Create Item Table
CREATE TABLE item (
	item_id int NOT NULL DEFAULT nextval('item_serial'),
	classification_id int NOT NULL,		-- Classification id (FK)
	name varchar NOT NULL,				-- Name of the item
	season varchar,						-- Season item is found in
	time varchar,						-- Time (if applicable) item is found during
	weather varchar,					-- Weather (if applicable) item is found during
	location varchar,					-- Location item is found
	description text,					-- Description of item
	completed boolean DEFAULT false,	-- Whether it's completed or not
	CONSTRAINT pk_item PRIMARY KEY (item_id),
	CONSTRAINT fk_item_classification FOREIGN KEY (classification_id) REFERENCES classification (classification_id)
);

-- Create Villager Table
CREATE TABLE villager (
	villager_id int NOT NULL DEFAULT nextval('villager_serial'), 
	classification_id int DEFAULT 1,	-- Foreign key, references classification (11 is classification villager)
	name varchar NOT NULL,				-- Villager name
	marriage_candidate boolean, 		-- Boolean, set true for marriage candidates
	birthday varchar NOT NULL,		-- Villager birthday
	loved1 varchar NOT NULL,		-- Loved gift of villager
	loved2 varchar NOT NULL,		-- Loved gift of villager
	loved3 varchar,					-- Loved gift of villager
	loved4 varchar,					-- Loved gift of villager
	loved5 varchar,					-- Loved gift of villager
	loved6 varchar,					-- Loved gift of villager
	loved7 varchar,					-- Loved gift of villager
	loved8 varchar,					-- Loved gift of villager
	loved9 varchar,					-- Loved gift of villager
	loved10 varchar,				-- Loved gift of villager
	loved11 varchar,				-- Loved gift of villager
	loved12 varchar,				-- Loved gift of villager
	description text NOT NULL,		-- Villager description from Stardew Valley Wiki
	CONSTRAINT pk_villager PRIMARY KEY (villager_id),
	CONSTRAINT fk_classification_villager FOREIGN KEY (classification_id) REFERENCES classification (classification_id)
);

-- Create item_classification Table
CREATE TABLE item_classification (
	item_id int NOT NULL,
	classification_id int NOT NULL,
	CONSTRAINT pk_item_classification PRIMARY KEY (item_id, classification_id),
	CONSTRAINT fk_item_classification_item FOREIGN KEY (item_id) REFERENCES item (item_id),
	CONSTRAINT fk_item_classification_classification FOREIGN KEY (classification_id) REFERENCES classification (classification_id)
);
	
-- Create achievement_item Table
CREATE TABLE achievement_item (
	achievement_id int NOT NULL,
	item_id int NOT NULL,
	CONSTRAINT pk_achievement_item PRIMARY KEY (achievement_id, item_id),
	CONSTRAINT fk_achievement_item_achievement FOREIGN KEY (achievement_id) REFERENCES achievement (achievement_id),
	CONSTRAINT fk_achievement_item_item FOREIGN KEY (item_id) REFERENCES item (item_id)
);

-- INSERT STATEMENTS 

-- Insert values for achievement table
INSERT INTO achievement (name, description, total_needed)
VALUES 
('Test Achievement One', 'Test Achievement 1 description', '100'),
('Test Achievement Two', 'Test Achievement 2 description', '1'),
('Test Achievement Three', 'Test Achievement 3 description', '0'),
('Test Achievement Four', 'Test Achievement 4 description', '50');


-- insert values for classification table
INSERT INTO classification (name)
VALUES 
('Test Classification One'),
('Test Classification Two'),
('Test Classification Three'),
('Test Classification Four');


-- insert values for item table
INSERT INTO item (classification_id, name, season, time, weather, location, description, achievement_id)
VALUES
('4', 'Test Item One', 'Spring', 'Any', 'Rain', 'Forest', 'Test Item 1 Description', 1),
('3', 'Test Item Two', 'Summer', 'Morning', 'Sunny', 'Mountain River', 'Test Item 2 Description', 2),
('2', 'Test Item Three', 'Fall', 'Evening', 'Windy', 'Ocean', 'Test Item 3 Description', 4),
('1', 'Test Item Four', 'Winter', 'Afternoon', 'Snow', 'Mines', 'Test Item 4 Description', 3);


-- insert values for villager
INSERT INTO villager (name, marriage_candidate, birthday, loved1, loved2, loved3, loved4, loved5, loved6, loved7, loved8, loved9, loved10, loved11, loved12, description)
VALUES 
('Murphy', FALSE, 'Winter 3', 'Hamburger', 'Fetch', 'Treat', 'Ball', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'A good boy!'),
('Mister Potato', FALSE, 'Spring 1', 'Mice', 'Yarn', 'Fish', 'Sleep', 'The Zoomies', NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'A friendly cat!'),
('Harry', TRUE, 'Summer 31', 'Magic', 'Flying', 'Sirius', 'Quidditch', 'Chocolate Frogs', 
 	'Family', 'Adventure', 'Lupin', 'Ron', 'Hermione', 'Pumpkin Juice', 'Fluffy', 'The boy who lived'),
('Sophie', TRUE, 'Winter 28', 'Adventure', 'Sewing', 'Cooking', 'Calcifer', 'Howl', 'Magic', NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'An ordinary girl.');


-- Inserts for achievement_item table
INSERT INTO achievement_item (item_id, achievement_id)
VALUES
(1, 3), (2, 4), (3, 2), (4, 1);


-- Insert into item_classification table
INSERT INTO item_classification (item_id, classification_id)
VALUES
(1, 3), (2, 4), (3, 2), (4, 1);


-- THE COMMIT
COMMIT;