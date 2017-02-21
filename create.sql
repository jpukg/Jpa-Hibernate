create table ContactEmployee (id number(10,0) not null, first_name varchar2(255 char) not null, contract_duration varchar2(255 char), pay_per_hour float, primary key (id))
create table Department (id number(10,0) not null, name varchar2(255 char) not null, primary key (id))
create table RegularEmployee (id number(10,0) not null, first_name varchar2(255 char) not null, bonus number(10,0) not null, salary double precision, primary key (id))
create sequence hibernate_sequence
