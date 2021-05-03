use bookshop;
insert into genre (genre)
values ('Adventure');
insert into genre (genre)
values ('Fantasy');
insert into genre (genre)
values ('Adventure');

insert into language (language)
values ('Russian');
insert into language (language)
values ('English');
insert into language (language)
values ('Japanese');
select * from author;
select * from book;
select * from author_language;
select * from book_author;
select * from book_genre;
select * from role;
select * from genre;
select * from language;
select id, language from language where id = 1;

insert into book_author (id, author_id) values (1, 1);