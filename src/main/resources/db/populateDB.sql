DELETE FROM meals;
DELETE FROM user_roles;
DELETE FROM users;
ALTER SEQUENCE global_seq RESTART WITH 100000;
ALTER SEQUENCE global_seq_meal RESTART WITH 100000;

INSERT INTO users (name, email, password) VALUES
  ('User', 'user@yandex.ru', 'password'),
  ('Admin', 'admin@gmail.com', 'admin');

INSERT INTO user_roles (role, user_id) VALUES
  ('ROLE_USER', 100000),
  ('ROLE_ADMIN', 100001);

INSERT INTO meals (description, date_time, user_id) VALUES
  ('breakfast_USER', '20090604 11:00:00', 100000),
  ('lunch_ADMIN', '20090604 11:00:00', 100001),
  ('supper_USER', '20100604 11:00:00', 100000),
  ('breakfast_ADMIN', '20100604 11:00:00', 100001),
  ('lunch_USER', '20110604 11:00:00', 100000),
  ('supper_ADMIN', '20110604 11:00:00', 100001),
  ('beer_USER', '20100604 12:00:00', 100000),
  ('beer_ADMIN', '20100604 12:00:00', 100001);