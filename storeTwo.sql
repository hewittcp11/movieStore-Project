use hewittcp11;

 drop table if exists sale;
 
create table sale (
 saleId int unsigned not null auto_increment,
 date datetime not null,
 custId int unsigned not null,
 primary key (saleId));
 
drop table if exists transact;
 
create table transact (
 saleId int unsigned not null,
 itemId int unsigned not null,
 qty int unsigned not null,
 price decimal(8,2) not null, 
primary key (saleId, itemId));
 
drop table if exists item;
 
create table item (
 itemId int unsigned not null auto_increment,
 itemName varchar(255) not null,
 qtyInStock int not null,
 retailPrice decimal(8,2) null,
 wholesalePrice decimal(8,2) null,
 primary key (itemId));
 
drop table if exists customer;
 
create table customer (
 custId int unsigned not null auto_increment,
 firstName varchar(30) not null,
 lastName varchar(30) not null,
 phone varchar(20) null,
 primary key (custId));
 
insert into customer values ( 1, 'Jane', 'Doe', '570-662-1111' );
 insert into customer values ( 2, 'John', 'Smith', '570-662-2222' );
 insert into item values (1, 'Ax', 10, 14.95, 9.00);
 insert into item values (2, 'Saw', 2, 25.95, 12.12);
 insert into item values (3, 'Hoe', 20, 13.50, 8.00);
 insert into sale values (1, '2012-08-25 09:14:07', 2);
 insert into transact values (1, 3, 2, 13.50);
 insert into sale values (2, '2012-09-02 14:35:58', 2);
 insert into transact values (2, 1, 1, 14.95);
 insert into transact values (2, 2, 1, 25.95);
 insert into sale values (3, '2012-09-05 12:30:01', 1);
 insert into transact values (3, 1, 2, 14.95);
