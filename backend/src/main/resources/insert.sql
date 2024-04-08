-- users 테이블에 더미 데이터 추가
insert into users (user_id, login_id, login_password, name, birth, mbti, gender, nickname)
values (1, 'tdawkes0@is.gd', 'zI4`(Q,udf40}WJ', 'Avigdor Meigh', '2023-08-16 22:21:22', 'ISTP', 'Male',
        'Tabor Dawkes');
insert into users (user_id, login_id, login_password, name, birth, mbti, gender, nickname)
values (2, 'lbittany1@ftc.gov', 'jS1#`89eGg8%OesC', 'Thaddus McCullouch', '2023-10-30 05:45:08', 'ISFP',
        'Male', 'Laurie Bittany');

-- places 테이블에 더미 데이터 추가
INSERT INTO places (place_id, user_id, phone_number, address, x, y, DTYPE, name)
VALUES (1, 1, '01012345678', '광교산로 뭐시기', '127.012', '37.567', 'place', 'Place One'),
       (2, 2, '01087654321', '보정로 30', '128.123', '38.678', 'place', 'Place Two');

-- events 테이블에 더미 데이터 추가
INSERT INTO events (event_id, name, start_date, end_date, price, place_id, x, y)
VALUES (1, '테스트 이벤트1', '2024-04-20 10:00:00', '2024-04-22 20:00:00', 50000, 1, '127.002', '37.002');

INSERT INTO events (event_id, name, start_date, end_date, price, place_id, x, y)
VALUES (2, '테스트 이벤트2', '2024-05-01 09:00:00', '2024-05-03 18:00:00', 30000, 1, '127.003', '37.003');

-- reviews 테이블에 더미 데이터 추가
insert into reviews (review_id, user_id, star_rating, content, place_id)
values (1, 1, 1.4,
        'Mauris ullamcorper purus sit amet nulla. Quisque arcu libero, rutrum ac, lobortis vel, dapibus at, diam.', 1);
insert into reviews (review_id, user_id, star_rating, content, place_id)
values (2, 2, 3.2, 'Nullam molestie nibh in lectus. Pellentesque at nulla. Suspendisse potenti.', 2);