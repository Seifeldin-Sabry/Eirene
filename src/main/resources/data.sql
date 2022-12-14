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

INSERT INTO sessions ( user_id, session_name, satisfaction, session_type, start_time, end_time )
VALUES ( 3, 'fifi1', 3, 'FOCUS', '2022-12-12 14:43:39.276000', '2022-12-12 14:44:35.997000' );

INSERT INTO brainwaves ( brainwave_id, signal, focus, meditation )
VALUES ( 1, 200, 0, 0 );
INSERT INTO brainwaves ( brainwave_id, signal, focus, meditation )
VALUES ( 2, 200, 0, 0 );
INSERT INTO brainwaves ( brainwave_id, signal, focus, meditation )
VALUES ( 3, 200, 0, 0 );
INSERT INTO brainwaves ( brainwave_id, signal, focus, meditation )
VALUES ( 4, 200, 0, 0 );
INSERT INTO brainwaves ( brainwave_id, signal, focus, meditation )
VALUES ( 5, 200, 0, 0 );
INSERT INTO brainwaves ( brainwave_id, signal, focus, meditation )
VALUES ( 6, 200, 0, 0 );
INSERT INTO brainwaves ( brainwave_id, signal, focus, meditation )
VALUES ( 7, 200, 0, 0 );
INSERT INTO brainwaves ( brainwave_id, signal, focus, meditation )
VALUES ( 8, 51, 0, 0 );
INSERT INTO brainwaves ( brainwave_id, signal, focus, meditation )
VALUES ( 9, 51, 0, 0 );
INSERT INTO brainwaves ( brainwave_id, signal, focus, meditation )
VALUES ( 10, 25, 0, 0 );
INSERT INTO brainwaves ( brainwave_id, signal, focus, meditation )
VALUES ( 11, 25, 0, 0 );
INSERT INTO brainwaves ( brainwave_id, signal, focus, meditation )
VALUES ( 12, 0, 0, 0 );
INSERT INTO brainwaves ( brainwave_id, signal, focus, meditation )
VALUES ( 13, 0, 0, 0 );
INSERT INTO brainwaves ( brainwave_id, signal, focus, meditation )
VALUES ( 14, 0, 47, 63 );
INSERT INTO brainwaves ( brainwave_id, signal, focus, meditation )
VALUES ( 15, 26, 48, 66 );
INSERT INTO brainwaves ( brainwave_id, signal, focus, meditation )
VALUES ( 16, 0, 96, 50 );
INSERT INTO brainwaves ( brainwave_id, signal, focus, meditation )
VALUES ( 17, 0, 87, 41 );
INSERT INTO brainwaves ( brainwave_id, signal, focus, meditation )
VALUES ( 18, 0, 37, 23 );
INSERT INTO brainwaves ( brainwave_id, signal, focus, meditation )
VALUES ( 19, 0, 48, 1 );
INSERT INTO brainwaves ( brainwave_id, signal, focus, meditation )
VALUES ( 20, 0, 60, 1 );
INSERT INTO brainwaves ( brainwave_id, signal, focus, meditation )
VALUES ( 21, 25, 41, 1 );
INSERT INTO brainwaves ( brainwave_id, signal, focus, meditation )
VALUES ( 22, 25, 41, 1 );
INSERT INTO brainwaves ( brainwave_id, signal, focus, meditation )
VALUES ( 23, 51, 41, 1 );
INSERT INTO brainwaves ( brainwave_id, signal, focus, meditation )
VALUES ( 24, 0, 56, 13 );
INSERT INTO brainwaves ( brainwave_id, signal, focus, meditation )
VALUES ( 25, 0, 64, 40 );
INSERT INTO brainwaves ( brainwave_id, signal, focus, meditation )
VALUES ( 26, 0, 47, 80 );
INSERT INTO brainwaves ( brainwave_id, signal, focus, meditation )
VALUES ( 27, 0, 43, 47 );
INSERT INTO brainwaves ( brainwave_id, signal, focus, meditation )
VALUES ( 28, 0, 78, 24 );

