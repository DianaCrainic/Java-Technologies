insert into users (username, password, role)
values ('admin1234', 'admin1234', 'admin');
insert into users (username, password, role)
values ('author1234', 'author1234', 'author');
insert into users (username, password, role)
values ('author5678', 'author5678', 'author');

insert into documents(registration_number, name, author_id, content)
values (12345, 'Document1', 3, '');
insert into documents(registration_number, name, author_id, content)
values (23456, 'Document2', 2, '');
insert into documents(registration_number, name, author_id, content)
values (34567, 'Document3', 2, '');
insert into documents(registration_number, name, author_id, content)
values (45678, 'Document4', 3, '');

insert into time_frames(start_date, end_date)
values('2021-12-10', '2021-12-11');
insert into time_frames(start_date, end_date)
values('2021-12-13', '2021-12-15');