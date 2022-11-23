DROP TABLE IF EXISTS brainwaves CASCADE;
DROP TABLE IF EXISTS readings CASCADE;
DROP TABLE IF EXISTS sensor_data CASCADE;
DROP TABLE IF EXISTS sessions CASCADE;
DROP TABLE IF EXISTS users CASCADE;
DROP TABLE IF EXISTS sensor_readings CASCADE;
DROP TYPE IF EXISTS GENDER CASCADE;
DROP TYPE IF EXISTS SESSION_TYPE CASCADE;
DROP TYPE IF EXISTS UNIT CASCADE;

CREATE TYPE SESSION_TYPE AS ENUM ('FOCUS', 'MEDITATION');
CREATE TYPE GENDER AS ENUM ('MALE', 'FEMALE', 'OTHER');
CREATE TYPE UNIT AS ENUM ('C', 'BPM', 'PERCENT', 'DB', 'L');


CREATE TABLE IF NOT EXISTS users
(
    user_id    BIGSERIAL
        PRIMARY KEY,
    "name"     VARCHAR(50) NOT NULL,
    gender     GENDER      NOT NULL,
    email      VARCHAR(50) NOT NULL
        UNIQUE,
    "password" VARCHAR(60) NOT NULL
);

CREATE TABLE IF NOT EXISTS sessions
(
    session_id   BIGSERIAL
        PRIMARY KEY,
    user_id      BIGINT       NOT NULL
        REFERENCES users ( user_id ),
    session_name VARCHAR,
    satisfaction INT
        CONSTRAINT satisfaction_check
            CHECK ( satisfaction >= 0 AND satisfaction <= 10 ),
    session_type SESSION_TYPE NOT NULL,
    start_time   TIMESTAMP    NOT NULL DEFAULT NOW( ),
    end_time     TIMESTAMP             DEFAULT NULL
);

CREATE TABLE IF NOT EXISTS brainwaves
(
    brainwave_id BIGSERIAL
        PRIMARY KEY,
    signal       INTEGER NOT NULL
        CHECK (signal >= 0 AND signal <= 200),
    focus        INTEGER NOT NULL
        CHECK (focus >= 0 AND focus <= 100),
    meditation   INTEGER NOT NULL
        CHECK (meditation >= 0 AND meditation <= 100)
);

CREATE TABLE IF NOT EXISTS sensor_readings
(
    sensor_reading_id BIGSERIAL
        PRIMARY KEY,
    value             FLOAT4 NOT NULL,
    unit              UNIT   NOT NULL
);

CREATE TABLE IF NOT EXISTS sensor_data
(
    sensor_data_id BIGSERIAL
        PRIMARY KEY,
    heart_rate_id  BIGINT NOT NULL
        REFERENCES sensor_readings ( sensor_reading_id ),
    temperature_id BIGINT NOT NULL
        REFERENCES sensor_readings ( sensor_reading_id ),
    light_id       BIGINT NOT NULL
        REFERENCES sensor_readings ( sensor_reading_id ),
    sound_id       BIGINT NOT NULL
        REFERENCES sensor_readings ( sensor_reading_id ),
    humidity_id    BIGINT NOT NULL
        REFERENCES sensor_readings ( sensor_reading_id )
);

CREATE TABLE IF NOT EXISTS readings
(
    reading_id     BIGSERIAL
        PRIMARY KEY,
    session_id     BIGINT    NOT NULL
        REFERENCES sessions ( session_id ),
    brainwave_id   BIGINT    NOT NULL
        REFERENCES brainwaves ( brainwave_id ),
    sensor_data_id BIGINT    NOT NULL
        REFERENCES sensor_data ( sensor_data_id ),
    time_stamp     TIMESTAMP NOT NULL DEFAULT NOW( )
);
