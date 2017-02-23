create table Department (id number(10,0) not null, name varchar2(255 char) not null, primary key (id))
create table Employee (id number(10,0) not null, firstName varchar2(255 char) not null, primary key (id))
create table Employee_Project (employee_id number(10,0) not null, project_id number(10,0) not null)
create table Project (id number(10,0) not null, name varchar2(255 char), primary key (id))
alter table Employee_Project add constraint FK_12s0w38gmd6c9c3bdipi7qx5 foreign key (project_id) references Project
alter table Employee_Project add constraint FK_rgf4fn51dytt4g0kqkyuetuqo foreign key (employee_id) references Employee
create sequence hibernate_sequence
