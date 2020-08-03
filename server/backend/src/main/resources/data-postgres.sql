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
INSERT INTO USERS (jmbg, role, username, password, first_name, last_name, email, enabled, status, last_password_reset_date,birthdate) VALUES (0804298745362, 'DOCTOR',  'dokt', '$2a$04$LHvrD8cHuPEFT4tRjbdYGehDJ9Awgs5CACcNkMj8kKOeXr6HMlRZ2', 'Damir', 'Jeftic', 'timadust@gmail.com', true,'ACTIVE', '2017-10-01 21:58:58.508-07', '10.1.1980');
INSERT INTO USERS (jmbg, role, username, password, first_name, last_name, email, enabled, status, last_password_reset_date,birthdate) VALUES (1005906506325, 'NURCE', 'medsis', '$2a$04$iSAH8.efg/0KXf8cwW8YQeyLCyLAyV7C7FRTeJCKa6e1bPXuxeute', 'Jelisaveta', 'Markovic', 'skolarajak.programerska@gmail.com', true, 'ACTIVE', '2017-10-01 21:58:58.508-07','27.5.2005');
INSERT INTO USERS (jmbg, role, username, password, first_name, last_name, email, enabled, status, last_password_reset_date,birthdate,pacient_data) VALUES (1102026842654, 'PACIENT', 'pacient1', '$2a$04$4fpToZOBmdxk81f0aqSXuOl3GBH43qb713asB.sP2RkSCCAMq3MNq', 'Ivo', 'Loncar', 'majasavic@gmail.com', true, 'ACTIVE','2017-10-01 21:58:58.508-07', '30.11.2007',1);
INSERT INTO USERS (jmbg, role, username, password, first_name, last_name, email, enabled, status, last_password_reset_date,birthdate,pacient_data) VALUES (2304966758967, 'PACIENT', 'pacient2', '$2a$04$4fpToZOBmdxk81f0aqSXuOl3GBH43qb713asB.sP2RkSCCAMq3MNq', 'Milana', 'Jorgic', 'nesto@example.com', true,'PENDING' ,'2017-10-01 21:58:58.508-07', '25.12.2005',2);
INSERT INTO USERS (jmbg, role, username, password, first_name, last_name, email, enabled, status, last_password_reset_date,birthdate,pacient_data) VALUES (1510994683002, 'PACIENT', 'pacient3', '$2a$04$4fpToZOBmdxk81f0aqSXuOl3GBH43qb713asB.sP2RkSCCAMq3MNq', 'Sara', 'Kostic', 'kostic12@example.com', true, 'PENDING', '2017-10-01 21:58:58.508-07', '10.12.2001',3);
INSERT INTO USERS (jmbg, role, username, password, first_name, last_name, email, enabled, status, last_password_reset_date,birthdate,pacient_data) VALUES (1210982356651, 'PACIENT', 'pacient4', '$2a$04$4fpToZOBmdxk81f0aqSXuOl3GBH43qb713asB.sP2RkSCCAMq3MNq', 'Igor', 'Trbic', 'mackaborbena@example.com', true, 'ACTIVE', '2017-10-01 21:58:58.508-07', '8.7.2003',4);
INSERT INTO USERS (jmbg, role, username, password, first_name, last_name, email, enabled, status, last_password_reset_date,birthdate,pacient_data) VALUES (1907970482220, 'PACIENT', 'pacient5', '$2a$04$4fpToZOBmdxk81f0aqSXuOl3GBH43qb713asB.sP2RkSCCAMq3MNq', 'Sasa', 'Milivojevic', 'majasavic199@gmail.com',true,'ACTIVE' , '2017-10-01 21:58:58.508-07', '11.3.2002',5);
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

INSERT INTO OPERATIONROOMS ( name, code) VALUES ( 'Operaciona sala 1', 'T1');
INSERT INTO OPERATIONROOMS ( name, code) VALUES ( 'Operaciona sala 2', 'T2');

