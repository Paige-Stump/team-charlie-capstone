-- *************************************************************************************************
-- This script creates all of the database objects (tables, sequences, etc) for the database
-- *************************************************************************************************

BEGIN;

-- CREATE statements go here
DROP TABLE IF EXISTS message;
DROP TABLE IF EXISTS itinerary;
DROP TABLE IF EXISTS app_user;
DROP TABLE IF EXISTS landmark;

DROP SEQUENCE IF EXISTS itinerary_itinerary_id_seq;
DROP SEQUENCE IF EXISTS message_message_id_seq;
DROP SEQUENCE IF EXISTS landmark_landmark_id_seq;

CREATE TABLE app_user (
  user_name varchar(32) NOT NULL,
  password varchar(32) NOT NULL,
  salt varchar(256) NOT NULL,
  first_name varchar(32) NOT NULL,
  last_name varchar(32) NOT NULL,
  admin boolean NOT NULL DEFAULT FALSE,
  CONSTRAINT pk_app_user_username PRIMARY KEY (user_name)
);

CREATE SEQUENCE itinerary_itinerary_id_seq
  INCREMENT BY 1
  NO MAXVALUE
  NO MINVALUE
  CACHE 1;

CREATE TABLE itinerary (
  itinerary_id integer DEFAULT nextval('itinerary_itinerary_id_seq'::regclass) NOT NULL,
  user_name varchar (32) NOT NULL,
  itinerary_name varchar(32) NOT NULL,
  starting_point varchar(32) NOT NULL,
  landmark_id integer ,
  CONSTRAINT pk_itinerary_itinerary_id PRIMARY KEY (itinerary_id)
);

CREATE SEQUENCE landmark_landmark_id_seq
  INCREMENT BY 1
  NO MAXVALUE
  NO MINVALUE
  CACHE 1;

CREATE TABLE landmark (
  landmark_id integer DEFAULT nextval('landmark_landmark_id_seq'::regclass) NOT NULL,
  landmark_title varchar(500) NOT NULL,
  landmark_description varchar(2500) NOT NULL,
  landmark_location varchar(500) NOT NULL,
  wikipedia varchar(500),
  CONSTRAINT pk_landmark_landmark_id PRIMARY KEY (landmark_id)
);
  
CREATE SEQUENCE message_message_id_seq
  INCREMENT BY 1
  NO MAXVALUE
  NO MINVALUE
  CACHE 1;

CREATE TABLE message (
  message_id integer DEFAULT nextval('message_message_id_seq'::regclass) NOT NULL,
  sender_name varchar(32) NOT NULL,
  private boolean NOT NULL DEFAULT FALSE,
  receiver_name varchar(32),
  message_text varchar(200) NOT NULL,
  create_date timestamp NOT NULL DEFAULT now(),
  CONSTRAINT pk_message_message_id PRIMARY KEY (message_id)
);

ALTER TABLE message ADD FOREIGN KEY (sender_name) REFERENCES app_user(user_name);


ALTER TABLE itinerary ADD FOREIGN KEY (landmark_id) REFERENCES landmark(landmark_id);
ALTER TABLE itinerary ADD FOREIGN KEY (user_name) REFERENCES app_user(user_name);




COMMIT;