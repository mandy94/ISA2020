	-- Lozinke su hesovane pomocu BCrypt algoritma https://www.dailycred.com/article/bcrypt-calculator
-- Lozinka za oba user-a je 123


INSERT INTO ALERGENS (name) VALUES('Soja');
INSERT INTO ALERGENS (name) VALUES('Grinje');
INSERT INTO ALERGENS (name) VALUES('Macija dlaka');
INSERT INTO ALERGENS (name) VALUES('Pasija dlaka');
INSERT INTO ALERGENS (name) VALUES('Budj');
INSERT INTO ALERGENS (name) VALUES('Grinje');
INSERT INTO ALERGENS (name) VALUES('Popino prase');
INSERT INTO ALERGENS (name) VALUES('Kalina');
INSERT INTO ALERGENS (name) VALUES('Bokvica');
INSERT INTO ALERGENS (name) VALUES('Kikirirki');
INSERT INTO BLOODTYPE ( type) VALUES ('A+');
INSERT INTO BLOODTYPE ( type) VALUES ('A-');
INSERT INTO BLOODTYPE ( type) VALUES ('B+');
INSERT INTO BLOODTYPE ( type) VALUES ('B-');
INSERT INTO BLOODTYPE ( type) VALUES ('AB+');
INSERT INTO BLOODTYPE ( type) VALUES ('AB-');
INSERT INTO BLOODTYPE ( type) VALUES ('0');

INSERT INTO PACIENTDATA ( height, weight,  blood_type_id) VALUES (180, 80,1);
INSERT INTO PACIENTDATA ( height, weight,  blood_type_id) VALUES (176, 76,1);
INSERT INTO PACIENTDATA ( height, weight,  blood_type_id) VALUES (169, 55,2);
INSERT INTO PACIENTDATA ( height, weight,  blood_type_id) VALUES (184, 70,5);
INSERT INTO PACIENTDATA ( height, weight,  blood_type_id) VALUES (175, 86,4);
INSERT INTO PACIENTDATA ( height, weight,  blood_type_id) VALUES (166, 65,7);


INSERT INTO CLINICROOM (name) VALUES ('Soba za preglede');
-- DOCTORS

INSERT INTO USERS (jmbg, role, username, password, first_name, last_name, email, enabled, status, last_password_reset_date,birthdate, my_clinic_id) VALUES (0804298745362, 'DOCTOR',  'dokt', '$2a$04$LHvrD8cHuPEFT4tRjbdYGehDJ9Awgs5CACcNkMj8kKOeXr6HMlRZ2', 'Damir', 'Jeftic', 'timadust@gmail.com', true,'ACTIVE', '2017-10-01 21:58:58.508-07', '10.1.1980',1);
INSERT INTO USERS (jmbg, role, username, password, first_name, last_name, email, enabled, status, last_password_reset_date,birthdate) VALUES (1804298745362, 'DOCTOR',  'dokt1', '$2a$04$LHvrD8cHuPEFT4tRjbdYGehDJ9Awgs5CACcNkMj8kKOeXr6HMlRZ2', 'Teodor', 'Radasic', 'glavni@gmail.com', true,'ACTIVE', '2017-10-01 21:58:58.508-07', '10.1.1980');
INSERT INTO USERS (jmbg, role, username, password, first_name, last_name, email, enabled, status, last_password_reset_date,birthdate) VALUES (0904298745362, 'DOCTOR',  'dokt2', '$2a$04$LHvrD8cHuPEFT4tRjbdYGehDJ9Awgs5CACcNkMj8kKOeXr6HMlRZ2', 'Emilija', 'Colic', 'netolikoglavni@gmail.com', true,'ACTIVE', '2017-10-01 21:58:58.508-07', '10.1.1981');

