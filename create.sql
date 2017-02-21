create table Contacts (id number(10,0) not null, CONTACT_LIST varchar2(255 char))
create table Department (id number(10,0) not null, name varchar2(255 char) not null, primary key (id))
create table Employee (DTYPE varchar2(31 char) not null, id number(10,0) not null, firstName varchar2(255 char) not null, bonus number(10,0), salary double precision, contract_duration varchar2(255 char), pay_per_hour float, primary key (id))
alter table Contacts add constraint FK_j1ctaccaumpq4tlbdc3vfncr9 foreign key (id) references Employee
create sequence hibernate_sequence
