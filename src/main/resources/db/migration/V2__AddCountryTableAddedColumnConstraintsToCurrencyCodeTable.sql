CREATE TABLE country
(
    country_id            BIGINT       NOT NULL,
    currency_id           BIGINT,
    country_name          VARCHAR(255) NOT NULL,
    currency_country_uses VARCHAR(255) NOT NULL,
    CONSTRAINT pk_country PRIMARY KEY (country_id)
);

ALTER TABLE country
    ADD CONSTRAINT FK_COUNTRY_ON_CURRENCY FOREIGN KEY (currency_id) REFERENCES currency_code (currency_id);

ALTER TABLE currency_code
    ALTER COLUMN currency_code SET NOT NULL;

CREATE SEQUENCE IF NOT EXISTS currency_code_currency_id_seq;
ALTER TABLE currency_code
    ALTER COLUMN currency_id SET NOT NULL;
ALTER TABLE currency_code
    ALTER COLUMN currency_id SET DEFAULT nextval('currency_code_currency_id_seq');

ALTER SEQUENCE currency_code_currency_id_seq OWNED BY currency_code.currency_id;

ALTER TABLE currency_code
    ALTER COLUMN currency_name SET NOT NULL;