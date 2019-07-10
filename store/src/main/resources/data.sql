DROP TABLE IF EXISTS users;
DROP TABLE IF EXISTS products;

CREATE TABLE users (
  id INT AUTO_INCREMENT  PRIMARY KEY,
  username VARCHAR(250) NOT NULL,
  password VARCHAR(250) NOT NULL,
  firstname VARCHAR(250) ,
  lastname VARCHAR(250),
  rol VARCHAR(250) DEFAULT NULL,
  image VARBINARY(MAX) DEFAULT NULL
);

CREATE TABLE products (
  id INT AUTO_INCREMENT  PRIMARY KEY,
  name VARCHAR(250) NOT NULL,
  price VARCHAR(250) NOT NULL,
  description VARCHAR(250) DEFAULT NULL
);

INSERT INTO products (name, price, description) VALUES
  ('Cono', '1,23€', 'Es un cono para el trafico'),
  ('Teclado', '20 €', null );

INSERT INTO users (username, password, rol) VALUES
  ('usuario', 'usuario', 'Cliente'),
  ('admin', 'admin', 'Admin');