-- NURCES
INSERT INTO USERS (jmbg, role, username, password, first_name, last_name, email, enabled, status, last_password_reset_date,birthdate) VALUES (1022006506325, 'NURCE', 'medsis', '$2a$04$iSAH8.efg/0KXf8cwW8YQeyLCyLAyV7C7FRTeJCKa6e1bPXuxeute', 'Jelisaveta', 'Markovic', 'skolarajak.programerska@gmail.com', true, 'ACTIVE', '2017-10-01 21:58:58.508-07','27.5.2005');
INSERT INTO USERS (jmbg, role, username, password, first_name, last_name, email, enabled, status, last_password_reset_date,birthdate) VALUES (1005906506325, 'NURCE', 'medsis2', '$2a04$iSAH8.efg/0KXf8cwW8YQeyLCyLAyV7C7FRTeJCKa6e1bPXuxeute', 'Violeta', 'Ilic', 'ordinacija@gmail.com', true, 'ACTIVE', '2017-10-01 21:58:58.508-07','27.5.1990');

-- PACIENTS
INSERT INTO USERS (jmbg, role, username, password, first_name, last_name, email, enabled, status, last_password_reset_date,birthdate,pacient_data) VALUES (1102026842654, 'PACIENT', 'pacient1', '$2a$04$4fpToZOBmdxk81f0aqSXuOl3GBH43qb713asB.sP2RkSCCAMq3MNq', 'Ivo', 'Loncar', 'majasavic@gmail.com', true, 'ACTIVE','2017-10-01 21:58:58.508-07', '30.11.2007',1);
INSERT INTO USERS (jmbg, role, username, password, first_name, last_name, email, enabled, status, last_password_reset_date,birthdate,pacient_data) VALUES (2304966758967, 'PACIENT', 'pacient2', '$2a$04$4fpToZOBmdxk81f0aqSXuOl3GBH43qb713asB.sP2RkSCCAMq3MNq', 'Milana', 'Jorgic', 'majasavic199@gmail.com', true,'PENDING' ,'2017-10-01 21:58:58.508-07', '25.12.2005',2);
INSERT INTO USERS (jmbg, role, username, password, first_name, last_name, email, enabled, status, last_password_reset_date,birthdate,pacient_data) VALUES (1510994683002, 'PACIENT', 'pacient3', '$2a$04$4fpToZOBmdxk81f0aqSXuOl3GBH43qb713asB.sP2RkSCCAMq3MNq', 'Sara', 'Kostic', 'kostic12@example.com', true, 'PENDING', '2017-10-01 21:58:58.508-07', '10.12.2001',3);
INSERT INTO USERS (jmbg, role, username, password, first_name, last_name, email, enabled, status, last_password_reset_date,birthdate,pacient_data) VALUES (1210982356651, 'PACIENT', 'pacient4', '$2a$04$4fpToZOBmdxk81f0aqSXuOl3GBH43qb713asB.sP2RkSCCAMq3MNq', 'Igor', 'Trbic', 'mackaborbena@example.com', true, 'ACTIVE', '2017-10-01 21:58:58.508-07', '8.7.2003',4);
INSERT INTO USERS (jmbg, role, username, password, first_name, last_name, email, enabled, status, last_password_reset_date,birthdate,pacient_data) VALUES (1907970482220, 'PACIENT', 'pacient5', '$2a$04$4fpToZOBmdxk81f0aqSXuOl3GBH43qb713asB.sP2RkSCCAMq3MNq', 'Sasa', 'Milivojevic', 'majasavic199@gmail.com',true,'ACTIVE' , '2017-10-01 21:58:58.508-07', '11.3.2002',5);
-- ADMIN
INSERT INTO USERS (jmbg, role ,username, password, first_name, last_name, email, enabled, status, last_password_reset_date) VALUES (0504691385551,'ADMIN',  'admin', '$2a$04$vERIUR9ievJ3IV0jfg6VTOMvmPMe3Nep9ZrZ9VEGoE/dAlO1gBzt.', 'Nikola', 'Nikolic', 'admin@example.com', true,'ACTIVE', '2017-10-01 18:57:58.508-07');



