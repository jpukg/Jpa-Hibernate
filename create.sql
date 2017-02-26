create table Department (id number(10,0) not null, name varchar2(255 char) not null, primary key (id))
create table Employee (id number(10,0) not null, firstName varchar2(255 char) not null, primary key (id))
create table EmployeeDetails (id number(10,0) not null, city varchar2(255 char), fatherName varchar2(255 char), post_code varchar2(255 char), street varchar2(255 char), primary key (id))
create table Project (id number(10,0) not null, name varchar2(255 char), primary key (id))
create sequence hibernate_sequence
