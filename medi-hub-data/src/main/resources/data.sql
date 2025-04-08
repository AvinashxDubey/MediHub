-- Insert user dummy data with roles
INSERT INTO "user" (name, username, password, role) VALUES ('Admin User', 'admin', 'admin123', 'ADMIN');
INSERT INTO "user" (name, username, password, role) VALUES ('Nurse One', 'nurse1', 'nurse123', 'STAFF');
INSERT INTO "user" (name, username, password, role) VALUES ('Doctor One', 'doctor1', 'doctor123', 'STAFF');
INSERT INTO "user" (name, username, password, role) VALUES ('Patient One', 'patient1', 'patient123', 'PATIENT');


-- Insert bed dummy data
INSERT INTO bed (occupied, ward) VALUES (FALSE, 'A');
INSERT INTO bed (occupied, ward) VALUES (TRUE, 'A');
INSERT INTO bed (occupied, ward) VALUES (FALSE, 'ICU');
