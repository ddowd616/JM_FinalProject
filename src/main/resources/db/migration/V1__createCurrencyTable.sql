CREATE TABLE currency_code
(
    currency_id   BIGINT NOT NULL,
    currency_name VARCHAR(255),
    currency_code VARCHAR(255),
    CONSTRAINT pk_currency_code PRIMARY KEY (currency_id)
);