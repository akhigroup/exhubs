-- 0. initiation --

drop table users;
drop sequence users_id_seq;
drop table group_role;
drop table groups;
drop sequence groups_id_seq;
drop table roles;
drop sequence roles_id_seq;

--------------------------------------

-- 1. groups --
create sequence groups_id_seq
	start with 1
	increment BY 1
	no minvalue
	no maxvalue
	cache 1;

create table groups (
	id int not null default nextval('groups_id_seq'),
	name varchar(32) not null,
	description varchar(32),
	primary key (id)
);

insert into groups (name, description) values ('GROUP_SYSTEM_ADMIN', 'System Admin Group');
insert into groups (name, description) values ('GROUP_NORMAL_USER', 'Normal User Group');


-- 2. roles --
create sequence roles_id_seq
	start with 1
	increment BY 1
	no minvalue
	no maxvalue
	cache 1;

create table roles (
	id int not null default nextval('roles_id_seq'),
	name varchar(32) not null,
	description varchar(32),
	primary key (id)
);

insert into roles (name, description) values ('ROLE_USER', 'Registered User');
insert into roles (name, description) values ('ROLE_USER_GROUP_MANAGER', 'User Group Manager');
insert into roles (name, description) values ('ROLE_USER_MANAGER', 'User Manager');


-- 3. group_role --
create table group_role (
	group_id int not null,
	role_id int not null,
	primary key (group_id, role_id),
	foreign key (group_id) references groups (id),
	foreign key (role_id) references roles (id)
);

insert into group_role (group_id, role_id) values (1, 1);
insert into group_role (group_id, role_id) values (1, 2);
insert into group_role (group_id, role_id) values (1, 3);
insert into group_role (group_id, role_id) values (2, 1);


-- 4. users --
create sequence users_id_seq
	start with 1
	increment BY 1
	no minvalue
	no maxvalue
	cache 1;

create table users (
    id int not null default nextval('users_id_seq'),
    userid varchar(32) not null,
    password varchar(32) not null,
    name varchar(32),
    email varchar(32) not null,
    group_id int not null,
    primary key (id),
    foreign key (group_id) references groups (id)
);

insert into users (userid, password, name, email, group_id) values ('admin', 'admin123', 'Super Admin', 'biminglei@gmail.com', 1);
insert into users (userid, password, name, email, group_id) values ('test', 'test123', 'Tester', 'jokeservice@gmail.com', 2);



