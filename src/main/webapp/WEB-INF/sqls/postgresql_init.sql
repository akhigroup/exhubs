/* 0. initiation */
drop table exam_paper_question_subject;
drop table exam_papers;
drop sequence exam_papers_id_seq;
drop table exam_types;
drop sequence exam_types_id_seq;
drop table question_subject_question_tag;
drop table question_tags;
drop sequence question_tags_id_seq;
drop table question_details;
drop sequence question_details_id_seq;
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


/* 1. groups */
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

insert into groups (name, description) values ('GROUP_SYSTEM_ADMIN', '系统管理员组');
insert into groups (name, description) values ('GROUP_NORMAL_USER', '普通用户组');


/* 2. roles */
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

insert into roles (name, description) values ('ROLE_USER', '注册用户');
insert into roles (name, description) values ('ROLE_USER_GROUP_MANAGER', '用户组管理');
insert into roles (name, description) values ('ROLE_USER_MANAGER', '用户管理');
insert into roles (name, description) values ('ROLE_EXAM_MANAGER', '考试管理');
insert into roles (name, description) values ('ROLE_QUESTION_MANAGER', '题目管理');


/* 3. group_role */
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
insert into group_role (group_id, role_id) values (1, 4);
insert into group_role (group_id, role_id) values (1, 5);
insert into group_role (group_id, role_id) values (2, 1);


/* 4. users */
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

insert into users (userid, password, name, email, group_id, active_flg, create_date, update_datetime) values ('admin', 'admin123', '系统管理员', 'biminglei@gmail.com', 1, TRUE, '2013-06-01', null);
insert into users (userid, password, name, email, group_id, active_flg, create_date, update_datetime) values ('test', 'test123', 'Tester', 'biminglei@gmail.com', 2, FALSE, '2013-06-02', null);



/* 5. question_types */
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

insert into question_types (name, description) values ('SCQ', '单选题');
insert into question_types (name, description) values ('MCQ', '多选题');
insert into question_types (name, description) values ('TFQ', '判断题');
insert into question_types (name, description) values ('BFQ', '填空题');
insert into question_types (name, description) values ('EQ', '简答题');


/* 6. question_subjects */
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

insert into question_subjects (content, total_score, question_type_id, user_id) values ('电视新闻上常见巴勒斯坦总统阿拉法特等中东国家领导人，他们往往头上围着头巾，有的身着长袍。根据这段文字，回答小题。', 2, 1, 1);
insert into question_subjects (content, total_score, question_type_id, user_id) values ('夜盲症是缺少维生素___；为了防治地方性甲状腺肿，应该食用含有___的食盐。', 10, 4, 1);
insert into question_subjects (content, total_score, question_type_id, user_id) values ('某山地垂直自然带数多，垂直带谱完整。', 2, 2, 1);
insert into question_subjects (content, total_score, question_type_id, user_id) values ('北京时间就是东八区的区时？', 1, 3, 1);
insert into question_subjects (content, total_score, question_type_id, user_id) values ('近年来，相继发生的"毒奶粉","瘦肉精","地沟油"等恶性食品药品安全事件足以表明,诚信的缺失、道德的滑坡已经到了严重的地步，规范市场秩序势在必行。', 8, 5, 1);


/* 7. question_answers */
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

insert into question_answers (binary_value, short_text_value, long_text_value, comment) values (1, null, null, null);
insert into question_answers (binary_value, short_text_value, long_text_value, comment) values (2, null, null, null);
insert into question_answers (binary_value, short_text_value, long_text_value, comment) values (null, 'A', null, null);
insert into question_answers (binary_value, short_text_value, long_text_value, comment) values (null, '碘', null, null);
insert into question_answers (binary_value, short_text_value, long_text_value, comment) values (5, null, null, null);
insert into question_answers (binary_value, short_text_value, long_text_value, comment) values (1, null, null, null);
insert into question_answers (binary_value, short_text_value, long_text_value, comment) values (null, null, '(1)国家运用经济手段，法律手段，行政手段实施宏观调控，以有效地弥补市场调节的缺陷。(4分); (2)引导企业诚信经营，建立健全以道德为支撑，法律为保障的社会信用制度，这是规范市场秩序的治本之策（4分）', null);


/* 8. question_headers */
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
	question_subject_id int not null,
	question_type_id int not null,
	question_answer_id int not null,
	primary key (id),
	foreign key (question_subject_id) references question_subjects (id),
	foreign key (question_type_id) references question_types (id),
	foreign key (question_answer_id) references question_answers (id)
);

