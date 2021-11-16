create table if not exists exams
(
    id                integer not null generated always as identity,
    name              varchar(100) not null,
    starting_time     timestamp,
    duration          integer,
    exam_type         varchar(100)         not null,
    primary key (id)
);

create table if not exists students
(
    id integer not null generated always as identity,
    name varchar(100) not null,
    assigned_exams varchar(50),
    primary key (id)
);