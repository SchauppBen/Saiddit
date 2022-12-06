BEGIN TRANSACTION;

DROP TABLE IF EXISTS direct_messages;
DROP TABLE IF EXISTS reply_votes;
DROP TABLE IF EXISTS replies;
DROP TABLE IF EXISTS post_votes;
DROP TABLE IF EXISTS posts;
DROP TABLE IF EXISTS forums_users;
DROP TABLE IF EXISTS forums;
DROP TABLE IF EXISTS banned_users;
DROP TABLE IF EXISTS users;

CREATE TABLE users (
	user_id SERIAL,
	username varchar(50) NOT NULL UNIQUE,
	password_hash varchar(200) NOT NULL,
	role varchar(50) NOT NULL,
	CONSTRAINT PK_user PRIMARY KEY (user_id)
);

CREATE TABLE banned_users (
	user_id int NOT NULL REFERENCES users(user_id),
	admin_id int NOT NULL REFERENCES users(user_id),
	banned_on timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
	banned_until timestamp,
	comment varchar(100), 
	PRIMARY KEY (user_id, admin_id) 
);

CREATE TABLE forums (
	forum_id SERIAL PRIMARY KEY,
	name varchar(50) NOT NULL,
	description varchar(500),
	time_created timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE forums_users (
	forum_id int NOT NULL REFERENCES forums(forum_id),
	user_id int NOT NULL REFERENCES users(user_id),
	is_moderator boolean NOT NULL,
	PRIMARY KEY (forum_id, user_id)
);

CREATE TABLE posts (
	post_id SERIAL PRIMARY KEY,
	user_id int NOT NULL,
	forum_id int NOT NULL,
	title varchar(100) NOT NULL,
	text varchar(1000),
	media_link varchar(50),
	date_time timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
	CONSTRAINT fk_user_id FOREIGN KEY (user_id) REFERENCES users(user_id),
	CONSTRAINT fk_forum_id FOREIGN KEY (forum_id) REFERENCES forums(forum_id)
);

CREATE TABLE post_votes (
	user_id int NOT NULL REFERENCES users(user_id),
	post_id int NOT NULL REFERENCES posts(post_id),
	is_upvote boolean NOT NULL,
	date_time timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
	PRIMARY KEY (user_id, post_id)
);

CREATE TABLE replies (
	reply_id SERIAL PRIMARY KEY,
	reply_to_id int NOT NULL,
	user_from_id int NOT NULL,
	post_id int NOT NULL REFERENCES posts(post_id),
	text varchar(1000),
	media_link varchar(50),
	date_time timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
	CONSTRAINT fk_reply_to_id FOREIGN KEY (reply_to_id) REFERENCES replies(reply_id),
	CONSTRAINT fk_user_from_id FOREIGN KEY (user_from_id) REFERENCES users(user_id),
	CONSTRAINT fk_post_id FOREIGN KEY (post_id) REFERENCES posts(post_id)
);

CREATE TABLE reply_votes (
	user_id int NOT NULL REFERENCES users(user_id),
	reply_id int NOT NULL REFERENCES replies(reply_id),
	is_upvote boolean NOT NULL,
	date_time timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
	PRIMARY KEY (user_id, reply_id)
);

CREATE TABLE direct_messages (
	message_id serial PRIMARY KEY,
	user_from_id int,
	user_to_id int ,
	text varchar(200),
	media_link varchar(50),
	time_sent timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
	CONSTRAINT fk_user_from_id FOREIGN KEY(user_from_id) REFERENCES users(user_id),
	CONSTRAINT fk_user_to_id FOREIGN KEY (user_to_id) REFERENCES users(user_id)
);

COMMIT TRANSACTION;
