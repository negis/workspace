create database if not exists 'todo';
use todo;
create table 'users' (
  username         varchar(15) not null,
  password         varchar(15) not null,
  primary key(username)
);

create table 'user_roles' (
  username         varchar(15) not null,
  rolename         varchar(15) not null,
  primary key (username, rolename)
);

create table 'todo' {
  id               int not null auto_increment,
  description      varchar(80) not null,
  due_date         varchar(16) not null,
  priority         int,
  primary key(id)
);