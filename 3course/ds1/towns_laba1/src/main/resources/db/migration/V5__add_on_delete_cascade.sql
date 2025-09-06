ALTER TABLE district
DROP CONSTRAINT fk_district_region;

ALTER TABLE district
    ADD CONSTRAINT fk_district_region
        FOREIGN KEY (region_id)
            REFERENCES region (id)
            ON DELETE CASCADE;

ALTER TABLE town
DROP CONSTRAINT town_district_id_fkey;

ALTER TABLE town
    ADD CONSTRAINT town_district_id_fkey
        FOREIGN KEY (district_id)
            REFERENCES district (id)
            ON DELETE CASCADE;