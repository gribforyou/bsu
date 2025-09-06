ALTER TABLE region
ADD CONSTRAINT unique_region UNIQUE (name);

ALTER TABLE district
ADD CONSTRAINT unique_district UNIQUE (name, region_id);

ALTER TABLE town
ADD CONSTRAINT unique_town UNIQUE (name, district_id);