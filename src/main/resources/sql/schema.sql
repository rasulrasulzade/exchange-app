DROP SCHEMA IF EXISTS exchange;

CREATE SCHEMA exchange;

use exchange;

DROP TABLE IF EXISTS exchange_rate;

CREATE TABLE exchange_rate
(
    id        BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    base      VARCHAR(3),
    date      Date,
    timestamp TIMESTAMP
);

DROP TABLE IF EXISTS exchange_rate_mapping;

CREATE TABLE exchange_rate_mapping
(
    exchange_rate_id BIGINT         NOT NULL,
    rate             DECIMAL(19, 2) NULL,
    currency         VARCHAR(3)     NOT NULL,
    PRIMARY KEY (exchange_rate_id, currency),
    CONSTRAINT FKnhw6dy0mqe5h2gr8hrh4j62lt
        FOREIGN KEY (exchange_rate_id) REFERENCES exchange_rate (id)
);

DROP TABLE IF EXISTS exchange_spread;

CREATE TABLE exchange_spread
(
    id         BIGINT         NOT NULL AUTO_INCREMENT PRIMARY KEY,
    currency   VARCHAR(3)     NOT NULL,
    percentage DECIMAL(19, 2) NOT NULL
)
