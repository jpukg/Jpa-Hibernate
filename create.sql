create table Department (id number(10,0) not null, name varchar2(255 char) not null, primary key (id))
create table Employee (DTYPE varchar2(31 char) not null, id number(10,0) not null, firstName varchar2(255 char) not null, bonus number(10,0), salary double precision, contract_duration varchar2(255 char), pay_per_hour float, primary key (id))
create sequence hibernate_sequence
