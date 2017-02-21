create table Department (id number(10,0) not null, name varchar2(255 char) not null, primary key (id))
create table Emp_contacts (employee_id number(10,0) not null, CONTACT_LIST varchar2(255 char))
create table Employee (id number(10,0) not null, firstName varchar2(255 char) not null, primary key (id))
alter table Emp_contacts add constraint FK_qwomp31ep4iieb79gb68rvc2b foreign key (employee_id) references Employee
create sequence hibernate_sequence
