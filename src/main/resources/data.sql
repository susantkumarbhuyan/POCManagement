-- Insert Address Data
INSERT INTO ADDRESS (street, city, state, postal_code) VALUES
    ('123 Main St', 'New York', 'NY', '10001'),
    ('456 Elm St', 'Los Angeles', 'CA', '90001');

-- Insert Student Data
INSERT INTO STUDENT (name, age, gender, father_name, mother_name, address_id) VALUES
    ('John Doe', 20, 'Male', 'Robert Doe', 'Jane Doe', 1),
    ('Jane Smith', 22, 'Female', 'Michael Smith', 'Laura Smith', 2);

-- Insert Qualification Data
INSERT INTO QUALIFICATION (degree, institution, student_id) VALUES
    ('Diploma ETC', 'UCP University', 1),
    ('BSc Computer Science', 'Lovely University', 1),
    ('BA English', 'University of Bhubaneswar', 2);

-- Insert Document Data with Hexadecimal Data
INSERT INTO DOCUMENT (document_name, document_data, student_id) VALUES
    ('Passport', X'89504E470D0A1A0A0000000D4948445200000008000000080802000000F25B84B200000004674944415478DAE8DD797D60D4F5300C5B0C9B7D8B058D2B060D58339B8B23BF8E05B0C48F84D0255B68C4B0494B0CC34027A2D832D822C0050C04CC0236B6C078000000000049454E44AE426082', 1);
