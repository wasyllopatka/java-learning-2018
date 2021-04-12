drop database test_db;
create database test_db;
use test_db;

create table person(
id int primary key auto_increment not null,
first_name varchar(30),
last_name varchar(40),
address varchar(50)
);

create table user(
id int primary key auto_increment not null,
username varchar(30),
password varchar(50),
id_person int
);

alter table user add constraint foreign key (id) references person(id) on delete cascade;

INSERT INTO person (id, first_name, last_name, address) VALUES (1, 'p1', 'p1.1', 'adress1');
INSERT INTO person (id, first_name, last_name, address) VALUES (2, 'p2', 'p1.2', 'adress2');

INSERT INTO user (id, username, password, id_person) VALUES (1,	'user1', 'user1p', 1);
INSERT INTO user (id, username, password, id_person) VALUES (2,	'user2', 'user2p', 2);
