CREATE TABLE IF NOT EXISTS user (
   id BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY,
   login VARCHAR(30) NOT NULL UNIQUE,
   password VARCHAR(100) NOT NULL,
   name VARCHAR(30),
   description TEXT,
   people VARCHAR(50),
   address VARCHAR(50),
   email VARCHAR(50),
   phone VARCHAR(50),
   facebook VARCHAR(30),
   twitter VARCHAR(30),
   instagram VARCHAR(30),
   role VARCHAR(5) NOT NULL,
   image longblob,
   name_image VARCHAR(15),
   type VARCHAR(15),
   font_color VARCHAR(7), 
   background_color VARCHAR(7)
);
   
CREATE TABLE IF NOT EXISTS persistent_logins (
    login VARCHAR(64) NOT NULL,
    series VARCHAR(64) NOT NULL PRIMARY KEY,
    token VARCHAR(64) NOT NULL,
    last_used TIMESTAMP NOT NULL
);
