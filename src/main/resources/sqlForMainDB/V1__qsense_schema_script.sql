/**
Database Schema Creation Script for QSense application.
Switch context to qsense_db
**/
USE qsense_db;

/**
Create role table.
This table would store information about the different kinds of roles (for users) supported by our application. 
Currently there are three roles; participant, parent and admin. 
This table would be seeded with data at the time of application deployment.
**/
DROP TABLE IF EXISTS role;
CREATE TABLE role (
    id INTEGER AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255) NOT NULL UNIQUE,
    description VARCHAR(1000)
);

/** Create app_group table.
This table would store information about the different kinds of groups (for users) supported by our application. 
Currently there two different groups; control and test. 
This table would be seeded with data at the time of application deployment.
**/
DROP TABLE IF EXISTS app_group;
CREATE TABLE app_group (
    id INTEGER AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255) NOT NULL UNIQUE,
    description VARCHAR(1000)
);

/** Create user table.
This table would store information specific to a user's profile in our system. 
**/
DROP TABLE IF EXISTS user;
CREATE TABLE IF NOT EXISTS user (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(255) NOT NULL,
    password VARCHAR(255) NOT NULL,
    first_name VARCHAR(45) NULL DEFAULT NULL,
    last_name VARCHAR(45) NULL DEFAULT NULL,
    role_id INTEGER NOT NULL,
    group_id INTEGER,
    associated_participant_id BIGINT UNIQUE,
    device_id VARCHAR(255),
	permanent_device_id VARCHAR(255),
    auth_token VARCHAR(255),
    created_by BIGINT NOT NULL,
    updated_by BIGINT NOT NULL,
    created_time DATETIME NOT NULL,
    updated_time DATETIME NOT NULL,
    FOREIGN KEY (created_by)
        REFERENCES user (id),
    FOREIGN KEY (updated_by)
        REFERENCES user (id),
    FOREIGN KEY (role_id)
        REFERENCES role (id),
    FOREIGN KEY (group_id)
        REFERENCES app_group (id),
    FOREIGN KEY (associated_participant_id)
        REFERENCES user (id)
);

/** Create user session table.
This table would store information about the user login time, logout time, its authentication code and deviceId .
**/
DROP TABLE IF EXISTS user_session;
CREATE TABLE IF NOT EXISTS user_session (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    user_id BIGINT NOT NULL,
	auth_token VARCHAR(255),
	login_time DATETIME NOT NULL,
    logout_time DATETIME,
	timezone VARCHAR(20) NOT NULL,
	permanent_device_id VARCHAR(255),
	device_id VARCHAR(255),
	is_step_count_sensor_available BOOLEAN NOT NULL DEFAULT TRUE,
    FOREIGN KEY (user_id)
        REFERENCES user (id)
);

/** Create category table.
This table would store meta level information which will be either Activity or Status ( Sensor Data)

**/
DROP TABLE IF EXISTS category;
CREATE TABLE category (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255) NOT NULL UNIQUE,
    description VARCHAR(1000)
);

/** Create sub_category table.
This table would store meta level information about the various activities and status (for a user) which will be tracked by our system. 
For example activities can be walking, running, driving, sleep etc. Statuses can be stepcount,location data
**/

DROP TABLE IF EXISTS sub_category;
CREATE TABLE sub_category (
	id BIGINT PRIMARY KEY AUTO_INCREMENT,
	category_id BIGINT NOT NULL,
	name VARCHAR(255) UNIQUE NOT NULL,
	description VARCHAR(1000) DEFAULT NULL,
	display_name VARCHAR(255) NOT NULL,
	units VARCHAR(45) DEFAULT NULL,
	color VARCHAR(45) DEFAULT NULL,
	sort_order BIGINT NOT NULL,
	is_dashboard_visible BIT NOT NULL DEFAULT 0,
	is_leaderboard_visible BIT NOT NULL DEFAULT 0,
	FOREIGN KEY (category_id) 
		REFERENCES category (id)
);

/** Create user_observation table.
This table would store the details of all the sub categories done by various users. 
This can be used to derive all sort of category based information grouped at different granularities. 
For example, date wise breakup of user activities or aggregates of a particular group of users.
**/
DROP TABLE IF EXISTS user_observation;
CREATE TABLE user_observation (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    user_id BIGINT NOT NULL,
    sub_category_id BIGINT NOT NULL,
    start_timestamp DATETIME NOT NULL,
    end_timestamp DATETIME,
    field1 DOUBLE,
    field2 DOUBLE,
    field3 DOUBLE,
	session_id BIGINT NOT NULL,
	session_record_id BIGINT DEFAULT NULL,
    created_time DATETIME NOT NULL,
    FOREIGN KEY (user_id)
        REFERENCES user (id),
    FOREIGN KEY (sub_category_id)
        REFERENCES sub_category (id),
	FOREIGN KEY (session_id)
        REFERENCES user_session (id)
);

/** Create message table.
This table would store information about the messages posted by the admin for different combination of roles and groups. 
The recipient of a particular message can be found out by taking a combination of the group_id and the role_id of the message.
**/
DROP TABLE IF EXISTS message;
CREATE TABLE message (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    title VARCHAR(200) NOT NULL,
    content VARCHAR(1000),
    posted_at DATETIME NOT NULL,
    posted_by BIGINT NOT NULL,
    group_id INTEGER NOT NULL,
    role_id INTEGER NOT NULL,
    FOREIGN KEY (posted_by)
        REFERENCES user (id),
    FOREIGN KEY (group_id)
        REFERENCES app_group (id),
    FOREIGN KEY (role_id)
        REFERENCES role (id)
);

/** Create user_message table.
This table would store a map of the users and the messages posted for them (group+role). 
It will be used for push notifications on their devices as well as to give the admin information about whether a user is reading messages or not.
**/
DROP TABLE IF EXISTS user_message;
CREATE TABLE user_message (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    user_id BIGINT NOT NULL,
    message_id BIGINT NOT NULL,
    is_read BOOLEAN NOT NULL DEFAULT FALSE,
    read_time DATETIME DEFAULT NULL,
    FOREIGN KEY (user_id)
        REFERENCES user (id),
    FOREIGN KEY (message_id)
        REFERENCES message (id)
);

/** Create observation_block table.
This table would store a map of the session_id and block_id of the observations
**/
DROP TABLE IF EXISTS observation_block;
CREATE TABLE observation_block (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    session_id BIGINT NOT NULL,
	block_id BIGINT NOT NULL,
    record_start_id BIGINT NOT NULL,
    record_end_id BIGINT NOT NULL,
	CONSTRAINT uc_sessionIdBlockId
		UNIQUE (session_id,block_id)
);