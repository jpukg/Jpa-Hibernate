create table Department (id number(10,0) not null, name varchar2(255 char) not null, primary key (id))
create table Emp_contacts (empl_id number(10,0) not null, CONTACT_LIST varchar2(255 char), CONTACT_TYPE varchar2(255 char) not null, primary key (empl_id, CONTACT_TYPE))
create table Employee (id number(10,0) not null, firstName varchar2(255 char) not null, primary key (id))
alter table Emp_contacts add constraint FK_ggs23sc4ufiwxr5uy0dd8a0fb foreign key (empl_id) references Employee
create sequence hibernate_sequence
