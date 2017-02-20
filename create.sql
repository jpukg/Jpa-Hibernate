create table Department (id number(10,0) not null, name varchar2(255 char) not null, primary key (id))
create table Employee (deptName varchar2(255 char) not null, id number(10,0) not null, firstName varchar2(255 char) not null, primary key (deptName, id))
create sequence hibernate_sequence
