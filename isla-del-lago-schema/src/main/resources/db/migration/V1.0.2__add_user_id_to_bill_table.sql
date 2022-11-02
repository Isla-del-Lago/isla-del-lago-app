ALTER TABLE bill
ADD (
    created_by VARCHAR(40) NOT NULL,
    updated_by VARCHAR(40) NOT NULL
);