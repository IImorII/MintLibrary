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

insert into language (language) values ('Russian');
insert into book_genre (id, genre_id) values (1, 10);
insert into book (name, year_of_release, rate, count, language_id_fk) values ('Test', 2000, 10, 5, 1);

select genre.* from genre join book_genre on book_genre.genre_id = genre.id join book on book.id = book_genre.id where book.id = 1;