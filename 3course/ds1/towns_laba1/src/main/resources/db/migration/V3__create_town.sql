CREATE TABLE town
(
    id          SERIAL PRIMARY KEY,
    district_id INT          NOT NULL REFERENCES district (id),
    name        VARCHAR(255) NOT NULL,
    type        VARCHAR(255) NOT NULL,
    population  INT          NOT NULL
);