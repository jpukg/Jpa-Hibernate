create table Department (id number(10,0) not null, name varchar2(255 char) not null, primary key (id))
create table EMP_SENIORITY (Department_id number(10,0) not null, SENIORITY number(10,0), EMP_ID number(10,0) not null, primary key (Department_id, EMP_ID))
create table Employee (id number(10,0) not null, firstName varchar2(255 char) not null, primary key (id))
alter table EMP_SENIORITY add constraint FK_c97ltw06hxxp0t14t4717nkt6 foreign key (EMP_ID) references Employee
alter table EMP_SENIORITY add constraint FK_dctc6q77od5b2tumib4msmw4h foreign key (Department_id) references Department
create sequence hibernate_sequence
