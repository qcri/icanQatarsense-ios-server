/**
Database Schema Creation Script for QSense application.
In this version, added the device-type in the application. Default device-type is Android.
Switch context to qsense_db
**/

USE qsense_db;

START TRANSACTION;
SET foreign_key_checks = 0;

/**
Create device_type table
This table will store information about the kind of device used to sync data viz. Android or Iphone
**/
DROP TABLE IF EXISTS device_type;
CREATE TABLE device_type (
  id INT NOT NULL PRIMARY KEY,
  name VARCHAR(10) NOT NULL UNIQUE
);


/**
Inserting seed data for device-type
**/
INSERT INTO `device_type` (`id`, `name`) VALUES ('1', 'ANDROID');
INSERT INTO `device_type` (`id`, `name`) VALUES ('2', 'IPHONE');


/**
Alter tables for adding new column device_type, setting default device-type to ANDROID.
**/
ALTER TABLE user_session 
	ADD COLUMN device_type_id INT NOT NULL DEFAULT 1 ,
	ADD FOREIGN KEY (device_type_id)
  		REFERENCES device_type (id);


ALTER TABLE user
	ADD COLUMN device_type_id INT NOT NULL DEFAULT 1 ,
	ADD FOREIGN KEY (device_type_id)
  		REFERENCES device_type (id);
  		
SET foreign_key_checks = 1;
COMMIT;
