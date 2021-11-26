create table if not exists exams
(
    id                integer not null generated always as identity,
    name              varchar(100) not null,
    starting_time     timestamp,
    duration          integer,
    exam_type         varchar(100)         not null,
    bibliography      varchar(200),
    partial_evaluation bool,
    primary key (id),
    check (exam_type = 'project presentation' or exam_type = 'written test')
    );

create table if not exists students
(
    id integer not null generated always as identity,
    name varchar(100) not null,
    assigned_exams varchar(50),
    primary key (id)
);

create table if not exists resources
(
    id                 integer      not null generated always as identity,
    name               varchar(100) not null,
    quantity integer      not null,
    primary key (id),
    check (quantity >= 0)
);