insert into question_headers (description, score, question_subject_id, question_type_id, question_answer_id) values ('你认为这种服装是', 1, 1, 1, 1);
insert into question_headers (description, score, question_subject_id, question_type_id, question_answer_id) values ('你认为这种服装可以起的作用是', 1, 1, 1, 2);
insert into question_headers (description, score, question_subject_id, question_type_id, question_answer_id) values ('___', 5, 2, 4, 3);
insert into question_headers (description, score, question_subject_id, question_type_id, question_answer_id) values ('___', 5, 2, 4, 4);
insert into question_headers (description, score, question_subject_id, question_type_id, question_answer_id) values ('这说明该山地', 2, 3, 2, 5);
insert into question_headers (description, score, question_subject_id, question_type_id, question_answer_id) values ('请判断正误', 1, 4, 3, 6);
insert into question_headers (description, score, question_subject_id, question_type_id, question_answer_id) values ('对于国家来说，如何规范市场秩序？', 8, 5, 5, 7);


/* 9. question_details */
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

insert into question_details (content, sort_order, question_header_id) values ('民族传统服装的象征', 1, 1);
insert into question_details (content, sort_order, question_header_id) values ('一种军装', 2, 1);
insert into question_details (content, sort_order, question_header_id) values ('完全是个人的爱好', 3, 1);
insert into question_details (content, sort_order, question_header_id) values ('国际上流行的装饰', 4, 1);
insert into question_details (content, sort_order, question_header_id) values ('遮风挡雨', 1, 2);
insert into question_details (content, sort_order, question_header_id) values ('遮阳挡风', 2, 2);
insert into question_details (content, sort_order, question_header_id) values ('保暖御寒', 3, 2);
insert into question_details (content, sort_order, question_header_id) values ('抵御风雪', 4, 2);
insert into question_details (content, sort_order, question_header_id) values ('', 1, 3);
insert into question_details (content, sort_order, question_header_id) values ('', 2, 4);
insert into question_details (content, sort_order, question_header_id) values ('纬度较低', 1, 5);
insert into question_details (content, sort_order, question_header_id) values ('纬度较高', 2, 5);
insert into question_details (content, sort_order, question_header_id) values ('海拔较高', 3, 5);
insert into question_details (content, sort_order, question_header_id) values ('海拔较低', 4, 5);
insert into question_details (content, sort_order, question_header_id) values ('正确', 1, 6);
insert into question_details (content, sort_order, question_header_id) values ('错误', 2, 6);
insert into question_details (content, sort_order, question_header_id) values ('', 1, 7);


/* 10. question_tags */
create sequence question_tags_id_seq
	start with 1
	increment BY 1
	no minvalue
	no maxvalue
	cache 1;

create table question_tags (
	id int not null default nextval('question_tags_id_seq'),
	name varchar(32) not null,
	primary key (id)
);


/* 11. question_subject_question_tag */
create table question_subject_question_tag (
	question_subject_id int not null,
	question_tag_id int not null,
	primary key (question_subject_id, question_tag_id),
	foreign key (question_subject_id) references question_subjects (id),
	foreign key (question_tag_id) references question_tags (id)
);


/* 12. exam_types */
create sequence exam_types_id_seq
	start with 1
	increment BY 1
	no minvalue
	no maxvalue
	cache 1;

create table exam_types (
	id int not null default nextval('exam_types_id_seq'),
	name varchar(32) not null,
	description varchar(32),
	primary key (id)
);

insert into exam_types (name, description) values ('计算机', '计算机类');


/* 13. exam_papers */
create sequence exam_papers_id_seq
	start with 1
	increment BY 1
	no minvalue
	no maxvalue
	cache 1;

create table exam_papers (
	id int not null default nextval('exam_papers_id_seq'),
	name varchar(32) not null,
	description varchar(32),
	create_date date not null,
	active_flg boolean default TRUE,
	user_id int not null,
	exam_type_id int not null,
	primary key (id),
	foreign key (user_id) references users (id),
	foreign key (exam_type_id) references exam_types (id)
);

insert into exam_papers (name, description, create_date, active_flg, user_id, exam_type_id) values ('计算机期中考试(2013)', '测试试卷', '2013-06-01', TRUE, 1, 1);


/* 14. exam_paper_question_subject */
create table exam_paper_question_subject (
	exam_paper_id int not null,
	question_subject_id int not null,
	sort_order int not null,
	primary key (exam_paper_id, question_subject_id),
	foreign key (exam_paper_id) references exam_papers (id),
	foreign key (question_subject_id) references question_subjects (id)
);

insert into exam_paper_question_subject (exam_paper_id, question_subject_id, sort_order) values (1, 1, 1);
insert into exam_paper_question_subject (exam_paper_id, question_subject_id, sort_order) values (1, 2, 1);

