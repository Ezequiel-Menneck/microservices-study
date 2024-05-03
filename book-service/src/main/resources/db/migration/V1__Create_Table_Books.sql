CREATE TABLE book (
  id serial PRIMARY KEY,
  author varchar(180),
  launch_date TIMESTAMP(6) NOT NULL,
  price decimal(65,2) NOT NULL,
  title varchar(255)
);
