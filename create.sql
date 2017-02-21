create table ContactEmployee (contract_duration varchar2(255 char), pay_per_hour float, employee_id number(10,0) not null, primary key (employee_id))
create table Department (id number(10,0) not null, name varchar2(255 char) not null, primary key (id))
create table Employee (id number(10,0) not null, firstName varchar2(255 char) not null, primary key (id))
create table RegularEmployee (bonus number(10,0) not null, salary double precision, employee_id number(10,0) not null, primary key (employee_id))
alter table ContactEmployee add constraint FK_blh37i213d4sijvg38d48hwq foreign key (employee_id) references Employee
alter table RegularEmployee add constraint FK_50lxjp1xtf08q31on55thoa90 foreign key (employee_id) references Employee
create sequence hibernate_sequence
