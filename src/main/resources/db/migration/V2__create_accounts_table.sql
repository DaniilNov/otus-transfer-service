CREATE TABLE accounts (
                          id SERIAL PRIMARY KEY,
                          account_number VARCHAR(255) NOT NULL UNIQUE,
                          client_id BIGINT NOT NULL,
                          balance DOUBLE PRECISION NOT NULL,
                          is_blocked BOOLEAN NOT NULL
);