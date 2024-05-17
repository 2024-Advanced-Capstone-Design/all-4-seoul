-- 테이블 삭제 전 외래 키 제약 조건 삭제
ALTER TABLE bookmarks DROP FOREIGN KEY FK_bookmarks_users;
ALTER TABLE bookmarks DROP FOREIGN KEY FK_bookmarks_places;
ALTER TABLE events DROP FOREIGN KEY FK_events_places;
ALTER TABLE reviews DROP FOREIGN KEY FK_reviews_users;
ALTER TABLE reviews DROP FOREIGN KEY FK_reviews_places;
ALTER TABLE reviews DROP FOREIGN KEY FK_reviews_events;

-- 테이블 삭제
DROP TABLE IF EXISTS reviews;
DROP TABLE IF EXISTS events;
DROP TABLE IF EXISTS bookmarks;
DROP TABLE IF EXISTS places;
DROP TABLE IF EXISTS users;

CREATE TABLE users
(
    user_id        BIGINT                                                                                                                                NOT NULL AUTO_INCREMENT,
    birth          DATE                                                                                                                                  NOT NULL,
    credit         INTEGER DEFAULT 0,
    login_id       VARCHAR(30)                                                                                                                           NOT NULL UNIQUE,
    name           VARCHAR(30)                                                                                                                           NOT NULL,
    nickname       VARCHAR(30)                                                                                                                           NOT NULL UNIQUE,
    login_password VARCHAR(100)                                                                                                                          NOT NULL,
    gender         ENUM ('Male', 'Female')                                                                                                               NOT NULL,
    mbti           ENUM ('INTJ', 'INTP', 'INFJ', 'INFP', 'ISTJ', 'ISFJ', 'ISTP', 'ISFP', 'ENTJ', 'ENTP', 'ENFJ', 'ENFP', 'ESTJ', 'ESFJ', 'ESTP', 'ESFP') NOT NULL,
    PRIMARY KEY (user_id)
) ENGINE = InnoDB;

CREATE TABLE places
(
    place_id     BIGINT       NOT NULL AUTO_INCREMENT,
    name         VARCHAR(100) NOT NULL,
    phone_number VARCHAR(20)  NOT NULL,
    address      VARCHAR(255) NOT NULL,
    x            DOUBLE       NOT NULL,
    y            DOUBLE       NOT NULL,
    website_uri  VARCHAR(255) NOT NULL,
    category     ENUM('PARKING_LOT', 'GAS_STATION', 'CULTURE_FACILITY', 'TOURIST_ATTRACTION', 'RESTAURANT', 'CAFE') NOT NULL,
    PRIMARY KEY (place_id)
) ENGINE = InnoDB;

CREATE TABLE bookmarks
(
    bookmark_id BIGINT NOT NULL AUTO_INCREMENT,
    user_id     BIGINT NOT NULL,
    place_id    BIGINT NOT NULL,
    PRIMARY KEY (bookmark_id),
    CONSTRAINT FK_bookmarks_users FOREIGN KEY (user_id) REFERENCES users (user_id) ON DELETE CASCADE,
    CONSTRAINT FK_bookmarks_places FOREIGN KEY (place_id) REFERENCES places (place_id) ON DELETE CASCADE,
    UNIQUE (user_id, place_id)
) ENGINE = InnoDB;

-- 이어서 events, reviews 테이블 생성
CREATE TABLE events
(
    event_id   BIGINT       NOT NULL AUTO_INCREMENT,
    place_id   BIGINT,
    x          DOUBLE       NOT NULL,
    y          DOUBLE       NOT NULL,
    name       VARCHAR(100) NOT NULL,
    start_date DATETIME     NOT NULL,
    end_date   DATETIME     NOT NULL,
    price      INTEGER      NOT NULL,
    PRIMARY KEY (event_id),
    CONSTRAINT FK_events_places FOREIGN KEY (place_id) REFERENCES places (place_id)
) ENGINE = InnoDB;

CREATE TABLE reviews
(
    review_id   BIGINT       NOT NULL AUTO_INCREMENT,
    user_id     BIGINT       NOT NULL,
    place_id    BIGINT,
    event_id    BIGINT,
    star_rating FLOAT        NOT NULL,
    content     VARCHAR(255) NOT NULL,
    PRIMARY KEY (review_id),
    CONSTRAINT FK_reviews_users FOREIGN KEY (user_id) REFERENCES users (user_id),
    CONSTRAINT FK_reviews_places FOREIGN KEY (place_id) REFERENCES places (place_id),
    CONSTRAINT FK_reviews_events FOREIGN KEY (event_id) REFERENCES events (event_id)
) ENGINE = InnoDB;
