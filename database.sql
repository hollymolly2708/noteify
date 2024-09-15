CREATE TABLE users(
  username VARCHAR(100) NOT NULL,
  password VARCHAR(100) NOT NULL,
  full_name VARCHAR(100) NOT NULL,
  address VARCHAR(100) NOT NULL,
  phone VARCHAR(100) NOT NULL,
  email VARCHAR(100) NOT NULL,
  token VARCHAR(100),
  token_expired_at BIGINT,PRIMARY KEY (username),
  UNIQUE (token)
);

SELECT * FROM users;
DROP TABLE user;
DESC users;

CREATE TABLE notes(
id VARCHAR(100) NOT NULL,
username VARCHAR(100) NOT NULL,
title VARCHAR(100) NOT NULL,
description VARCHAR(100) NOT NULL,
created_at VARCHAR(100),
updated_at VARCHAR(100),
PRIMARY KEY(id),
FOREIGN KEY fk_users_notes(username) REFERENCES users(username)
);

DELETE FROM users;
SELECT * FROM notes;
DESC notes;