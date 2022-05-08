alter table user
    modify email varchar(30) not null;

alter table user
    modify login_id varchar(15) not null;

alter table user
    modify name varchar(30) not null;

alter table user
    modify nickname varchar(7) not null;

alter table user
    modify password varchar(100) not null;
