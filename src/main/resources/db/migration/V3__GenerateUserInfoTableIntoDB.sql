CREATE TABLE user_info
(
    user_id       BIGINT NOT NULL,
    country_id    BIGINT,
    user_name     VARCHAR(255),
    user_password VARCHAR(255),
    date_of_birth date,
    CONSTRAINT pk_user_info PRIMARY KEY (user_id)
);

ALTER TABLE user_info
    ADD CONSTRAINT FK_USER_INFO_ON_COUNTRYID FOREIGN KEY (country_id) REFERENCES country (country_id);