ALTER TABLE bill
MODIFY COLUMN residential_basic_cubic_meters FLOAT(8, 2) NOT NULL;

ALTER TABLE bill
MODIFY COLUMN residential_basic_superior_cubic_meters FLOAT(8, 2) NOT NULL;

ALTER TABLE bill
ADD (
    not_residential_cubic_meters FLOAT(8, 2) NOT NULL DEFAULT 0.0,
    not_residential_fixed_aqueduct FLOAT(8, 2) NOT NULL DEFAULT 0.0,
    not_residential_aqueduct FLOAT(8, 2) NOT NULL DEFAULT 0.0,
    not_residential_fixed_sewerage FLOAT(8, 2) NOT NULL DEFAULT 0.0,
    not_residential_sewerage FLOAT(8, 2) NOT NULL DEFAULT 0.0
);
