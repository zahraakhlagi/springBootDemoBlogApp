======ADMIN SCRIPT=================

#Use below SQL statements to create an admin user in database tables.


-- Insert roles
INSERT INTO roles (id, name) VALUES (1, 'ROLE_ADMIN'), (2, 'ROLE_GUEST');

-- Insert users
INSERT INTO users (id, email, name, password) VALUES (1, 'admin@gmail.com', 'admin', '$2a$10$k9644mshajjDvMhU8p76.u4sgOFuINZDkZ/csNgzFY99W1diZjBuC');

-- Insert users_roles
INSERT INTO users_roles (user_id, role_id) VALUES (1, 1);

Email: admin@gmail.com

Password: admin

===============================================
