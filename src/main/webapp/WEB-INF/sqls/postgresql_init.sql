-- 0. initiation --

drop table question_details;
drop sequence question_details_id_seq;
drop table question_subject_question_header;
drop table question_headers;
drop sequence question_headers_id_seq;
drop table question_answers;
drop sequence question_answers_id_seq;
drop table question_subjects;
drop sequence question_subjects_id_seq;
drop table question_types;
drop sequence question_types_id_seq;
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
    active_flg boolean default TRUE,
    create_date date not null,
    update_datetime timestamp null,
    primary key (id),
    foreign key (group_id) references groups (id)
);

insert into users (userid, password, name, email, group_id, active_flg, create_date, update_datetime) values ('admin', 'admin123', 'Super Admin', 'biminglei@gmail.com', 1, TRUE, '2013-06-01', null);
insert into users (userid, password, name, email, group_id, active_flg, create_date, update_datetime) values ('test', 'test123', 'Tester', 'jokeservice@gmail.com', 2, FALSE, '2013-06-02', null);


-------------------------------------------

-- 5. question_types --
create sequence question_types_id_seq
	start with 1
	increment BY 1
	no minvalue
	no maxvalue
	cache 1;

create table question_types (
	id int not null default nextval('question_types_id_seq'),
	name varchar(32) not null,
	description varchar(32),
	primary key (id)
);

insert into question_types (name, description) values ('SCQ', 'single choice question');
insert into question_types (name, description) values ('MCQ', 'multiple choice question');
insert into question_types (name, description) values ('TFQ', 'true false question');
insert into question_types (name, description) values ('BFQ', 'blank filling question');
insert into question_types (name, description) values ('EQ', 'essay question');


-- 6. question_subjects --
create sequence question_subjects_id_seq
	start with 1
	increment BY 1
	no minvalue
	no maxvalue
	cache 1;

create table question_subjects (
	id int not null default nextval('question_subjects_id_seq'),
	content varchar(512) not null,
	total_score int not null,
	question_type_id int not null,
	user_id int not null,
	primary key (id),
	foreign key (question_type_id) references question_types (id),
	foreign key (user_id) references users (id)
);

insert into question_subjects (content, total_score, question_type_id, user_id) values ('What is your favourite fruit and sport?', 2, 1, 1);


-- 7. question_answers --
create sequence question_answers_id_seq
	start with 1
	increment BY 1
	no minvalue
	no maxvalue
	cache 1;

create table question_answers (
	id int not null default nextval('question_answers_id_seq'),
	binary_value int,
	short_text_value varchar(32),
	long_text_value varchar(2000),
	comment varchar(64),
	primary key (id)
);

insert into question_answers (binary_value, short_text_value, long_text_value, comment) values (4, null, null, null);
insert into question_answers (binary_value, short_text_value, long_text_value, comment) values (2, null, null, null);


-- 8. question_headers --
create sequence question_headers_id_seq
	start with 1
	increment BY 1
	no minvalue
	no maxvalue
	cache 1;

create table question_headers (
	id int not null default nextval('question_headers_id_seq'),
	description varchar(32),
	score int not null,
	question_type_id int not null,
	question_answer_id int not null,
	primary key (id),
	foreign key (question_type_id) references question_types (id),
	foreign key (question_answer_id) references question_answers (id)
);

insert into question_headers (description, score, question_type_id, question_answer_id) values ('Choose a fruit here:', 1, 1, 1);
insert into question_headers (description, score, question_type_id, question_answer_id) values ('Choose a sport here:', 1, 1, 2);


-- 9. question_subject_question_header --
create table question_subject_question_header (
	question_subject_id int not null,
	question_header_id int not null,
	primary key (question_subject_id, question_header_id),
	foreign key (question_subject_id) references question_subjects (id),
	foreign key (question_header_id) references question_headers (id)
);

insert into question_subject_question_header (question_subject_id, question_header_id) values (1, 1);
insert into question_subject_question_header (question_subject_id, question_header_id) values (1, 2);


-- 10. question_details --
create sequence question_details_id_seq
	start with 1
	increment BY 1
	no minvalue
	no maxvalue
	cache 1;

create table question_details (
	id int not null default nextval('question_details_id_seq'),
	content varchar(64) not null,
	sort_order int not null,
	question_header_id int not null,
	primary key (id),
	foreign key (question_header_id) references question_headers (id)
);

insert into question_details (content, sort_order, question_header_id) values ('Apple', 1, 1);
insert into question_details (content, sort_order, question_header_id) values ('Orange', 2, 1);
insert into question_details (content, sort_order, question_header_id) values ('Pear', 3, 1);
insert into question_details (content, sort_order, question_header_id) values ('Grape', 4, 1);
insert into question_details (content, sort_order, question_header_id) values ('swimming', 1, 2);
insert into question_details (content, sort_order, question_header_id) values ('tennis', 2, 2);
insert into question_details (content, sort_order, question_header_id) values ('football', 3, 2);
insert into question_details (content, sort_order, question_header_id) values ('golf', 4, 2);


