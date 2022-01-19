create table if not exists libraries
(
    id                  integer      not null generated always as identity,
    name                varchar(100) not null,
    budget              integer      not null,
    primary key (id)
    );

create table if not exists authors
(
    id                  integer      not null generated always as identity,
    name                varchar(100) not null,
    primary key (id)
    );

create table if not exists books
(
    id                 	integer      	not null generated always as identity,
    title              	varchar(100) 	not null,
    library_id         	integer 		references libraries on delete restrict,
    author_id          	integer 		references authors on delete restrict,
    review				integer      	not null,
    price 				integer			not null,
    primary key (id)
    );

create table if not exists books_authors
(
    id integer not null generated always as identity,
    book_id integer not null references books on delete restrict,
    author_id integer not null references authors on delete restrict,
    primary key (id)
    );
