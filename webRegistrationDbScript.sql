BEGIN TRANSACTION;

DROP TABLE IF EXISTS users;

CREATE TABLE users (
  id VARCHAR(300) PRIMARY KEY UNIQUE,   --Unique hexadecimal ID based on email, required
  firstname varchar(100) NOT NULL,     -- Required
  lastname varchar(100),      
  email varchar(100) NOT NULL,                   --Required
  phonenumber varchar(16)
);

COMMIT TRANSACTION;