---- EXAMINATION REPORTS DATA ----
INSERT INTO EXAMINATIONREPORTS (date,time, is_doctor, is_examined, details, diagnose) VALUES ('24.06.2020', '08:00', 1 , 6, 'Pacijent se zali na jak bol u grlu', 2);
INSERT INTO EXAMINATIONREPORTS (date,time, is_doctor, is_examined, details,diagnose) VALUES ('03.07.2020', '08:00', 1 , 6, 'Pacijent se zali na sekret, nesanicu i povremeno visoku temperaturu.', 5);
INSERT INTO EXAMINE_MEDICATION(examination_id, medication_id) VALUES( 1, 2); -- prepisan je nixar
INSERT INTO EXAMINATIONREPORTS (date,time,is_doctor, is_examined, details, diagnose)VALUES('27.06.2020', '09:00', 1, 6, 'Pacijent se zali na povisenu temperaturu i tesko disanje',4);
INSERT INTO EXAMINATIONREPORTS (date,time,is_doctor, is_examined, diagnose) VALUES('28.06.2020', '08:30', 1, 4,4);
INSERT INTO EXAMINE_MEDICATION(examination_id, medication_id) VALUES( 3, 3); -- 
INSERT INTO EXAMINE_MEDICATION(examination_id, medication_id) VALUES( 3, 4); -- prepisan je insulin i ventolin

-- TIME FOR AVAILABLE OPERATION ROOM

INSERT INTO SCHEDULERTIMES ( room, start , ending, type) VALUES (1 , '08:00', '10:30', 'OPERATION');
INSERT INTO SCHEDULERTIMES ( room, start , ending, type) VALUES (1 , '10:30', '12:30','OPERATION');
INSERT INTO SCHEDULERTIMES ( room, start , ending, type) VALUES (1 , '13:00', '14:30','OPERATION');
INSERT INTO SCHEDULERTIMES (room, start , ending, type) VALUES (1 , '14:30', '16:00','OPERATION');

INSERT INTO SCHEDULERTIMES (room, start , ending) VALUES (2 , '08:15', '10:45');
INSERT INTO SCHEDULERTIMES (room, start , ending) VALUES (2 , '10:15', '12:45');
INSERT INTO SCHEDULERTIMES (room, start , ending) VALUES (2 , '14:15', '16:45');

-- TIME FOR VISITING DOCTOR
INSERT INTO SCHEDULERTIMES (room, start , ending, type) VALUES (1 , '08:00', '08:30','VISIT');
INSERT INTO SCHEDULERTIMES (room, start , ending, type) VALUES (1 , '08:30', '09:00','VISIT');
INSERT INTO SCHEDULERTIMES (room, start , ending, type) VALUES (1 , '09:00', '09:30','VISIT');
INSERT INTO SCHEDULERTIMES (room, start , ending, type) VALUES (1 , '09:30', '10:00','VISIT');
INSERT INTO SCHEDULERTIMES (room, start , ending, type) VALUES (1 , '11:00', '11:30','VISIT');
INSERT INTO SCHEDULERTIMES (room, start , ending, type) VALUES (1 , '12:00', '12:30','VISIT');
INSERT INTO SCHEDULERTIMES (room, start , ending, type) VALUES (1 , '12:30', '13:00','VISIT');

-- APPOINTMENTS AS VISIT - room is null
INSERT INTO APPOINTMENTS ( date, start, ending, pacientid, doctorid ) VALUES ( '2020-06-14','10:30', '12:30', 4, 1);
INSERT INTO APPOINTMENTS ( date, start, ending, pacientid,  doctorid ) VALUES ( '2020-06-13','08:00', '10:30', 3, 1);

-- APPOINTMETS AS OOPERATIONS - doctor id is null
INSERT INTO APPOINTMENTS ( date, start, ending, pacientid, room) VALUES ( '2020-06-02','10:30', '12:30', 4, 1);
INSERT INTO APPOINTMENTS ( date, start, ending, pacientid, room) VALUES ( '2020-06-03','08:00', '10:30', 3, 1);



INSERT INTO MANDATORY_DOCTORS (room_id, user_id) VALUES( 1, 1);
INSERT INTO MANDATORY_DOCTORS (room_id, user_id) VALUES( 1, 2);

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