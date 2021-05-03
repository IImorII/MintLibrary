create database bookshop;
drop database bookshop;
use bookshop;

create table if not exists book
(
    id              int unique auto_increment primary key,
    name            varchar(50) unique not null,
    year_of_release int,
    rate            decimal,
    count           int
);

create table if not exists genre
(
    id    int unique auto_increment primary key,
    genre varchar(50) unique not null
);

create table if not exists language
(
    id       int unique auto_increment primary key,
    language varchar(50) unique not null
);

create table if not exists author
(
    id    int unique auto_increment primary key,
    birth int,
    name  varchar(50) unique not null
);

create table if not exists user
(
    id       int unique auto_increment primary key,
    name     varchar(50)         not null,
    password varchar(200)        not null,
    login    varchar(200) unique not null
);

create table if not exists role
(
    id   int unique auto_increment primary key,
    role varchar(50) unique not null
);

create table if not exists ticket
(
    id             int unique auto_increment primary key,
    amount_current int not null
);

create table if not exists ticket_type
(
    id         int unique auto_increment primary key,
    name       varchar(50) unique not null,
    amount_max int                not null
);

alter table ticket
    add type_fk int;
alter table ticket
    add constraint ticket_type_fk foreign key (type_fk) references ticket_type (id) on delete set null;

alter table user
    add role_fk varchar(50);
alter table user
    add constraint user_role_fk foreign key (role_fk) references role (role) on delete set null;
alter table user
    add constraint user_ticket_id_fk foreign key (id) references ticket (id) on delete cascade;


alter table book
    add language_id_fk int;
alter table book
    add constraint book_language_id_fk foreign key (language_id_fk) references language (id) on delete set null;

create table if not exists book_genre
(
    id       int not null,
    genre_id int not null,
    primary key (id, genre_id),
    foreign key (id) references book (id),
    foreign key (genre_id) references genre (id)
);

create table if not exists book_author
(
    id        int not null,
    author_id int,
    primary key (id, author_id),
    foreign key (id) references book (id),
    foreign key (author_id) references author (id)
);

create table if not exists author_language
(
    id          int not null,
    language_id int,
    primary key (id, language_id),
    foreign key (id) references author (id),
    foreign key (language_id) references language (id)
);

create table if not exists ticket_book
(
    id      int not null,
    book_id int,
    primary key (id, book_id),
    foreign key (id) references ticket (id),
    foreign key (book_id) references book (id)
);

