-- *****************************************************************************
-- This script contains INSERT statements for populating tables with seed data
-- *****************************************************************************

BEGIN;

-- INSERT statements go here

INSERT INTO app_user (user_name, password, salt, first_name, last_name) VALUES ('CINDY', 'WzYbaCzcPr6PSX0W50gUWw==', 'SDJydqT014UpMj7hCx+1/aeV7HJ82PA+CxZm0BeelT75HfxhbnAH/w5AVIHoX+A6eGiyzIMPflQlLiYhQxtB8LqddoMPWIW6jsX3MrjuKZKlRdd52az4DXdV3jWzFb+CwkGjVlLZ0SE/NvNmMxoEcwKPEEnGevnkZ8T0ZJJdh/s=', 'Cindy', 'Edington');

INSERT INTO landmark (landmark_title, landmark_description, landmark_location, landmark_city, wikipedia) VALUES ('Great American Ball Park','Great American Ball Park is a baseball stadium in Cincinnati, Ohio, which is the home field of Major League Baseball''s Cincinnati Reds. It opened in 2003, replacing Cinergy Field, their home field from 1970 to 2002.','ChIJLwacWEOxQYgRcu3UJQuLYwc','Cincinnati, OH','https://en.wikipedia.org/wiki/Great_American_Ball_Park');
INSERT INTO landmark (landmark_title, landmark_description, landmark_location, landmark_city, wikipedia) VALUES ('Spring Grove Cemetery & Arboretum','Spring Grove Cemetery and Arboretum is a nonprofit garden cemetery and arboretum located at 4521 Spring Grove Avenue, Cincinnati, Ohio. It is the fourth largest cemetery in the United States and is recognized as a US National Historic Landmark.','ChIJh5T69aG0QYgRYy2pedvhx_0', 'Cincinnati, OH','https://en.wikipedia.org/wiki/Spring_Grove_Cemetery');
INSERT INTO landmark (landmark_title, landmark_description, landmark_location, landmark_city, wikipedia) VALUES ('John A. Roebling Suspension Bridge','The John A. Roebling Suspension Bridge, originally known as the Cincinnati-Covington Bridge spans the Ohio River between Cincinnati, Ohio and Covington, Kentucky.','ChIJvVE_ekaxQYgRt78Wg7uX35w','Cincinnati, OH','https://en.wikipedia.org/wiki/John_A._Roebling_Suspension_Bridge');
INSERT INTO landmark (landmark_title, landmark_description, landmark_location, landmark_city, wikipedia) VALUES ('Carew Tower','Carew Tower is a 49-story, 574-foot Art Deco building completed in 1930 in the heart of downtown Cincinnati, Ohio, overlooking the Ohio River waterfront.','ChIJ71_TXlCxQYgRFGoLgUL1W5A','Cincinnati, OH','https://en.wikipedia.org/wiki/Carew_Tower');
INSERT INTO landmark (landmark_title, landmark_description, landmark_location, landmark_city, wikipedia) VALUES ('Fountain Square','Fountain Square is a city square in Cincinnati, Ohio, United States. Founded in 1871, it was renovated in 1971 and 2005 and currently features many shops, restaurants, hotels, and offices.','ChIJPUufhlCxQYgRwJQ0Q6DRQ9I','Cincinnati, OH','https://en.wikipedia.org/wiki/Fountain_Square,_Cincinnati');
INSERT INTO landmark (landmark_title, landmark_description, landmark_location, landmark_city, wikipedia) VALUES ('Cathedral Basilica of the Assumption','The Roman Catholic St. Mary''s Cathedral Basilica of the Assumption in Covington, Kentucky, is a minor basilica in the United States.','ChIJLedfITuxQYgRWfxdJ0gV4-8', 'Covington, KY', 'https://en.wikipedia.org/wiki/Cathedral_Basilica_of_the_Assumption_(Covington,_Kentucky)');
INSERT INTO landmark (landmark_title, landmark_description, landmark_location, landmark_city, wikipedia) VALUES ('William Howard Taft National Historic Site','William Howard Taft National Historic Site is a historic house at 2038 Auburn Avenue in the Mount Auburn Historic District of Cincinnati, Ohio, a mile north of Downtown.','ChIJGS9Rb--zQYgROLm-jznQwtg','Cincinnati, OH','https://en.wikipedia.org/wiki/William_Howard_Taft_National_Historic_Site');
INSERT INTO landmark (landmark_title, landmark_description, landmark_location, landmark_city, wikipedia) VALUES ('Paul Brown Stadium','Paul Brown Stadium is an outdoor football stadium in Cincinnati, Ohio. It is the home venue of the Cincinnati Bengals of the National Football League and opened on August 19, 2000.','ChIJsVqZjk6xQYgRy4tNuL4VF5k','Cincinnati, OH','https://en.wikipedia.org/wiki/Paul_Brown_Stadium');
INSERT INTO landmark (landmark_title, landmark_description, landmark_location, landmark_city, wikipedia) VALUES ('Mount Adams','Mt. Adams is a geographic landmark and residential neighborhood of Cincinnati, Ohio, located directly east of downtown Cincinnati, south of Walnut Hills, south-west of East Walnut Hills, and west of the East End.','ChIJ3SqiINmzQYgR1OKDFKMl9ic','Cincinnati, OH','https://en.wikipedia.org/wiki/Mount_Adams,_Cincinnati');
INSERT INTO landmark (landmark_title, landmark_description, landmark_location, landmark_city, wikipedia) VALUES ('Winton Woods Park','Winton Woods is a park in Springfield Township and the village of Greenhills in the northern suburbs of Cincinnati, Ohio, United States.','ChIJyRi7YQVMQIgRj694gLg6yRg','Cincinnati, OH','https://en.wikipedia.org/wiki/Winton_Woods');


INSERT INTO itinerary (user_name, itinerary_name, starting_point, landmark_id) VALUES ('CINDY', 'myItinerary', '21 C Hotel, Cincinnati, OH', '1');
INSERT INTO itinerary (user_name, itinerary_name, starting_point, landmark_id) VALUES ('CINDY', 'myItinerary', '21 C Hotel, Cincinnati, OH', '4');
INSERT INTO itinerary (user_name, itinerary_name, starting_point, landmark_id) VALUES ('CINDY', 'myItinerary', '21 C Hotel, Cincinnati, OH', '7');
INSERT INTO itinerary (user_name, itinerary_name, starting_point, landmark_id) VALUES ('CINDY', 'myItinerary', '21 C Hotel, Cincinnati, OH', '3');
INSERT INTO itinerary (user_name, itinerary_name, starting_point, landmark_id) VALUES ('CINDY', 'daytwo', '21 C Hotel, Cincinnati, OH', '1');
INSERT INTO itinerary (user_name, itinerary_name, starting_point, landmark_id) VALUES ('CINDY', 'daytwo', '21 C Hotel, Cincinnati, OH', '3');
INSERT INTO itinerary (user_name, itinerary_name, starting_point, landmark_id) VALUES ('CINDY', 'daytwo', '21 C Hotel, Cincinnati, OH', '6');
INSERT INTO itinerary (user_name, itinerary_name, starting_point, landmark_id) VALUES ('CINDY', 'myItinerary', '21 C Hotel, Cincinnati, OH', '2');





COMMIT;