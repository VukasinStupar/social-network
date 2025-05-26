INSERT INTO USERS (name, surname, username, adress, email, password, last_password_reset_date, enabled, last_login, number_of_posts, number_of_followers, number_of_followees, activation_token) VALUES ('Marko', 'Markovic', 'mare', 'Omladinska 8', 'marko@gmail.com', '$2a$04$Vbug2lwwJGrvUXTj6z7ff.97IzVBkrJ1XfApfGNl.Z695zqcnPYra', '2017-10-01 21:58:58.508-07', true, null, 1, 0, 0, 'abc');
INSERT INTO USERS (name, surname, username, adress, email, password, last_password_reset_date, enabled, last_login, number_of_posts, number_of_followers, number_of_followees, activation_token) VALUES ('petar', 'petrovic', 'pera', 'omladinska 8', 'pera@gmail.com', '$2a$04$Vbug2lwwJGrvUXTj6z7ff.97IzVBkrJ1XfApfGNl.Z695zqcnPYra','2017-10-01 21:58:58.508-07',true, null,1, 0, 0, 'abc1');
INSERT INTO USERS (name, surname, username, adress, email, password, last_password_reset_date, enabled, last_login, number_of_posts, number_of_followers, number_of_followees, activation_token) VALUES ('jovan', 'jovanovic', 'jova', 'omladinska 8', 'jovan@gmail.com', '$2a$04$Vbug2lwwJGrvUXTj6z7ff.97IzVBkrJ1XfApfGNl.Z695zqcnPYra','2017-10-01 21:58:58.508-07',true, null,1, 0, 0, 'abc2');
INSERT INTO USERS (name, surname, username, adress, email, password, last_password_reset_date, enabled, last_login, number_of_posts, number_of_followers, number_of_followees, activation_token) VALUES ('milan', 'milanovic', 'miki', 'omladinska 8', 'milan@gmail.com', '$2a$04$Vbug2lwwJGrvUXTj6z7ff.97IzVBkrJ1XfApfGNl.Z695zqcnPYra','2017-10-01 21:58:58.508-07',true, null,1, 0, 0, 'abc3');
INSERT INTO USERS (name, surname, username, adress, email, password, last_password_reset_date, enabled, last_login, number_of_posts, number_of_followers, number_of_followees, activation_token) VALUES ('stefan', 'stefanovic', 'stefke', 'omladinska 8', 'stefan@gmail.com', '$2a$04$Vbug2lwwJGrvUXTj6z7ff.97IzVBkrJ1XfApfGNl.Z695zqcnPYra','2017-10-01 21:58:58.508-07',true,null, 1, 0, 0, 'abc4');

INSERT INTO ROLE (name) VALUES ('ROLE_USER');
INSERT INTO ROLE (name) VALUES ('ROLE_ADMIN');

INSERT INTO ROLES (user_id, role_id) VALUES (1, 1);
INSERT INTO ROLES (user_id, role_id) VALUES (2, 1);
INSERT INTO ROLES (user_id, role_id) VALUES (3, 2);
INSERT INTO ROLES (user_id, role_id) VALUES (4, 2);
INSERT INTO ROLES (user_id, role_id) VALUES (5, 1);

INSERT INTO Post (description, created_at, bunny_image, user_id, likes) VALUES ('objava 1','2017-10-01 21:58','slika1',1, 0);
INSERT INTO Post (description, created_at, bunny_image, user_id, likes) VALUES ('objava 2','2017-10-01 21:58','slika2',2, 0);
INSERT INTO Post (description, created_at, bunny_image, user_id, likes) VALUES ('objava 3','2017-10-01 21:58','slika3',3, 0);
INSERT INTO Post (description, created_at, bunny_image, user_id, likes) VALUES ('objava 4','2017-10-01 21:58','slika4',4, 0);
INSERT INTO Post (description, created_at, bunny_image, user_id, likes) VALUES ('objava 5','2017-10-01 21:58','slika5',5, 0);

INSERT INTO Message (sender_id, recipient_id, text, send_time) VALUES (1,2,'test1','2017-10-01 21:58:58.508-07');
INSERT INTO Message (sender_id, recipient_id, text, send_time) VALUES (1,2,'test2','2017-10-01 21:58:58.508-07');
INSERT INTO Message (sender_id, recipient_id, text, send_time) VALUES (2,1,'test3','2017-10-01 21:58:58.508-07');
INSERT INTO Message (sender_id, recipient_id, text, send_time) VALUES (3,1,'test4','2017-10-01 21:58:58.508-07');
INSERT INTO Message (sender_id, recipient_id, text, send_time) VALUES (3,2,'test5','2017-10-01 21:58:58.508-07');
INSERT INTO Message (sender_id, recipient_id, text, send_time) VALUES (1,2,'test6','2017-10-01 21:58:58.508-07');
INSERT INTO Message (sender_id, recipient_id, text, send_time) VALUES (3,1,'test7','2017-10-01 21:58:58.508-07');
INSERT INTO Message (sender_id, recipient_id, text, send_time) VALUES (2,1,'test8','2017-10-01 21:58:58.508-07');
INSERT INTO Message (sender_id, recipient_id, text, send_time) VALUES (2,3,'test9','2017-10-01 21:58:58.508-07');
INSERT INTO Message (sender_id, recipient_id, text, send_time) VALUES (3,2,'test10','2017-10-01 21:58:58.508-07');

INSERT INTO GROUP_CHAT (admin_id, name, create_time) VALUES (1, 'Group1', '2024-04-13 15:10:00');
INSERT INTO GROUP_CHAT (admin_id, name, create_time) VALUES (1, 'Group2', '2024-05-13 15:10:00');
INSERT INTO GROUP_CHAT (admin_id, name, create_time) VALUES (1, 'Group3', '2024-06-13 15:10:00');
INSERT INTO GROUP_CHAT (admin_id, name, create_time) VALUES (1, 'Group4', '2024-07-13 15:10:00');

INSERT INTO group_user (user_id, group_chat_id, added_at) VALUES (1, 1, '2024-04-13 15:15:00');
INSERT INTO group_user (user_id, group_chat_id, added_at) VALUES (1, 2, '2024-04-13 15:15:00');
INSERT INTO group_user (user_id, group_chat_id, added_at) VALUES (1, 3, '2024-04-13 15:15:00');
INSERT INTO group_user (user_id, group_chat_id, added_at) VALUES (1, 4, '2024-04-13 15:15:00');

INSERT INTO group_user (user_id, group_chat_id, added_at) VALUES (2, 1, '2024-04-13 15:20:00');
INSERT INTO group_user (user_id, group_chat_id, added_at) VALUES (3, 1, '2024-04-13 15:25:00');

INSERT INTO group_message (group_chat_id, sender_id, text, send_time) VALUES (1, 1, 'Hello, this is Mare!', '2024-04-13 15:25:00');
INSERT INTO group_message (group_chat_id, sender_id, text, send_time) VALUES (1, 2, 'Hello, this is Pera!', '2024-04-13 15:25:00');