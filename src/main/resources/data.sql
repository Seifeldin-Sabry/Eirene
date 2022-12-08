INSERT INTO users ( email, name, password, gender )
VALUES ( 'se@gmail.com', 'Seif', '$2a$10$w5HLlv2qAv59t1WCPE.5aOwbGvGItDPnnNTZR5EmsT6AYHgicnT1O', 'MALE' );

INSERT INTO users ( email, name, password, gender )
VALUES ( 'p@gmail.com', 'Peter', '$2a$10$w5HLlv2qAv59t1WCPE.5aOwbGvGItDPnnNTZR5EmsT6AYHgicnT1O', 'MALE' );

INSERT INTO users ( email, name, password, gender )
VALUES ( 'f@gmail.com', 'Filip', '$2a$10$w5HLlv2qAv59t1WCPE.5aOwbGvGItDPnnNTZR5EmsT6AYHgicnT1O', 'MALE' );

INSERT INTO users ( email, name, password, gender )
VALUES ( 'e@gmail.com', 'Elina', '$2a$10$w5HLlv2qAv59t1WCPE.5aOwbGvGItDPnnNTZR5EmsT6AYHgicnT1O', 'FEMALE' );

INSERT INTO sessions ( user_id, session_type, start_time, end_time, session_name, satisfaction )
VALUES ( 1, 'FOCUS', NOW( ), '2019-01-01 01:10:50', 'Session 1', '1' ),
       ( 1, 'FOCUS', NOW( ), '2019-01-01 02:14:55', 'Session 2', '6' ),
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
       ( 4, 'FOCUS', '2019-01-01 00:00:00', '2019-01-01 02:14:55', 'Session 2', '6' ),
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
       ( 4, 'FOCUS', '2019-01-01 00:00:00', '2019-01-01 02:14:55', 'Session 2', '6' ),
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
       ( 1, 'FOCUS', NOW( ), '2019-01-01 02:14:55', 'Session 2', '6' ),
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
       ( 4, 'FOCUS', '2019-01-01 00:00:00', '2019-01-01 02:14:55', 'Session 2', '6' ),
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
       ( 4, 'FOCUS', '2019-01-01 00:00:00', '2019-01-01 02:14:55', 'Session 2', '6' ),
       ( 1, 'FOCUS', '2019-01-01 00:00:00', '2019-01-01 01:10:50', 'Session 1', '1' ),
       ( 1, 'FOCUS', '2019-01-01 00:00:00', '2019-01-01 02:14:55', 'Session 2', '6' ),
       ( 1, 'FOCUS', '2019-01-01 00:00:00', '2019-01-01 01:10:50', 'Session 1', '1' ),
       ( 1, 'FOCUS', '2019-01-01 00:00:00', '2019-01-01 02:14:55', 'Session 2', '6' ),
       ( 1, 'FOCUS', '2019-01-01 00:00:00', '2019-01-01 01:10:50', 'Session 1', '1' ),
       ( 1, 'FOCUS', '2019-01-01 00:00:00', '2019-01-01 02:14:55', 'Session 2', '6' ),
       ( 1, 'FOCUS', '2019-01-01 00:00:00', '2019-01-01 01:10:50', 'Session 1', '1' ),
       ( 1, 'FOCUS', '2019-01-01 00:00:00', '2019-01-01 02:14:55', 'Session 2', '6' ),
       ( 1, 'FOCUS', '2019-01-01 00:00:00', '2019-01-01 01:10:50', 'Session 1', '1' );

INSERT INTO brainwaves( brainwave_id, signal, focus, meditation )
VALUES ( 1, 0, 100, 0 );

INSERT INTO sensor_data ( sensor_data_id, heart_rate, humidity, light, sound, temperature )
VALUES ( 1, 78, 50, 0, 25, 23 );

INSERT INTO brainwaves( brainwave_id, signal, focus, meditation )
VALUES ( 2, 0, 100, 0 );

INSERT INTO sensor_data ( sensor_data_id, heart_rate, humidity, light, sound, temperature )
VALUES ( 2, 78, 50, 0, 25, 23 );

INSERT INTO brainwaves( brainwave_id, signal, focus, meditation )
VALUES ( 3, 0, 100, 0 );

INSERT INTO sensor_data ( sensor_data_id, heart_rate, humidity, light, sound, temperature )
VALUES ( 3, 78, 50, 0, 25, 23 );

INSERT INTO brainwaves( brainwave_id, signal, focus, meditation )
VALUES ( 4, 0, 100, 0 );

INSERT INTO sensor_data ( sensor_data_id, heart_rate, humidity, light, sound, temperature )
VALUES ( 4, 78, 50, 0, 25, 23 );

INSERT INTO brainwaves( brainwave_id, signal, focus, meditation )
VALUES ( 5, 0, 100, 0 );

INSERT INTO sensor_data ( sensor_data_id, heart_rate, humidity, light, sound, temperature )
VALUES ( 5, 78, 50, 0, 25, 23 );

INSERT INTO readings ( reading_id, session_id, brainwave_id, sensor_data_id, time_stamp )
VALUES ( 1, 1, 1, 1, '2019-01-01 00:00:00' );

INSERT INTO readings ( reading_id, session_id, brainwave_id, sensor_data_id, time_stamp )
VALUES ( 2, 1, 2, 2, NOW( ) );

INSERT INTO readings ( reading_id, session_id, brainwave_id, sensor_data_id, time_stamp )
VALUES ( 3, 1, 3, 3, NOW( ) );

INSERT INTO readings ( reading_id, session_id, brainwave_id, sensor_data_id, time_stamp )
VALUES ( 4, 1, 4, 4, NOW( ) - INTERVAL '4' MONTH );

INSERT INTO readings ( reading_id, session_id, brainwave_id, sensor_data_id, time_stamp )
VALUES ( 5, 1, 5, 5, NOW( ) - INTERVAL '1' MONTH );