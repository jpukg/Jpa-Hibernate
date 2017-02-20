create table Department (id number(10,0) not null, name varchar2(255 char) not null, primary key (id))
create table Employee (id number(10,0) not null, createDate date, firstName varchar2(255 char) not null, gender varchar2(255 char), isActive varchar2(255 char), joinDate date, picture blob, remarks clob, salary double precision, primary key (id))
create table Employee_address (Employee_id number(10,0) not null, city varchar2(255 char), post_code varchar2(255 char), street varchar2(255 char))
alter table Employee_address add constraint FK_4vnp5s9i78kefrqbvdoorohok foreign key (Employee_id) references Employee
create sequence hibernate_sequence
