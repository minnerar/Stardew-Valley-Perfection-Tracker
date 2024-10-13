-- database m2_final_project
BEGIN TRANSACTION;

-- *************************************************************************************************
-- Drop all db objects in the proper order
-- *************************************************************************************************
DROP TABLE IF EXISTS users;
-- Drop tables if they exist
DROP TABLE IF EXISTS item_classification, achievement_item, item, villager, achievement, classification CASCADE;

-- *************************************************************************************************
-- Create the tables and constraints
-- *************************************************************************************************

-- users (name is pluralized because 'user' is a SQL keyword)
CREATE TABLE users (
	user_id SERIAL,
	username varchar(50) NOT NULL UNIQUE,
	password_hash varchar(200) NOT NULL,
	role varchar(50) NOT NULL,
	CONSTRAINT PK_user PRIMARY KEY (user_id)
);

-- Create Achievement Table
CREATE TABLE achievement (
	achievement_id int,
	name varchar(80) NOT NULL,          -- Name of the achievement
	progress int NOT NULL DEFAULT 0,    -- Current completion progress
	current int NOT NULL DEFAULT 0,		-- Progress / total_needed
	description text NOT NULL,	        -- Description of the achievement
	total_needed int NOT NULL,		    -- Total amount of things needed to complete the achievement
	imageURL varchar,               -- png for the image
	CONSTRAINT pk_achievement PRIMARY KEY (achievement_id)
);

-- Create Classification Table
CREATE TABLE classification (
	classification_id int,
	name varchar NOT NULL,	-- classification name of the item
	CONSTRAINT pk_classification PRIMARY KEY (classification_id)
);

-- Create Item Table
CREATE TABLE item (
	item_id int,
	classification_id int,		-- Classification id (FK)
	name varchar NOT NULL,				-- Name of the item
	season varchar,						-- Season item is found in
	time varchar,						-- Time (if applicable) item is found during
	weather varchar,					-- Weather (if applicable) item is found during
	location varchar,					-- Location item is found
	description text,					-- Description of item
	completed boolean DEFAULT false,	-- Whether it's completed or not
	imageURL varchar,               -- png for the image
	CONSTRAINT pk_item PRIMARY KEY (item_id),
	CONSTRAINT fk_item_classification FOREIGN KEY (classification_id) REFERENCES classification (classification_id)
);

-- Create Villager Table
CREATE TABLE villager (
	villager_id int,
	classification_id int DEFAULT 11,	-- Foreign key, references classification (11 is classification villager)
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
	imageURL varchar,               -- png for the image
	CONSTRAINT pk_villager PRIMARY KEY (villager_id),
	CONSTRAINT fk_classification_villager FOREIGN KEY (classification_id) REFERENCES classification (classification_id)
);


-- -- Create SKill Table
-- CREATE TABLE skill (
-- 	skill_id int NOT NULL DEFAULT nextval('skill_serial'),
-- 	name varchar NOT NULL,				-- Skill name
-- 	current_level int NOT NULL DEFAULT 0,	-- Current skill level, starts at 0
-- 	max_level int NOT NULL DEFAULT 10,		-- Max still level, max is 10
-- 	description text NOT NULL,				-- Skill description
-- 	classification_id int NOT NULL DEFAULT 12,		-- Foreign Key to classification table (12 is miscellaneous)
-- 	CONSTRAINT pk_skill PRIMARY KEY (skill_id),
-- 	CONSTRAINT fk_skill_classification FOREIGN KEY (classification_id) REFERENCES classification (classification_id)
-- );

-- Create item_classification Table
CREATE TABLE item_classification (
	item_id int,
	classification_id int,
	CONSTRAINT pk_item_classification PRIMARY KEY (item_id, classification_id),
	CONSTRAINT fk_item_classification_item FOREIGN KEY (item_id) REFERENCES item (item_id),
	CONSTRAINT fk_item_classification_classification FOREIGN KEY (classification_id) REFERENCES classification (classification_id)
);

-- Create achievement_item Table
CREATE TABLE achievement_item (
	achievement_id int,
	item_id int,
	CONSTRAINT pk_achievement_item PRIMARY KEY (achievement_id, item_id),
	CONSTRAINT fk_achievement_item_achievement FOREIGN KEY (achievement_id) REFERENCES achievement (achievement_id),
	CONSTRAINT fk_achievement_item_item FOREIGN KEY (item_id) REFERENCES item (item_id)
);

-- *************************************************************************************************
-- Insert some sample starting data
-- *************************************************************************************************

-- Users
-- Password for all users is password
INSERT INTO
    users (username, password_hash, role)
VALUES
    ('user', '$2a$10$tmxuYYg1f5T0eXsTPlq/V.DJUKmRHyFbJ.o.liI1T35TFbjs2xiem','ROLE_USER'),
    ('admin','$2a$10$tmxuYYg1f5T0eXsTPlq/V.DJUKmRHyFbJ.o.liI1T35TFbjs2xiem','ROLE_ADMIN');

-- Insert values for achievement table
INSERT INTO achievement (achievement_id, name, description, total_needed, imageURL)
VALUES
(0, 'Empty Achievement', 'Empty Achievement to catch deleted Achievements.', 0, 'https://upload.wikimedia.org/wikipedia/commons/thumb/d/d9/Icon-round-Question_mark.svg/1200px-Icon-round-Question_mark.svg.png'),
(1, 'Produce & Forage Shipped', 'Ship one of every item in the Items Shipped (Farm & Forage) tab in the collections menu.', 155, 'https://stardewvalleywiki.com/mediawiki/images/b/b8/Achievement_Full_Shipment.jpg'),
(2, 'Obelisks on Farm', 'Build Earth Obelisk, Water Obelisk, Desert Obelisk, and Island Obelisk on the farm.', 4, 'https://stardewvalleywiki.com/mediawiki/images/e/e4/Warp_Totem_Farm.png'),
(3, 'Golden Clock on Farm', 'Build Gold Clock on the farm.', 1, 'https://stardewvalleywiki.com/mediawiki/images/thumb/b/b5/Gold_Clock.png/100px-Gold_Clock.png'),
(4, 'Monster Slayer Hero', 'Complete all monster eradication goals for the Adventurers Guild.', 12, 'https://stardewvalleywiki.com/mediawiki/images/6/66/Achievement_Protector_Of_The_Valley.jpg'),
(5, 'Great Friends', 'Reach maximum hearts with every villager. This includes Kent, so it is not possible to complete this in year 1. Note: The maximum hearts for datable villagers is 8, and for non-datable villagers, 10 is the max. If the player has children, their friendship status does not affect the score.', 34, 'https://stardewvalleywiki.com/mediawiki/images/2/29/Emote_Heart.png'),
(6, 'Farmer Level', 'Reach level 10 in every skill.', 25, 'https://stardewvalleywiki.com/mediawiki/images/4/49/Achievement_Master_Of_The_Five_Ways.jpg'),
(7, 'Found All Stardrops', 'Find all Stardrops.', 7, 'https://stardewvalleywiki.com/mediawiki/images/e/e0/Achievement_Mystery_Of_The_Stardrops.jpg'),
(8, 'Cooking Recipes Made', 'Cook every recipe.', 81, 'https://stardewvalleywiki.com/mediawiki/images/d/dd/Achievement_Gourmet_Chef.jpg'),
(9, 'Crafting Recipes Made', 'Craft all items. In single-player, the Wedding Ring is not required.', 149, 'https://stardewvalleywiki.com/mediawiki/images/6/60/Achievement_Master_Craft.jpg'),
(10, 'Fish Caught', 'Catch every fish in the Fish tab in the collections menu.', 72, 'https://stardewvalleywiki.com/mediawiki/images/6/65/Achievement_Master_Angler.jpg'),
(11, 'Golden Walnuts Found', 'Find all Golden Walnuts on Ginger Island.', 130, 'https://stardewvalleywiki.com/mediawiki/images/5/54/Golden_Walnut.png');

