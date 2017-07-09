/**
QSense V2 schema ddl script.
**/

USE qsense_db;

START TRANSACTION;
SET FOREIGN_KEY_CHECKS = 0;

/**
This table would store generalized unit of activities (duration), 
foods (gram, bar, quantity), statuses(number)  
**/
DROP TABLE IF EXISTS  observation_unit ;
CREATE TABLE observation_unit (
	id BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY,
	name VARCHAR(45) NOT NULL UNIQUE,
	description  VARCHAR(100) DEFAULT NULL,
	display_name VARCHAR(45) NOT NULL
);

DROP TABLE IF EXISTS  duration ;
CREATE TABLE duration (
	id BIGINT NOT NULL PRIMARY KEY AUTO_INCREMENT,
	display_name VARCHAR(45) NULL,
	name VARCHAR(45) NULL,
	description VARCHAR(100) NULL,
	number_of_days INT NOT NULL
);
  
/**
This table would store generalized food category. For example: Green, Red, Yellow  
**/
DROP TABLE IF EXISTS  food_type ;
CREATE TABLE  food_type  (
   	id  BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY,
   	name  VARCHAR(255) NOT NULL UNIQUE,
   	description  VARCHAR(1000) DEFAULT NULL,
    price BIGINT NULL DEFAULT 0
);


/**
This table would store the information about various kinds of foods. Like Apple, Orange, Fish
**/
DROP TABLE IF EXISTS  food_sub_type ;
CREATE TABLE  food_sub_type  (
	id  BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY,
   	food_type_id  BIGINT NOT NULL,
   	name  VARCHAR(255) DEFAULT NULL,
   	description  VARCHAR(1000) DEFAULT NULL,
   	display_name VARCHAR(255) NOT NULL,
   	observation_unit_id  BIGINT DEFAULT NULL,
   	calories_in_unit  INTEGER DEFAULT NULL,
   	icon_source  VARCHAR(255) DEFAULT NULL,
   	FOREIGN KEY ( food_type_id ) REFERENCES  food_type  ( id ),
   	FOREIGN KEY ( observation_unit_id ) REFERENCES  observation_unit  ( id )
);


/**
This table would store generalized goal. For example: 
Weekly goal for diabetic patients (it's activity level goals are stored in goal_attributes table)
***/
DROP TABLE IF EXISTS  goal ;
CREATE TABLE  goal  (
   id  BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY,
   title  VARCHAR(255) NOT NULL UNIQUE,
   description  VARCHAR(1000) DEFAULT NULL,
   duration_id  BIGINT NOT NULL,
   created_time  DATETIME NOT NULL,
   updated_time  DATETIME NOT NULL,
   FOREIGN KEY ( duration_id ) REFERENCES  duration  ( id )
);


/**
This table would store information about the goals per activity level. 
Like: Running 2hours, Walking 4 hours
***/
DROP TABLE IF EXISTS  goal_attributes ;
CREATE TABLE  goal_attributes  (
    id  BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    goal_id  BIGINT NOT NULL,
    sub_category_id  BIGINT,
    food_sub_type_id  BIGINT,
    value  INTEGER DEFAULT NULL,
    created_time  DATETIME NOT NULL,
    updated_time  DATETIME NOT NULL,
   	FOREIGN KEY ( goal_id ) REFERENCES  goal  ( id ),
   	FOREIGN KEY ( sub_category_id ) REFERENCES  sub_category  ( id ),
   	FOREIGN KEY ( food_sub_type_id ) REFERENCES  food_sub_type  ( id )
);

/**
 This table would store information about the food intake by the user.
 */
DROP TABLE IF EXISTS  user_food_observation ;
CREATE TABLE  user_food_observation  (
   	id  BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY,
   	user_id  BIGINT NOT NULL,
   	food_id  BIGINT NOT NULL,
   	session_id  BIGINT NOT NULL,
  	session_record_id  BIGINT NOT NULL,
  	time  DATETIME NOT NULL,
  	food_serving_size  DOUBLE DEFAULT NULL,
   	created_time  DATETIME NOT NULL,
   	updated_time  DATETIME NOT NULL,
   	KEY  user_id_idx  ( user_id ),
  	FOREIGN KEY ( user_id ) REFERENCES  user  ( id ),
  	FOREIGN KEY ( food_id ) REFERENCES  sub_category  ( id ),
	FOREIGN KEY ( session_id ) REFERENCES  user_session  ( id )
);


/**
 This table would store the assigned individual goal.
 */
DROP TABLE IF EXISTS  user_goal ;
CREATE TABLE  user_goal  (
   	id  BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY,
   	user_id  BIGINT NOT NULL,
   	goal_id  BIGINT NOT NULL,
   	start_time  DATETIME DEFAULT NULL,
   	created_time  DATETIME NOT NULL,
   	updated_time  DATETIME NOT NULL,
   	FOREIGN KEY ( user_id ) REFERENCES  user  ( id ),
   	FOREIGN KEY ( goal_id ) REFERENCES  goal  ( id )
);

/**
 This table would store the oryx information.
 */
DROP TABLE IF EXISTS  oryx ;
CREATE TABLE  oryx  (
   	id  BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY,
   	name  VARCHAR(255) NOT NULL,
   	display_name  VARCHAR(255) DEFAULT NULL,
   	icon_source VARCHAR(255) NOT NULL,
   	price  BIGINT NOT NULL,
   	created_time  DATETIME NOT NULL,
   	updated_time  DATETIME NOT NULL
);

/**
 This table would store message status information.
 */
DROP TABLE IF EXISTS  message_status;
CREATE TABLE  message_status  (
   	id  BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY,
   	name  VARCHAR(255) NOT NULL
);


/*
 * Altered sub-category table to have is_maually allowed field.
 */
ALTER TABLE qsense_db.sub_category 
	ADD COLUMN is_manually_allowed TINYINT(1) DEFAULT 0,
	ADD COLUMN observation_unit_id BIGINT(20) NULL,
	ADD CONSTRAINT observation_unit_id
  		FOREIGN KEY (observation_unit_id) REFERENCES qsense_db.observation_unit (id);

UPDATE qsense_db.sub_category set is_manually_allowed = 1 where category_id = 1;

ALTER TABLE qsense_db.user_observation
ADD COLUMN is_manual TINYINT(1) NULL DEFAULT 0;

ALTER TABLE qsense_db.user
ADD COLUMN coins BIGINT NULL DEFAULT 0,
ADD COLUMN oryx_id BIGINT NULL,
ADD CONSTRAINT oryx_id
  		FOREIGN KEY (oryx_id) REFERENCES qsense_db.oryx (id);

ALTER TABLE qsense_db.user_message
ADD COLUMN message_status_id BIGINT NULL,
ADD COLUMN is_deleted BOOLEAN NOT NULL DEFAULT FALSE,
ADD CONSTRAINT message_status_id
  		FOREIGN KEY (message_status_id) REFERENCES qsense_db.message_status (id);
  		
SET FOREIGN_KEY_CHECKS = 1;
COMMIT;
