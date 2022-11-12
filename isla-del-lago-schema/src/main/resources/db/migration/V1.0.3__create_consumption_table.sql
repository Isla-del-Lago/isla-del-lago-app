CREATE TABLE consumption(
    id INT PRIMARY KEY AUTO_INCREMENT,
    traceability_id VARCHAR(40) NOT NULL,
    apartment_id VARCHAR(30) NOT NULL,
    bill_id INT NOT NULL,
    value DOUBLE(10,1) NOT NULL,
    residential_basic_cubic_meters DOUBLE(5,2) NOT NULL,
    residential_basic_superior_cubic_meters DOUBLE(5,2) NOT NULL,
    created_at TIMESTAMP NOT NULL,
    updated_at TIMESTAMP NOT NULL,
    created_by VARCHAR(40) NOT NULL,
    updated_by VARCHAR(40) NOT NULL
);