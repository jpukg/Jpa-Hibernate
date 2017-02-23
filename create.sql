create table Department (id number(10,0) not null, name varchar2(255 char) not null, primary key (id))
create table Employee (id number(10,0) not null, firstName varchar2(255 char) not null, empDetails_id number(10,0), primary key (id))
create table EmployeeDetails (id number(10,0) not null, city varchar2(255 char), fatherName varchar2(255 char), post_code varchar2(255 char), street varchar2(255 char), primary key (id))
create table Project (id number(10,0) not null, name varchar2(255 char), primary key (id))
alter table Employee add constraint FK_i54ci8ygc2v1d19cr07gxpe65 foreign key (empDetails_id) references EmployeeDetails
create sequence hibernate_sequence
