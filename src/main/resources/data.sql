INSERT INTO users ( email, name, password, gender )
VALUES ( 'se@gmail.com', 'Seif', '$2a$10$w5HLlv2qAv59t1WCPE.5aOwbGvGItDPnnNTZR5EmsT6AYHgicnT1O', 'MALE' );

INSERT INTO users ( email, name, password, gender )
VALUES ( 'p@gmail.com', 'Peter', '$2a$10$w5HLlv2qAv59t1WCPE.5aOwbGvGItDPnnNTZR5EmsT6AYHgicnT1O', 'MALE' );

INSERT INTO users ( email, name, password, gender )
VALUES ( 'f@gmail.com', 'Filip', '$2a$10$w5HLlv2qAv59t1WCPE.5aOwbGvGItDPnnNTZR5EmsT6AYHgicnT1O', 'MALE' );

INSERT INTO users ( email, name, password, gender )
VALUES ( 'e@gmail.com', 'Elina', '$2a$10$w5HLlv2qAv59t1WCPE.5aOwbGvGItDPnnNTZR5EmsT6AYHgicnT1O', 'FEMALE' );

INSERT INTO sessions ( user_id, session_type, start_time, end_time, session_name, satisfaction )
VALUES ( 1, 'FOCUS', '2019-01-01 00:00:00', '2019-01-01 01:10:50', 'Session 1', '1' ),
       ( 1, 'FOCUS', '2019-01-01 00:00:00', '2019-01-01 02:14:55', 'Session 2', '6' ),
       ( 1, 'FOCUS', '2019-01-01 00:00:00', '2019-01-01 01:10:50', 'Session 1', '1' ),
       ( 1, 'FOCUS', '2019-01-01 00:00:00', '2019-01-01 02:14:55', 'Session 2', '6' ),
       ( 1, 'FOCUS', '2019-01-01 00:00:00', '2019-01-01 01:10:50', 'Session 1', '1' ),
       ( 1, 'FOCUS', '2019-01-01 00:00:00', '2019-01-01 02:14:55', 'Session 2', '6' ),
       ( 1, 'FOCUS', '2019-01-01 00:00:00', '2019-01-01 01:10:50', 'Session 1', '1' ),
       ( 1, 'FOCUS', '2019-01-01 00:00:00', '2019-01-01 02:14:55', 'Session 2', '6' ),
       ( 1, 'FOCUS', '2019-01-01 00:00:00', '2019-01-01 01:10:50', 'Session 1', '1' ),
       ( 1, 'FOCUS', '2019-01-01 00:00:00', '2019-01-01 02:14:55', 'Session 2', '6' ),
       ( 1, 'FOCUS', '2019-01-01 00:00:00', '2019-01-01 01:10:50', 'Session 1', '1' ),
       ( 2, 'FOCUS', '2019-01-01 00:00:00', '2019-01-01 02:14:55', 'Session 2', '6' ),
       ( 3, 'FOCUS', '2019-01-01 00:00:00', '2019-01-01 01:10:50', 'Session 1', '1' ),
       ( 4, 'FOCUS', '2019-01-01 00:00:00', '2019-01-01 02:14:55', 'Session 2', '6' );

-- INSERT INTO brainwaves( signal, focus, meditation )
-- VALUES ( 0, 100, 0 );
--
-- INSERT INTO sensor_readings ( sensor_reading_id, value, unit )
-- VALUES ( 1, 23, 'C' ), ( 2, 23, 'C' ), ( 3, 23, 'C' ), ( 4, 23, 'C' ), ( 5, 23, 'C' );
--
-- INSERT INTO sensor_data ( heart_rate_id, temperature_id, light_id, sound_id, humidity_id )
-- VALUES ( 1, 2, 3, 4, 5 );
--
-- INSERT INTO readings ( reading_id, session_id, brainwave_id, sensor_data_id, time_stamp )
-- VALUES ( 1, 1, 1, 1, '2019-01-01 00:00:00' );