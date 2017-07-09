/**
Seed Data Loader Script for QSense Application
**/

START TRANSACTION;

USE qsense_db;

/**
Inserting seed data for roles in the application
**/
INSERT INTO role (id,name, description) VALUES
(1,'ADMIN', 'Administrator for the application');

INSERT INTO role (id,name, description) VALUES
(2,'PARENT', 'Parent of a participant');

INSERT INTO role (id,name, description) VALUES
(3,'PARTICIPANT' , 'Participant himself');

/**
Inserting seed data for groups in the application
**/
INSERT INTO app_group(id,name, description) VALUES 
(1,'control' , 'The control group');

INSERT INTO app_group(id,name, description) VALUES 
(2,'trial', 'The trial group');

INSERT INTO user(id,username,password,role_id,created_by, updated_by, created_time, updated_time) VALUES 
(1,'admin', '098f6bcd4621d373cade4e832627b4f6',1,1,1,now(),now());

/**
Inserting seed data for category in the application
**/
INSERT INTO category(id,name, description) VALUES 
(1,'ACTIVITY' , 'activities');

INSERT INTO category(id,name, description) VALUES 
(2,'STATUS', 'statuses');

/**
Inserting seed data for sub-category in the application
**/
INSERT INTO `sub_category` (`id`,`category_id`,`name`,`description`,`display_name`,`units`,`color`,`sort_order`,`is_dashboard_visible`,`is_leaderboard_visible`) VALUES (1,1,'WALK','Walking','Walking','unit_walk','#3F51B5',2,true,true);
INSERT INTO `sub_category` (`id`,`category_id`,`name`,`description`,`display_name`,`units`,`color`,`sort_order`,`is_dashboard_visible`,`is_leaderboard_visible`) VALUES (2,2,'STEP_COUNT','Steps','Steps','unit_steps','#FFFFFF',1,true,true);
INSERT INTO `sub_category` (`id`,`category_id`,`name`,`description`,`display_name`,`units`,`color`,`sort_order`,`is_dashboard_visible`,`is_leaderboard_visible`) VALUES (3,2,'LUMINESCENCE','Light','Light','unit_lumina','#FFFFFF',7,false,false);
INSERT INTO `sub_category` (`id`,`category_id`,`name`,`description`,`display_name`,`units`,`color`,`sort_order`,`is_dashboard_visible`,`is_leaderboard_visible`) VALUES (4,2,'LOCATION','Location','Location','unit_location','#FFFFFF',8,false,false);
INSERT INTO `sub_category` (`id`,`category_id`,`name`,`description`,`display_name`,`units`,`color`,`sort_order`,`is_dashboard_visible`,`is_leaderboard_visible`) VALUES (5,1,'IN_VEHICLE','In Vehicle','In Vehicle','unit_in_vehicle','#D3511E',4,false,false);
INSERT INTO `sub_category` (`id`,`category_id`,`name`,`description`,`display_name`,`units`,`color`,`sort_order`,`is_dashboard_visible`,`is_leaderboard_visible`) VALUES (6,1,'ON_BICYCLE','On Bicycle','Cycling','unit_on_vehicle','#FF5252',5,false,false);
INSERT INTO `sub_category` (`id`,`category_id`,`name`,`description`,`display_name`,`units`,`color`,`sort_order`,`is_dashboard_visible`,`is_leaderboard_visible`) VALUES (7,1,'RUN','Running','Running','unit_running','#FFD23B',3,true,true);
INSERT INTO `sub_category` (`id`,`category_id`,`name`,`description`,`display_name`,`units`,`color`,`sort_order`,`is_dashboard_visible`,`is_leaderboard_visible`) VALUES (8,1,'STILL','Still','Stationary','unit_stay','#4CAF50',6,false,false);
INSERT INTO `sub_category` (`id`,`category_id`,`name`,`description`,`display_name`,`units`,`color`,`sort_order`,`is_dashboard_visible`,`is_leaderboard_visible`) VALUES (9,2,'BATTERY','Battery ','Battery ','unit_battery ','#FFFFFF',9,false,false);
INSERT INTO `sub_category` (`id`,`category_id`,`name`,`description`,`display_name`,`units`,`color`,`sort_order`,`is_dashboard_visible`,`is_leaderboard_visible`) VALUES (10,2,'SCREEN_STATUS','Screen_Status','Screen Status','unit_screen_status','#FFFFFF',10,false,false);
INSERT INTO `sub_category` (`id`,`category_id`,`name`,`description`,`display_name`,`units`,`color`,`sort_order`,`is_dashboard_visible`,`is_leaderboard_visible`) VALUES (11,1,'SENSING_DISABLED','Sensing_Disabled','Sensing Disabled','unit_sensing_disabled','#FFFF50',11,false,false);

/**
Creating B-Tree index in user_observation table
**/
CREATE INDEX user_observation_sub_category_index 
ON user_observation(sub_category_id) 
USING HASH;
CREATE INDEX user_observation_user_index 
ON user_observation(user_id) 
USING HASH;
CREATE INDEX user_observation_start_time_index 
ON user_observation(start_timestamp) 
USING BTREE;
CREATE iNDEX user_observation_end_time_index 
ON user_observation(end_timestamp) 
USING BTREE;

/**
Creating B-Tree index in user_session table
**/
CREATE INDEX user_login_time_session_idx
ON user_session(user_id, login_time)
USING BTREE;

COMMIT;
