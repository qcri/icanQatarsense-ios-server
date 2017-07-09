/**
Seed Data Loader Script for QSense Application
**/

START TRANSACTION;

USE qsense_db;

/**
Inserting seed data for observation_unit in the application
**/
INSERT INTO observation_unit (id,name,display_name,description) VALUES (1,'BAR','bar',NULL);
INSERT INTO observation_unit (id,name,display_name,description) VALUES (2,'BOTTLE','bottle',NULL);
INSERT INTO observation_unit (id,name,display_name,description) VALUES (3,'BOX','box',NULL);
INSERT INTO observation_unit (id,name,display_name,description) VALUES (4,'CAN','can',NULL);
INSERT INTO observation_unit (id,name,display_name,description) VALUES (5,'CONTAINER','container',NULL);
INSERT INTO observation_unit (id,name,display_name,description) VALUES (6,'CUBE','cube',NULL);
INSERT INTO observation_unit (id,name,display_name,description) VALUES (7,'CUP','cup',NULL);
INSERT INTO observation_unit (id,name,display_name,description) VALUES (8,'FL_OZ','fl oz',NULL);
INSERT INTO observation_unit (id,name,display_name,description) VALUES (9,'GRAM','gram',NULL);
INSERT INTO observation_unit (id,name,display_name,description) VALUES (10,'HOUR','hr',NULL);
INSERT INTO observation_unit (id,name,display_name,description) VALUES (11,'JAR','jar',NULL);
INSERT INTO observation_unit (id,name,display_name,description) VALUES (12,'KG','kg',NULL);
INSERT INTO observation_unit (id,name,display_name,description) VALUES (13,'LARGE','large',NULL);
INSERT INTO observation_unit (id,name,display_name,description) VALUES (14,'LB','lb',NULL);
INSERT INTO observation_unit (id,name,display_name,description) VALUES (15,'LITER','liter',NULL);
INSERT INTO observation_unit (id,name,display_name,description) VALUES (16,'MEDIUM','medium',NULL);
INSERT INTO observation_unit (id,name,display_name,description) VALUES (17,'MILLIGRAM','milligram',NULL);
INSERT INTO observation_unit (id,name,display_name,description) VALUES (18,'MILLISECOND','msec',NULL);
INSERT INTO observation_unit (id,name,display_name,description) VALUES (19,'MINUTE','min',NULL);
INSERT INTO observation_unit (id,name,display_name,description) VALUES (20,'ML','ml',NULL);
INSERT INTO observation_unit (id,name,display_name,description) VALUES (21,'NUMBER','nos',NULL);
INSERT INTO observation_unit (id,name,display_name,description) VALUES (22,'OZ','oz',NULL);
INSERT INTO observation_unit (id,name,display_name,description) VALUES (23,'PACKAGE','package',NULL);
INSERT INTO observation_unit (id,name,display_name,description) VALUES (24,'PIECE','piece',NULL);
INSERT INTO observation_unit (id,name,display_name,description) VALUES (25,'PINT','pint',NULL);
INSERT INTO observation_unit (id,name,display_name,description) VALUES (26,'QUART','quart',NULL);
INSERT INTO observation_unit (id,name,display_name,description) VALUES (27,'SCOOP','scoop',NULL);
INSERT INTO observation_unit (id,name,display_name,description) VALUES (28,'SERVING','serving',NULL);
INSERT INTO observation_unit (id,name,display_name,description) VALUES (29,'SECOND','sec',NULL);
INSERT INTO observation_unit (id,name,display_name,description) VALUES (30,'SLICE','slice',NULL);
INSERT INTO observation_unit (id,name,display_name,description) VALUES (31,'SMALL','small',NULL);
INSERT INTO observation_unit (id,name,display_name,description) VALUES (32,'STICK','stick',NULL);
INSERT INTO observation_unit (id,name,display_name,description) VALUES (33,'TBLSP','tblsp',NULL);
INSERT INTO observation_unit (id,name,display_name,description) VALUES (34,'TSP','tsp',NULL);
INSERT INTO observation_unit (id,name,display_name,description) VALUES (35,'METER','meter',NULL);


