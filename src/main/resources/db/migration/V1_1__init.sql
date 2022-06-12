CREATE TABLE IF NOT EXISTS employee
(
    id          BIGSERIAL PRIMARY KEY,
    firstname   VARCHAR(128)          NOT NULL,
    lastname    VARCHAR(128)          NOT NULL,
    birthday    TIMESTAMP             NOT NULL,
    city        VARCHAR(100)          NOT NULL,
    gender      VARCHAR(16)           NOT NULL,
    isDismissed BOOLEAN DEFAULT FALSE NOT NULL
);