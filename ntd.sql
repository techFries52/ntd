--NOTES FOR PROBLEMS I RAN INTO THEN FIXED

--tables werent actually being created so I had to 
--add todo.[tablename] in order to have them added to 
--the correct schema


CREATE SCHEMA IF NOT EXISTS todo;


DROP TABLE IF EXISTS todo.users CASCADE;
DROP TABLE IF EXISTS todo.items CASCADE;
DROP TABLE IF EXISTS todo.categories CASCADE;
DROP TABLE IF EXISTS todo.bills CASCADE;

CREATE TABLE todo.users (
    user_id SERIAL PRIMARY KEY,
    email VARCHAR(255) NOT NULL,
    f_name VARCHAR(255) NOT NULL,
    l_name VARCHAR(255) NOT NULL,
    password VARCHAR(255) NOT NULL,
    created DATE

);



CREATE TABLE todo.categories (
    category_id SERIAL PRIMARY KEY,
    user_id INT NOT NULL,
    category_name VARCHAR(255),
    date_created DATE,
    date_modified DATE,
    color VARCHAR(100),
    FOREIGN KEY (user_id) REFERENCES todo.users(user_id)
);

CREATE TABLE todo.items (
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
    FOREIGN KEY (user_id) REFERENCES todo.users(user_id),
    FOREIGN KEY (category_id) REFERENCES todo.categories(category_id)
);

CREATE TABLE todo.bills (
    bill_id SERIAL PRIMARY KEY,
    user_id INT NOT NULL,
    bill_name VARCHAR(255),
    company VARCHAR(255),
    amount FLOAT,
    bill_due_date DATE,
    is_paid BOOLEAN,
    FOREIGN KEY (user_id) REFERENCES todo.users(user_id)
);

INSERT INTO todo.users (email, password, f_name, l_name, created)
VALUES
('nick@example.com', 'nick123', 'Nick', 'Card', NOW()),
('Jesse@example.com', 'jesse123', 'Jesse', 'Lauesen', NOW())
;

INSERT INTO todo.categories (user_id, category_name, color, date_created, date_modified)
VALUES
(1, 'Cleaning', 'Blue', NOW(), NOW()),
(1, 'Work', 'Light Green', NOW(), NOW()),
(1, 'Personal', 'light red', '2022/05/10', '2022/06/01')
;

INSERT INTO todo.items (user_id, category_id, item_name, repetition, notes, completed, item_priority, date_created, date_modified)
VALUES
(1, 1, 'Do the Dishes', 'Weekly','I just need to do the dishes', FALSE, 'HIGH', NOW(), NOW()),
(1, 1, 'Clean Bedroom', 'Weekly', 'need to clean this after the dishes', TRUE, 'HIGH', '2022/06/01', '2022/06/01'),
(1, 1, 'Vaccuum the apartment', 'Weekly', '<-', FALSE, 'HIGH', NOW(), NOW()),
(1, 2, 'Research SQL', 'By this date', 'Need to research SQL for work specifically psql', FALSE, 'HIGH', NOW(), NOW()),
(1, 3, 'Workout', 'Calisthenic Day', 'daily', FALSE, 'HIGH', NOW(), NOW())
;

INSERT INTO todo.bills (user_id, bill_name, company, amount, bill_due_date, is_paid)
VALUES
(2, 'Internet', 'Xfinity', 75, '2022/06/23', FALSE),
(2, 'Rent', 'Apartment Complex', 75, '2022/07/01', FALSE),
(2, 'WOW Subscription', 'Blizzard', 15, '2022/06/15', FALSE),
(1, 'Gas', 'Gas South', 50, '2022/06/22', FALSE),
(1, 'Electricity', 'GA Power', 79, '2022/06/22', FALSE)
;