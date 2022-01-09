create table if not exists users
(
    username varchar(100) not null unique,
    password varchar(64)  not null,
    primary key (username)
    );

create table if not exists groups
(
    name varchar(32) not null unique,
    primary key (name)
    );

create table if not exists user_groups
(
    username   varchar(100) not null references users (username) on delete cascade,
    group_name varchar(32)  not null references groups (name) on delete restrict,
    primary key (username, group_name)
    );

create table if not exists documents
(
    id                  integer      not null generated always as identity,
    registration_number integer      not null unique,
    name                varchar(100) not null,
    author_username     varchar(100) not null references users on delete restrict,
    content             bytea        not null,
    primary key (id)
    );

insert into users(username, password)
values ('admin', encode(sha256('password'), 'hex'));
insert into users(username, password)
values ('author', encode(sha256('password'), 'hex'));

insert into groups(name)
values ('admin');
insert into groups(name)
values ('author');

insert into user_groups(username, group_name)
values('admin', 'admin');
insert into user_groups(username, group_name)
values('author', 'author');