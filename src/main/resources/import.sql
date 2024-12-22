INSERT INTO USERS (name, surname, username, adress, email, password, last_password_reset_date, enabled) VALUES ('marko', 'markovic', 'mare', 'omladinska 8', 'marko@gmail.com', '$2a$04$Vbug2lwwJGrvUXTj6z7ff.97IzVBkrJ1XfApfGNl.Z695zqcnPYra','2017-10-01 21:58:58.508-07',true);
INSERT INTO USERS (name, surname, username, adress, email, password, last_password_reset_date, enabled) VALUES ('petar', 'petrovic', 'pera', 'omladinska 8', 'pera@gmail.com', '$2a$04$Vbug2lwwJGrvUXTj6z7ff.97IzVBkrJ1XfApfGNl.Z695zqcnPYra','2017-10-01 21:58:58.508-07',true);
INSERT INTO USERS (name, surname, username, adress, email, password, last_password_reset_date, enabled) VALUES ('jovan', 'jovanovic', 'jova', 'omladinska 8', 'jovan@gmail.com', '$2a$04$Vbug2lwwJGrvUXTj6z7ff.97IzVBkrJ1XfApfGNl.Z695zqcnPYra','2017-10-01 21:58:58.508-07',true);
INSERT INTO USERS (name, surname, username, adress, email, password, last_password_reset_date, enabled) VALUES ('milan', 'milanovic', 'miki', 'omladinska 8', 'milan@gmail.com', '$2a$04$Vbug2lwwJGrvUXTj6z7ff.97IzVBkrJ1XfApfGNl.Z695zqcnPYra','2017-10-01 21:58:58.508-07',true);
INSERT INTO USERS (name, surname, username, adress, email, password, last_password_reset_date, enabled) VALUES ('stefan', 'stefanovic', 'stefke', 'omladinska 8', 'stefan@gmail.com', '$2a$04$Vbug2lwwJGrvUXTj6z7ff.97IzVBkrJ1XfApfGNl.Z695zqcnPYra','2017-10-01 21:58:58.508-07',true);

INSERT INTO ROLE (name) VALUES ('ROLE_USER');
INSERT INTO ROLE (name) VALUES ('ROLE_SYSTEM_ADMIN');
INSERT INTO ROLE (name) VALUES ('ROLE_COMPANY_ADMIN');

INSERT INTO ROLES (user_id, role_id) VALUES (1, 1);
INSERT INTO ROLES (user_id, role_id) VALUES (2, 1);
INSERT INTO ROLES (user_id, role_id) VALUES (3, 2);
INSERT INTO ROLES (user_id, role_id) VALUES (4, 2);
INSERT INTO ROLES (user_id, role_id) VALUES (5, 3);

INSERT INTO Post (description, created_at, bunny_image, user_id, likes) VALUES ('objava 1','2017-10-01 21:58:58.508-07','slika1',1, 0);
INSERT INTO Post (description, created_at, bunny_image, user_id, likes) VALUES ('objava 2','2017-10-01 21:58:58.508-07','slika2',2, 0);
INSERT INTO Post (description, created_at, bunny_image, user_id, likes) VALUES ('objava 3','2017-10-01 21:58:58.508-07','slika3',3, 0);
INSERT INTO Post (description, created_at, bunny_image, user_id, likes) VALUES ('objava 4','2017-10-01 21:58:58.508-07','slika4',4, 0);
INSERT INTO Post (description, created_at, bunny_image, user_id, likes) VALUES ('objava 5','2017-10-01 21:58:58.508-07','slika5',5, 0);



