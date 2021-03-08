UPDATE status SET name = 'NEW2' WHERE id = 1;
UPDATE status SET name = 'WIP2' WHERE id = 2;
UPDATE status SET name = 'PASS2' WHERE id = 3;
UPDATE status SET name = 'FAIL2' WHERE id = 4;
UPDATE status SET name = 'ERROR2' WHERE id = 5;
UPDATE status SET name = 'STOP2' WHERE id = 6;
UPDATE status SET name = 'N/A2' WHERE id = 7;
UPDATE status SET name = 'DONE2' WHERE id = 8;
UPDATE status SET name = 'FREE2' WHERE id = 9;
UPDATE status SET name = 'WORKING2' WHERE id = 10;
UPDATE status SET name = 'DOWN2' WHERE id = 11;
UPDATE status SET name = 'HOLD2' WHERE id = 12;

INSERT INTO status (id,name) VALUES (13,'READY') ON CONFLICT DO NOTHING;

UPDATE status SET name = 'NEW' WHERE id = 1;
UPDATE status SET name = 'WIP' WHERE id = 2;
UPDATE status SET name = 'PASS' WHERE id = 3;
UPDATE status SET name = 'FAIL' WHERE id = 4;
UPDATE status SET name = 'ERROR' WHERE id = 5;
UPDATE status SET name = 'STOP' WHERE id = 6;
UPDATE status SET name = 'N/A' WHERE id = 7;
UPDATE status SET name = 'HOLD' WHERE id = 8;
UPDATE status SET name = 'FREE' WHERE id = 9;
UPDATE status SET name = 'WORKING' WHERE id = 10;
UPDATE status SET name = 'DOWN' WHERE id = 11;
UPDATE status SET name = 'DONE' WHERE id = 12;
