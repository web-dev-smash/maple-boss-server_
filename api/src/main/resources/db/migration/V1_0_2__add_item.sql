create table item
(
    id        bigint not null auto_increment,
    create_at datetime(6),
    name      varchar(50),
    status    varchar(20),
    type      varchar(20),
    primary key (id)
);