INSERT INTO sensor_data ( sensor_data_id, heart_rate, temperature, light, sound, humidity )
VALUES ( 1, 48, 23.799999237060547, 0, 52, 23 );
INSERT INTO sensor_data ( sensor_data_id, heart_rate, temperature, light, sound, humidity )
VALUES ( 2, 50, 23.799999237060547, 0, 52, 22 );
INSERT INTO sensor_data ( sensor_data_id, heart_rate, temperature, light, sound, humidity )
VALUES ( 3, 49, 23.799999237060547, 0, 52, 22 );
INSERT INTO sensor_data ( sensor_data_id, heart_rate, temperature, light, sound, humidity )
VALUES ( 4, 49, 23.799999237060547, 0, 52, 23 );
INSERT INTO sensor_data ( sensor_data_id, heart_rate, temperature, light, sound, humidity )
VALUES ( 5, 49, 23.799999237060547, 0, 52, 23 );
INSERT INTO sensor_data ( sensor_data_id, heart_rate, temperature, light, sound, humidity )
VALUES ( 6, 49, 23.799999237060547, 0, 52, 23 );
INSERT INTO sensor_data ( sensor_data_id, heart_rate, temperature, light, sound, humidity )
VALUES ( 7, 37, 23.799999237060547, 0, 52, 23 );
INSERT INTO sensor_data ( sensor_data_id, heart_rate, temperature, light, sound, humidity )
VALUES ( 8, 39, 23.799999237060547, 0, 52, 22 );
INSERT INTO sensor_data ( sensor_data_id, heart_rate, temperature, light, sound, humidity )
VALUES ( 9, 43, 23.799999237060547, 0, 52, 23 );
INSERT INTO sensor_data ( sensor_data_id, heart_rate, temperature, light, sound, humidity )
VALUES ( 10, 75, 23.799999237060547, 0, 52, 23 );
INSERT INTO sensor_data ( sensor_data_id, heart_rate, temperature, light, sound, humidity )
VALUES ( 11, 166, 23.799999237060547, 0, 53, 23 );
INSERT INTO sensor_data ( sensor_data_id, heart_rate, temperature, light, sound, humidity )
VALUES ( 12, 177, 23.799999237060547, 0, 52, 23 );
INSERT INTO sensor_data ( sensor_data_id, heart_rate, temperature, light, sound, humidity )
VALUES ( 13, 177, 23.799999237060547, 0, 52, 23 );
INSERT INTO sensor_data ( sensor_data_id, heart_rate, temperature, light, sound, humidity )
VALUES ( 14, 106, 23.799999237060547, 0, 52, 23 );
INSERT INTO sensor_data ( sensor_data_id, heart_rate, temperature, light, sound, humidity )
VALUES ( 15, 93, 23.799999237060547, 0, 52, 23 );
INSERT INTO sensor_data ( sensor_data_id, heart_rate, temperature, light, sound, humidity )
VALUES ( 16, 81, 23.799999237060547, 0, 52, 23 );
INSERT INTO sensor_data ( sensor_data_id, heart_rate, temperature, light, sound, humidity )
VALUES ( 17, 81, 23.799999237060547, 0, 52, 22 );
INSERT INTO sensor_data ( sensor_data_id, heart_rate, temperature, light, sound, humidity )
VALUES ( 18, 81, 23.799999237060547, 0, 53, 22 );
INSERT INTO sensor_data ( sensor_data_id, heart_rate, temperature, light, sound, humidity )
VALUES ( 19, 81, 23.799999237060547, 0, 52, 22 );
INSERT INTO sensor_data ( sensor_data_id, heart_rate, temperature, light, sound, humidity )
VALUES ( 20, 81, 23.799999237060547, 0, 52, 22 );
INSERT INTO sensor_data ( sensor_data_id, heart_rate, temperature, light, sound, humidity )
VALUES ( 21, 81, 23.799999237060547, 0, 52, 22 );
INSERT INTO sensor_data ( sensor_data_id, heart_rate, temperature, light, sound, humidity )
VALUES ( 22, 81, 23.799999237060547, 0, 52, 22 );
INSERT INTO sensor_data ( sensor_data_id, heart_rate, temperature, light, sound, humidity )
VALUES ( 23, 81, 23.799999237060547, 0, 52, 23 );
INSERT INTO sensor_data ( sensor_data_id, heart_rate, temperature, light, sound, humidity )
VALUES ( 24, 81, 23.799999237060547, 0, 53, 25 );
INSERT INTO sensor_data ( sensor_data_id, heart_rate, temperature, light, sound, humidity )
VALUES ( 25, 81, 23.799999237060547, 0, 52, 25 );
INSERT INTO sensor_data ( sensor_data_id, heart_rate, temperature, light, sound, humidity )
VALUES ( 26, 81, 23.799999237060547, 0, 52, 25 );
INSERT INTO sensor_data ( sensor_data_id, heart_rate, temperature, light, sound, humidity )
VALUES ( 27, 81, 23.799999237060547, 0, 52, 25 );
INSERT INTO sensor_data ( sensor_data_id, heart_rate, temperature, light, sound, humidity )
VALUES ( 28, 81, 23.799999237060547, 0, 52, 24 );

