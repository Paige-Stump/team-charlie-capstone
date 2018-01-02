-- *****************************************************************************
-- This script contains INSERT statements for populating tables with seed data
-- *****************************************************************************

BEGIN;

-- INSERT statements go here

INSERT INTO app_user (user_name, password, first_name, last_name) VALUES ('cindy', 'AAAAAaaaaa;', 'Cindy', 'Edington');
INSERT INTO app_user (user_name, password, first_name, last_name, admin) VALUES ('paige', 'AAAAAaaaaa;', 'Paige', 'Stump', 'true');
INSERT INTO app_user (user_name, password, first_name, last_name) VALUES ('jon', 'AAAAAaaaaa;', 'Jon', 'Wolper');
INSERT INTO app_user (user_name, password, first_name, last_name) VALUES ('chris', 'AAAAAaaaaa;', 'Chris', 'Rupp');

COMMIT;