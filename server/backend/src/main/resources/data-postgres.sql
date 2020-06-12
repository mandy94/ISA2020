	-- Lozinke su hesovane pomocu BCrypt algoritma https://www.dailycred.com/article/bcrypt-calculator
-- Lozinka za oba user-a je 123

INSERT INTO DIAGNOSES (id, code, name) VALUES (910, 'PP8690', 'Decije boginje');
INSERT INTO DIAGNOSES (id, code, name) VALUES (912, 'PG8795', 'Gnojna angina');
INSERT INTO DIAGNOSES (id, code, name) VALUES (913, 'GM2290', 'Upala uha');
INSERT INTO DIAGNOSES (id, code, name) VALUES (914, 'GM2291', 'Upala grla');
INSERT INTO DIAGNOSES (id, code, name) VALUES (915, 'GP1690', 'Sezonska alergija');
INSERT INTO DIAGNOSES (id, code, name) VALUES (916, 'RB7890', 'Asma');
INSERT INTO DIAGNOSES (id, code, name) VALUES (917, 'RB1120', 'Bronhitis');
INSERT INTO DIAGNOSES (id, code, name) VALUES (918, 'ZQ9601', 'Dijabetes');

INSERT INTO THERAPIES (id, name) VALUES (101 ,'Fiziklana terapija');
INSERT INTO THERAPIES (id, name) VALUES (102 ,'Inhalacija');

INSERT INTO MEDICATIONS (id, name) VALUES (1, 'Penicilin');
INSERT INTO MEDICATIONS (id, name) VALUES (2, 'Nixar');
INSERT INTO MEDICATIONS (id, name) VALUES (3, 'Insulin');
INSERT INTO MEDICATIONS (id, name) VALUES (4, 'Ventolin');

INSERT INTO OPERATIONROOMS (id, name, code) VALUES (101, 'Operaciona sala 1', 'T1');
INSERT INTO OPERATIONROOMS (id, name, code) VALUES (102, 'Operaciona sala 2', 'T2');


INSERT INTO SCHEDULERTIMES ( id, room, start , ending) VALUES (2,101 , '08:00', '10:30');
INSERT INTO SCHEDULERTIMES (id, room, start , ending) VALUES (6,101 , '10:30', '12:30');
INSERT INTO SCHEDULERTIMES (id, room, start , ending) VALUES (9,101 , '13:00', '14:30');
INSERT INTO SCHEDULERTIMES ( id,room, start , ending) VALUES (10,101 , '14:30', '16:00');
INSERT INTO SCHEDULERTIMES ( id,room, start , ending) VALUES (23,102 , '08:15', '10:45');
INSERT INTO SCHEDULERTIMES ( id,room, start , ending) VALUES (24, 102 , '10:15', '12:45');


INSERT INTO APPOINTMENTS (id, start, ending, pacientid, room) VALUES (101, '2020-06-02T13:00', '2020-06-02T16:00', 4, 102);
INSERT INTO APPOINTMENTS (id, start, ending, pacientid, room) VALUES (102, '2020-06-03T10:00', '2020-06-03T10:00', 4, 102);
INSERT INTO APPOINTMENTS (id, start, ending, pacientid, room) VALUES (103, '2020-06-04T09:00', '2020-06-04T09:00', 4, 102);
INSERT INTO APPOINTMENTS (id, start, ending, pacientid, room) VALUES (104, '2020-06-010T08:00', '2020-06-10T08:00', 4, 102);
INSERT INTO APPOINTMENTS (id, start, ending, pacientid, room) VALUES (105, '2020-06-12T13:00', '2020-06-12T16:00', 4, 102);
INSERT INTO APPOINTMENTS (id, start, ending, pacientid, room) VALUES (106, '2020-06-12T16:00', '2020-06-12T18:00', 4, 101);
INSERT INTO APPOINTMENTS (id, start, ending, pacientid, room) VALUES (107, '2020-06-02T13:00', '2020-06-02T16:00', 4, 101);

INSERT INTO USERS (jmbg, role ,username, password, first_name, last_name, email, enabled, last_password_reset_date,birthdate) VALUES (0804298745362, 'DOCTOR',  'dokt', '$2a$04$LHvrD8cHuPEFT4tRjbdYGehDJ9Awgs5CACcNkMj8kKOeXr6HMlRZ2', 'Damir', 'Jeftic', 'user@example.com', true, '2017-10-01 21:58:58.508-07', '10.1.1980');
INSERT INTO USERS (jmbg, role,  username, password, first_name, last_name, email, enabled, last_password_reset_date,birthdate) VALUES (1005906506325, 'NURCE', 'medsis', '$2a$04$iSAH8.efg/0KXf8cwW8YQeyLCyLAyV7C7FRTeJCKa6e1bPXuxeute', 'Jelisaveta', 'Markovic', 'user@example.com', true, '2017-10-01 21:58:58.508-07','27.5.2005');

