CREATE TABLE REVIEWS (
    id INT AUTO_INCREMENT PRIMARY KEY,
    author_name VARCHAR(255),
    restaurant_id INT,
    peanut_score DECIMAL(3,2),
    egg_score DECIMAL(3,2),
    dairy_score DECIMAL(3,2),
    comment TEXT,
    status VARCHAR(50)
);

CREATE TABLE USERS (
    id INT AUTO_INCREMENT PRIMARY KEY,
    user_name VARCHAR(255) NOT NULL,
    city VARCHAR(255),
    country VARCHAR(255),
    zip_code VARCHAR(20),
    is_interested_in_peanut BOOLEAN,
    is_interested_in_egg BOOLEAN,
    is_interested_in_dairy BOOLEAN
);

CREATE TABLE RESTAURANTS (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255),
    city VARCHAR(255),
    zip_code VARCHAR(255),
    capacity INT,
    peanut_score DECIMAL(3,2),
    egg_score DECIMAL(3,2),
    dairy_score DECIMAL(3,2),
    overall_score DECIMAL(3,2)
)