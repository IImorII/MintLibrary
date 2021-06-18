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
insert into role (name) values ('User');
insert into role (name) values ('Admin');
insert into role (name) values ('Librarian');

select * from book;
select * from author;
select * from author_language;
select * from book_author;
select * from book_genre;
select * from book_account;
select * from role;
select * from genre;
select * from language;
select * from account;
select id, name from language where id = 1;
delete from book where id >= 0;
delete from account where id >= 0;
delete from author where name = 'Antonio';
delete from language where name = 'Russian';
delete from account where login = 'Mikunika';
insert into author_language (id, language_id) values (1, 4);
insert into author_language (id, language_id) values (1, 2);
insert into book_author (id, author_id) values (1, 1);

insert into language (name) values ('Russian');
insert into book_genre (id, genre_id) values (1, 10);
insert into book (name, year_of_release, rate, count, language_id_fk) values ('Test', 2000, 10, 5, 1);
update book
set count = 10, description = 'lol'
where name = 'Iriska';
update book
set description = 'Using the SELECT command, results were returned in the same order the records were added into the database. This is the default sort order. In this section, we will be looking at how we can sort our query results. Sorting is simply re-arranging our query results in a specified way. Sorting can be performed on a single column or on more than one column. It can be done on number, strings as well as date data types'
where name = 'Book 1';
select genre.* from genre join book_genre on book_genre.genre_id = genre.id join book on book.id = book_genre.id where book.id = 1;

select book.* from book
            join book_author on book_author.id = book.id
            join author on author.id = book_author.author_id where author.id = 1;


update account
set book_amount_current = 0
where name = 'Lesha';

update account
set role_id_fk = 2
where login = 'admin';

update account
set role_id_fk = 3
where login = 'librarian';