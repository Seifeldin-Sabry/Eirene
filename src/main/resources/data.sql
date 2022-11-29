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