create table if not exists users
(
    id       integer      not null generated always as identity,
    username varchar(50) not null unique,
    password varchar(50) not null,
    role     varchar(10) not null,
    primary key (id),
    check (role = 'admin' or role = 'author')
);

create table if not exists documents
(
    id                  integer      not null generated always as identity,
    registration_number integer      not null unique,
    name                varchar(100) not null,
    author_id           integer      not null,
    content             bytea        not null,
    primary key (id)
);

create table if not exists time_frames
(
    id         integer not null generated always as identity,
    start_date date    not null,
    end_date   date    not null,
    primary key (id),
    check (start_date < end_date)
);