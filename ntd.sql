CREATE SCHEMA IF NOT EXISTS todo;


DROP TABLE IF EXISTS users CASCADE;
DROP TABLE IF EXISTS items CASCADE;
DROP TABLE IF EXISTS categories CASCADE;
DROP TABLE IF EXISTS bills CASCADE;

CREATE TABLE users (
    id SERIAL PRIMARY KEY,
    email VARCHAR(255) NOT NULL,
    f_name VARCHAR(255) NOT NULL,
    l_name VARCHAR(255) NOT NULL,
    password VARCHAR(255) NOT NULL,
    created DATE

);



CREATE TABLE categories (
    category_id SERIAL PRIMARY KEY,
    category_name VARCHAR(255),
    date_created DATE,
    date_modified DATE,
    color VARCHAR(100)
);

CREATE TABLE items (
    item_id SERIAL PRIMARY KEY,
    user_id INT NOT NULL,
    category_id INT NOT NULL,
    item_name VARCHAR(200), 
    repetition VARCHAR(200),
    notes VARCHAR(500),
    completed BOOLEAN,
    item_priority VARCHAR(100),
    date_created DATE,
    date_completed DATE,
    date_modified DATE,
    due_date DATE,
    FOREIGN KEY (user_id) REFERENCES users(id),
    FOREIGN KEY (category_id) REFERENCES categories(category_id)
);

CREATE TABLE bills (
    bill_id SERIAL PRIMARY KEY,
    user_id INT NOT NULL,
    bill_name VARCHAR(255),
    company VARCHAR(255),
    amount FLOAT,
    bill_due_date DATE,
    is_paid BOOLEAN
);

INSERT INTO users (email, password, f_name, l_name, created)
VALUES
('nick@example.com', 'nick123', 'Nick', 'Card', NOW()),
('Jesse@example.com', 'jesse123', 'Jesse', 'Lauesen', NOW())
;

INSERT INTO categories (category_name, color, date_created, date_modified)
VALUES
('Cleaning', 'Blue', NOW(), NOW()),
('Work', 'Light Green', NOW(), NOW()),
('Personal', 'light red', '2022/05/10', '2022/06/01')
;

INSERT INTO items (user_id, category_id, item_name, repetition, notes, completed, item_priority, date_created, date_modified)
VALUES
(1, 1, 'Do the Dishes', 'Weekly','I just need to do the dishes', FALSE, 'HIGH', NOW(), NOW()),
(1, 1, 'Clean Bedroom', 'Weekly', 'need to clean this after the dishes', TRUE, 'HIGH', '2022/06/01', '2022/06/01'),
(1, 1, 'Vaccuum the apartment', 'Weekly', '<-', FALSE, 'HIGH', NOW(), NOW()),
(1, 2, 'Research SQL', 'By this date', 'Need to research SQL for work specifically psql', FALSE, 'HIGH', NOW(), NOW()),
(1, 3, 'Workout', 'Calisthenic Day', 'daily', FALSE, 'HIGH', NOW(), NOW())
;

INSERT INTO bills (user_id, bill_name, company, amount, bill_due_date, is_paid)
VALUES
(2, 'Internet', 'Xfinity', 75, '2022/06/23', FALSE),
(2, 'Rent', 'Apartment Complex', 75, '2022/07/01', FALSE),
(2, 'WOW Subscription', 'Blizzard', 15, '2022/06/15', FALSE),
(1, 'Gas', 'Gas South', 50, '2022/06/22', FALSE),
(1, 'Electricity', 'GA Power', 79, '2022/06/22', FALSE)
;