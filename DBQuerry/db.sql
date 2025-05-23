drop database if exists appointments;
create database appointments;
use appointments;

create table Location ( 
	id int not null auto_increment, 
    location varchar(100),
    primary Key (id)
);

create table Time ( 
	id int not null auto_increment, 
    time datetime,
    primary Key (id)
);

create table Appointment ( 
	idlo int, 
    idti int,
    foreign key (idlo) references Location(id) on delete cascade on update cascade,
    foreign key (idti) references Time(id) on delete cascade on update cascade,
    primary Key (idlo, idti)
);

insert into Location (location) values ('Olten');
insert into Location (location) values ('Basel');

insert into Time (time) values (20250210120000);
insert into time (time) values (20250209190000);
insert into time (time) values (20250211100000);

insert into appointment (idlo, idti) values (1, 1);
insert into appointment (idlo, idti) values (1, 2);
insert into appointment (idlo, idti) values (2, 2);
insert into appointment (idlo, idti) values (2, 3);