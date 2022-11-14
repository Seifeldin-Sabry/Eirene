DROP TABLE IF EXISTS brainwaves CASCADE;
DROP TABLE IF EXISTS readings CASCADE;
DROP TABLE IF EXISTS sensor_data CASCADE;
DROP TABLE IF EXISTS sessions CASCADE;
DROP TABLE IF EXISTS users CASCADE;
DROP TABLE IF EXISTS sensor_readings CASCADE;
DROP TYPE IF EXISTS sex CASCADE;
DROP TYPE IF EXISTS session_type CASCADE;
DROP TYPE IF EXISTS unit CASCADE;

CREATE TYPE SESSION_TYPE AS ENUM ('FOCUS', 'MEDITATION');
CREATE TYPE SEX AS ENUM ('MALE', 'FEMALE', 'INTERSEX');
CREATE TYPE UNIT AS ENUM ('C', 'BPM', 'PERCENT', 'DB', 'L');


CREATE TABLE IF NOT EXISTS USERS
(
    user_id    BIGSERIAL PRIMARY KEY,
    "name"     VARCHAR(50) NOT NULL,
    sex        SEX         NOT NULL,
    email      VARCHAR(50) NOT NULL UNIQUE,
    "password" VARCHAR(60) NOT NULL
);

CREATE TABLE IF NOT EXISTS SESSIONS
(
    session_id   BIGSERIAL PRIMARY KEY,
    user_id      BIGINT       NOT NULL
        REFERENCES USERS (user_id),
    session_type SESSION_TYPE NOT NULL,
    start_time   TIMESTAMP    NOT NULL DEFAULT NOW(),
    end_time     TIMESTAMP             DEFAULT NULL
);

CREATE TABLE IF NOT EXISTS BRAINWAVES
(
    brainwave_id BIGSERIAL PRIMARY KEY,
    signal       INTEGER NOT NULL CHECK (signal >= 0 AND signal <= 200),
    focus        INTEGER NOT NULL CHECK (focus >= 0 AND focus <= 100),
    meditation   INTEGER NOT NULL CHECK (meditation >= 0 AND meditation <= 100)
);

CREATE TABLE IF NOT EXISTS SENSOR_READINGS
(
    sensor_reading_id BIGSERIAL PRIMARY KEY,
    value             float4 NOT NULL,
    unit              UNIT   NOT NULL
);

CREATE TABLE IF NOT EXISTS SENSOR_DATA
(
    sensor_data_id BIGSERIAL PRIMARY KEY,
    heart_rate_id  BIGINT NOT NULL
        REFERENCES SENSOR_READINGS (sensor_reading_id),
    temperature_id BIGINT NOT NULL
        REFERENCES SENSOR_READINGS (sensor_reading_id),
    light_id       BIGINT NOT NULL
        REFERENCES SENSOR_READINGS (sensor_reading_id),
    sound_id       BIGINT NOT NULL
        REFERENCES SENSOR_READINGS (sensor_reading_id),
    humidity_id    BIGINT NOT NULL
        REFERENCES SENSOR_READINGS (sensor_reading_id)
);

CREATE TABLE IF NOT EXISTS READINGS
(
    reading_id     BIGSERIAL PRIMARY KEY,
    session_id     BIGINT    NOT NULL REFERENCES sessions (session_id),
    brainwave_id   BIGINT    NOT NULL REFERENCES brainwaves (brainwave_id),
    sensor_data_id BIGINT    NOT NULL REFERENCES sensor_data (sensor_data_id),
    time_stamp     TIMESTAMP NOT NULL DEFAULT NOW()
);








