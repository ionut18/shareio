
--TABLE CREATION
CREATE TABLE app_user (
	id_app_user bigint NOT NULL,
	username character varying(255),
	password character varying(255),
	first_name character varying(255),
	last_name character varying(255),
	email character varying(255),
	enabled boolean
);

CREATE TABLE user_role (
	id_user_role bigint NOT NULL,
	role character varying(255),
	id_app_user bigint
);

--SEQUENCE CREATION
CREATE SEQUENCE seq_app_user START WITH 1 INCREMENT BY 1 NO MINVALUE NO MAXVALUE CACHE 1;

CREATE SEQUENCE seq_user_role START WITH 1 INCREMENT BY 1 NO MINVALUE NO MAXVALUE CACHE 1;

--CONSTRAINTS
ALTER TABLE ONLY app_user
	ADD CONSTRAINT app_user_pkey PRIMARY KEY(id_app_user);

ALTER TABLE ONLY user_role
	ADD CONSTRAINT user_role_pkey PRIMARY KEY(id_user_role);

ALTER TABLE ONLY user_role
	ADD CONSTRAINT user_fk FOREIGN KEY (id_app_user) REFERENCES app_user(id_app_user);


--ALTER
ALTER TABLE user_role
	ADD COLUMN username character varying(255);