/**
Inserting seed data for food_type in the application
**/
INSERT INTO food_type (id,name,description) VALUES (1,'GREEN','Green');
INSERT INTO food_type (id,name,description) VALUES (2,'YELLOW','Yellow');
INSERT INTO food_type (id,name,description) VALUES (3,'RED','Red');

/**
Inserting seed data for food_sub_type in the application
**/
INSERT INTO food_sub_type (id,food_type_id,name,description,display_name,observation_unit_id,calories_in_unit,icon_source) VALUES (1,1,'APPLE','Apple','Apple',21,52,'apple.jpeg');
INSERT INTO food_sub_type (id,food_type_id,name,description,display_name,observation_unit_id,calories_in_unit,icon_source) VALUES (2,1,'MANGO','Mango','Mango',21,60,'mango.jpeg');
INSERT INTO food_sub_type (id,food_type_id,name,description,display_name,observation_unit_id,calories_in_unit,icon_source) VALUES (3,2,'BREAD','Bread','Bread',21,155,'bread.jpeg');
INSERT INTO food_sub_type (id,food_type_id,name,description,display_name,observation_unit_id,calories_in_unit,icon_source) VALUES (4,2,'RICE','Rice','Rice',30,166,'rice.jpeg');
INSERT INTO food_sub_type (id,food_type_id,name,description,display_name,observation_unit_id,calories_in_unit,icon_source) VALUES (5,3,'CHOCOLATE','Chocolate','Chocolate',1,239,'chocolate.jpeg');
INSERT INTO food_sub_type (id,food_type_id,name,description,display_name,observation_unit_id,calories_in_unit,icon_source) VALUES (6,3,'CAKE','cake','cake',30,250,'cake.jpeg');
INSERT INTO food_sub_type (id,food_type_id,name,description,display_name,observation_unit_id,calories_in_unit,icon_source) VALUES (7,1,'ORANGE','Orange','Orange',21,47,'orange.jpeg');
INSERT INTO food_sub_type (id,food_type_id,name,description,display_name,observation_unit_id,calories_in_unit,icon_source) VALUES (8,1,'BANANA','Banana','Banana',21,89,'banana.jpeg');
INSERT INTO food_sub_type (id,food_type_id,name,description,display_name,observation_unit_id,calories_in_unit,icon_source) VALUES (9,1,'PEAR','Pear','Pear',21,57,'pear.jpeg');
INSERT INTO food_sub_type (id,food_type_id,name,description,display_name,observation_unit_id,calories_in_unit,icon_source) VALUES (10,1,'GRAPES','Grapes','Grapes',21,67,'grape.jepg');
INSERT INTO food_sub_type (id,food_type_id,name,description,display_name,observation_unit_id,calories_in_unit,icon_source) VALUES (11,1,'MELON','Melon','Melon',21,34,'melon.jpeg');
INSERT INTO food_sub_type (id,food_type_id,name,description,display_name,observation_unit_id,calories_in_unit,icon_source) VALUES (12,1,'PINEAPPLE','Pineapple','Pineapple',21,50,'Pineapple.jpeg');
INSERT INTO food_sub_type (id,food_type_id,name,description,display_name,observation_unit_id,calories_in_unit,icon_source) VALUES (13,1,'KIWI','Kiwi','Kiwi',21,61,'Kiwi.jpeg');
INSERT INTO food_sub_type (id,food_type_id,name,description,display_name,observation_unit_id,calories_in_unit,icon_source) VALUES (14,1,'LETTUCE','Lettuce','Lettuce',21,15,'Lettuce.jpeg');
INSERT INTO food_sub_type (id,food_type_id,name,description,display_name,observation_unit_id,calories_in_unit,icon_source) VALUES (15,1,'ROCCA','Rocca','Rocca',21,5,'Rocca.jpeg');
INSERT INTO food_sub_type (id,food_type_id,name,description,display_name,observation_unit_id,calories_in_unit,icon_source) VALUES (16,1,'BROCCOLI','Broccoli','Broccoli',21,21,'Broccoli.jpeg');
INSERT INTO food_sub_type (id,food_type_id,name,description,display_name,observation_unit_id,calories_in_unit,icon_source) VALUES (17,1,'EGGPLANT','eggplant','eggplant',21,36,'eggplant.jpeg');
INSERT INTO food_sub_type (id,food_type_id,name,description,display_name,observation_unit_id,calories_in_unit,icon_source) VALUES (18,1,'TOMATO','Tomato','Tomato',21,58,'Tomato.jpeg');
INSERT INTO food_sub_type (id,food_type_id,name,description,display_name,observation_unit_id,calories_in_unit,icon_source) VALUES (19,1,'PEPPERS','Peppers','Peppers',21,41,'Peppers.jpeg');
INSERT INTO food_sub_type (id,food_type_id,name,description,display_name,observation_unit_id,calories_in_unit,icon_source) VALUES (20,1,'SQUASH','Squash','Squash',21,52,'Squash.jpeg');
INSERT INTO food_sub_type (id,food_type_id,name,description,display_name,observation_unit_id,calories_in_unit,icon_source) VALUES (21,1,'CUCUMBER','Cucumber','Cucumber',21,69,'Cucumber.jpeg');
INSERT INTO food_sub_type (id,food_type_id,name,description,display_name,observation_unit_id,calories_in_unit,icon_source) VALUES (22,1,'MUSHROOM','Mushroom','Mushroom',21,25,'Mushroom.jpeg');
INSERT INTO food_sub_type (id,food_type_id,name,description,display_name,observation_unit_id,calories_in_unit,icon_source) VALUES (23,1,'CARROT','Carrot','Carrot',9,14,'Carrot.jpeg');
INSERT INTO food_sub_type (id,food_type_id,name,description,display_name,observation_unit_id,calories_in_unit,icon_source) VALUES (24,1,'RED BEANS','Red beans','Red beans',9,25,'Redbeans.jpeg');
INSERT INTO food_sub_type (id,food_type_id,name,description,display_name,observation_unit_id,calories_in_unit,icon_source) VALUES (25,1,'CHICKPEAS','Chickpeas','Chickpeas',9,36,'Chickpeas.jpeg');
INSERT INTO food_sub_type (id,food_type_id,name,description,display_name,observation_unit_id,calories_in_unit,icon_source) VALUES (26,1,'LENTILS','Lentils','Lentils',9,96,'Lentils.jpeg');
INSERT INTO food_sub_type (id,food_type_id,name,description,display_name,observation_unit_id,calories_in_unit,icon_source) VALUES (27,1,'FAVA BEANS','Fava beans','Fava beans',9,85,'Favabeans.jpeg');
INSERT INTO food_sub_type (id,food_type_id,name,description,display_name,observation_unit_id,calories_in_unit,icon_source) VALUES (28,1,'HUMMUS','Hummus','Hummus',9,74,'Hummus.jpeg');
INSERT INTO food_sub_type (id,food_type_id,name,description,display_name,observation_unit_id,calories_in_unit,icon_source) VALUES (29,3,'CEREAL','Cereal','Cereal',9,14,'Cereal.jpeg');
INSERT INTO food_sub_type (id,food_type_id,name,description,display_name,observation_unit_id,calories_in_unit,icon_source) VALUES (30,3,'RICE','Rice','Rice',9,85,'Rice.jpeg');
INSERT INTO food_sub_type (id,food_type_id,name,description,display_name,observation_unit_id,calories_in_unit,icon_source) VALUES (31,3,'BREAD','bread','bread',9,26,'bread.jpeg');
INSERT INTO food_sub_type (id,food_type_id,name,description,display_name,observation_unit_id,calories_in_unit,icon_source) VALUES (32,3,'POTATO','potato','potato',9,15,'potato.jpeg');
INSERT INTO food_sub_type (id,food_type_id,name,description,display_name,observation_unit_id,calories_in_unit,icon_source) VALUES (33,3,'PASTA','pasta','pasta',9,26,'pasta.jpeg');
INSERT INTO food_sub_type (id,food_type_id,name,description,display_name,observation_unit_id,calories_in_unit,icon_source) VALUES (34,3,'MEAT','meat','meat',9,95,'meat.jpeg');
INSERT INTO food_sub_type (id,food_type_id,name,description,display_name,observation_unit_id,calories_in_unit,icon_source) VALUES (35,3,'CHICKEN','chicken','chicken',9,84,'chicken.jpeg');
INSERT INTO food_sub_type (id,food_type_id,name,description,display_name,observation_unit_id,calories_in_unit,icon_source) VALUES (36,3,'COLD CUTS','Cold Cuts','Cold Cuts',9,74,'coldcuts.jpeg');
INSERT INTO food_sub_type (id,food_type_id,name,description,display_name,observation_unit_id,calories_in_unit,icon_source) VALUES (37,3,'MILK','Milk','Milk',9,85,'milk.jpeg');
INSERT INTO food_sub_type (id,food_type_id,name,description,display_name,observation_unit_id,calories_in_unit,icon_source) VALUES (38,3,'YOGURT','Yogurt','Yogurt',9,34,'yogurt.jpeg');
INSERT INTO food_sub_type (id,food_type_id,name,description,display_name,observation_unit_id,calories_in_unit,icon_source) VALUES (39,3,'NUTS','Nuts','Nuts',9,35,'nuts.jpeg');
INSERT INTO food_sub_type (id,food_type_id,name,description,display_name,observation_unit_id,calories_in_unit,icon_source) VALUES (40,3,'OLIVE OIL','Olive Oil','Olive Oil',9,39,'oliveoil.jpeg');
INSERT INTO food_sub_type (id,food_type_id,name,description,display_name,observation_unit_id,calories_in_unit,icon_source) VALUES (41,3,'AVOCADO','Avocado','Avocado',9,38,'Avocado.jpeg');
INSERT INTO food_sub_type (id,food_type_id,name,description,display_name,observation_unit_id,calories_in_unit,icon_source) VALUES (42,2,'FAST FOOD','Fast Food','Fast Food',9,41,'fastfood.jpeg');
INSERT INTO food_sub_type (id,food_type_id,name,description,display_name,observation_unit_id,calories_in_unit,icon_source) VALUES (43,2,'FRIED FOOD','Fried Food','Fried Food',9,58,'friedfood.jpeg');
INSERT INTO food_sub_type (id,food_type_id,name,description,display_name,observation_unit_id,calories_in_unit,icon_source) VALUES (44,2,'FRENCH FRIES','French Fries','French Fries',9,29,'Frenchfries.jpeg');
INSERT INTO food_sub_type (id,food_type_id,name,description,display_name,observation_unit_id,calories_in_unit,icon_source) VALUES (45,2,'CAKE','Cake','Cake',9,28,'Cake.jpeg');
INSERT INTO food_sub_type (id,food_type_id,name,description,display_name,observation_unit_id,calories_in_unit,icon_source) VALUES (46,2,'SWEETS','Sweets','Sweets',9,17,'Sweets.jpeg');
INSERT INTO food_sub_type (id,food_type_id,name,description,display_name,observation_unit_id,calories_in_unit,icon_source) VALUES (47,2,'ICE CREAM','Ice Cream','Ice Cream',9,21,'icecream.jpeg');
INSERT INTO food_sub_type (id,food_type_id,name,description,display_name,observation_unit_id,calories_in_unit,icon_source) VALUES (48,2,'CHOCOLATE','Chocolate','Chocolate',9,29,'chocolate.jpeg');
INSERT INTO food_sub_type (id,food_type_id,name,description,display_name,observation_unit_id,calories_in_unit,icon_source) VALUES (49,2,'CHIPS','Chips','Chips',9,58,'chips.jpeg');
INSERT INTO food_sub_type (id,food_type_id,name,description,display_name,observation_unit_id,calories_in_unit,icon_source) VALUES (50,2,'BUTTER','Butter','Butter',9,68,'butter.jpeg');
INSERT INTO food_sub_type (id,food_type_id,name,description,display_name,observation_unit_id,calories_in_unit,icon_source) VALUES (51,2,'MARGARINE','Margarine','Margarine',9,75,'margarine.jpeg');
INSERT INTO food_sub_type (id,food_type_id,name,description,display_name,observation_unit_id,calories_in_unit,icon_source) VALUES (52,2,'BURGER','Burger','Burger',9,48,'burger.jpeg');
INSERT INTO food_sub_type (id,food_type_id,name,description,display_name,observation_unit_id,calories_in_unit,icon_source) VALUES (53,2,'PIZZA','Pizza','Pizza',9,52,'pizza.jpeg');
INSERT INTO food_sub_type (id,food_type_id,name,description,display_name,observation_unit_id,calories_in_unit,icon_source) VALUES (54,2,'MAYONNAISE','Mayonnaise','Mayonnaise',9,55,'mayonnaise.jpeg');
INSERT INTO food_sub_type (id,food_type_id,name,description,display_name,observation_unit_id,calories_in_unit,icon_source) VALUES (55,2,'SODAS','Sodas','Sodas',9,84,'sodas.jpeg');
/*
 * Inserting Data in duration table
 */

