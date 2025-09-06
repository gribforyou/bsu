CREATE TABLE district
(
    id        SERIAL PRIMARY KEY,
    region_id INT          NOT NULL,
    name      VARCHAR(255) NOT NULL,
    CONSTRAINT fk_district_region
        FOREIGN KEY (region_id)
            REFERENCES region (id)
);