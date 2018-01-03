-- *****************************************************************************
-- This script contains INSERT statements for populating tables with seed data
-- *****************************************************************************

BEGIN;

-- INSERT statements go here

INSERT INTO app_user (user_name, password, salt, first_name, last_name) VALUES ('cindy', 'WzYbaCzcPr6PSX0W50gUWw==', 'SDJydqT014UpMj7hCx+1/aeV7HJ82PA+CxZm0BeelT75HfxhbnAH/w5AVIHoX+A6eGiyzIMPflQlLiYhQxtB8LqddoMPWIW6jsX3MrjuKZKlRdd52az4DXdV3jWzFb+CwkGjVlLZ0SE/NvNmMxoEcwKPEEnGevnkZ8T0ZJJdh/s=', 'Cindy', 'Edington');

COMMIT;