INSERT INTO readings ( reading_id, session_id, brainwave_id, sensor_data_id, time_stamp )
VALUES ( 1, 72, 1, 1, '2022-12-12 15:43:44.266000' );
INSERT INTO readings ( reading_id, session_id, brainwave_id, sensor_data_id, time_stamp )
VALUES ( 2, 72, 2, 2, '2022-12-12 15:43:46.287000' );
INSERT INTO readings ( reading_id, session_id, brainwave_id, sensor_data_id, time_stamp )
VALUES ( 3, 72, 3, 3, '2022-12-12 15:43:48.302000' );
INSERT INTO readings ( reading_id, session_id, brainwave_id, sensor_data_id, time_stamp )
VALUES ( 4, 72, 4, 4, '2022-12-12 15:43:50.317000' );
INSERT INTO readings ( reading_id, session_id, brainwave_id, sensor_data_id, time_stamp )
VALUES ( 5, 72, 5, 5, '2022-12-12 15:43:52.338000' );
INSERT INTO readings ( reading_id, session_id, brainwave_id, sensor_data_id, time_stamp )
VALUES ( 6, 72, 6, 6, '2022-12-12 15:43:54.356000' );
INSERT INTO readings ( reading_id, session_id, brainwave_id, sensor_data_id, time_stamp )
VALUES ( 7, 72, 7, 7, '2022-12-12 15:43:56.377000' );
INSERT INTO readings ( reading_id, session_id, brainwave_id, sensor_data_id, time_stamp )
VALUES ( 8, 72, 8, 8, '2022-12-12 15:43:58.392000' );
INSERT INTO readings ( reading_id, session_id, brainwave_id, sensor_data_id, time_stamp )
VALUES ( 9, 72, 9, 9, '2022-12-12 15:44:00.411000' );
INSERT INTO readings ( reading_id, session_id, brainwave_id, sensor_data_id, time_stamp )
VALUES ( 10, 72, 10, 10, '2022-12-12 15:44:02.432000' );
INSERT INTO readings ( reading_id, session_id, brainwave_id, sensor_data_id, time_stamp )
VALUES ( 11, 72, 11, 11, '2022-12-12 15:44:04.450000' );
INSERT INTO readings ( reading_id, session_id, brainwave_id, sensor_data_id, time_stamp )
VALUES ( 12, 72, 12, 12, '2022-12-12 15:44:06.465000' );
INSERT INTO readings ( reading_id, session_id, brainwave_id, sensor_data_id, time_stamp )
VALUES ( 13, 72, 13, 13, '2022-12-12 15:44:08.485000' );
INSERT INTO readings ( reading_id, session_id, brainwave_id, sensor_data_id, time_stamp )
VALUES ( 14, 72, 14, 14, '2022-12-12 15:44:10.504000' );
INSERT INTO readings ( reading_id, session_id, brainwave_id, sensor_data_id, time_stamp )
VALUES ( 15, 72, 15, 15, '2022-12-12 15:44:12.528000' );
INSERT INTO readings ( reading_id, session_id, brainwave_id, sensor_data_id, time_stamp )
VALUES ( 16, 72, 16, 16, '2022-12-12 15:44:14.545000' );
INSERT INTO readings ( reading_id, session_id, brainwave_id, sensor_data_id, time_stamp )
VALUES ( 17, 72, 17, 17, '2022-12-12 15:44:16.566000' );
INSERT INTO readings ( reading_id, session_id, brainwave_id, sensor_data_id, time_stamp )
VALUES ( 18, 72, 18, 18, '2022-12-12 15:44:18.588000' );
INSERT INTO readings ( reading_id, session_id, brainwave_id, sensor_data_id, time_stamp )
VALUES ( 19, 72, 19, 19, '2022-12-12 15:44:20.610000' );
INSERT INTO readings ( reading_id, session_id, brainwave_id, sensor_data_id, time_stamp )
VALUES ( 20, 72, 20, 20, '2022-12-12 15:44:22.619000' );
INSERT INTO readings ( reading_id, session_id, brainwave_id, sensor_data_id, time_stamp )
VALUES ( 21, 72, 21, 21, '2022-12-12 15:44:24.639000' );
INSERT INTO readings ( reading_id, session_id, brainwave_id, sensor_data_id, time_stamp )
VALUES ( 22, 72, 22, 22, '2022-12-12 15:44:26.661000' );
INSERT INTO readings ( reading_id, session_id, brainwave_id, sensor_data_id, time_stamp )
VALUES ( 23, 72, 23, 23, '2022-12-12 15:44:28.681000' );
INSERT INTO readings ( reading_id, session_id, brainwave_id, sensor_data_id, time_stamp )
VALUES ( 24, 72, 24, 24, '2022-12-12 15:44:30.701000' );
INSERT INTO readings ( reading_id, session_id, brainwave_id, sensor_data_id, time_stamp )
VALUES ( 25, 72, 25, 25, '2022-12-12 15:44:32.721000' );
INSERT INTO readings ( reading_id, session_id, brainwave_id, sensor_data_id, time_stamp )
VALUES ( 26, 72, 26, 26, '2022-12-12 15:44:34.742000' );
INSERT INTO readings ( reading_id, session_id, brainwave_id, sensor_data_id, time_stamp )
VALUES ( 27, 72, 27, 27, '2022-12-12 15:44:36.763000' );
INSERT INTO readings ( reading_id, session_id, brainwave_id, sensor_data_id, time_stamp )
VALUES ( 28, 72, 28, 28, '2022-12-12 15:44:38.783000' );
