ALTER TABLE bill
MODIFY COLUMN start_date VARCHAR(11) UNIQUE NOT NULL;

ALTER TABLE bill
MODIFY COLUMN end_date VARCHAR(11) UNIQUE NOT NULL;