INSERT INTO USERS (jmbg, role,  username, password, first_name, last_name, email, enabled, last_password_reset_date,birthdate) VALUES (1102026842654, 'PACIENT', 'pacient1', '$2a$04$4fpToZOBmdxk81f0aqSXuOl3GBH43qb713asB.sP2RkSCCAMq3MNq', 'Ivo', 'Loncar', 'iloncarr@example.com', true, '2017-10-01 21:58:58.508-07', '30.11.2007');
INSERT INTO USERS (jmbg, role,  username, password, first_name, last_name, email, enabled, last_password_reset_date,birthdate) VALUES (2304966758967, 'PACIENT', 'pacient2', '$2a$04$4fpToZOBmdxk81f0aqSXuOl3GBH43qb713asB.sP2RkSCCAMq3MNq', 'Milana', 'Jorgic', 'milanaj@example.com', true, '2017-10-01 21:58:58.508-07', '25.12.2005');
INSERT INTO USERS (jmbg, role,  username, password, first_name, last_name, email, enabled, last_password_reset_date,birthdate) VALUES (1510994683002, 'PACIENT', 'pacient3', '$2a$04$4fpToZOBmdxk81f0aqSXuOl3GBH43qb713asB.sP2RkSCCAMq3MNq', 'Sara', 'Kostic', 'kostic12@example.com', true, '2017-10-01 21:58:58.508-07', '10.12.2001');
INSERT INTO USERS (jmbg, role,  username, password, first_name, last_name, email, enabled, last_password_reset_date,birthdate) VALUES (1210982356651, 'PACIENT', 'pacient4', '$2a$04$4fpToZOBmdxk81f0aqSXuOl3GBH43qb713asB.sP2RkSCCAMq3MNq', 'Igor', 'Trbic', 'mackaborbena@example.com', true, '2017-10-01 21:58:58.508-07', '8.7.2003');
INSERT INTO USERS (jmbg, role,  username, password, first_name, last_name, email, enabled, last_password_reset_date,birthdate) VALUES (1907970482220, 'PACIENT', 'pacient5', '$2a$04$4fpToZOBmdxk81f0aqSXuOl3GBH43qb713asB.sP2RkSCCAMq3MNq', 'Sasa', 'Milivojevic', 'kumara	@example.com', true, '2017-10-01 21:58:58.508-07', '11.3.2002');

INSERT INTO USERS (jmbg, role ,username, password, first_name, last_name, email, enabled, last_password_reset_date) VALUES (0504691385551,'ADMIN',  'admin', '$2a$04$vERIUR9ievJ3IV0jfg6VTOMvmPMe3Nep9ZrZ9VEGoE/dAlO1gBzt.', 'Nikola', 'Nikolic', 'admin@example.com', true, '2017-10-01 18:57:58.508-07');

INSERT INTO PRESCRIPTION (id, bar_code, clinic_details, doctor_id, dosage, medicine_name, nurse_id, pacient, signed) values (55, 181057, 'Bul. Jovana Ducica 51', 1, '2 puta dnevno', 'Nixar', '2','5', false);

INSERT INTO AUTHORITY (name) VALUES ('ROLE_DOCTOR');
INSERT INTO AUTHORITY (name) VALUES ('ROLE_NURCE');
INSERT INTO AUTHORITY (name) VALUES ('ROLE_PACIENT');
INSERT INTO AUTHORITY (name) VALUES ('ROLE_ADMIN');

INSERT INTO USER_AUTHORITY (user_id, authority_id) VALUES (1, 1);
INSERT INTO USER_AUTHORITY (user_id, authority_id) VALUES (2, 2);

INSERT INTO USER_AUTHORITY (user_id, authority_id) VALUES (3, 3);
INSERT INTO USER_AUTHORITY (user_id, authority_id) VALUES (4,3);
INSERT INTO USER_AUTHORITY (user_id, authority_id) VALUES (5,3);
INSERT INTO USER_AUTHORITY (user_id, authority_id) VALUES (6,3);
INSERT INTO USER_AUTHORITY (user_id, authority_id) VALUES (7,3);

INSERT INTO USER_AUTHORITY (user_id, authority_id) VALUES (8, 4);
INSERT INTO USER_AUTHORITY (user_id, authority_id) VALUES (8, 3);
INSERT INTO USER_AUTHORITY (user_id, authority_id) VALUES (8, 2);
INSERT INTO USER_AUTHORITY (user_id, authority_id) VALUES (8, 1);
