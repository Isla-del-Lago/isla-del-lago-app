CREATE TABLE bill(
    bill_id INT PRIMARY KEY AUTO_INCREMENT,
    traceability_id VARCHAR(40) UNIQUE NOT NULL,
    start_date TIMESTAMP UNIQUE NOT NULL,
    end_date TIMESTAMP UNIQUE NOT NULL,
    residential_basic_cubic_meters INT NOT NULL,
    residential_basic_superior_cubic_meters INT NOT NULL,
    discounts FLOAT(8, 2) NOT NULL,
    residential_fixed_aqueduct FLOAT(8, 2) NOT NULL,
    residential_basic_aqueduct FLOAT(8, 2) NOT NULL,
    residential_basic_superior_aqueduct FLOAT(8, 2) NOT NULL,
    residential_fixed_sewerage FLOAT(8, 2) NOT NULL,
    residential_basic_sewerage FLOAT(8, 2) NOT NULL,
    residential_basic_superior_sewerage FLOAT(8, 2) NOT NULL,
    cleaning INT NOT NULL,
    creation_date TIMESTAMP NOT NULL,
    update_date TIMESTAMP NOT NULL
);
