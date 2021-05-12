use bookshop;
insert into genre (name)
values ('Adventure');
insert into genre (name)
values ('Fantasy');
insert into genre (name)
values ('Adventure');

insert into language (name)
values ('Russian');
insert into language (name)
values ('English');
insert into language (name)
values ('Japanese');

insert into author (name)
values ('Antonio');
select * from author;
select * from book;
select * from author_language;
select * from book_author;
select * from book_genre;
select * from role;
select * from genre;
select * from language;
select id, name from language where id = 1;

delete from author where name = 'Antonio';
delete from language where name = 'Russian';
insert into author_language (id, language_id) values (1, 4);
insert into author_language (id, language_id) values (1, 2);
insert into book_author (id, author_id) values (1, 1);

insert into language (name) values ('Russian');
insert into book_genre (id, genre_id) values (1, 10);
insert into book (name, year_of_release, rate, count, language_id_fk) values ('Test', 2000, 10, 5, 1);

select genre.* from genre join book_genre on book_genre.genre_id = genre.id join book on book.id = book_genre.id where book.id = 1;