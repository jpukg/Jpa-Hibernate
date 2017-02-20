create table Department (id number(10,0) not null, name varchar2(255 char) not null, primary key (id))
create table Employee (id number(10,0) not null, createDate date, firstName varchar2(255 char) not null, gender varchar2(255 char), isActive varchar2(255 char), joinDate date, picture blob, remarks clob, salary double precision, primary key (id))
create table emp_address (emp_id number(10,0) not null, city varchar2(255 char), post_code varchar2(255 char), street varchar2(255 char), address_id number(19,0) not null, primary key (address_id))
alter table emp_address add constraint FK_nkk8b2wvhf49lcay3oyxshxs9 foreign key (emp_id) references Employee
create sequence hibernate_sequence
create table hibernate_unique_key ( next_hi number(10,0) )
insert into hibernate_unique_key values ( 0 )
