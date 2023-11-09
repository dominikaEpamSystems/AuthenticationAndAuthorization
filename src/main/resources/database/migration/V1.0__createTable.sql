create table users (
    id BIGSERIAL PRIMARY KEY,
     username VARCHAR(255),
     password VARCHAR(255),
     email VARCHAR(255),
     enabled BOOLEAN DEFAULT TRUE,
     blocking_time TIMESTAMP  NULL
);

create table roles (
    id BIGSERIAL PRIMARY KEY,
    role VARCHAR(255)
);

create table user_roles (
    id BIGSERIAL PRIMARY KEY,
    user_id BIGINT NOT NULL,
    role_id INTEGER NOT NULL,
    FOREIGN KEY (user_id) REFERENCES users(id),
    FOREIGN KEY (role_id) REFERENCES roles(id)
);


INSERT INTO users(id, username, password, email, blocking_time) VALUES (1, 'ADMIN', '{bcrypt}$2a$10$Hp1D3AGqRta6xg/4hq43NOQS.18fZ6IMX.9Hpxw9sEgKiB7Gv1aAy', 'admin@email.com', '2023-11-08 20:14:56.409');
INSERT INTO users(id, username, password, email, blocking_time) VALUES (2, 'USER', '{bcrypt}$2a$10$zMIvUGQfFDxE6gqahhLng.XPZX9FJyu2PgvodWmwV56/GyAHMbHmy', 'user@email.com', '2023-11-08 20:14:56.409');
INSERT INTO users(id, username, password, email, blocking_time) VALUES (3, 'USER1', '{bcrypt}$2a$10$zoj0.5ok/j4aaPvDhhFZ9uEvkESM9XiDM0j/dkzNUGbGzkoY4zeIm', 'user1@email.com', '2023-11-08 20:14:56.409');
INSERT INTO users(id, username, password, email, blocking_time) VALUES (4, 'USER2', '{bcrypt}$2a$10$3E9q3YU8esj1Z5Jo7x1PsOeymCfsLCq5uk3C8pMcx/nzss77FEWJ6', 'user2@email.com', '2023-11-08 20:14:56.409');

INSERT INTO roles(id, role) VALUES (1, 'ADMIN');
INSERT INTO roles(id, role) VALUES (2, 'USER');
INSERT INTO roles(id, role) VALUES (3, 'VIEW_INFO');
INSERT INTO roles(id, role) VALUES (4, 'VIEW_ADMIN');

INSERT INTO user_roles(id, user_id, role_id) VALUES (1, 1, 2);
INSERT INTO user_roles(id, user_id, role_id) VALUES (2, 1, 4);
INSERT INTO user_roles(id, user_id, role_id) VALUES (3, 2, 2);
INSERT INTO user_roles(id, user_id, role_id) VALUES (4, 3, 2);
INSERT INTO user_roles(id, user_id, role_id) VALUES (5, 3, 3);