-- insert values for classification table
INSERT INTO classification (classification_id, name)
VALUES
(0, 'Empty'),
(1, 'Animal'),
(2, 'Artifact'),
(3, 'Artisan'),
(4, 'Cooking'),
(5, 'Crafting'),
(6, 'Crop'),
(7, 'Fish'),
(8, 'Forageable'),
(9, 'Mineral'),
(10, 'Stardrop'),
(11, 'Villager'),
(12, 'Miscellaneous');

-- -- insert values for skill table
-- INSERT INTO skill (name, description)
-- VALUES
-- ('Farming', 'Levels are gained by harvesting crops and caring for animals.
--  	Each level grants +1 how and watering can proficiency.'),
-- ('Mining', 'Mining skill is increased by breaking rocks (normally done with a Pickaxe).
--  	Each level grants +1 pickaxe proficiency'),
-- ('Foraging', 'Forgaging skill includes both gathered foraged goods, and wood from trees chopped with an axe tool.
--  	Each level grants +1 axe proficiency.'),
-- ('Fishing', 'Fishing is associated with successfully completing the fishing mini-game or catching fish in a Crab Pot,
--  	increasing the fishing skill. Each level grants +1 fishing rod proficiency.'),
-- ('Combat', 'Combat is a skill tied to the players ability to fight against monsters.');

-- insert values for item table
INSERT INTO item (item_id, name, season, time, weather, location, description, imageURL)
VALUES
(0, 'Empty Item', NULL, NULL, NULL, NULL, 'An Item To Catch Deleted Items.', 'https://upload.wikimedia.org/wikipedia/commons/thumb/d/d9/Icon-round-Question_mark.svg/1200px-Icon-round-Question_mark.svg.png'),
(1, 'Algae Soup', NULL, NULL, NULL, NULL, 'It''s a little slimy.', 'https://stardewvalleywiki.com/mediawiki/images/5/53/Algae_Soup.png'),
(2, 'Artichoke Dip', NULL, NULL, NULL, NULL, 'It''s cool and refreshing.', 'https://stardewvalleywiki.com/mediawiki/images/7/77/Artichoke_Dip.png'),
(3, 'Autumn''s Bounty', NULL, NULL, NULL, NULL, 'A taste of the season.', 'https://stardewvalleywiki.com/mediawiki/images/f/f4/Autumn%27s_Bounty.png'),
(4, 'Baked Fish', NULL, NULL, NULL, NULL, 'Baked fish on a bed of herbs.', 'https://stardewvalleywiki.com/mediawiki/images/9/94/Baked_Fish.png'),
(5, 'Banana Pudding', NULL, NULL, NULL, NULL, 'A creamy dessert with a wonderful tropical flavor.', 'https://stardewvalleywiki.com/mediawiki/images/4/40/Banana_Pudding.png'),
(6, 'Bean Hotpot', NULL, NULL, NULL, NULL, 'It sure is healthy.', 'https://stardewvalleywiki.com/mediawiki/images/2/24/Bean_Hotpot.png'),
(7, 'Blackberry Cobbler', NULL, NULL, NULL, NULL, 'There''s nothing quite like it.', 'https://stardewvalleywiki.com/mediawiki/images/7/70/Blackberry_Cobbler.png'),
(8, 'Blueberry Tart', NULL, NULL, NULL, NULL, 'It''s subtle and refreshing.', 'https://stardewvalleywiki.com/mediawiki/images/9/9b/Blueberry_Tart.png'),
(9, 'Bread', NULL, NULL, NULL, NULL, 'A crusty baguette.', 'https://stardewvalleywiki.com/mediawiki/images/e/e1/Bread.png'),
(10, 'Bruschetta', NULL, NULL, NULL, NULL, 'Roasted tomatoes on a crisp white bread.', 'https://stardewvalleywiki.com/mediawiki/images/c/ca/Bruschetta.png'),
(11, 'Carp Surprise', NULL, NULL, NULL, NULL, 'It''s bland and oily.', 'https://stardewvalleywiki.com/mediawiki/images/c/cc/Carp_Surprise.png'),
(12, 'Cheese Cauliflower', NULL, NULL, NULL, NULL, 'It smells great!', 'https://stardewvalleywiki.com/mediawiki/images/6/6e/Cheese_Cauliflower.png'),
(13, 'Chocolate Cake', NULL, NULL, NULL, NULL, 'Rich and moist with a thick fudge icing.', 'https://stardewvalleywiki.com/mediawiki/images/8/87/Chocolate_Cake.png'),
(14, 'Chowder', NULL, NULL, NULL, NULL, 'A perfect way to warm yourself after a cold night at sea.', 'https://stardewvalleywiki.com/mediawiki/images/9/95/Chowder.png'),
(15, 'Coleslaw', NULL, NULL, NULL, NULL, 'It''s light, fresh and very healthy.', 'https://stardewvalleywiki.com/mediawiki/images/e/e1/Coleslaw.png'),
(16, 'Complete Breakfast', NULL, NULL, NULL, NULL, 'You''ll feel ready to take on the world!', 'https://stardewvalleywiki.com/mediawiki/images/3/3d/Complete_Breakfast.png'),
(17, 'Blackberry', 'Fall', NULL, 'ANY', 'SDV', 'An early-fall treat.', 'https://stardewvalleywiki.com/mediawiki/images/2/25/Blackberry.png'),
(18, 'Cactus Fruit', 'Any', NULL, 'ANY', 'Oasis', 'The sweet fruit of the prickly pear cactus.', 'https://stardewvalleywiki.com/mediawiki/images/thumb/3/32/Cactus_Fruit.png/36px-Cactus_Fruit.png'),
(19, 'Cave Carrot', 'Any', NULL, 'ANY', 'Mines', 'A starchy snack found in caves. It helps miners work longer.', 'https://stardewvalleywiki.com/mediawiki/images/3/34/Cave_Carrot.png'),
(20, 'Chanterelle', 'Fall', NULL, 'ANY', 'Secret Woods', 'A tasty mushroom with a fruity smell and slightly peppery flavor.', 'https://stardewvalleywiki.com/mediawiki/images/1/1d/Chanterelle.png'),
(21, 'Coconut', 'Any', NULL, 'ANY', 'Oasis', 'A seed of the coconut palm. It has many culinary uses.', 'https://stardewvalleywiki.com/mediawiki/images/thumb/2/2f/Coconut.png/36px-Coconut.png'),
(22, 'Common Mushroom', 'Spring', NULL, 'ANY', 'Secret Woods', 'Slightly nutty, with good texture.', 'https://stardewvalleywiki.com/mediawiki/images/2/2e/Common_Mushroom.png'),
(23, 'Coral', 'Any', NULL, 'ANY', 'Beach', 'A colony of tiny creatures that clump together to form beautiful structures.', 'https://stardewvalleywiki.com/mediawiki/images/thumb/b/b1/Coral.png/36px-Coral.png'),
(24, 'Crocus', 'Winter', NULL, 'ANY', 'SDV', 'A flower that can bloom in the winter.', 'https://stardewvalleywiki.com/mediawiki/images/2/2f/Crocus.png'),
(25, 'Crystal Fruit', 'Winter', NULL, 'ANY', 'SDV', 'A delicate fruit that pops up from the snow.', 'https://stardewvalleywiki.com/mediawiki/images/1/16/Crystal_Fruit.png'),
(26, 'Daffodil', 'Spring', NULL, 'ANY', 'SDV', 'A traditional spring flower that makes a nice gift.', 'https://stardewvalleywiki.com/mediawiki/images/4/4b/Daffodil.png'),
(27, 'Dandelion', 'Spring', NULL, 'ANY', 'SDV', 'Not the prettiest flower, but the leaves make a good salad.', 'https://stardewvalleywiki.com/mediawiki/images/b/b1/Dandelion.png'),
(28, 'Fiddlehead Fern', 'Summer', NULL, 'ANY', 'Secret Woods', 'The young shoots are an edible specialty.', 'https://stardewvalleywiki.com/mediawiki/images/4/48/Fiddlehead_Fern.png'),
(29, 'Ginger', 'Any', NULL, 'ANY', 'Ginger Island', 'This sharp, spicy root is said to increase vitality.', 'https://stardewvalleywiki.com/mediawiki/images/8/85/Ginger.png'),
(30, 'Grape', 'Summer', NULL, 'ANY', 'SDV', 'A sweet cluster of fruit.', 'https://stardewvalleywiki.com/mediawiki/images/c/c2/Grape.png'),
(31, 'Hazelnut', 'Fall', NULL, 'ANY', 'SDV', 'That is one big hazelnut!', 'https://stardewvalleywiki.com/mediawiki/images/3/31/Hazelnut.png'),
(32, 'Holly', 'Winter', NULL, 'ANY', 'SDV', 'The leaves and bright red berries make a popular winter decoration.', 'https://stardewvalleywiki.com/mediawiki/images/b/b8/Holly.png'),
(33, 'Albacore', 'Fall, Winter', '6am – 11am, 6pm – 2am', 'Any', 'Ocean', 'Prefers temperature "edges" where cool and warm water meet.', 'https://stardewvalleywiki.com/mediawiki/images/e/e1/Albacore.png'),
(34, 'Anchovy', 'Spring, Fall', 'Anytime', 'Any', 'Ocean', 'A small silver fish found in the ocean.', 'https://stardewvalleywiki.com/mediawiki/images/7/79/Anchovy.png'),
(35, 'Angler', 'Fall', 'Any', 'Any', 'North of JojaMart on the wooden plank bridge. Requires level 3 fishing.', 'Uses a bioluminescent dangler to attract prey.', 'https://stardewvalleywiki.com/mediawiki/images/b/bf/Angler.png'),
(36, 'Blobfish', 'Winter 15-17', '5pm-2am', 'Any', 'Night Market', 'This odd creature floats above the ocean floor, consuming any edible material in its path.', 'https://stardewvalleywiki.com/mediawiki/images/7/7f/Blobfish.png'),
(37, 'Blue Discus', 'All Seasons', 'Anytime', 'Any', 'Ginger Island Pond and Rivers', 'A brightly colored tropical fish that is popular in aquariums.', 'https://stardewvalleywiki.com/mediawiki/images/e/ee/Blue_Discus.png'),
(38, 'Bream', 'All Seasons', '6pm – 2am', 'Any', 'River', 'A fairly common river fish that becomes active at night.', 'https://stardewvalleywiki.com/mediawiki/images/8/82/Bream.png'),
(39, 'Bullhead', 'All Seasons', 'Anytime', 'Any', 'Mountain Lake', 'A relative of the catfish that eats a variety of foods off the lake bottom.', 'https://stardewvalleywiki.com/mediawiki/images/d/db/Bullhead.png'),
(40, 'Carp', 'All Seasons', 'Anytime', 'Any', 'Mountain Lake', 'A common pond fish.', 'https://stardewvalleywiki.com/mediawiki/images/a/a8/Carp.png'),
(41, 'Catfish', 'Spring, Fall', '6am – 12am', 'Rain', 'River', 'An uncommon fish found in streams.', 'https://stardewvalleywiki.com/mediawiki/images/9/99/Catfish.png'),
(42, 'Cave Jelly', 'All Seasons', 'Anytime', 'Any', 'Mines', 'A rare jelly found in underground lakes.', 'https://stardewvalleywiki.com/mediawiki/images/thumb/0/0a/Cave_Jelly.png/36px-Cave_Jelly.png'),
(43, 'Chub', 'All Seasons', 'Anytime', 'Any', 'Forest River', 'A common freshwater fish known for its voracious appetite.', 'https://stardewvalleywiki.com/mediawiki/images/b/bd/Chub.png'),
(44, 'Clam', 'All Seasons', 'Any', 'Any', 'Ocean (Crabpot)', 'There''s a chewy little guy in there...', 'https://stardewvalleywiki.com/mediawiki/images/e/ed/Clam.png'),
(45, 'Cockle', 'All Seasons', 'Any', 'Any', 'Ocean (Crabpot)', 'A common saltwater clam.', 'https://stardewvalleywiki.com/mediawiki/images/a/ad/Cockle.png'),
(46, 'Crab', 'All Seasons', 'Any', 'Any', 'Ocean (Crabpot)', 'A marine crustacean with two powerful pincers.', 'https://stardewvalleywiki.com/mediawiki/images/6/63/Crab.png'),
(47, 'Crayfish', 'All Seasons', 'Any', 'Any', 'Freshwater (Crabpot)', 'A small freshwater relative of the lobster.', 'https://stardewvalleywiki.com/mediawiki/images/5/53/Crayfish.png'),
(48, 'Crimsonfish', 'Summer', 'Any', 'Any', 'East Pier Beach Fishing Lvl 5', 'Lives deep in the ocean but likes to lay its eggs in the warm summer water.', 'https://stardewvalleywiki.com/mediawiki/images/d/dc/Crimsonfish.png'),
(49, 'Amphibian Fossil', NULL, NULL, NULL, 'Forest (0.6%), Mountain (0.6%), Fishing Chest (3.1-3.4%[2]), Bone Nodes (0.8%)', 'The relatively short hind legs suggest some kind of primordial toad.', 'https://stardewvalleywiki.com/mediawiki/images/c/cf/Amphibian_Fossil.png'),
(50, 'Anchor', NULL, NULL, NULL, 'Beach (4%), Fishing Chest (0.8-0.9%[2]), Artifact Trove (3.6%)', 'It may have belonged to ancient pirates.', 'https://stardewvalleywiki.com/mediawiki/images/c/c0/Anchor.png'),
(51, 'Ancient Doll', NULL, NULL, NULL, 'Mountain (3%), Forest (2.4%), Bus Stop (2.4%), Town (0.8%), Fishing Chest (0.8-0.9%[2]), Feast of the Winter Star, Artifact Trove (3.6%)', 'An ancient doll covered in grime. This doll may have been used as a toy, a decoration, or a prop in some kind of ritual.', 'https://stardewvalleywiki.com/mediawiki/images/c/c0/Ancient_Doll.png'),
(52, 'Ancient Drum', NULL, NULL, NULL, 'Bus Stop (0.7%), Forest (0.7%), Town (0.4%), Frozen Geode (3%), Omni Geode (1%), Artifact Trove (3.6%)', 'It''s a drum made from wood and animal skin. It has a low, reverberating tone.', 'https://stardewvalleywiki.com/mediawiki/images/1/1e/Ancient_Drum.png'),
(53, 'Ancient Seed', NULL, NULL, NULL, 'Forest (0.7%), Mountain (0.7%), Fishing Chest (0.8-0.9%[2]), Monster drop (Bug, Cave Fly, Grub, Mutant Fly, Mutant Grub: 0.5%), Artifact Trove (3.6%)', 'It''s a dry old seed from some ancient plant. By all appearances it''s long since dead...', 'https://stardewvalleywiki.com/mediawiki/images/a/af/Ancient_Seed.png'),
(54, 'Ancient Sword', NULL, NULL, NULL, 'Forest (0.7%), Mountain (0.6%), Fishing Chest (0.8-0.9%[2]), Artifact Trove (3.6%)', 'It''s the remains of an ancient sword. Most of the blade has turned to rust, but the hilt is very finely crafted.', 'https://stardewvalleywiki.com/mediawiki/images/7/76/Ancient_Sword.png'),
(55, 'Arrowhead', NULL, NULL, NULL, 'Mountain (1.6%), Forest (1.6%), Bus Stop (1.6%), Artifact Trove (3.6%)', 'A crudely fashioned point used for hunting.', 'https://stardewvalleywiki.com/mediawiki/images/d/d1/Arrowhead.png'),
(56, 'Bone Flute', NULL, NULL, NULL, 'Forest (0.7%), Mountain (0.7%), Town (0.4%), Fishing Chest (0.8-0.9%[2]), Artifact Trove (3.6%)', 'It''s a prehistoric wind instrument carved from an animal''s bone. It produces an eerie tone.', 'https://stardewvalleywiki.com/mediawiki/images/f/fc/Bone_Flute.png'),
(57, 'Chewing Stick', NULL, NULL, NULL, 'Mountain (1.5%), Forest (1.5%), Town (0.8%), Fishing Chest (0.8-0.9%[2]), Duggy drop (2%), Artifact Trove (3.6%)', 'Ancient people chewed on these to keep their teeth clean.', 'https://stardewvalleywiki.com/mediawiki/images/d/d2/Chewing_Stick.png'),
(58, 'Chicken Statue', NULL, NULL, NULL, 'Farm (9%), Fishing Chest (0.8-0.9%[2]), Artifact Trove (3.6%)', 'It''s a statue of a chicken on a bronze base. The ancient people of this area must have been very fond of chickens.', 'https://stardewvalleywiki.com/mediawiki/images/a/af/Chicken_Statue.png'),
(59, 'Chipped Amphora', NULL, NULL, NULL, 'Town (3%), Artifact Trove (3.6%)', 'An ancient vessel made of ceramic material. Used to transport both dry and wet goods.', 'https://stardewvalleywiki.com/mediawiki/images/9/9e/Chipped_Amphora.png'),
(60, 'Dinosaur Egg', NULL, NULL, NULL, 'Mountain (0.6%), Fishing Chest (0.8-0.9%[2]), Foraged on prehistoric floors of the Skull Cavern, Pepper Rex drop (10%)', 'A giant dino egg... The entire shell is still intact!', 'https://stardewvalleywiki.com/mediawiki/images/a/a1/Dinosaur_Egg.png'),
(61, 'Ancient Seeds', NULL, NULL, NULL, NULL, 'Could these still grow?', 'https://stardewvalleywiki.com/mediawiki/images/e/ec/Ancient_Seeds.png'),
(62, 'Anvil', NULL, NULL, NULL, NULL, 'Allows you to re-forge trinkets, randomizing their stats. Costs 3 iridium bars per use.','https://stardewvalleywiki.com/mediawiki/images/d/dd/Anvil.png'),
(63, 'Bait', NULL, NULL, NULL, NULL, 'Causes fish to bite faster. Must first be attached to a fishing rod.', 'https://stardewvalleywiki.com/mediawiki/images/f/ff/Bait.png'),
(64, 'Bait Maker', NULL, NULL, NULL, NULL, 'Place a fish inside to create targeted bait.', 'https://stardewvalleywiki.com/mediawiki/images/c/c0/Bait_Maker.png'),
(65, 'Barbed Hook', NULL, NULL, NULL, NULL, 'Makes your catch more secure, causing the "fishing bar" to cling to your catch. Works best on slow, weak fish.', 'https://stardewvalleywiki.com/mediawiki/images/4/4f/Barbed_Hook.png'),
(66, 'Barrel Brazier', NULL, NULL, NULL, NULL, 'Provides a moderate amount of light.', 'https://stardewvalleywiki.com/mediawiki/images/7/78/Barrel_Brazier.png'),
(67, 'Basic Fertilizer', NULL, NULL, NULL, NULL, 'Improves soil quality a little, increasing your chance to grow quality crops. Mix into tilled soil.', 'https://stardewvalleywiki.com/mediawiki/images/9/9b/Basic_Fertilizer.png'),
(68, 'Basic Retaining Soil', NULL, NULL, NULL, NULL, 'This soil has a chance of staying watered overnight. Mix into tilled soil.', 'https://stardewvalleywiki.com/mediawiki/images/c/c7/Basic_Retaining_Soil.png'),
(69, 'Bee House', NULL, NULL, NULL, NULL, 'Place outside and wait for delicious honey! (Except in Winter).', 'https://stardewvalleywiki.com/mediawiki/images/c/ce/Bee_House.png'),
(70, 'Big Chest', NULL, NULL, NULL, NULL, 'It can store almost twice as much as a regular chest.', 'https://stardewvalleywiki.com/mediawiki/images/8/89/Big_Chest.png'),
(71, 'Big Stone Chest', NULL, NULL, NULL, NULL, 'It can store almost twice as much as a regular chest.', 'https://stardewvalleywiki.com/mediawiki/images/a/a6/Big_Stone_Chest.png'),
(72, 'Blue Grass Starter', NULL, NULL, NULL, NULL, 'Place this on your farm to plant a clump of blue grass.', 'https://stardewvalleywiki.com/mediawiki/images/1/18/Blue_Grass_Starter.png'),
(73, 'Bomb', NULL, NULL, NULL, NULL, 'Generates an explosion. Watch out!', 'https://stardewvalleywiki.com/mediawiki/images/3/3b/Bomb.png'),
(74, 'Bone Mill', NULL, NULL, NULL, NULL, 'Turns bone items into fertilizers.', 'https://stardewvalleywiki.com/mediawiki/images/c/cc/Bone_Mill.png'),
(75, 'Brick Floor', NULL, NULL, NULL, NULL, 'Place on the ground to create paths or to decorate your floors.', 'https://stardewvalleywiki.com/mediawiki/images/2/29/Brick_Floor.png'),
(76, 'Bug Steak', NULL, NULL, NULL, NULL, 'The last resort of the hungry cave diver.', 'https://stardewvalleywiki.com/mediawiki/images/b/b7/Bug_Steak.png'),
(77, 'Duck Egg', NULL, NULL, NULL, NULL, 'It''s still warm.', 'https://stardewvalleywiki.com/mediawiki/images/3/31/Duck_Egg.png'),
(78, 'Duck Feather', NULL, NULL, NULL, NULL, 'It''s so colorful.', 'https://stardewvalleywiki.com/mediawiki/images/f/f9/Duck_Feather.png'),
(79, 'White Egg', NULL, NULL, NULL, NULL, 'A regular white chicken egg.', 'https://stardewvalleywiki.com/mediawiki/images/2/26/Egg.png'),
(80, 'Large White Egg', NULL, NULL, NULL, NULL, 'It''s an uncommonly large white egg!', 'https://stardewvalleywiki.com/mediawiki/images/5/5d/Large_Egg.png'),
(81, 'Brown Egg', NULL, NULL, NULL, NULL, 'A regular brown chicken egg.', 'https://stardewvalleywiki.com/mediawiki/images/0/01/Brown_Egg.png'),
(82, 'Large Brown Egg', NULL, NULL, NULL, NULL, 'It''s an uncommonly large brown egg!', 'https://stardewvalleywiki.com/mediawiki/images/9/91/Large_Brown_Egg.png'),
(83, 'Goat Milk', NULL, NULL, NULL, NULL, 'The milk of a goat.', 'https://stardewvalleywiki.com/mediawiki/images/4/45/Goat_Milk.png'),
(84, 'Large Goat Milk', NULL, NULL, NULL, NULL, 'A gallon of creamy goat''s milk.', 'https://stardewvalleywiki.com/mediawiki/images/f/f2/Large_Goat_Milk.png'),
(85, 'Golden Egg', NULL, NULL, NULL, NULL, 'A very rare and special egg with a solid gold shell.', 'https://stardewvalleywiki.com/mediawiki/images/9/97/Golden_Egg.png'),
(86, 'Milk', NULL, NULL, NULL, NULL, 'A jug of cow''s milk.', 'https://stardewvalleywiki.com/mediawiki/images/9/92/Milk.png'),
(87, 'Large Milk', NULL, NULL, NULL, NULL, 'A large jug of cows milk.', 'https://stardewvalleywiki.com/mediawiki/images/6/67/Large_Milk.png'),
(88, 'Ostrich Egg', NULL, NULL, NULL, NULL, 'It may be the world''s largest egg.', 'https://stardewvalleywiki.com/mediawiki/images/c/c3/Ostrich_Egg.png'),
(89, 'Rabbit''s Foot', NULL, NULL, NULL, NULL, 'Some say it''s lucky.', 'https://stardewvalleywiki.com/mediawiki/images/c/ca/Rabbit%27s_Foot.png'),
(90, 'Roe', NULL, NULL, NULL, NULL, 'Fresh fish eggs. These can be aged in a preserves jar to bring out more flavor.', 'https://stardewvalleywiki.com/mediawiki/images/5/56/Roe.png'),
(91, 'Wine', NULL, NULL, NULL, NULL, 'Drink in moderation.', 'https://stardewvalleywiki.com/mediawiki/images/6/69/Wine.png'),
(92, 'Pale Ale', NULL, NULL, NULL, NULL, 'Drink in moderation.', 'https://stardewvalleywiki.com/mediawiki/images/7/78/Pale_Ale.png'),
(93, 'Beer', NULL, NULL, NULL, NULL, 'Drink in moderation.', 'https://stardewvalleywiki.com/mediawiki/images/b/b3/Beer.png'),
(94, 'Mead', NULL, NULL, NULL, NULL, 'A fermented beverage made from honey. Drink in moderation.', 'https://stardewvalleywiki.com/mediawiki/images/8/84/Mead.png'),
(95, 'Cheese', NULL, NULL, NULL, NULL, 'It''s your basic cheese.', 'https://stardewvalleywiki.com/mediawiki/images/a/a5/Cheese.png'),
(96, 'Goat Cheese', NULL, NULL, NULL, NULL, 'Soft cheese made from goat''s milk.', 'https://stardewvalleywiki.com/mediawiki/images/c/c8/Goat_Cheese.png'),
(97, 'Juice', NULL, NULL, NULL, NULL, 'A sweet, nutritious beverage.', 'https://stardewvalleywiki.com/mediawiki/images/f/f1/Juice.png'),
(98, 'Cloth', NULL, NULL, NULL, NULL, 'A bolt of fine wool cloth.', 'https://stardewvalleywiki.com/mediawiki/images/5/51/Cloth.png'),
(99, 'Mayonnaise', NULL, NULL, NULL, NULL, 'It looks spreadable.', 'https://stardewvalleywiki.com/mediawiki/images/4/4e/Mayonnaise.png'),
(100, 'Duck Mayonnaise', NULL, NULL, NULL, NULL, 'It''s a rich, yellow mayonnaise.', 'https://stardewvalleywiki.com/mediawiki/images/2/23/Duck_Mayonnaise.png'),
(101, 'Void Mayonnaise', NULL, NULL, NULL, NULL, 'A thick, black paste that smells like burnt hair.', 'https://stardewvalleywiki.com/mediawiki/images/f/f3/Void_Mayonnaise.png'),
(102, 'Dinosaur Mayonnaise', NULL, NULL, NULL, NULL, 'It''s thick and creamy, with a vivid green hue. It smells like grass and leather.', 'https://stardewvalleywiki.com/mediawiki/images/c/c3/Dinosaur_Mayonnaise.png'),
(103, 'Truffle Oil', NULL, NULL, NULL, NULL, 'A gourmet cooking ingredient.', 'https://stardewvalleywiki.com/mediawiki/images/3/3d/Truffle_Oil.png'),
(104, 'Pickles', NULL, NULL, NULL, NULL, 'A jar of your home-made pickles.', 'https://stardewvalleywiki.com/mediawiki/images/c/c7/Pickles.png'),
(105, 'Blue Jazz', 'Spring', NULL, NULL, NULL, 'The flower grows in a sphere to invite as many butterflies as possible.', 'https://stardewvalleywiki.com/mediawiki/images/2/2f/Blue_Jazz.png'),
(106, 'Carrot', 'Spring', NULL, NULL, NULL, 'A fast-growing, colorful tuber that makes for a great snack.', 'https://stardewvalleywiki.com/mediawiki/images/c/c3/Carrot.png'),
(107, 'Cauliflower', 'Spring', NULL, NULL, NULL, 'Valuable, but slow-growing. Despite its pale color, the florets are packed with nutrients.', 'https://stardewvalleywiki.com/mediawiki/images/a/aa/Cauliflower.png'),
(108, 'Coffee Bean', 'Spring / Summer', NULL, NULL, NULL, 'Plant in spring or summer to grow a coffee plant. Place five beans in a keg to make coffee.', 'https://stardewvalleywiki.com/mediawiki/images/3/33/Coffee_Bean.png'),
(109, 'Garlic', 'Spring', NULL, NULL, NULL, 'Adds a wonderful zestiness to dishes. High quality garlic can be pretty spicy.', 'https://stardewvalleywiki.com/mediawiki/images/c/cc/Garlic.png'),
(110, 'Green Bean', 'Spring', NULL, NULL, NULL, 'A juicy little bean with a cool, crisp snap.', 'https://stardewvalleywiki.com/mediawiki/images/5/5c/Green_Bean.png'),
(111, 'Kale', 'Spring', NULL, NULL, NULL, 'The waxy leaves are great in soups and stir fries.', 'https://stardewvalleywiki.com/mediawiki/images/d/d1/Kale.png'),
(112, 'Hops', 'Summer', NULL, NULL, NULL, 'A bitter, tangy flower used to flavor beer.', 'https://stardewvalleywiki.com/mediawiki/images/5/59/Hops.png'),
(113, 'Hot Pepper', 'Summer', NULL, NULL, NULL, 'Fiery hot with a hint of sweetness.', 'https://stardewvalleywiki.com/mediawiki/images/f/f1/Hot_Pepper.png'),
(114, 'Melon', 'Summer', NULL, NULL, NULL, 'A cool, sweet summer treat.', 'https://stardewvalleywiki.com/mediawiki/images/1/19/Melon.png'),
(115, 'Poppy', 'Summer', NULL, NULL, NULL, 'In addition to its colorful flower, the Poppy has culinary and medicinal uses.', 'https://stardewvalleywiki.com/mediawiki/images/3/37/Poppy.png'),
(116, 'Radish', 'Summer', NULL, NULL, NULL, 'A crisp and refreshing root vegetable with hints of pepper when eaten raw.', 'https://stardewvalleywiki.com/mediawiki/images/d/d5/Radish.png'),
(117, 'Beet', 'Fall', NULL, NULL, NULL, 'A sweet and earthy root vegetable. As a bonus, the leaves make a great salad.', 'https://stardewvalleywiki.com/mediawiki/images/a/a4/Beet.png'),
(118, 'Bok Choy', 'Fall', NULL, NULL, NULL, 'The leafy greens and fibrous stalks are healthy and delicious.', 'https://stardewvalleywiki.com/mediawiki/images/4/40/Bok_Choy.png'),
(119, 'Broccoli', 'Fall', NULL, NULL, NULL, 'The flowering head of a broccoli plant. The tiny buds give it a unique texture.', 'https://stardewvalleywiki.com/mediawiki/images/f/f1/Broccoli.png'),
(120, 'Cranberries', 'Fall', NULL, NULL, NULL, 'These tart red berries are a traditional winter food.', 'https://stardewvalleywiki.com/mediawiki/images/6/6e/Cranberries.png'),
(121, 'Eggplant', 'Fall', NULL, NULL, NULL, 'A rich and wholesome relative of the tomato. Delicious fried or stewed.', 'https://stardewvalleywiki.com/mediawiki/images/8/8f/Eggplant.png'),
(122, 'Aerinite', NULL, NULL, NULL, 'Frozen Geode, Omni Geode', 'These crystals are curiously light.', 'https://stardewvalleywiki.com/mediawiki/images/6/6b/Aerinite.png'),
(123, 'Alamite', NULL, NULL, NULL, 'Geode, Omni Geode', 'Its distinctive fluorescence makes it a favorite among rock collectors.', 'https://stardewvalleywiki.com/mediawiki/images/7/7c/Alamite.png'),
(124, 'Baryte', NULL, NULL, NULL, 'Magma Geode, Omni Geode', 'The best specimens resemble a desert rose.', 'https://stardewvalleywiki.com/mediawiki/images/a/aa/Baryte.png'),
(125, 'Basalt', NULL, NULL, NULL, 'Magma Geode, Omni Geode', 'Forms near searing hot magma.', 'https://stardewvalleywiki.com/mediawiki/images/2/22/Basalt.png'),
(126, 'Bixite', NULL, NULL, NULL, 'Magma Geode, Omni Geode, Black Slime', 'A dark metallic Mineral sought after for its cubic structure.', 'https://stardewvalleywiki.com/mediawiki/images/9/98/Bixite.png'),
(127, 'Calcite', NULL, NULL, NULL, 'Geode, Omni Geode', 'This yellow crystal is speckled with shimmering nodules.', 'https://stardewvalleywiki.com/mediawiki/images/9/97/Calcite.png'),
(128, 'Celestine', NULL, NULL, NULL, 'Geode, Omni Geode', 'Some early life forms had bones made from this.', 'https://stardewvalleywiki.com/mediawiki/images/1/19/Celestine.png'),
(129, 'Dolomite', NULL, NULL, NULL, 'Magma Geode, Omni Geode', 'It can occur in coral reefs, often near an underwater volcano.', 'https://stardewvalleywiki.com/mediawiki/images/d/d4/Dolomite.png'),
(130, 'Esperite', NULL, NULL, NULL, 'Frozen Geode, Omni Geode', 'The crystals glow bright green when stimulated.', 'https://stardewvalleywiki.com/mediawiki/images/a/aa/Esperite.png'),
(131, 'Fairy Stone', NULL, NULL, NULL, 'Frozen Geode, Omni Geode', 'An old miner''s song suggests these are made from the bones of ancient fairies.', 'https://stardewvalleywiki.com/mediawiki/images/d/d9/Fairy_Stone.png'),
(132, 'Fire Opal', NULL, NULL, NULL, 'Madma Geode, Omni Geode', 'A rare variety of opal, named for its red spots.', 'https://stardewvalleywiki.com/mediawiki/images/6/60/Fire_Opal.png'),
(133, 'Fluorapatite', NULL, NULL, NULL, 'Frozen Geode, Omni Geode', 'Small amounts are found in human teeth.', 'https://stardewvalleywiki.com/mediawiki/images/4/4a/Fluorapatite.png'),
(134, 'Geminite', NULL, NULL, NULL, 'Frozen Geode, Omni Geode', 'Occurs in brilliant clusters.', 'https://stardewvalleywiki.com/mediawiki/images/5/54/Geminite.png'),
(135, 'Stardrop', NULL, NULL, NULL, NULL, 'A mysterious fruit that empowers those who eat it. The flavor is like a dream... a powerful personal experience, yet difficult to describe to others.', 'https://stardewvalleywiki.com/mediawiki/images/a/a5/Stardrop.png');

-- insert values for villager
INSERT INTO villager (villager_id, name, marriage_candidate, birthday, loved1, loved2, loved3, loved4, loved5, loved6, loved7, loved8, loved9, loved10, loved11, loved12, description, imageURL)
VALUES
(1, 'Alex', TRUE, 'Summer 13', 'Complete Breakfast', 'Jack Be Nimble, Jack Be Thick', 'Salmon Dinner', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'Alex loves sports and hanging out at the beach. He is quite arrogant and brags to everyone that he is going to be a professional athlete. Is his cockiness just a facade to mask his crushing self-doubt? Is he using his sports dream to fill the void left by the disappearance of his parents? Or is he just a brazen youth trying to "look cool"?', 'https://stardewvalleywiki.com/mediawiki/images/0/04/Alex.png'),
(2, 'Elliott', TRUE, 'Fall 5', 'Crab Cakes', 'Duck Feather', 'Lobster', 'Pomegranate', 'Squid Ink', 'Tom Kha Soup', NULL, NULL, NULL, NULL, NULL, NULL, 'Elliott lives alone in a cabin on the beach. He is a writer who dreams of one day writing a magnificent novel. He is a sentimental “romantic” with a tendency to go off onto flowery, poetic tangents. When he can afford it, he enjoys a strong beverage at the Stardrop Saloon. Could a humble farmer such as yourself be the inspiration Elliott is looking for? There’s only one way to find out…', 'https://stardewvalleywiki.com/mediawiki/images/b/bd/Elliott.png'),
(3, 'Harvey', TRUE, 'Winter 14', 'Coffee', 'Pickles', 'Super Meal', 'Truffle Oil', 'Wine', NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'Harvey is the town doctor. He’s a little old for a bachelor, but he has a kind heart and a respected position in the community. He lives in a small apartment above the medical clinic, but spends most of his time working. You can sense a sadness about him, as if there’s something he’s not telling you…', 'https://stardewvalleywiki.com/mediawiki/images/9/95/Harvey.png'),
(4, 'Sam', TRUE, 'Summer 17', 'Cactus Fruit', 'Maple Bar', 'Pizza', 'Tigerseye', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'Sam is an outgoing, friendly guy who is brimming with youthful energy. He plays guitar and drums, and wants to start a band with Sebastian as soon as he has enough songs together. However, he does have a habit of starting ambitious projects and not finishing them. Sam is a little stressed about the impending return of his father, who has been away for years due to his line of work.', 'https://stardewvalleywiki.com/mediawiki/images/9/94/Sam.png'),
(5, 'Sebastian', TRUE, 'Winter 10', 'Frog Egg', 'Frozen Tear', 'Obsidian', 'Pumpkin Soup', 'Sashimi', 'Void Egg', NULL, NULL, NULL, NULL, NULL, NULL, 'Sebastian is a rebellious loner who lives in his parents’ basement. He is Maru’s older half-brother, and feels like his sister gets all the attention and adoration, while he is left to rot in the dark. He tends to get deeply absorbed in his work, computer games, comic books, sci-fi novels, and will sometimes spend great lengths of time pursuing these hobbies alone in his room. He can be a bit unfriendly to people he doesn’t know. Could a charming new farmer cultivate the wasteland of his heart? Who knows?', 'https://stardewvalleywiki.com/mediawiki/images/a/a8/Sebastian.png'),
(6, 'Shane', TRUE, 'Spring 20', 'Beer', 'Hot Pepper', 'Pepper Poppers', 'Pizza', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'I am renting my room from Marnie at a really good price. It is small but I cannot complain. If I could reset my life maybe I would start a chicken farm. Only free-range eggs of course.', 'https://stardewvalleywiki.com/mediawiki/images/8/8b/Shane.png'),
(7, 'Abigail', TRUE, 'Fall 13', 'Amethyst', 'Banana Pudding', 'Blackberry Cobbler', 'Chocolate Cake', 'Monster Compendium', 'Pufferfish', 'Pumpkin', 'Spicy Eel', NULL, NULL, NULL, NULL, 'Abigail lives at the general store with her parents. She sometimes fights with her mom, who worries about Abigails "alternative lifestyle". Her mom has the following to say: "I wish Abby would dress more appropriately and stop dyeing her hair blue. She has such a wonderful natural hair color, just like her grandmother did. Oh, and I wish she’d find some wholesome interests instead of this occult nonsense she is into." You might find Abigail alone in the graveyard, or maybe out in a rainstorm looking for frogs.', 'https://stardewvalleywiki.com/mediawiki/images/8/88/Abigail.png'),
(8, 'Emily', TRUE, 'Spring 27', 'Amethyst', 'Aquamarine', 'Cloth', 'Emerald', 'Jade', 'Parrot Egg', 'Ruby', 'Survival Burger', 'Topaz', 'Wool', NULL, NULL, 'I am just working at the tavern to make ends meet... but my real passion is tailoring. I made these clothes from scratch.', 'https://stardewvalleywiki.com/mediawiki/images/2/28/Emily.png'),
(9, 'Haley', TRUE, 'Spring 14', 'Coconut', 'Fruit Salad', 'Pink Cake', 'Sunflower', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'Being wealthy and popular throughout high school has made Haley a little conceited and self-centered. She has a tendency to judge people for superficial reasons. But is it too late for her to discover a deeper meaning to life? Is there a fun, open-minded young woman hidden within that candy-coated shell?', 'https://stardewvalleywiki.com/mediawiki/images/1/1b/Haley.png'),
(10, 'Leah', TRUE, 'Winter 23', 'Goat Cheese', 'Poppyseed Muffin', 'Salad', 'Stir Fry', 'Truffle', 'Vegetable Medley', 'Wine', NULL, NULL, NULL, NULL, NULL, 'Leah lives alone in a small cabin just outside of town. She loves to spend time outside, foraging for a wild meal or simply enjoying the gifts of the season. She is a talented artist with a large portfolio of work… yet she is too nervous to display it to the public. Maybe you can give her a little confidence boost?', 'https://stardewvalleywiki.com/mediawiki/images/e/e6/Leah.png'),
(11, 'Maru', TRUE, 'Summer 10', 'Battery Pack', 'Cauliflower', 'Cheese Cauliflower', 'Diamond', 'Dwarf Gadget', 'Gold Bar', 'Iridium Bar', 'Miners Treat', 'Pepper Poppers', 'Radioactive Bar', 'Rhubarb Pie', 'Starwberry', 'Growing up with a carpenter and a scientist for parents, Maru acquired a passion for creating gadgets at a young age. When she isn’t in her room, fiddling with tools and machinery, she sometimes does odd jobs at the local clinic. Friendly, outgoing, and ambitious, Maru would be quite a lucky match for a lowly newcomer such as yourself… Can you win her heart, or will she slip through your fingers and disappear from your life forever?', 'https://stardewvalleywiki.com/mediawiki/images/f/f8/Maru.png'),
(12, 'Penny', TRUE, 'Fall 2', 'All Books', 'Diamond', 'Emerald', 'Melon', 'Poppy', 'Poppyseed Muffin', 'Red Plate', 'Roots Platter', 'Sandfish', 'Tom Kha Soup', NULL, NULL, 'Penny lives with her mom, Pam, in a little trailer by the river. While Pam is out carousing at the saloon, Penny quietly tends to her chores in the dim, stuffy room she is forced to call home. She is shy and modest, without any grand ambitions for life other than settling in and starting a family. She likes to cook (although her skills are questionable) and read books from the local library.', 'https://stardewvalleywiki.com/mediawiki/images/a/ab/Penny.png'),
(13, 'Caroline', FALSE, 'Winter 7', 'Fish Taco', 'Green Tea', 'Summer Spangle', 'Tropical Curry', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'My husband runs the general store here. And have you met my daughter, Abigail? She is the pale one with the purple hair.', 'https://stardewvalleywiki.com/mediawiki/images/8/87/Caroline.png'),
(14, 'Clint', FALSE, 'Winter 16', 'Amethyst', 'Aquamarine', 'Artichoke Dip', 'Emerald', 'Fiddlehead Risotto', 'Gold Bar', 'Iridium Bar', 'Jade', 'Omni Geode', 'Ruby', 'Topaz', NULL, 'It is nicer to work outdoors than by a hot furnace all day. I am only a blacksmith because my father pushed me into it.', 'https://stardewvalleywiki.com/mediawiki/images/3/31/Clint.png'),
(15, 'Demetrius', FALSE, 'Summer 19', 'Bean Hotpot', 'Ice Cream', 'Rice Pudding', 'Strawberry', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'I am studying the local plants and animals from my home laboratory. Have you met my daughter Maru? She is interested to meet you.', 'https://stardewvalleywiki.com/mediawiki/images/f/f9/Demetrius.png'),
(16, 'Dwarf', FALSE, 'Summer 22', 'Amethyst', 'Aquamarine', 'Emerald', 'Jade', 'Lava Eel', 'Lemon Stone', 'Omni Geode', 'Ruby', 'Topaz', NULL, NULL, NULL, 'Long ago, my people knew the secrets of advanced technology. The archaeological evidence proves that. But I wonder where it came from? And where did it all go? I guess some questions will never be answered...', 'https://stardewvalleywiki.com/mediawiki/images/e/ed/Dwarf.png'),
(17, 'Evelyn', FALSE, 'Winter 23', 'Beet', 'Chocolate Cake', 'Diamond', 'Fairy Rose', 'Raisins', 'Stuffing', 'Tulip', NULL, NULL, NULL, NULL, NULL, 'Evelyn has lived in Pelican Town her entire life. Always hopeful and optimistic, "Granny" spends her days tending the town gardens, baking her signature cookies, and reminiscing about Stardew Valleys vibrant past.', 'https://stardewvalleywiki.com/mediawiki/images/8/8e/Evelyn.png'),
(18, 'George', FALSE, 'Fall 24', 'Fried Mushroom', 'Leek', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'My grand-pap was a farmer. It is a respectable profession. If I wasn not so darned old I would come to your farm and show you a thing or two...', 'https://stardewvalleywiki.com/mediawiki/images/7/78/George.png'),
(19, 'Gus', FALSE, 'Summer 8', 'Diamond', 'Escargot', 'Fish Taco', 'Orange', 'Tropical Curry', NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'Pam and Clint come into the saloon almost every night. I would probably go out of business if they stopped coming. So make sure you don not drive them away!', 'https://stardewvalleywiki.com/mediawiki/images/5/52/Gus.png'),
(20, 'Jas', FALSE, 'Summer 4', 'Ancient Doll', 'Fairy Box', 'Fairy Rose', 'Pink Cake', 'Plum Pudding', 'Strange Doll (green)', 'Strange Doll (yellow)', NULL, NULL, NULL, NULL, NULL, 'You can play with my dolls if you want to. Just make sure to brush their hair when you are done.', 'https://stardewvalleywiki.com/mediawiki/images/5/55/Jas.png'),
(21, 'Jodi', FALSE, 'Fall 11', 'Chocolate Cake', 'Crispy Bass', 'Diamond', 'Eggplant Parmesan', 'Fried Eel', 'Pancakes', 'Rhubarb Pie', 'Vegetable Medley', NULL, NULL, NULL, NULL, 'Maybe I have spoiled the boys a bit. They have never had to do any chores. I guess I want them to be able to enjoy their childhood while they still can.', 'https://stardewvalleywiki.com/mediawiki/images/4/41/Jodi.png'),
(22, 'Kent', FALSE, 'Spring 4', 'Fiddlehead Risotto', 'Roasted Hazelnuts', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'I don not know if I will ever get used to being back home. The peacefulness of the town feels like a mask. That is probably just me though.', 'https://stardewvalleywiki.com/mediawiki/images/9/99/Kent.png'),
(23, 'Krobus', FALSE, 'Winter 1', 'Diamond', 'Iridum Bar', 'Monster Compendium', 'Monster Musk', 'Pumpkin', 'Void Egg', 'Void Mayonnaise', 'Wild Horseradish', NULL, NULL, NULL, Null, '...Have you encountered others like me, in the mines? I am sorry if they were hostile towards you. You see, we have learned to fear humans... there have been too many... unpleasant encounters.', 'https://stardewvalleywiki.com/mediawiki/images/7/71/Krobus.png'),
(24, 'Leo', FALSE, 'Summer 26', 'Duck Feather', 'Mango', 'Ostrich Egg', 'Parrot Egg', 'Poi', NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'One time... I was at the top of the volcano at night, and I saw lights far, far away. I wonder if there are other bird families out there?', 'https://stardewvalleywiki.com/mediawiki/images/1/1d/Leo.png'),
(25, 'Lewis', FALSE, 'Spring 7', 'Autumns Bounty', 'Glazed Yams', 'Green Tea', 'Hot Pepper', 'Vegetable Medley', NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'I have been Mayor of Pelican Town for over twenty years! No one ever runs against me when it is time for an election. I like to think that it means I am doing my job well. I like being Mayor.', 'https://stardewvalleywiki.com/mediawiki/images/2/2b/Lewis.png'),
(26, 'Linus', FALSE, 'Winter 3', 'Blueberry Tart', 'Cactus Fruit', 'Coconut', 'Dish O The Sea', 'The Alleyway Buffet', 'Yam', NULL, NULL, NULL, NULL, NULL, NULL, 'You can learn to survive in the wild. I have. I think we all have a hidden urge to return to nature. It is just a little scary to make the leap.', 'https://stardewvalleywiki.com/mediawiki/images/3/31/Linus.png'),
(27, 'Marnie', FALSE, 'Fall 18', 'Diamond', 'Farmers Lunch', 'Pink Cake', 'Pumpkin Pie', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'Animals are so innocent, so sweet. And If I do not look after them, who will? I just hope my chickens are not too upset when I take their eggs.', 'https://stardewvalleywiki.com/mediawiki/images/5/52/Marnie.png'),
(28, 'Pam', FALSE, 'Spring 25', 'Beer', 'Cactus Fruit', 'Glazed Yams', 'Mead', 'Pale Ale', 'Parsnip', 'Parsnip Soup', 'Pina Colada', NULL, NULL, NULL, NULL, 'I was reading the newspaper this morning but then I got depressed. It is a rotten world, kid. Keep your head screwed on right and you will make it through in one piece... That is what my Pappy always used to say. Heh heh heh.', 'https://stardewvalleywiki.com/mediawiki/images/d/da/Pam.png'),
(29, 'Pierre', FALSE, 'Spring 26', 'Fried Calamari', 'Price Catalogue', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'If you are looking for seeds, my shop is the place to go. I will also buy produce from you for a good price! A little agriculture could really inject new life into the local economy!', 'https://stardewvalleywiki.com/mediawiki/images/7/7e/Pierre.png'),
(30, 'Robin', FALSE, 'Fall 21', 'Goat Cheese', 'Peach', 'Spaghetti', 'Woodys Secret', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'My parents were bewildered when I told them I wanted to be a carpenter. They were pretty old-fashioned.', 'https://stardewvalleywiki.com/mediawiki/images/1/1b/Robin.png'),
(31, 'Sandy', FALSE, 'Fall 15', 'Crocus', 'Daffodil', 'Mango Sticky Rice', 'Sweet Pea', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'My name is not really Sandy, you know. Its just good for business.', 'https://stardewvalleywiki.com/mediawiki/images/4/4e/Sandy.png'),
(32, 'Vincent', FALSE, 'Spring 10', 'Cranberry Candy', 'Frog Egg', 'Ginger Ale', 'Grape', 'Pink Cake', 'Snail', NULL, NULL, NULL, NULL, NULL, NULL, 'I wanna be just like my big brother when I grow up!', 'https://stardewvalleywiki.com/mediawiki/images/f/f1/Vincent.png'),
(33, 'Willy', FALSE, 'Spring 9', 'Catfish', 'Diamond', 'Gold Bar', 'Iridium Bar', 'Jewels of the Sea', 'Mead', 'Octopus', 'Pumpkin', 'Sea Cucumber', 'Sturgeon', 'The Art O Crabbing', NULL, 'A true angler has respect for the water... dont you forget that.', 'https://stardewvalleywiki.com/mediawiki/images/8/82/Willy.png'),
(34, 'Wizard', FALSE, 'Winter 17', 'Book of Mysteries', 'Purple Mushroom', 'Solar Essence', 'Super Cucumber', 'Void Essence', NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'Wizard studies the spirit world from his tower in cindersap forest. He is fluent in many elemental languages.', 'https://stardewvalleywiki.com/mediawiki/images/c/c7/Wizard.png');

-- Inserts for achievement_item table
INSERT INTO achievement_item (item_id, achievement_id)
VALUES
(0, 0),
(1, 8),
(2, 8),
(3, 8),
(4, 8),
(5, 8),
(6, 8),
(7, 8),
(8, 8),
(9, 8),
(10, 8),
(11, 8),
(12, 8),
(13, 8),
(14, 8),
(15, 8),
(16, 8),
(17, 1),
(18, 1),
(19, 1),
(20, 1),
(21, 1),
(22, 1),
(23, 1),
(24, 1),
(25, 1),
(26, 1),
(27, 1),
(28, 1),
(29, 1),
(30, 1),
(31, 1),
(32, 1),
(33, 10),
(34, 10),
(35, 10),
(36, 10),
(37, 10),
(38, 10),
(39, 10),
(40, 10),
(41, 10),
(42, 10),
(43, 10),
(44, 10),
(45, 10),
(46, 10),
(47, 10),
(48, 10),
(49, 7),
(50, 7),
(51, 7),
(52, 7),
(53, 7),
(54, 7),
(55, 7),
(56, 7),
(57, 7),
(58, 7),
(59, 7),
(60, 7),
(61, 9),
(62, 9),
(63, 9),
(64, 9),
(65, 9),
(66, 9),
(67, 9),
(68, 9),
(69, 9),
(70, 9),
(71, 9),
(72, 9),
(73, 9),
(74, 9),
(75, 9),
(76, 9),
(77, 1),
(78, 1),
(79, 1),
(80, 1),
(81, 1),
(82, 1),
(83, 1),
(84, 1),
(85, 1),
(86, 1),
(87, 1),
(88, 1),
(89, 1),
(90, 1),
(91, 1),
(92, 1),
(93, 1),
(94, 1),
(95, 1),
(96, 1),
(97, 1),
(98, 1),
(99, 1),
(100, 1),
(101, 1),
(102, 1),
(103, 1),
(104, 1),
(105, 1),
(106, 1),
(107, 1),
(108, 1),
(109, 1),
(110, 1),
(111, 1),
(112, 1),
(113, 1),
(114, 1),
(115, 1),
(116, 1),
(117, 1),
(118, 1),
(119, 1),
(120, 1),
(121, 1),
(122, 1),
(123, 7),
(124, 7),
(125, 7),
(126, 7),
(127, 7),
(128, 7),
(129, 7),
(130, 7),
(131, 7),
(132, 7),
(133, 7),
(134, 7),
(135, 7);
-- (136, 7);

-- Insert into item_classification table
INSERT INTO item_classification (item_id, classification_id)
VALUES
(0, 0),
(1, 4),
(2, 4),
(3, 4),
(4, 4),
(5, 4),
(6, 4),
(7, 4),
(8, 4),
(9, 4),
(10, 4),
(11, 4),
(12, 4),
(13, 4),
(14, 4),
(15, 4),
(16, 4),
(17, 8),
(18, 8),
(19, 8),
(20, 8),
(21, 8),
(22, 8),
(23, 8),
(24, 8),
(25, 8),
(26, 8),
(27, 8),
(28, 8),
(29, 8),
(30, 8),
(31, 8),
(32, 8),
(33, 7),
(34, 7),
(35, 7),
(36, 7),
(37, 7),
(38, 7),
(39, 7),
(40, 7),
(41, 7),
(42, 7),
(43, 7),
(44, 7),
(45, 7),
(46, 7),
(47, 7),
(48, 7),
(49, 2),
(50, 2),
(51, 2),
(52, 2),
(53, 2),
(54, 2),
(55, 2),
(56, 2),
(57, 2),
(58, 2),
(59, 2),
(60, 2),
(61, 5),
(62, 5),
(63, 5),
(64, 5),
(65, 5),
(66, 5),
(67, 5),
(68, 5),
(69, 5),
(70, 5),
(71, 5),
(72, 5),
(73, 5),
(74, 5),
(75, 5),
(76, 5),
(77, 1),
(78, 1),
(79, 1),
(80, 1),
(81, 1),
(82, 1),
(83, 1),
(84, 1),
(85, 1),
(86, 1),
(87, 1),
(88, 1),
(89, 1),
(90, 1),
(91, 1),
(92, 3),
(93, 3),
(94, 3),
(95, 3),
(96, 3),
(97, 3),
(98, 3),
(99, 3),
(100, 3),
(101, 3),
(102, 3),
(103, 3),
(104, 3),
(105, 3),
(106, 6),
(107, 6),
(108, 6),
(109, 6),
(110, 6),
(111, 6),
(112, 6),
(113, 6),
(114, 6),
(115, 6),
(116, 6),
(117, 6),
(118, 6),
(119, 6),
(120, 6),
(121, 6),
(122, 6),
(123, 9),
(124, 9),
(125, 9),
(126, 9),
(127, 9),
(128, 9),
(129, 9),
(130, 9),
(131, 9),
(132, 9),
(133, 9),
(134, 9),
(135, 9);
-- (136, 10)

COMMIT TRANSACTION;
