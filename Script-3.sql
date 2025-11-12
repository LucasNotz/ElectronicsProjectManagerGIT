create database note_db;

use note_db;

create table users (
username char(40) primary key,
password varchar(40) not null)

create table note (
title char(30) not null,
username_fk char(40) not null,
note varchar(1000),
primary key (title),
foreign key (username_fk) references users(username))

-- Populate users
insert into users (username, password) values
('Daniel', 'asdf'),
('Lucas', 'asdf'),
('Antonio', 'asdf');

-- Populate notes
insert into note (title, username_fk, note) values
('Daily Reflection', 'Daniel', 'Today I focused on structuring SQL tables efficiently and learned about primary and foreign key relationships.'),
('Workout Plan', 'Lucas', 'Bench press 3x10, squats 3x12, and add cardio for 20 minutes. Keeping consistency is key.'),
('Book Summary', 'Antonio', 'Finished reading "Sapiens". The author explores how human cooperation and shared myths shaped civilization.'),
('Weekend Ideas', 'Daniel', 'Thinking about going hiking and trying out some landscape photography this weekend.'),
('Study Schedule', 'Lucas', 'Morning: database design; Afternoon: data normalization; Evening: practice SQL queries.'),
('Project Notes', 'Antonio', 'Need to finalize user authentication module and test password encryption before deployment.');

