

INSERT INTO _user (id, email, first_name, last_name, password)
VALUES ('02ad1da46a44e93a', 'superadmin@gmail.com', 'Super', 'Admin', '$2a$10$e/mtPhwNlO7O/Lh/npgDpOSJDgjgGFtAiTOphLwf1OcDjM8yTjxCe');

INSERT INTO user_role (user_id, role) VALUES ('02ad1da46a44e93a', 'ADMIN');
INSERT INTO user_role (user_id, role) VALUES ('02ad1da46a44e93a', 'USER');