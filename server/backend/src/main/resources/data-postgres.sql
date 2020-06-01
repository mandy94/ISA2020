-- Lozinke su hesovane pomocu BCrypt algoritma https://www.dailycred.com/article/bcrypt-calculator
-- Lozinka za oba user-a je 123

INSERT INTO DIAGNOSES (id, code, name) VALUES (1, 'PP8690', 'Decije boginje');
INSERT INTO DIAGNOSES (id, code, name) VALUES (2, 'PG8795', 'Gnojna angina');
INSERT INTO DIAGNOSES (id, code, name) VALUES (3, 'GM2290', 'Upala uha');
INSERT INTO DIAGNOSES (id, code, name) VALUES (4, 'GM2291', 'Upala grla');
INSERT INTO DIAGNOSES (id, code, name) VALUES (5, 'GP1690', 'Sezonska alergija');
INSERT INTO DIAGNOSES (id, code, name) VALUES (6, 'RB7890', 'Asma');
INSERT INTO DIAGNOSES (id, code, name) VALUES (7, 'RB1120', 'Bronhitis');
INSERT INTO DIAGNOSES (id, code, name) VALUES (8, 'ZQ9601', 'Dijabetes');


INSERT INTO USERS (username, password, first_name, last_name, email, enabled, last_password_reset_date) VALUES ('dokt', '$2a$04$LHvrD8cHuPEFT4tRjbdYGehDJ9Awgs5CACcNkMj8kKOeXr6HMlRZ2', 'Damir', 'Jeftic', 'user@example.com', true, '2017-10-01 21:58:58.508-07');
INSERT INTO USERS (username, password, first_name, last_name, email, enabled, last_password_reset_date) VALUES ('medsis', '$2a$04$iSAH8.efg/0KXf8cwW8YQeyLCyLAyV7C7FRTeJCKa6e1bPXuxeute', 'Jelisaveta', 'Markovic', 'user@example.com', true, '2017-10-01 21:58:58.508-07');
INSERT INTO USERS (username, password, first_name, last_name, email, enabled, last_password_reset_date) VALUES ('pacient', '$2a$04$4fpToZOBmdxk81f0aqSXuOl3GBH43qb713asB.sP2RkSCCAMq3MNq', 'Ivo', 'Loncar', 'user@example.com', true, '2017-10-01 21:58:58.508-07');
INSERT INTO USERS (username, password, first_name, last_name, email, enabled, last_password_reset_date) VALUES ('admin', '$2a$04$vERIUR9ievJ3IV0jfg6VTOMvmPMe3Nep9ZrZ9VEGoE/dAlO1gBzt.', 'Nikola', 'Nikolic', 'admin@example.com', true, '2017-10-01 18:57:58.508-07');

INSERT INTO AUTHORITY (name) VALUES ('ROLE_DOCTOR');
INSERT INTO AUTHORITY (name) VALUES ('ROLE_MED_SISTER');
INSERT INTO AUTHORITY (name) VALUES ('ROLE_USER');
INSERT INTO AUTHORITY (name) VALUES ('ROLE_ADMIN');

INSERT INTO USER_AUTHORITY (user_id, authority_id) VALUES (1, 1);
INSERT INTO USER_AUTHORITY (user_id, authority_id) VALUES (2, 1);
INSERT INTO USER_AUTHORITY (user_id, authority_id) VALUES (2, 2);
INSERT INTO USER_AUTHORITY (user_id, authority_id) VALUES (4, 4);
INSERT INTO USER_AUTHORITY (user_id, authority_id) VALUES (4, 3);
INSERT INTO USER_AUTHORITY (user_id, authority_id) VALUES (4, 2);
INSERT INTO USER_AUTHORITY (user_id, authority_id) VALUES (4, 1);
