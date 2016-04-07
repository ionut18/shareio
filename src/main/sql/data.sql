INSERT INTO app_user values(nextval('SEQ_APP_USER'), 'user', 'us3r', 'User', 'Usereanu', 'userean@sad.com', true, '07567523017', 1);
INSERT INTO app_user values(nextval('SEQ_APP_USER'), 'admin', 'adm1n', 'Admin', 'Admineanu', 'admin@sad.com', true '07567537510', 2);
INSERT INTO app_user values(nextval('SEQ_APP_USER'), 'moderator', 'mod3rator', 'Moderator', 'Moderatoreanu', 'modi@sad.com', true, '07567596720', 3);

INSERT INTO user_role values(nextval('SEQ_USER_ROLE'), 'ROLE_CLIENT', 1);
INSERT INTO user_role values(nextval('SEQ_USER_ROLE'), 'ROLE_ADMIN', 2);
INSERT INTO user_role values(nextval('SEQ_USER_ROLE'), 'ROLE_MODERATOR', 3);
