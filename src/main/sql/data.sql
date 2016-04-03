INSERT INTO app_user values(nextval('SEQ_APP_USER'), 'user', 'us3r', 'User', 'Usereanu', 'userean@sad.com', true, '07567523017');
INSERT INTO app_user values(nextval('SEQ_APP_USER'), 'admin', 'adm1n', 'Admin', 'Admineanu', 'admin@sad.com', true '07567537510');
INSERT INTO app_user values(nextval('SEQ_APP_USER'), 'moderator', 'mod3rator', 'Moderator', 'Moderatoreanu', 'modi@sad.com', true, '07567596720');
INSERT INTO app_user values(nextval('SEQ_APP_USER'), 'driver', 'driv3r', 'Driver', 'Drivereanu', 'driver@sad.com', true, '07567595021');
INSERT INTO app_user values(nextval('SEQ_APP_USER'), 'client', 'cli3nt', 'Client', 'Clienteanu', 'client@sad.com', true,'07567512458');

INSERT INTO user_role values(nextval('SEQ_USER_ROLE'), 'ROLE_CLIENT', 1);
INSERT INTO user_role values(nextval('SEQ_USER_ROLE'), 'ROLE_ADMIN', 2);
INSERT INTO user_role values(nextval('SEQ_USER_ROLE'), 'ROLE_MODERATOR', 3);
INSERT INTO user_role values(nextval('SEQ_USER_ROLE'), 'ROLE_CLIENT', 4);
INSERT INTO user_role values(nextval('SEQ_USER_ROLE'), 'ROLE_CLIENT', 5);
