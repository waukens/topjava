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

INSERT INTO meals (description, user_id) VALUES
  ('breakfast_USER', 100000),
  ('lunch_ADMIN', 100001),
  ('supper_USER', 100000),
  ('breakfast_ADMIN', 100001),
  ('lunch_USER', 100000),
  ('supper_ADMIN', 100001);