INSERT INTO PRESCRIPTION ( bar_code, clinic_details, doctor_id, dosage, medicine_name, nurse_id, pacient, signed) values ( 181057, 'Bul. Jovana Ducica 51', 1, '2 puta dnevno', 'Nixar', '2','5', false);

INSERT INTO AUTHORITY (name) VALUES ('ROLE_DOCTOR');
INSERT INTO AUTHORITY (name) VALUES ('ROLE_NURCE');
INSERT INTO AUTHORITY (name) VALUES ('ROLE_PACIENT');
INSERT INTO AUTHORITY (name) VALUES ('ROLE_ADMIN');


INSERT INTO DIAGNOSES ( code, name) VALUES ('PP8690', 'Decije boginje');
INSERT INTO DIAGNOSES ( code, name) VALUES ( 'PG8795', 'Gnojna angina');
INSERT INTO DIAGNOSES ( code, name) VALUES ( 'GM2290', 'Upala uha');
INSERT INTO DIAGNOSES ( code, name) VALUES ( 'GM2291', 'Upala grla');
INSERT INTO DIAGNOSES ( code, name) VALUES ( 'GP1690', 'Sezonska alergija');
INSERT INTO DIAGNOSES ( code, name) VALUES ( 'RB7890', 'Asma');
INSERT INTO DIAGNOSES ( code, name) VALUES ( 'RB1120', 'Bronhitis');
INSERT INTO DIAGNOSES ( code, name) VALUES ( 'ZQ9601', 'Dijabetes');

INSERT INTO THERAPIES ( name) VALUES ('Fiziklana terapija');
INSERT INTO THERAPIES ( name) VALUES ('Inhalacija');

INSERT INTO MEDICATIONS ( name, dosage) VALUES ('Brufen'   , 'tableta 400mg'    );
INSERT INTO MEDICATIONS ( name, dosage) VALUES ('Brufen'   , 'tableta 600mg'    );
INSERT INTO MEDICATIONS ( name, dosage) VALUES ('Brufen'   , 'tableta 800mg'    );
INSERT INTO MEDICATIONS ( name, dosage) VALUES ('Nixar'    , 'tableta 20mg'     );
INSERT INTO MEDICATIONS ( name, dosage) VALUES ('Insulin'  , 'suspenzija 100mL' );
INSERT INTO MEDICATIONS ( name, dosage) VALUES ('Ventolin' , 'suspenzija100 ug' );

INSERT INTO ROOMS ( name, code, type) VALUES ( 'Operaciona sala 1', 'T1', 'OPERATION');
INSERT INTO ROOMS ( name, code, type) VALUES ( 'Operaciona sala 2', 'T2', 'OPERATION');
INSERT INTO ROOMS ( name, code, type) VALUES ( 'Velika sala', 'MM0', 'OPERATION');
INSERT INTO ROOMS ( name, code, type) VALUES ( 'Mala sala', 'MM1', 'OPERATION');


-- TIME EXAMPLES
INSERT INTO SCHEDULERTIMES (  start , ending) VALUES ( '08:00', '10:30' );--1
INSERT INTO SCHEDULERTIMES ( start , ending ) VALUES ( '08:00', '08:30' );--2
INSERT INTO SCHEDULERTIMES (  start , ending) VALUES ( '08:15', '10:45' );--3
INSERT INTO SCHEDULERTIMES ( start , ending ) VALUES ( '08:30', '09:00' );--4
INSERT INTO SCHEDULERTIMES (  start , ending) VALUES ( '10:30', '12:30' );--5
INSERT INTO SCHEDULERTIMES (  start , ending) VALUES ( '13:00', '14:30' );--6
INSERT INTO SCHEDULERTIMES (  start , ending) VALUES ( '14:30', '16:00' );--7
INSERT INTO SCHEDULERTIMES (  start , ending) VALUES ( '10:15', '12:45' );--8
INSERT INTO SCHEDULERTIMES (  start , ending) VALUES ( '14:15', '16:45' );--9
INSERT INTO SCHEDULERTIMES ( start , ending ) VALUES ( '09:00', '09:30' );--10
INSERT INTO SCHEDULERTIMES ( start , ending ) VALUES ( '09:30', '10:00' );
INSERT INTO SCHEDULERTIMES ( start , ending ) VALUES ( '10:00', '10:30' );
INSERT INTO SCHEDULERTIMES ( start , ending ) VALUES ( '10:30', '11:00' );
INSERT INTO SCHEDULERTIMES ( start , ending ) VALUES ( '11:00', '11:30' );
INSERT INTO SCHEDULERTIMES ( start , ending ) VALUES ( '12:00', '12:30' );
INSERT INTO SCHEDULERTIMES ( start , ending ) VALUES ( '12:30', '13:00' );

