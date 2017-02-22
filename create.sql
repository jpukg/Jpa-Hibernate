create table Department (id number(10,0) not null, name varchar2(255 char) not null, primary key (id))
create table Employee (id number(10,0) not null, firstName varchar2(255 char) not null, department_id number(10,0), primary key (id))
alter table Employee add constraint FK_lk0a412kck2kdc6slousi528s foreign key (department_id) references Department
create sequence hibernate_sequence