INSERT INTO qsense_db.duration (name, display_name, description, number_of_days) VALUES ('DAILY', 'Daily', 'Daily', '1');
INSERT INTO qsense_db.duration (name, display_name, description, number_of_days) VALUES ('WEEKLY', 'Weekly', 'Weekly', '7');
INSERT INTO qsense_db.duration (name, display_name, description, number_of_days) VALUES ('FORTNIGHTLY', 'Fortnightly', 'Fortnightly', '14');
INSERT INTO qsense_db.duration (name, display_name, description, number_of_days) VALUES ('MONTHLY', 'Monthly', 'Monthly', '30');
INSERT INTO qsense_db.duration (name, display_name,description, number_of_days) VALUES ('YEARLY', 'Yearly', 'Yearly', '365');

/*
 * Inserting Data in message status table
 */

INSERT INTO message_status (id,name) VALUES (1,'LIKED');
INSERT INTO message_status (id,name) VALUES (2,'UNLIKED');

/*
 * Inserting Observation Units in sub category table
 */
UPDATE qsense_db.sub_category SET observation_unit_id='21' WHERE id='2';
UPDATE qsense_db.sub_category SET observation_unit_id='19' WHERE id='5';
UPDATE qsense_db.sub_category SET observation_unit_id='19' WHERE id='6';
UPDATE qsense_db.sub_category SET observation_unit_id='19' WHERE id='8';
UPDATE qsense_db.sub_category SET observation_unit_id='19' WHERE id='9';
UPDATE qsense_db.sub_category SET observation_unit_id='19' WHERE id='13';
UPDATE qsense_db.sub_category SET observation_unit_id='21' WHERE id='14';
UPDATE qsense_db.sub_category SET observation_unit_id='21' WHERE id='15';
UPDATE qsense_db.sub_category SET observation_unit_id='19' WHERE id='16';
UPDATE qsense_db.sub_category SET observation_unit_id='19' WHERE id='17';
UPDATE qsense_db.sub_category SET observation_unit_id='19' WHERE id='18';
UPDATE qsense_db.sub_category SET observation_unit_id='19' WHERE id='19';
UPDATE qsense_db.sub_category SET observation_unit_id='19' WHERE id='20';
UPDATE qsense_db.sub_category SET observation_unit_id='19' WHERE id='21';
UPDATE qsense_db.sub_category SET observation_unit_id='19' WHERE id='22';
UPDATE qsense_db.sub_category SET observation_unit_id='19' WHERE id='23';
UPDATE qsense_db.sub_category SET observation_unit_id='19' WHERE id='24';
UPDATE qsense_db.sub_category SET observation_unit_id='19' WHERE id='25';
UPDATE qsense_db.sub_category SET observation_unit_id='19' WHERE id='26';
UPDATE qsense_db.sub_category SET observation_unit_id='19' WHERE id='27';
UPDATE qsense_db.sub_category SET observation_unit_id='19' WHERE id='28';
UPDATE qsense_db.sub_category SET observation_unit_id='19' WHERE id='29';
UPDATE qsense_db.sub_category SET observation_unit_id='19' WHERE id='30';
UPDATE qsense_db.sub_category SET observation_unit_id='19' WHERE id='31';
UPDATE qsense_db.sub_category SET observation_unit_id='19' WHERE id='32';
UPDATE qsense_db.sub_category SET observation_unit_id='19' WHERE id='33';
UPDATE qsense_db.sub_category SET observation_unit_id='19' WHERE id='34';
UPDATE qsense_db.sub_category SET observation_unit_id='19' WHERE id='35';
UPDATE qsense_db.sub_category SET observation_unit_id='19' WHERE id='36';
UPDATE qsense_db.sub_category SET observation_unit_id='19' WHERE id='37';
UPDATE qsense_db.sub_category SET observation_unit_id='19' WHERE id='38';
UPDATE qsense_db.sub_category SET observation_unit_id='19' WHERE id='39';
UPDATE qsense_db.sub_category SET observation_unit_id='19' WHERE id='40';
UPDATE qsense_db.sub_category SET observation_unit_id='19' WHERE id='41';
UPDATE qsense_db.sub_category SET observation_unit_id='19' WHERE id='42';
UPDATE qsense_db.sub_category SET observation_unit_id='19' WHERE id='43';
UPDATE qsense_db.sub_category SET observation_unit_id='19' WHERE id='44';
UPDATE qsense_db.sub_category SET observation_unit_id='19' WHERE id='45';
UPDATE qsense_db.sub_category SET observation_unit_id='19' WHERE id='46';
UPDATE qsense_db.sub_category SET observation_unit_id='19' WHERE id='47';
UPDATE qsense_db.sub_category SET observation_unit_id='19' WHERE id='48';
UPDATE qsense_db.sub_category SET observation_unit_id='19' WHERE id='49';
UPDATE qsense_db.sub_category SET observation_unit_id='19' WHERE id='50';
UPDATE qsense_db.sub_category SET observation_unit_id='19' WHERE id='51';
UPDATE qsense_db.sub_category SET observation_unit_id='19' WHERE id='52';
UPDATE qsense_db.sub_category SET observation_unit_id='19' WHERE id='53';
UPDATE qsense_db.sub_category SET observation_unit_id='19' WHERE id='54';
UPDATE qsense_db.sub_category SET observation_unit_id='19' WHERE id='55';
UPDATE qsense_db.sub_category SET observation_unit_id='19' WHERE id='56';
UPDATE qsense_db.sub_category SET observation_unit_id='19' WHERE id='57';
UPDATE qsense_db.sub_category SET observation_unit_id='19' WHERE id='58';
UPDATE qsense_db.sub_category SET observation_unit_id='19' WHERE id='59';
UPDATE qsense_db.sub_category SET observation_unit_id='19' WHERE id='60';
UPDATE qsense_db.sub_category SET observation_unit_id='19' WHERE id='61';
UPDATE qsense_db.sub_category SET observation_unit_id='19' WHERE id='62';
UPDATE qsense_db.sub_category SET observation_unit_id='19' WHERE id='63';
UPDATE qsense_db.sub_category SET observation_unit_id='19' WHERE id='64';
UPDATE qsense_db.sub_category SET observation_unit_id='19' WHERE id='65';
UPDATE qsense_db.sub_category SET observation_unit_id='19' WHERE id='66';
UPDATE qsense_db.sub_category SET observation_unit_id='19' WHERE id='67';
UPDATE qsense_db.sub_category SET observation_unit_id='19' WHERE id='68';
UPDATE qsense_db.sub_category SET observation_unit_id='19' WHERE id='69';
UPDATE qsense_db.sub_category SET observation_unit_id='35' WHERE id='12';

UPDATE qsense_db.sub_category SET category_id='2' WHERE id='11';



COMMIT;
