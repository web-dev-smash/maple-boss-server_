create table party
(
    id          bigint      not null auto_increment,
    create_at   datetime(6),
    description varchar(50),
    name        varchar(15) not null,
    status      varchar(20),
    leader_id   bigint,
    primary key (id)
);

create table party_members
(
    party_id   bigint not null,
    members_id bigint not null
);

alter table party
    add constraint FK4f41fojj339pedktq75eytc5u foreign key (leader_id) references user (id);

alter table party_members
    add constraint FK663m3mf3umbsohvumap692dlu foreign key (members_id) references user (id);

alter table party_members
    add constraint FKbj7ffwnnid9vl17e4om4iw36g foreign key (party_id) references party (id);
