create table testdb;

create table users (
  userid int(11) not null primary key
, username varchar(100) not null
, user_type varchar(45)
);

create table useraudit (
  auditid int(11) not null primary key
, userid int(11) not null
, addedon date not null
, status varchar(45) not null
, old_value varchar(45)
, new_value varchar(45)
);

insert into users values(1, "user11111111", null);
insert into users values(2, "user22222222", "general");
insert into users values(3, "user33333333", "expert");
commit;

