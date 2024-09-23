INSERT INTO REVIEWS (author_name, restaurant_id, peanut_score, egg_score, dairy_score, comment, status ) VALUES ('Frank', 1, 2.00, 3.00, 5.00, 'Heel goed', 'PENDING');
INSERT INTO REVIEWS (author_name, restaurant_id, peanut_score, egg_score, dairy_score, comment, status ) VALUES ('Camila', 1, 5.00, 4.00, 5.00, 'Excellent', 'PENDING');
INSERT INTO REVIEWS (author_name, restaurant_id, peanut_score, egg_score, dairy_score, comment, status ) VALUES ('Frank', 1, 0.00, 0.00, 1.00, 'Outstanding', 'PENDING');


INSERT INTO USERS (user_name, city, country, zip_code, is_interested_in_peanut, is_interested_in_egg, is_interested_in_dairy) VALUES ('Camila', 'Amsterdam', 'Netherlands', '1111AL', true, true, false);
INSERT INTO USERS (user_name, city, country, zip_code, is_interested_in_peanut, is_interested_in_egg, is_interested_in_dairy) VALUES ('Jan', 'Amsterdam', 'Netherlands', '1093DM', true, false, false);
INSERT INTO USERS (user_name, city, country, zip_code, is_interested_in_peanut, is_interested_in_egg, is_interested_in_dairy) VALUES ('Fleur', 'Amsterdam', 'Netherlands', '1024KA', false, true, true);
INSERT INTO USERS (user_name, city, country, zip_code, is_interested_in_peanut, is_interested_in_egg, is_interested_in_dairy) VALUES ('Frank', 'Diemen', 'Netherlands', '1111NE', true, true, true);

INSERT INTO RESTAURANTS (name, city, zip_code, capacity, peanut_score, egg_score, dairy_score, overall_score) VALUES ('D & A Hummus Bistro', 'Amsterdam', '1111NE', 45, NULL, NULL, NULL, NULL);
INSERT INTO RESTAURANTS (name, city, zip_code, capacity, peanut_score, egg_score, dairy_score, overall_score) VALUES ('Xian Delicious Foods', 'Den Haag', '2511CM', 22, NULL, NULL, NULL, NULL);
