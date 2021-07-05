drop database bookshop;
create database bookshop;
use bookshop;

create table if not exists book
(
    id              int unique auto_increment primary key,
    name            varchar(50) unique not null,
    description     text,
    photo_url       varchar(5000),
    year_of_release int,
    rate            decimal default 0,
    count           int     default 1,
    check ( count >= 0 )
);

create table if not exists genre
(
    id   int unique auto_increment primary key,
    name varchar(50) unique not null
);

create table if not exists language
(
    id   int unique auto_increment primary key,
    name varchar(50) unique not null
);

create table if not exists author
(
    id            int unique auto_increment primary key,
    name          varchar(50) unique not null,
    year_of_birth int
);

create table if not exists account
(
    id                  int unique auto_increment primary key,
    name                varchar(50)         not null,
    login               varchar(100) unique not null,
    password            varchar(200)        not null,
    book_amount_current int default 0,
    book_amount_max     int default 5,
    check ( book_amount_current >= 0 ),
    check ( book_amount_max >= book_amount_current )
);

create table if not exists role
(
    id   int unique auto_increment primary key,
    name varchar(50) unique not null
);

alter table account
    add role_id_fk int not null;
alter table account
    add constraint account_role_fk foreign key (role_id_fk) references role (id)
        on delete set null
        on update cascade;

alter table book
    add language_id_fk int not null;
alter table book
    add constraint book_language_id_fk foreign key (language_id_fk) references language (id)
        on delete set null
        on update cascade;

create table if not exists book_genre
(
    id       int not null,
    genre_id int not null,
    primary key (id, genre_id),
    foreign key (id) references book (id)
        on delete cascade
        on update cascade,
    foreign key (genre_id) references genre (id)
        on delete cascade
        on update cascade
);

create table if not exists book_author
(
    id        int not null,
    author_id int not null,
    primary key (id, author_id),
    foreign key (id) references book (id)
        on delete cascade
        on update cascade,
    foreign key (author_id) references author (id)
        on delete cascade
        on update cascade
);

create table if not exists book_account
(
    id         int not null,
    account_id int not null,
    confirmed  boolean default false,
    primary key (id, account_id),
    foreign key (id) references book (id)
        on delete cascade
        on update cascade,
    foreign key (account_id) references account (id)
        on delete cascade
        on update cascade
);

create table if not exists author_language
(
    id          int not null,
    language_id int not null,
    primary key (id, language_id),
    foreign key (id) references author (id)
        on delete cascade
        on update cascade,
    foreign key (language_id) references language (id)
        on delete cascade
        on update cascade
);

insert into role (name)
values ('User');
insert into role (name)
values ('Admin');
insert into role (name)
values ('Librarian');


