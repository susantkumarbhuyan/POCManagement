-- Drop tables if they exist (useful for testing)
DROP TABLE IF EXISTS DOCUMENT;
DROP TABLE IF EXISTS QUALIFICATION;
DROP TABLE IF EXISTS STUDENT;
DROP TABLE IF EXISTS ADDRESS;

-- Create Address Table
CREATE TABLE ADDRESS (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    street VARCHAR(255),
    city VARCHAR(255),
    state VARCHAR(255),
    postal_code VARCHAR(20)
);

-- Create Student Table
CREATE TABLE STUDENT (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255),
    age INT,
    gender VARCHAR(10),
    father_name VARCHAR(255),
    mother_name VARCHAR(255),
    address_id BIGINT,
    FOREIGN KEY (address_id) REFERENCES ADDRESS(id)
);

-- Create Qualification Table
CREATE TABLE QUALIFICATION (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    degree VARCHAR(255),
    institution VARCHAR(255),
    student_id BIGINT,
    FOREIGN KEY (student_id) REFERENCES STUDENT(id)
);

-- Create Document Table
CREATE TABLE DOCUMENT (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    document_name VARCHAR(255),
    document_data BLOB,
    student_id BIGINT,
    FOREIGN KEY (student_id) REFERENCES STUDENT(id)
);
