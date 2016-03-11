INSERT INTO app_user values(nextval('SEQ_APP_USER'), 'user', 'us3r', 'User', 'Usereanu', 'userean@sad.com', true);
INSERT INTO app_user values(nextval('SEQ_APP_USER'), 'admin', 'adm1n', 'Admin', 'Admineanu', 'admin@sad.com', true);

INSERT INTO user_role values(nextval('SEQ_USER_ROLE'), 'ROLE_USER', 1);
INSERT INTO user_role values(nextval('SEQ_USER_ROLE'), 'ROLE_ADMIN', 2);
