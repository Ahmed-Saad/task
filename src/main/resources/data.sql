DROP TABLE IF EXISTS users;
DROP TABLE IF EXISTS items; 
DROP TABLE IF EXISTS users_type;
DROP TABLE IF EXISTS items_type;

CREATE TABLE users_type (
  id INT AUTO_INCREMENT  PRIMARY KEY,
  type VARCHAR(250) NOT NULL
);

INSERT INTO users_type (type) VALUES
  ('Employee'),
  ('Affiliate'),
  ('Customer');
 
CREATE TABLE users (
  id INT AUTO_INCREMENT  PRIMARY KEY,
  first_name VARCHAR(250) NOT NULL,
  last_name VARCHAR(250) NOT NULL,
  type_id INT,
  creation_date DATE,
  foreign key (type_id) references users_type(id)
);
 
INSERT INTO users (first_name, last_name, type_id, creation_date) VALUES
  ('Ahmed', 'Saad', (select id from users_type where type = 'Employee'), to_date('01-01-19', 'DD-MM-RR')),
  ('Baraa', 'Ahmed', (select id from users_type where type = 'Affiliate'), to_date('01-01-19', 'DD-MM-RR')),
  ('Mohammed', 'Ahmed', (select id from users_type where type = 'Customer'), to_date('01-01-17', 'DD-MM-RR')),
  ('Ibrahim', 'Ahmed', (select id from users_type where type = 'Customer'), to_date('01-01-20', 'DD-MM-RR'));


 
CREATE TABLE items_type (
  id INT AUTO_INCREMENT  PRIMARY KEY,
  type VARCHAR(250) NOT NULL
);

INSERT INTO items_type (type) VALUES
  ('Groceries'),
  ('Other');
  
 
  
CREATE TABLE items (
  id INT AUTO_INCREMENT  PRIMARY KEY,
  item_name VARCHAR(250) NOT NULL,
  price double NOT NULL,
  type_id INT,
  foreign key (type_id) references items_type(id)
);

INSERT INTO items(item_name, price, type_id) VALUES
  ('cheese', '50', (select id from items_type where type = 'Groceries')),
  ('iphone', '5000', (select id from items_type where type = 'Other')),
  ('shirt', '150', (select id from items_type where type = 'Other'));