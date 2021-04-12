DROP TABLE IF EXISTS genre;
DROP TABLE IF EXISTS album;

create table genre(
  id SERIAL PRIMARY KEY,
  g_name varchar(30)
);

create table album(
  id SERIAL PRIMARY KEY,
  a_name varchar(30),
  artist varchar(50),
  a_year int,
  genreId int,
  FOREIGN KEY(genreId) REFERENCES genre(id) ON DELETE CASCADE
);

CREATE SEQUENCE genre_sequence start 5 increment 1;
CREATE SEQUENCE album_sequence start 5 increment 1;

INSERT INTO genre (id, g_name) VALUES (1, 'genre1');
INSERT INTO genre (id, g_name) VALUES (2, 'genre2');
INSERT INTO genre (id, g_name) VALUES (3, 'genre3');
INSERT INTO genre (id, g_name) VALUES (4, 'genre4');

INSERT INTO album (id, a_name, artist, a_year, genreId) VALUES (1, 'album1', 'artist1', 2001, 1 );
INSERT INTO album (id, a_name, artist, a_year, genreId) VALUES (2, 'album2', 'artist2', 2002, 2 );
INSERT INTO album (id, a_name, artist, a_year, genreId) VALUES (3, 'album3', 'artist3', 2003, 3 );
INSERT INTO album (id, a_name, artist, a_year, genreId) VALUES (4, 'album4', 'artist4', 2004, 4 );