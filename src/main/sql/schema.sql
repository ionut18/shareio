
--TABLE CREATION
CREATE TABLE app_user (
	id_app_user bigint NOT NULL,
	username character varying(255),
	password character varying(255),
	first_name character varying(255),
	last_name character varying(255),
	email character varying(255),
	enabled boolean,
	telephone character varying(255),
	id_user_role bigint NOT NULL
);

CREATE TABLE user_role (
	id_user_role bigint NOT NULL,
	role character varying(255)
);

CREATE TABLE ride (
	id_ride bigint NOT NULL,
	departure character varying(255),
	destination character varying(255),
	departure_time timestamp,
	price smallint,
	distance smallint,
	available_seats smallint,
	available_instead_storage character varying(255)
);

CREATE TABLE car (
	id_car bigint NOT NULL,
	manufacturer character varying(255),
	model character varying(255),
	comfort character varying(255),
	id_app_user bigint NOT NULL
);

CREATE TABLE driver (
	id_driver bigint NOT NULL,
	id_app_user bigint NOT NULL,
	id_car bigint NOT NULL,
	id_ride bigint NOT NULL,
	rating character varying(255)
);

CREATE TABLE passenger (
	id_passenger bigint NOT NULL,
	id_app_user bigint NOT NULL,
	id_ride bigint NOT NULL
);

CREATE TABLE message (
	id_message bigint NOT NULL,
	id_ride bigint NOT NULL,
	driver character varying(255),
	passenger character varying(255),
	sender character varying(255),
	receiver character varying(255),
	text character varying(255),
	date timestamp,
	done boolean
);


--SEQUENCE CREATION
CREATE SEQUENCE seq_app_user START WITH 1 INCREMENT BY 1 NO MINVALUE NO MAXVALUE CACHE 1;

CREATE SEQUENCE seq_user_role START WITH 1 INCREMENT BY 1 NO MINVALUE NO MAXVALUE CACHE 1;

CREATE SEQUENCE seq_ride START WITH 1 INCREMENT BY 1 NO MINVALUE NO MAXVALUE CACHE 1;

CREATE SEQUENCE seq_car START WITH 1 INCREMENT BY 1 NO MINVALUE NO MAXVALUE CACHE 1;

CREATE SEQUENCE seq_driver START WITH 1 INCREMENT BY 1 NO MINVALUE NO MAXVALUE CACHE 1;

CREATE SEQUENCE seq_passenger START WITH 1 INCREMENT BY 1 NO MINVALUE NO MAXVALUE CACHE 1;

CREATE SEQUENCE seq_message START WITH 1 INCREMENT BY 1 NO MINVALUE NO MAXVALUE CACHE 1;

--CONSTRAINTS
ALTER TABLE ONLY app_user
	ADD CONSTRAINT app_user_pkey PRIMARY KEY(id_app_user);

ALTER TABLE ONLY user_role
	ADD CONSTRAINT user_role_pkey PRIMARY KEY(id_user_role);

ALTER TABLE ONLY ride
	ADD CONSTRAINT ride_pkey PRIMARY KEY(id_ride);

ALTER TABLE ONLY car
	ADD CONSTRAINT car_pkey PRIMARY KEY(id_car);

ALTER TABLE ONLY driver
	ADD CONSTRAINT driver_pkey PRIMARY KEY(id_driver);

ALTER TABLE ONLY passenger
	ADD CONSTRAINT passenger_pkey PRIMARY KEY(id_passenger);

ALTER TABLE ONLY message
	ADD CONSTRAINT message_pkey PRIMARY KEY(id_message);

ALTER TABLE ONLY app_user
	ADD CONSTRAINT user_role_fk FOREIGN KEY (id_user_role) REFERENCES user_role(id_user_role);

ALTER TABLE ONLY driver
	ADD CONSTRAINT car_fk FOREIGN KEY (id_car) REFERENCES car(id_car);

ALTER TABLE ONLY driver
	ADD CONSTRAINT ride_driver_fk FOREIGN KEY (id_ride) REFERENCES ride(id_ride);

ALTER TABLE ONLY driver
	ADD CONSTRAINT driver_user_fk FOREIGN KEY (id_app_user) REFERENCES app_user(id_app_user);

ALTER TABLE ONLY passenger
	ADD CONSTRAINT ride_passenger_fk FOREIGN KEY (id_ride) REFERENCES ride(id_ride);

ALTER TABLE ONLY passenger
	ADD CONSTRAINT passenger_user_fk FOREIGN KEY (id_app_user) REFERENCES app_user(id_app_user);

ALTER TABLE ONLY car
	ADD CONSTRAINT car_user_fk FOREIGN KEY (id_app_user) REFERENCES app_user(id_app_user);

ALTER TABLE ONLY message
	ADD CONSTRAINT message_ride_fk FOREIGN KEY (id_ride) REFERENCES ride(id_ride);
