create table boss_item
(
    dtype     varchar(31) not null,
    id        bigint      not null auto_increment,
    create_at datetime(6),
    maximum   integer     not null,
    minimum   integer     not null,
    price     bigint,
    boss_id   bigint,
    item_id   bigint,
    primary key (id)
);

alter table boss_item
    add constraint uq_boss_item unique (boss_id, item_id);

alter table boss_item
    add constraint FK7ha3vfslotwvh9i4a7lk5e8fm foreign key (boss_id) references boss (id);

alter table boss_item
    add constraint FKrmg5sip07k47nm8vjqyj0jue4 foreign key (item_id) references item (id);
