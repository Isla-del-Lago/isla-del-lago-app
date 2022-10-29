CREATE TABLE isla_del_lago_database.bill(
    bill_id INT PRIMARY KEY AUTO_INCREMENT,
    start_date TIMESTAMP NOT NULL,
    end_date TIMESTAMP NOT NULL,
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
