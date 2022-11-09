CREATE TYPE SESSION_TYPE AS ENUM ('FOCUS', 'MEDITATION');
CREATE TYPE SEX AS ENUM ('MALE', 'FEMALE', 'INTERSEX');


CREATE TABLE USERS
(
    user_id    SERIAL PRIMARY KEY,
    "name"     VARCHAR(50) NOT NULL,
    sex        SEX         NOT NULL,
    email      VARCHAR(50) NOT NULL UNIQUE,
    "password" CHAR(64)    NOT NULL
);

CREATE TABLE SESSIONS
(
    session_id   SERIAL PRIMARY KEY,
    user_id      INTEGER      NOT NULL
        REFERENCES USERS (user_id),
    session_type SESSION_TYPE NOT NULL,
    start_time   TIMESTAMP    NOT NULL DEFAULT NOW(),
    end_time     TIMESTAMP             DEFAULT NULL
);

CREATE TABLE BRAINWAVES
(
    brainwave_id SERIAL PRIMARY KEY,
    signal       INT NOT NULL CHECK (signal >= 0 AND signal <= 200),
    "level"      INT NOT NULL CHECK ("level" >= 0 AND "level" <= 100)
);

CREATE TABLE SENSORDATA
(
    sensor_data_id SERIAL PRIMARY KEY,
    heart_rate     INT        NOT NULL CHECK ( heart_rate >= 40 AND heart_rate <= 200 ),
    temperature    NUMERIC(1) NOT NULL CHECK ( temperature >= 0 AND temperature <= 100 ),
    light          NUMERIC(1) NOT NULL CHECK ( light >= 0 ),
    sound          INT        NOT NULL CHECK ( sound >= 0 ),
    humidity       NUMERIC(1) NOT NULL CHECK ( humidity >= 0 AND humidity <= 100 )
);

CREATE TABLE READINGS
(
    reading_id     SERIAL PRIMARY KEY,
    session_id     INTEGER   NOT NULL REFERENCES sessions (session_id),
    brainwave_id   INTEGER   NOT NULL REFERENCES brainwaves (brainwave_id),
    sensor_data_id INTEGER   NOT NULL REFERENCES sensordata (sensor_data_id),
    time_stamp     TIMESTAMP NOT NULL DEFAULT NOW()
);






