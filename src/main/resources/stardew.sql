--ROLLBACK;

START TRANSACTION;

-- Drop tables if they exist
DROP TABLE IF EXISTS item_classification, achievement_item, item, villager, skill, achievement, classification CASCADE;
DROP SEQUENCE IF EXISTS achievement_serial, item_serial, villager_serial, classification_serial, skill_serial;

-- Create serials
CREATE SEQUENCE achievement_serial;
CREATE SEQUENCE item_serial;
CREATE SEQUENCE villager_serial;
CREATE SEQUENCE classification_serial;
CREATE SEQUENCE skill_serial;


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
	CONSTRAINT pk_villager PRIMARY KEY (villager_id),
	CONSTRAINT fk_classification_villager FOREIGN KEY (classification_id) REFERENCES classification (classification_id)
);


-- -- Create Skill Table
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
('Produce & Forage Shipped', 'Ship one of every item in the Items Shipped (Farm & Forage) tab in the collections menu.', 155),
('Obelisks on Farm', 'Build Earth Obelisk, Water Obelisk, Desert Obelisk, and Island Obelisk on the farm.', 4),
('Golden Clock on Farm', 'Build Gold Clock on the farm.', 1),
('Monster Slayer Hero', 'Complete all monster eradication goals for the Adventurers Guild.', 12),
('Great Friends', 'Reach maximum hearts with every villager. This includes Kent, so it is not possible to complete this in year 1. 
 Note: The maximum hearts for datable villagers is 8, and for non-datable villagers, 10 is the max. 
 If the player has children, their friendship status does not affect the score.', 34),
('Farmer Level', 'Reach level 10 in every skill.', 25),
('Found All Stardrops', 'Find all Stardrops.', 7),
('Cooking Recipes Made', 'Cook every recipe.', 81),
('Crafting Recipes Made', 'Craft all items. In single-player, the Wedding Ring is not required.', 149),
('Fish Caught', 'Catch every fish in the Fish tab in the collections menu.', 72),
('Golden Walnuts Found', 'Find all Golden Walnuts on Ginger Island.', 130);

-- insert values for classification table
INSERT INTO classification (name)
VALUES 
('Animal'),
('Artifact'),
('Artisan'),
('Cooking'),
('Crafting'),
('Crop'),
('Fish'),
('Forageable'),
('Mineral'),
('Stardrop'),
('Villager'),
('Miscellaneous');

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
INSERT INTO item (classification_id, name, season, time, weather, location, description)
VALUES
(4, 'Algae Soup', NULL, NULL, NULL, NULL, 'It''s a little slimy.'),
(4, 'Artichoke Dip', NULL, NULL, NULL, NULL, 'It''s cool and refreshing.'),
(4, 'Autumn''s Bounty', NULL, NULL, NULL, NULL, 'A taste of the season.'),
(4, 'Baked Fish', NULL, NULL, NULL, NULL, 'Baked fish on a bed of herbs.'),
(4, 'Banana Pudding', NULL, NULL, NULL, NULL, 'A creamy dessert with a wonderful tropical flavor.'),
(4, 'Bean Hotpot', NULL, NULL, NULL, NULL, 'It sure is healthy.'),
(4, 'Blackberry Cobbler', NULL, NULL, NULL, NULL, 'There''s nothing quite like it.'),
(4, 'Blueberry Tart', NULL, NULL, NULL, NULL, 'It''s subtle and refreshing.'),
(4, 'Bread', NULL, NULL, NULL, NULL, 'A crusty baguette.'),
(4, 'Bruschetta', NULL, NULL, NULL, NULL, 'Roasted tomatoes on a crisp white bread.'),
(4, 'Carp Surprise', NULL, NULL, NULL, NULL, 'It''s bland and oily.'),
(4, 'Cheese Cauliflower', NULL, NULL, NULL, NULL, 'It smells great!'),
(4, 'Chocolate Cake', NULL, NULL, NULL, NULL, 'Rich and moist with a thick fudge icing.'),
(4, 'Chowder', NULL, NULL, NULL, NULL, 'A perfect way to warm yourself after a cold night at sea.'),
(4, 'Coleslaw', NULL, NULL, NULL, NULL, 'It''s light, fresh and very healthy.'),
(4, 'Complete Breakfast', NULL, NULL, NULL, NULL, 'You''ll feel ready to take on the world!'),
(8, 'Blackberry', 'Fall', NULL, 'ANY', 'SDV', 'An early-fall treat.'),
(8, 'Cactus Fruit', 'Any', NULL, 'ANY', 'Oasis', 'The sweet fruit of the prickly pear cactus.'),
(8, 'Cave Carrot', 'Any', NULL, 'ANY', 'Mines', 'A starchy snack found in caves. It helps miners work longer.'),
(8, 'Chanterelle', 'Fall', NULL, 'ANY', 'Secret Woods', 'A tasty mushroom with a fruity smell and slightly peppery flavor.'),
(8, 'Coconut', 'Any', NULL, 'ANY', 'Oasis', 'A seed of the coconut palm. It has many culinary uses.'),
(8, 'Common Mushroom', 'Spring', NULL, 'ANY', 'Secret Woods', 'Slightly nutty, with good texture.'),
(8, 'Coral', 'Any', NULL, 'ANY', 'Beach', 'A colony of tiny creatures that clump together to form beautiful structures.'),
(8, 'Crocus', 'Winter', NULL, 'ANY', 'SDV', 'A flower that can bloom in the winter.'),
(8, 'Crystal Fruit', 'Winter', NULL, 'ANY', 'SDV', 'A delicate fruit that pops up from the snow.'),
(8, 'Daffodil', 'Spring', NULL, 'ANY', 'SDV', 'A traditional spring flower that makes a nice gift.'),
(8, 'Dandelion', 'Spring', NULL, 'ANY', 'SDV', 'Not the prettiest flower, but the leaves make a good salad.'),
(8, 'Fiddlehead Fern', 'Summer', NULL, 'ANY', 'Secret Woods', 'The young shoots are an edible specialty.'),
(8, 'Ginger', 'Any', NULL, 'ANY', 'Ginger Island', 'This sharp, spicy root is said to increase vitality.'),
(8, 'Grape', 'Summer', NULL, 'ANY', 'SDV', 'A sweet cluster of fruit.'),
(8, 'Hazelnut', 'Fall', NULL, 'ANY', 'SDV', 'That''s one big hazelnut!'),
(8, 'Holly', 'Winter', NULL, 'ANY', 'SDV', 'The leaves and bright red berries make a popular winter decoration.'),
(7, 'Albacore', 'Fall, Winter', '6am – 11am, 6pm – 2am', 'Any', 'Ocean', 'Prefers temperature "edges" where cool and warm water meet.'),
(7, 'Anchovy', 'Spring, Fall', 'Anytime', 'Any', 'Ocean', 'A small silver fish found in the ocean.'),
(7, 'Angler', 'Fall', 'Any', 'Any', 'North of JojaMart on the wooden plank bridge. Requires level 3 fishing.', 'Uses a bioluminescent dangler to attract prey.'),
(7, 'Blobfish', 'Winter 15-17', '5pm-2am', 'Any', 'Night Market', 'This odd creature floats above the ocean floor, consuming any edible material in its path.'),
(7, 'Blue Discus', 'All Seasons', 'Anytime', 'Any', 'Ginger Island Pond and Rivers', 'A brightly colored tropical fish that is popular in aquariums.'),
(7, 'Bream', 'All Seasons', '6pm – 2am', 'Any', 'River', 'A fairly common river fish that becomes active at night.'),
(7, 'Bullhead', 'All Seasons', 'Anytime', 'Any', 'Mountain Lake', 'A relative of the catfish that eats a variety of foods off the lake bottom.'),
(7, 'Carp', 'All Seasons', 'Anytime', 'Any', 'Mountain Lake', 'A common pond fish.'),
(7, 'Catfish', 'Spring, Fall', '6am – 12am', 'Rain', 'River', 'An uncommon fish found in streams.'),
(7, 'Cave Jelly', 'All Seasons', 'Anytime', 'Any', 'Mines', 'A rare jelly found in underground lakes.'),
(7, 'Chub', 'All Seasons', 'Anytime', 'Any', 'Forest River', 'A common freshwater fish known for its voracious appetite.'),
(7, 'Clam', 'All Seasons', 'Any', 'Any', 'Ocean (Crabpot)', 'There''s a chewy little guy in there...'),
(7, 'Cockle', 'All Seasons', 'Any', 'Any', 'Ocean (Crabpot)', 'A common saltwater clam.'),
(7, 'Crab', 'All Seasons', 'Any', 'Any', 'Ocean (Crabpot)', 'A marine crustacean with two powerful pincers.'),
(7, 'Crayfish', 'All Seasons', 'Any', 'Any', 'Freshwater (Crabpot)', 'A small freshwater relative of the lobster.'),
(7, 'Crimsonfish', 'Summer', 'Any', 'Any', 'East Pier Beach Fishing Lvl 5', 'Lives deep in the ocean but likes to lay its eggs in the warm summer water.'),
(2, 'Amphibian Fossil', NULL, NULL, NULL, 'Forest (0.6%), Mountain (0.6%), Fishing Chest (3.1-3.4%[2]), Bone Nodes (0.8%)', 
 	'The relatively short hind legs suggest some kind of primordial toad.'),
(2, 'Anchor', NULL, NULL, NULL, 'Beach (4%), Fishing Chest (0.8-0.9%[2]), Artifact Trove (3.6%)', 'It may have belonged to ancient pirates.'),
(2, 'Ancient Doll', NULL, NULL, NULL, 'Mountain (3%), Forest (2.4%), Bus Stop (2.4%), Town (0.8%), Fishing Chest (0.8-0.9%[2]), Feast of the Winter Star, 
 	Artifact Trove (3.6%)', 'An ancient doll covered in grime. This doll may have been used as a toy, a decoration, or a prop in some kind of ritual.'),
(2, 'Ancient Drum', NULL, NULL, NULL, 'Bus Stop (0.7%), Forest (0.7%), Town (0.4%), Frozen Geode (3%), Omni Geode (1%), Artifact Trove (3.6%)', 
 	'It''s a drum made from wood and animal skin. It has a low, reverberating tone.'),
(2, 'Ancient Seed', NULL, NULL, NULL, 'Forest (0.7%), Mountain (0.7%), Fishing Chest (0.8-0.9%[2]), Monster drop (Bug, Cave Fly, Grub, 
 	Mutant Fly, Mutant Grub: 0.5%), Artifact Trove (3.6%)', 'It''s a dry old seed from some ancient plant. By all appearances it''s long since dead...'),
(2, 'Ancient Sword', NULL, NULL, NULL, 'Forest (0.7%), Mountain (0.6%), Fishing Chest (0.8-0.9%[2]), Artifact Trove (3.6%)', 
 	'It''s the remains of an ancient sword. Most of the blade has turned to rust, but the hilt is very finely crafted.'),
(2, 'Arrowhead', NULL, NULL, NULL, 'Mountain (1.6%), Forest (1.6%), Bus Stop (1.6%), Artifact Trove (3.6%)', 'A crudely fashioned point used for hunting.'),
(2, 'Bone Flute', NULL, NULL, NULL, 'Forest (0.7%), Mountain (0.7%), Town (0.4%), Fishing Chest (0.8-0.9%[2]), Artifact Trove (3.6%)', 
 	'It''s a prehistoric wind instrument carved from an animal''s bone. It produces an eerie tone.'),
(2, 'Chewing Stick', NULL, NULL, NULL, 'Mountain (1.5%), Forest (1.5%), Town (0.8%), Fishing Chest (0.8-0.9%[2]), Duggy drop (2%), 
	 Artifact Trove (3.6%)', 'Ancient people chewed on these to keep their teeth clean.'),
(2, 'Chicken Statue', NULL, NULL, NULL, 'Farm (9%), Fishing Chest (0.8-0.9%[2]), Artifact Trove (3.6%)', 'It''s a statue of a chicken on a bronze base. 
 The ancient people of this area must have been very fond of chickens.'),
(2, 'Chipped Amphora', NULL, NULL, NULL, 'Town (3%), Artifact Trove (3.6%)', 'An ancient vessel made of ceramic material. Used to transport both dry and wet goods.'),
(2, 'Dinosaur Egg', NULL, NULL, NULL, 'Mountain (0.6%), Fishing Chest (0.8-0.9%[2]), Foraged on prehistoric floors of the Skull Cavern, 
 Pepper Rex drop (10%)', 'A giant dino egg... The entire shell is still intact!'),
(5, 'Ancient Seeds', NULL, NULL, NULL, NULL, 'Could these still grow?'),
(5, 'Anvil', NULL, NULL, NULL, NULL, 'Allows you to re-forge trinkets, randomizing their stats. Costs 3 iridium bars per use.'),
(5, 'Bait', NULL, NULL, NULL, NULL, 'Causes fish to bite faster. Must first be attached to a fishing rod.'),
(5, 'Bait Maker', NULL, NULL, NULL, NULL, 'Place a fish inside to create targeted bait.'),
(5, 'Barbed Hook', NULL, NULL, NULL, NULL, 'Makes your catch more secure, causing the "fishing bar" to cling to your catch. Works best on slow, weak fish.'),
(5, 'Barrel Brazier', NULL, NULL, NULL, NULL, 'Provides a moderate amount of light.'),
(5, 'Basic Fertilizer', NULL, NULL, NULL, NULL, 'Improves soil quality a little, increasing your chance to grow quality crops. Mix into tilled soil.'),
(5, 'Basic Retaining Soil', NULL, NULL, NULL, NULL, 'This soil has a chance of staying watered overnight. Mix into tilled soil.'),
(5, 'Bee House', NULL, NULL, NULL, NULL, 'Place outside and wait for delicious honey! (Except in Winter).'),
(5, 'Big Chest', NULL, NULL, NULL, NULL, 'It can store almost twice as much as a regular chest.'),
(5, 'Big Stone Chest', NULL, NULL, NULL, NULL, 'It can store almost twice as much as a regular chest.'),
(5, 'Blue Grass Starter', NULL, NULL, NULL, NULL, 'Place this on your farm to plant a clump of blue grass.'),
(5, 'Bomb', NULL, NULL, NULL, NULL, 'Generates an explosion. Watch out!'),
(5, 'Bone Mill', NULL, NULL, NULL, NULL, 'Turns bone items into fertilizers.'),
(5, 'Brick Floor', NULL, NULL, NULL, NULL, 'Place on the ground to create paths or to decorate your floors.'),
(5, 'Bug Steak', NULL, NULL, NULL, NULL, 'The last resort of the hungry cave diver.'),
(1, 'Duck Egg', NULL, NULL, NULL, NULL, 'It''s still warm.'),
(1, 'Duck Feather', NULL, NULL, NULL, NULL, 'It''s so colorful.'),
(1, 'White Egg', NULL, NULL, NULL, NULL, 'A regular white chicken egg.'),
(1, 'Large White Egg', NULL, NULL, NULL, NULL, 'It''s an uncommonly large white egg!'),
(1, 'Brown Egg', NULL, NULL, NULL, NULL, 'A regular brown chicken egg.'),
(1, 'Large Brown Egg', NULL, NULL, NULL, NULL, 'It''s an uncommonly large brown egg!'),
(1, 'Goat Milk', NULL, NULL, NULL, NULL, 'The milk of a goat.'),
(1, 'Large Goat Milk', NULL, NULL, NULL, NULL, 'A gallon of creamy goat''s milk.'),
(1, 'Golden Egg', NULL, NULL, NULL, NULL, 'A very rare and special egg with a solid gold shell.'),
(1, 'Milk', NULL, NULL, NULL, NULL, 'A jug of cow''s milk.'),
(1, 'Large Milk', NULL, NULL, NULL, NULL, 'A large jug of cows milk.'),
(1, 'Ostrich Egg', NULL, NULL, NULL, NULL, 'It may be the world''s largest egg.'),
(1, 'Rabbit''s Foot', NULL, NULL, NULL, NULL, 'Some say it''s lucky.'),
(1, 'Roe', NULL, NULL, NULL, NULL, 'Fresh fish eggs. These can be aged in a preserves jar to bring out more flavor.'),
(3, 'Wine', NULL, NULL, NULL, NULL, 'Drink in moderation.'),
(3, 'Pale Ale', NULL, NULL, NULL, NULL, 'Drink in moderation.'),
(3, 'Beer', NULL, NULL, NULL, NULL, 'Drink in moderation.'),
(3, 'Mead', NULL, NULL, NULL, NULL, 'A fermented beverage made from honey. Drink in moderation.'),
(3, 'Cheese', NULL, NULL, NULL, NULL, 'It''s your basic cheese.'),
(3, 'Goat Cheese', NULL, NULL, NULL, NULL, 'Soft cheese made from goat''s milk.'),
(3, 'Juice', NULL, NULL, NULL, NULL, 'A sweet, nutritious beverage.'),
(3, 'Cloth', NULL, NULL, NULL, NULL, 'A bolt of fine wool cloth.'),
(3, 'Mayonnaise', NULL, NULL, NULL, NULL, 'It looks spreadable.'),
(3, 'Duck Mayonnaise', NULL, NULL, NULL, NULL, 'It''s a rich, yellow mayonnaise.'),
(3, 'Void Mayonnaise', NULL, NULL, NULL, NULL, 'A thick, black paste that smells like burnt hair.'),
(3, 'Dinosaur Mayonnaise', NULL, NULL, NULL, NULL, 'It''s thick and creamy, with a vivid green hue. It smells like grass and leather.'),
(3, 'Truffle Oil', NULL, NULL, NULL, NULL, 'A gourmet cooking ingredient.'),
(3, 'Pickles', NULL, NULL, NULL, NULL, 'A jar of your home-made pickles.'),
(6, 'Blue Jazz', 'Spring', NULL, NULL, NULL, 'The flower grows in a sphere to invite as many butterflies as possible.'),
(6, 'Carrot', 'Spring', NULL, NULL, NULL, 'A fast-growing, colorful tuber that makes for a great snack.'),
(6, 'Cauliflower', 'Spring', NULL, NULL, NULL, 'Valuable, but slow-growing. Despite its pale color, the florets are packed with nutrients.'),
(6, 'Coffee Bean', 'Spring / Summer', NULL, NULL, NULL, 'Plant in spring or summer to grow a coffee plant. Place five beans in a keg to make coffee.'),
(6, 'Garlic', 'Spring', NULL, NULL, NULL, 'Adds a wonderful zestiness to dishes. High quality garlic can be pretty spicy.'),
(6, 'Green Bean', 'Spring', NULL, NULL, NULL, 'A juicy little bean with a cool, crisp snap.'),
(6, 'Kale', 'Spring', NULL, NULL, NULL, 'The waxy leaves are great in soups and stir fries.'),
(6, 'Hops', 'Summer', NULL, NULL, NULL, 'A bitter, tangy flower used to flavor beer.'),
(6, 'Hot Pepper', 'Summer', NULL, NULL, NULL, 'Fiery hot with a hint of sweetness.'),
(6, 'Melon', 'Summer', NULL, NULL, NULL, 'A cool, sweet summer treat.'),
(6, 'Poppy', 'Summer', NULL, NULL, NULL, 'In addition to its colorful flower, the Poppy has culinary and medicinal uses.'),
(6, 'Radish', 'Summer', NULL, NULL, NULL, 'A crisp and refreshing root vegetable with hints of pepper when eaten raw.'),
(6, 'Beet', 'Fall', NULL, NULL, NULL, 'A sweet and earthy root vegetable. As a bonus, the leaves make a great salad.'),
(6, 'Bok Choy', 'Fall', NULL, NULL, NULL, 'The leafy greens and fibrous stalks are healthy and delicious.'),
(6, 'Broccoli', 'Fall', NULL, NULL, NULL, 'The flowering head of a broccoli plant. The tiny buds give it a unique texture.'),
(6, 'Cranberries', 'Fall', NULL, NULL, NULL, 'These tart red berries are a traditional winter food.'),
(6, 'Eggplant', 'Fall', NULL, NULL, NULL, 'A rich and wholesome relative of the tomato. Delicious fried or stewed.'),
(9, 'Aerinite', NULL, NULL, NULL, 'Frozen Geode, Omni Geode', 'These crystals are curiously light.'),
(9, 'Alamite', NULL, NULL, NULL, 'Geode, Omni Geode', 'Its distinctive fluorescence makes it a favorite among rock collectors.'),
(9, 'Baryte', NULL, NULL, NULL, 'Magma Geode, Omni Geode', 'The best specimens resemble a desert rose.'),
(9, 'Basalt', NULL, NULL, NULL, 'Magma Geode, Omni Geode', 'Forms near searing hot magma.'),
(9, 'Bixite', NULL, NULL, NULL, 'Magma Geode, Omni Geode, Black Slime', 'A dark metallic Mineral sought after for its cubic structure.'),
(9, 'Calcite', NULL, NULL, NULL, 'Geode, Omni Geode', 'This yellow crystal is speckled with shimmering nodules.'),
(9, 'Celestine', NULL, NULL, NULL, 'Geode, Omni Geode', 'Some early life forms had bones made from this.'),
(9, 'Dolomite', NULL, NULL, NULL, 'Magma Geode, Omni Geode', 'It can occur in coral reefs, often near an underwater volcano.'),
(9, 'Esperite', NULL, NULL, NULL, 'Frozen Geode, Omni Geode', 'The crystals glow bright green when stimulated.'),
(9, 'Fairy Stone', NULL, NULL, NULL, 'Frozen Geode, Omni Geode', 'An old miner''s song suggests these are made from the bones of ancient fairies.'),
(9, 'Fire Opal', NULL, NULL, NULL, 'Madma Geode, Omni Geode', 'A rare variety of opal, named for its red spots.'),
(9, 'Fluorapatite', NULL, NULL, NULL, 'Frozen Geode, Omni Geode', 'Small amounts are found in human teeth.'),
(9, 'Geminite', NULL, NULL, NULL, 'Frozen Geode, Omni Geode', 'Occurs in brilliant clusters.'),
(10, 'Stardrop', NULL, NULL, NULL, NULL, 'A mysterious fruit that empowers those who eat it. The flavor is like a dream... a 
 powerful personal experience, yet difficult to describe to others.');

-- insert values for villager
INSERT INTO villager (name, marriage_candidate, birthday, loved1, loved2, loved3, loved4, loved5, loved6, loved7, loved8, loved9, loved10, loved11, loved12, description)
VALUES 
('Alex', TRUE, 'Summer 13', 'Complete Breakfast', 'Jack Be Nimble, Jack Be Thick', 'Salmon Dinner', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'Alex loves sports and hanging out at the beach. He is quite arrogant and brags to everyone that he is going to be a professional athlete. Is his cockiness just a facade to mask his crushing self-doubt? Is he using his sports dream to fill the void left by the disappearance of his parents? Or is he just a brazen youth trying to "look cool"?'),
('Elliott', TRUE, 'Fall 5', 'Crab Cakes', 'Duck Feather', 'Lobster', 'Pomegranate', 'Squid Ink', 'Tom Kha Soup', NULL, NULL, NULL, NULL, NULL, NULL, 'Elliott lives alone in a cabin on the beach. He is a writer who dreams of one day writing a magnificent novel. He is a sentimental “romantic” with a tendency to go off onto flowery, poetic tangents. When he can afford it, he enjoys a strong beverage at the Stardrop Saloon. Could a humble farmer such as yourself be the inspiration Elliott is looking for? There’s only one way to find out…'),
('Harvey', TRUE, 'Winter 14', 'Coffee', 'Pickles', 'Super Meal', 'Truffle Oil', 'Wine', NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'Harvey is the town doctor. He’s a little old for a bachelor, but he has a kind heart and a respected position in the community. He lives in a small apartment above the medical clinic, but spends most of his time working. You can sense a sadness about him, as if there’s something he’s not telling you…'),
('Sam', TRUE, 'Summer 17', 'Cactus Fruit', 'Maple Bar', 'Pizza', 'Tigerseye', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'Sam is an outgoing, friendly guy who is brimming with youthful energy. He plays guitar and drums, and wants to start a band with Sebastian as soon as he has enough songs together. However, he does have a habit of starting ambitious projects and not finishing them. Sam is a little stressed about the impending return of his father, who has been away for years due to his line of work.'),
('Sebastian', TRUE, 'Winter 10', 'Frog Egg', 'Frozen Tear', 'Obsidian', 'Pumpkin Soup', 'Sashimi', 'Void Egg', NULL, NULL, NULL, NULL, NULL, NULL, 'Sebastian is a rebellious loner who lives in his parents’ basement. He is Maru’s older half-brother, and feels like his sister gets all the attention and adoration, while he is left to rot in the dark. He tends to get deeply absorbed in his work, computer games, comic books, sci-fi novels, and will sometimes spend great lengths of time pursuing these hobbies alone in his room. He can be a bit unfriendly to people he doesn’t know. Could a charming new farmer cultivate the wasteland of his heart? Who knows?'),
('Shane', TRUE, 'Spring 20', 'Beer', 'Hot Pepper', 'Pepper Poppers', 'Pizza', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'I am renting my room from Marnie at a really good price. It is small but I cannot complain. If I could reset my life maybe I would start a chicken farm. Only free-range eggs of course.'),
('Abigail', TRUE, 'Fall 13', 'Amethyst', 'Banana Pudding', 'Blackberry Cobbler', 'Chocolate Cake', 'Monster Compendium', 'Pufferfish', 'Pumpkin', 'Spicy Eel', NULL, NULL, NULL, NULL, 'Abigail lives at the general store with her parents. She sometimes fights with her mom, who worries about Abigails "alternative lifestyle". Her mom has the following to say: "I wish Abby would dress more appropriately and stop dyeing her hair blue. She has such a wonderful natural hair color, just like her grandmother did. Oh, and I wish she’d find some wholesome interests instead of this occult nonsense she is into." You might find Abigail alone in the graveyard, or maybe out in a rainstorm looking for frogs.'),
('Emily', TRUE, 'Spring 27', 'Amethyst', 'Aquamarine', 'Cloth', 'Emerald', 'Jade', 'Parrot Egg', 'Ruby', 'Survival Burger', 'Topaz', 'Wool', NULL, NULL, 'I am just working at the tavern to make ends meet... but my real passion is tailoring. I made these clothes from scratch.'),
('Haley', TRUE, 'Spring 14', 'Coconut', 'Fruit Salad', 'Pink Cake', 'Sunflower', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'Being wealthy and popular throughout high school has made Haley a little conceited and self-centered. She has a tendency to judge people for superficial reasons. But is it too late for her to discover a deeper meaning to life? Is there a fun, open-minded young woman hidden within that candy-coated shell?'),
('Leah', TRUE, 'Winter 23', 'Goat Cheese', 'Poppyseed Muffin', 'Salad', 'Stir Fry', 'Truffle', 'Vegetable Medley', 'Wine', NULL, NULL, NULL, NULL, NULL, 'Leah lives alone in a small cabin just outside of town. She loves to spend time outside, foraging for a wild meal or simply enjoying the gifts of the season. She is a talented artist with a large portfolio of work… yet she is too nervous to display it to the public. Maybe you can give her a little confidence boost?'),
('Maru', TRUE, 'Summer 10', 'Battery Pack', 'Cauliflower', 'Cheese Cauliflower', 'Diamond', 'Dwarf Gadget', 'Gold Bar', 'Iridium Bar', 'Miners Treat', 'Pepper Poppers', 'Radioactive Bar', 'Rhubarb Pie', 'Starwberry', 'Growing up with a carpenter and a scientist for parents, Maru acquired a passion for creating gadgets at a young age. When she isn’t in her room, fiddling with tools and machinery, she sometimes does odd jobs at the local clinic. Friendly, outgoing, and ambitious, Maru would be quite a lucky match for a lowly newcomer such as yourself… Can you win her heart, or will she slip through your fingers and disappear from your life forever?'),
('Penny', TRUE, 'Fall 2', 'All Books', 'Diamond', 'Emerald', 'Melon', 'Poppy', 'Poppyseed Muffin', 'Red Plate', 'Roots Platter', 'Sandfish', 'Tom Kha Soup', NULL, NULL, 'Penny lives with her mom, Pam, in a little trailer by the river. While Pam is out carousing at the saloon, Penny quietly tends to her chores in the dim, stuffy room she is forced to call home. She is shy and modest, without any grand ambitions for life other than settling in and starting a family. She likes to cook (although her skills are questionable) and read books from the local library.'),
('Caroline', FALSE, 'Winter 7', 'Fish Taco', 'Green Tea', 'Summer Spangle', 'Tropical Curry', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'My husband runs the general store here. And have you met my daughter, Abigail? She is the pale one with the purple hair.'),
('Clint', FALSE, 'Winter 16', 'Amethyst', 'Aquamarine', 'Artichoke Dip', 'Emerald', 'Fiddlehead Risotto', 'Gold Bar', 'Iridium Bar', 'Jade', 'Omni Geode', 'Ruby', 'Topaz', NULL, 'It is nicer to work outdoors than by a hot furnace all day. I am only a blacksmith because my father pushed me into it.'),
('Demetrius', FALSE, 'Summer 19', 'Bean Hotpot', 'Ice Cream', 'Rice Pudding', 'Strawberry', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'I am studying the local plants and animals from my home laboratory. Have you met my daughter Maru? She is interested to meet you.'),
('Dwarf', FALSE, 'Summer 22', 'Amethyst', 'Aquamarine', 'Emerald', 'Jade', 'Lava Eel', 'Lemon Stone', 'Omni Geode', 'Ruby', 'Topaz', NULL, NULL, NULL, 'Long ago, my people knew the secrets of advanced technology. The archaeological evidence proves that. But I wonder where it came from? And where did it all go? I guess some questions will never be answered...'),
('Evelyn', FALSE, 'Winter 23', 'Beet', 'Chocolate Cake', 'Diamond', 'Fairy Rose', 'Raisins', 'Stuffing', 'Tulip', NULL, NULL, NULL, NULL, NULL, 'Evelyn has lived in Pelican Town her entire life. Always hopeful and optimistic, "Granny" spends her days tending the town gardens, baking her signature cookies, and reminiscing about Stardew Valleys vibrant past.'),
('George', FALSE, 'Fall 24', 'Fried Mushroom', 'Leek', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'My grand-pap was a farmer. It is a respectable profession. If I wasn not so darned old I would come to your farm and show you a thing or two...'),
('Gus', FALSE, 'Summer 8', 'Diamond', 'Escargot', 'Fish Taco', 'Orange', 'Tropical Curry', NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'Pam and Clint come into the saloon almost every night. I would probably go out of business if they stopped coming. So make sure you don not drive them away!'),
('Jas', FALSE, 'Summer 4', 'Ancient Doll', 'Fairy Box', 'Fairy Rose', 'Pink Cake', 'Plum Pudding', 'Strange Doll (green)', 'Strange Doll (yellow)', NULL, NULL, NULL, NULL, NULL, 'You can play with my dolls if you want to. Just make sure to brush their hair when you are done.'),
('Jodi', FALSE, 'Fall 11', 'Chocolate Cake', 'Crispy Bass', 'Diamond', 'Eggplant Parmesan', 'Fried Eel', 'Pancakes', 'Rhubarb Pie', 'Vegetable Medley', NULL, NULL, NULL, NULL, 'Maybe I have spoiled the boys a bit. They have never had to do any chores. I guess I want them to be able to enjoy their childhood while they still can.'),
('Kent', FALSE, 'Spring 4', 'Fiddlehead Risotto', 'Roasted Hazelnuts', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'I don not know if I will ever get used to being back home. The peacefulness of the town feels like a mask. That is probably just me though.'),
('Krobus', FALSE, 'Winter 1', 'Diamond', 'Iridum Bar', 'Monster Compendium', 'Monster Musk', 'Pumpkin', 'Void Egg', 'Void Mayonnaise', 'Wild Horseradish', NULL, NULL, NULL, Null, '...Have you encountered others like me, in the mines? I am sorry if they were hostile towards you. You see, we have learned to fear humans... there have been too many... unpleasant encounters.'),
('Leo', FALSE, 'Summer 26', 'Duck Feather', 'Mango', 'Ostrich Egg', 'Parrot Egg', 'Poi', NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'One time... I was at the top of the volcano at night, and I saw lights far, far away. I wonder if there are other bird families out there?'),
('Lewis', FALSE, 'Spring 7', 'Autumns Bounty', 'Glazed Yams', 'Green Tea', 'Hot Pepper', 'Vegetable Medley', NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'I have been Mayor of Pelican Town for over twenty years! No one ever runs against me when it is time for an election. I like to think that it means I am doing my job well. I like being Mayor.'),
('Linus', FALSE, 'Winter 3', 'Blueberry Tart', 'Cactus Fruit', 'Coconut', 'Dish O The Sea', 'The Alleyway Buffet', 'Yam', NULL, NULL, NULL, NULL, NULL, NULL, 'You can learn to survive in the wild. I have. I think we all have a hidden urge to return to nature. It is just a little scary to make the leap.'),
('Marnie', FALSE, 'Fall 18', 'Diamond', 'Farmers Lunch', 'Pink Cake', 'Pumpkin Pie', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'Animals are so innocent, so sweet. And If I do not look after them, who will? I just hope my chickens are not too upset when I take their eggs.'),
('Pam', FALSE, 'Spring 25', 'Beer', 'Cactus Fruit', 'Glazed Yams', 'Mead', 'Pale Ale', 'Parsnip', 'Parsnip Soup', 'Pina Colada', NULL, NULL, NULL, NULL, 'I was reading the newspaper this morning but then I got depressed. It is a rotten world, kid. Keep your head screwed on right and you will make it through in one piece... That is what my Pappy always used to say. Heh heh heh.'),
('Pierre', FALSE, 'Spring 26', 'Fried Calamari', 'Price Catalogue', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'If you are looking for seeds, my shop is the place to go. I will also buy produce from you for a good price! A little agriculture could really inject new life into the local economy!'),
('Robin', FALSE, 'Fall 21', 'Goat Cheese', 'Peach', 'Spaghetti', 'Woodys Secret', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'My parents were bewildered when I told them I wanted to be a carpenter. They were pretty old-fashioned.'),
('Sandy', FALSE, 'Fall 15', 'Crocus', 'Daffodil', 'Mango Sticky Rice', 'Sweet Pea', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'My name is not really Sandy, you know. Its just good for business.'),
('Vincent', FALSE, 'Spring 10', 'Cranberry Candy', 'Frog Egg', 'Ginger Ale', 'Grape', 'Pink Cake', 'Snail', NULL, NULL, NULL, NULL, NULL, NULL, 'I wanna be just like my big brother when I grow up!'),
('Willy', FALSE, 'Spring 9', 'Catfish', 'Diamond', 'Gold Bar', 'Iridium Bar', 'Jewels of the Sea', 'Mead', 'Octopus', 'Pumpkin', 'Sea Cucumber', 'Sturgeon', 'The Art O Crabbing', NULL, 'A true angler has respect for the water... dont you forget that.'),
('Wizard', FALSE, 'Winter 17', 'Book of Mysteries', 'Purple Mushroom', 'Solar Essence', 'Super Cucumber', 'Void Essence', NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'Wizard studies the spirit world from his tower in cindersap forest. He is fluent in many elemental languages.');

-- Inserts for achievement_item table
INSERT INTO achievement_item (item_id, achievement_id)
VALUES
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

-- THE COMMIT
COMMIT;







