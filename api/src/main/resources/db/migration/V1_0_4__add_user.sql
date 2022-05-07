create table user
(
    id        bigint not null auto_increment,
    create_at datetime(6),
    email     varchar(30),
    login_id  varchar(15),
    name      varchar(30),
    nickname  varchar(7),
    password  varchar(100),
    status    varchar(20),
    primary key (id)
);

alter table user
    add constraint uq_login_id unique (login_id);

alter table user
    add constraint uq_email unique (email);
