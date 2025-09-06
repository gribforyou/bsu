SELECT name
FROM district
WHERE region_id = (SELECT id FROM region WHERE name = ?)
LIMIT ?
OFFSET ?;