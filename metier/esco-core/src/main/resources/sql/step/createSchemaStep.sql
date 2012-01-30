CREATE SCHEMA STEP;
SET SCHEMA STEP;

DROP TABLE STEP.currentmoving;
CREATE TABLE STEP.currentmoving (
	id_moving BIGINT NOT NULL GENERATED ALWAYS AS IDENTITY,
	id_person VARCHAR(255) NOT NULL,
	current_step VARCHAR(255) NOT NULL,
	PRIMARY KEY (id_moving));