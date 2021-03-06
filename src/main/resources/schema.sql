CREATE TABLE IF NOT EXISTS user (
   id BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY,
   login VARCHAR(30) NOT NULL UNIQUE,
   password VARCHAR(100) NOT NULL,
   name VARCHAR(50),
   description TEXT,
   people VARCHAR(50),
   address VARCHAR(50),
   email VARCHAR(50),
   phone VARCHAR(50),
   linkedin VARCHAR(100),
   facebook VARCHAR(100),
   twitter VARCHAR(100),
   instagram VARCHAR(100),
   role VARCHAR(5) NOT NULL,
   image longblob,
   name_image VARCHAR(30),
   type VARCHAR(20),
   font_color VARCHAR(7), 
   background_color VARCHAR(7),
   visible TINYINT(1)
);

CREATE TABLE IF NOT EXISTS review (
  id BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY,
  author VARCHAR(50) NOT NULL,
  description TEXT NOT NULL,
  rating INT NOT NULL,
  user_id bigint REFERENCES user (id)
);
   
CREATE TABLE IF NOT EXISTS persistent_logins (
    login VARCHAR(64) NOT NULL,
    series VARCHAR(64) NOT NULL PRIMARY KEY,
    token VARCHAR(64) NOT NULL,
    last_used TIMESTAMP NOT NULL
);