-- DOCTORS TIME TABLR
INSERT INTO TIME_TABLE VALUES ( 1 , 2);
INSERT INTO TIME_TABLE VALUES ( 1 , 4);
INSERT INTO TIME_TABLE VALUES( 1 , 11);
INSERT INTO TIME_TABLE VALUES( 2 , 1);
INSERT INTO TIME_TABLE VALUES( 3 , 3);
INSERT INTO TIME_TABLE VALUES( 3 , 6);



-- DOCTORS DEDICATED TO ROOM

INSERT INTO MANDATORY_DOCTORS (room_id, user_id) VALUES(1, 2);
INSERT INTO MANDATORY_DOCTORS (room_id, user_id) VALUES(1, 3);
INSERT INTO MANDATORY_DOCTORS (room_id, user_id) VALUES(2, 3);
INSERT INTO MANDATORY_DOCTORS (room_id, user_id) VALUES(3, 1);

-- APPOINTMENTS AS VISIT - room
INSERT INTO APPOINTMENTS ( date, term, pacientid, doctorid, room) VALUES ( '06.08.2020', 5, 4, 1, 3);
INSERT INTO APPOINTMENTS ( date, term, pacientid, doctorid, room) VALUES ( '06.08.2020', 2, 3, 1, 3);
INSERT INTO APPOINTMENTS ( date, term, pacientid, doctorid, room) VALUES ( '26.08.2020', 2, 3, 1, 3);
INSERT INTO APPOINTMENTS ( date, term, pacientid, doctorid, room) VALUES ( '17.08.2020', 5, 4, 1, 3);


-- APPOINTMETS AS OOPERATIONS - doctor 
INSERT INTO APPOINTMENTS ( date, term, pacientid, room) VALUES ( '06.08.2020',5, 4, 1);
INSERT INTO APPOINTMENTS ( date, term, pacientid, room) VALUES ( '07.08.2020',5, 3, 1);
INSERT INTO APPOINTMENTS ( date, term, pacientid,  room) VALUES ('17.08.2020',13, 2, 1);


INSERT INTO USER_AUTHORITY (user_id, authority_id) VALUES (1, 1);
INSERT INTO USER_AUTHORITY (user_id, authority_id) VALUES (2, 1);
INSERT INTO USER_AUTHORITY (user_id, authority_id) VALUES (3, 1);

INSERT INTO USER_AUTHORITY (user_id, authority_id) VALUES (4,2);
INSERT INTO USER_AUTHORITY (user_id, authority_id) VALUES (5,2);

INSERT INTO USER_AUTHORITY (user_id, authority_id) VALUES (6,3);
INSERT INTO USER_AUTHORITY (user_id, authority_id) VALUES (7,3);
INSERT INTO USER_AUTHORITY (user_id, authority_id) VALUES (8,3);
INSERT INTO USER_AUTHORITY (user_id, authority_id) VALUES (9,3);
INSERT INTO USER_AUTHORITY (user_id, authority_id) VALUES (10,3);

INSERT INTO USER_AUTHORITY (user_id, authority_id) VALUES (11, 4); -- admin