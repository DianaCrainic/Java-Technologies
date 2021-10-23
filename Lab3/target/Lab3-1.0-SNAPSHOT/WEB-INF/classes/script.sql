create table if not exists exams(
    id integer not null generated always as identity,
    name varchar(100) not null,
    starting_time timestamp,
    duration float,
    primary key (id)
);

insert into exams(name, starting_time, duration)
values('Software Engineering', '2021-11-20 12:00', 1.5);
insert into exams(name, starting_time, duration)
values('Java', '2021-11-16 11:00', 2);
insert into exams(name, starting_time, duration)
values('Android Technologies', '2021-11-17 13:30', 0.5);
insert into exams(name, starting_time, duration)
values('Machine Learning', '2021-11-20 12:00', 3);

create table if not exists students(
    id integer not null generated always as identity,
    name varchar(100) not null,
    primary key (id)
);

insert into students(name) values('Mike');
insert into students(name) values('John');
insert into students(name) values('Roger');

create table if not exists students_exams(
    student_id integer not null references students on delete restrict,
    exam_id integer not null references exams on delete restrict,
    primary key (student_id, exam_id)
);

insert into students_exams(student_id, exam_id)
values(1, 1);
insert into students_exams(student_id, exam_id)
values(1, 2);
insert into students_exams(student_id, exam_id)
values(1, 4);
insert into students_exams(student_id, exam_id)
values(2, 1);
insert into students_exams(student_id, exam_id)
values(2, 3);
insert into students_exams(student_id, exam_id)
values(3, 2);
insert into students_exams(student_id, exam_id)
values(3, 3);
insert into students_exams(student_id, exam_id)
values(3, 4);
