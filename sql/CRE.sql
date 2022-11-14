CREATE TYPE SESSION_TYPE AS ENUM ('FOCUS', 'MEDITATION');
CREATE TYPE SEX AS ENUM ('MALE', 'FEMALE', 'INTERSEX');
CREATE TYPE UNIT AS ENUM ('C', 'BPM', 'PERCENT', 'DB', 'L');


CREATE TABLE IF NOT EXISTS USERS
(
    user_id    BIGSERIAL PRIMARY KEY,
    "name"     VARCHAR(50) NOT NULL,
    sex        SEX         NOT NULL,
    email      VARCHAR(50) NOT NULL UNIQUE,
    "password" varchar(60)    NOT NULL
);

CREATE TABLE IF NOT EXISTS SESSIONS
(
    session_id   BIGSERIAL PRIMARY KEY,
    user_id      BIGINT      NOT NULL
        REFERENCES USERS (user_id),
    session_type SESSION_TYPE NOT NULL,
    start_time   TIMESTAMP    NOT NULL DEFAULT NOW(),
    end_time     TIMESTAMP             DEFAULT NULL
);

CREATE TABLE IF NOT EXISTS BRAINWAVES
(
    brainwave_id BIGSERIAL PRIMARY KEY,
    signal       INTEGER NOT NULL CHECK (signal >= 0 AND signal <= 200),
    focus     INTEGER NOT NULL CHECK (focus >= 0 AND focus <= 100),
    meditation      INTEGER NOT NULL CHECK (meditation >= 0 AND meditation <= 100)
);

CREATE TABLE IF NOT EXISTS SENSOR_READINGS
(
    sensor_reading_id BIGSERIAL PRIMARY KEY,
    value float4 NOT NULL,
    unit UNIT NOT NULL
);

CREATE TABLE IF NOT EXISTS SENSOR_DATA
(
    sensor_data_id BIGSERIAL PRIMARY KEY,
    heart_rate_id  BIGINT      NOT NULL
        REFERENCES SENSOR_READINGS (sensor_reading_id),
    temperature_id  BIGINT      NOT NULL
        REFERENCES SENSOR_READINGS (sensor_reading_id),
    light_id  BIGINT      NOT NULL
        REFERENCES SENSOR_READINGS (sensor_reading_id),
    sound_id  BIGINT      NOT NULL
        REFERENCES SENSOR_READINGS (sensor_reading_id),
    humidity_id  BIGINT      NOT NULL
        REFERENCES SENSOR_READINGS (sensor_reading_id)

--     CONSTRAINT unique_data (heart_rate_id, temperature_id, light_id, sound_id, humidity_id)




--     heart_rate     INTEGER        NOT NULL CHECK ( heart_rate >= 40 AND heart_rate <= 200 ),
--     temperature    float4 NOT NULL CHECK ( temperature >= 0 AND temperature <= 100 ),
--     light          float4 NOT NULL CHECK ( light >= 0 ),
--     sound          INTEGER        NOT NULL CHECK ( sound >= 0 ),
--     humidity       float4 NOT NULL CHECK ( humidity >= 0 AND humidity <= 100 )
);

CREATE TABLE IF NOT EXISTS READINGS
(
    reading_id     BIGSERIAL PRIMARY KEY,
    session_id     BIGINT   NOT NULL REFERENCES sessions (session_id),
    brainwave_id   BIGINT   NOT NULL REFERENCES brainwaves (brainwave_id),
    sensor_data_id BIGINT   NOT NULL REFERENCES sensor_data (sensor_data_id),
    time_stamp     TIMESTAMP NOT NULL DEFAULT NOW()
);








