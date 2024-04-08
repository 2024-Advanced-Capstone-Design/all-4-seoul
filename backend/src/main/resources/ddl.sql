-- 테이블 삭제 전 외래 키 제약 조건 삭제
ALTER TABLE places DROP FOREIGN KEY FK_places_users;
ALTER TABLE events DROP FOREIGN KEY FK_events_places;
ALTER TABLE parking_lot DROP FOREIGN KEY FK_parking_lot_places;
ALTER TABLE reviews DROP FOREIGN KEY FK_reviews_users;
ALTER TABLE reviews DROP FOREIGN KEY FK_reviews_places;
ALTER TABLE reviews DROP FOREIGN KEY FK_reviews_events;

-- 테이블 삭제
DROP TABLE IF EXISTS reviews;
DROP TABLE IF EXISTS parking_lot;
DROP TABLE IF EXISTS events;
DROP TABLE IF EXISTS places;
DROP TABLE IF EXISTS users;

-- 테이블 생성: places와 users 먼저 생성
CREATE TABLE users (
                       user_id BIGINT NOT NULL AUTO_INCREMENT,
                       birth DATE NOT NULL,
                       credit INTEGER DEFAULT 0,
                       login_id VARCHAR(30) NOT NULL,
                       name VARCHAR(30) NOT NULL,
                       nickname VARCHAR(30) NOT NULL,
                       login_password VARCHAR(100) NOT NULL,
                       gender ENUM('Male', 'Female') NOT NULL,
                       mbti ENUM('INTJ', 'INTP', 'INFJ', 'INFP', 'ISTJ', 'ISFJ', 'ISTP', 'ISFP', 'ENTJ', 'ENTP', 'ENFJ', 'ENFP', 'ESTJ', 'ESFJ', 'ESTP', 'ESFP') NOT NULL,
                       PRIMARY KEY (user_id),
                       UNIQUE KEY UK_i3xs7wmfu2i3jt079uuetycit (login_id),
                       UNIQUE KEY UK_2ty1xmrrgtn89xt7kyxx6ta7h (nickname)
) ENGINE=InnoDB;

CREATE TABLE places (
                        place_id BIGINT NOT NULL AUTO_INCREMENT,
                        user_id BIGINT,
                        phone_number VARCHAR(11) NOT NULL,
                        address VARCHAR(100) NOT NULL,
                        x DOUBLE NOT NULL,
                        y DOUBLE NOT NULL,
                        DTYPE VARCHAR(31) NOT NULL,
                        name VARCHAR(100) NOT NULL,
                        PRIMARY KEY (place_id),
                        UNIQUE KEY UK_ax1t8ehbn4dqi0avgjsu0n86q (phone_number),
                        CONSTRAINT FK_places_users FOREIGN KEY (user_id) REFERENCES users (user_id)
) ENGINE=InnoDB;

-- 이어서 events, parking_lot, reviews 테이블 생성
CREATE TABLE events (
                        event_id BIGINT NOT NULL AUTO_INCREMENT,
                        place_id BIGINT,
                        x DOUBLE NOT NULL,
                        y DOUBLE NOT NULL,
                        name VARCHAR(100) NOT NULL,
                        start_date DATETIME NOT NULL,
                        end_date DATETIME NOT NULL,
                        price INTEGER NOT NULL,
                        PRIMARY KEY (event_id),
                        CONSTRAINT FK_events_places FOREIGN KEY (place_id) REFERENCES places (place_id)
) ENGINE=InnoDB;

CREATE TABLE parking_lot (
                             place_id BIGINT NOT NULL,
                             add_rates VARCHAR(255),
                             capacity VARCHAR(255),
                             etc VARCHAR(255),
                             price VARCHAR(255),
                             time_rates VARCHAR(255),
                             PRIMARY KEY (place_id),
                             CONSTRAINT FK_parking_lot_places FOREIGN KEY (place_id) REFERENCES places (place_id)
) ENGINE=InnoDB;

CREATE TABLE reviews (
                         review_id BIGINT NOT NULL AUTO_INCREMENT,
                         user_id BIGINT NOT NULL,
                         place_id BIGINT,
                         event_id BIGINT,
                         star_rating FLOAT NOT NULL,
                         content VARCHAR(255) NOT NULL,
                         PRIMARY KEY (review_id),
                         CONSTRAINT FK_reviews_users FOREIGN KEY (user_id) REFERENCES users (user_id),
                         CONSTRAINT FK_reviews_places FOREIGN KEY (place_id) REFERENCES places (place_id),
                         CONSTRAINT FK_reviews_events FOREIGN KEY (event_id) REFERENCES events (event_id)
) ENGINE=InnoDB;
