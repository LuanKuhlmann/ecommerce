-- Inserindo Roles
INSERT INTO role (id, name) VALUES (1, 'ADMIN');
INSERT INTO role (id, name) VALUES (2, 'USER');

-- Inserindo Usuário ADMIN
INSERT INTO users (id, name, email, password)
VALUES (1, 'Administrador', 'admin@system.com', '$2a$10$9v8kh0gHhY2uH0A9SePOmubZjE8lGp2jNfT9gZbyUVhGQfPZCjHeW');
-- senha = admin123 (BCrypt)

-- Vinculando ADMIN à Role ADMIN
INSERT INTO users_roles (users_id, role_id) VALUES (1, 1);

-- Inserindo 5 Usuários comuns, senha para todos usuarios "admin123"
INSERT INTO users (id, name, email, password)
VALUES (2, 'Usuário 1', 'user1@email.com', '$2a$10$9v8kh0gHhY2uH0A9SePOmubZjE8lGp2jNfT9gZbyUVhGQfPZCjHeW'),
       (3, 'Usuário 2', 'user2@email.com', '$2a$10$9v8kh0gHhY2uH0A9SePOmubZjE8lGp2jNfT9gZbyUVhGQfPZCjHeW'),
       (4, 'Usuário 3', 'user3@email.com', '$2a$10$9v8kh0gHhY2uH0A9SePOmubZjE8lGp2jNfT9gZbyUVhGQfPZCjHeW'),
       (5, 'Usuário 4', 'user4@email.com', '$2a$10$9v8kh0gHhY2uH0A9SePOmubZjE8lGp2jNfT9gZbyUVhGQfPZCjHeW'),
       (6, 'Usuário 5', 'user5@email.com', '$2a$10$9v8kh0gHhY2uH0A9SePOmubZjE8lGp2jNfT9gZbyUVhGQfPZCjHeW');

-- Vinculando os 5 usuários à Role USER
INSERT INTO users_roles (users_id, role_id) VALUES (2, 2);
INSERT INTO users_roles (users_id, role_id) VALUES (3, 2);
INSERT INTO users_roles (users_id, role_id) VALUES (4, 2);
INSERT INTO users_roles (users_id, role_id) VALUES (5, 2);
INSERT INTO users_roles (users_id, role_id) VALUES (6, 2);
