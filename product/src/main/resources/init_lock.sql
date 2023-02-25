create table product(
                        id serial primary key,
                        name varchar(50) unique not null,
                        details varchar(100) unique not null
);

INSERT INTO product(id, name, details)
VALUES (1, 'Product 1', 'First product details');

INSERT INTO product(id, name, details)
VALUES (2, 'Product 2', 'Second product details');

CREATE TABLE INT_LOCK  (
                           LOCK_KEY CHAR(36) NOT NULL,
                           REGION VARCHAR(100) NOT NULL,
                           CLIENT_ID CHAR(36),
                           CREATED_DATE TIMESTAMP NOT NULL,
                           constraint INT_LOCK_PK primary key (LOCK_KEY, REGION)
);