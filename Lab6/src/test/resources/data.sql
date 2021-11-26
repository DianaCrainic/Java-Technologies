insert into exams(name, starting_time, duration, exam_type, bibliography, partial_evaluation)
values ('Java Technologies', '2021-10-18 09:00', 60, 'written test', 'Courses', true);
insert into exams(name, starting_time, duration, exam_type, bibliography, partial_evaluation)
values ('ASET', '2021-10-18 12:00', 30, 'project presentation', 'Courses', true);
insert into exams(name, starting_time, duration, exam_type, bibliography, partial_evaluation)
values ('Blockchain', '2021-10-19 10:30', 90, 'written test', 'Courses', false);
insert into exams(name, starting_time, duration, exam_type, bibliography, partial_evaluation)
values ('Software Engineering', '2021-10-24 12:00', 30, 'written test', 'Courses', true);

insert into students(name, assigned_exams)
values ('Diana Crainic', '3,5');
insert into students(name, assigned_exams)
values ('Maria Popescu', '1,4,6');

insert into resources(name, quantity)
values ('room', 5);
insert into resources(name, quantity)
values ('laptop', 10);
insert into resources(name, quantity)
values ('video projector', 15);
