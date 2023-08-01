DROP TABLE IF EXISTS accounts;

CREATE TABLE accounts (
    user_name VARCHAR(255) NOT NULL PRIMARY KEY,
    password VARCHAR(255) NOT NULL,
    enabled BOOLEAN,
    expiration DATE,
    password_expiration DATE,
    login_failure_count INTEGER
);