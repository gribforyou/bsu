SELECT name, type, population
FROM town
WHERE district_id = (SELECT id FROM district WHERE name = ?)
LIMIT ?
OFFSET ?;