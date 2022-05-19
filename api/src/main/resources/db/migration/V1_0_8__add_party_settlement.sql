alter table boss_item
    add column meso bigint;

create table party_settlement
(
    id        bigint not null auto_increment,
    create_at datetime(6),
    status    varchar(20),
    boss_id   bigint,
    party_id  bigint,
    primary key (id)
);

create table party_settlement_dividends
(
    party_settlement_id bigint not null,
    member_id           bigint,
    rate                numeric(3, 0)
);

create table party_settlement_items
(
    party_settlement_id bigint  not null,
    amount              integer not null,
    meso                bigint  not null,
    random_boss_item_id bigint
);

alter table user
    add column cert_code varchar(30) not null;

alter table party_settlement
    add constraint FKi15owtuqr2u20oui0gx69ecq foreign key (boss_id) references boss (id);

alter table party_settlement
    add constraint FKaya9mcdmrh4g6ej5eqb15kyof foreign key (party_id) references party (id);

alter table party_settlement_dividends
    add constraint FKbyr15hvhgdnq380j1kw7y0qjc foreign key (member_id) references user (id);

alter table party_settlement_dividends
    add constraint FKa61ofvj59jknio1cu0p42soiq foreign key (party_settlement_id) references party_settlement (id);

alter table party_settlement_items
    add constraint FKj0m7ofevstefuyctmq8v4swph foreign key (random_boss_item_id) references boss_item (id);

alter table party_settlement_items
    add constraint FKkdfdcepmhtdjynkb280kkkrqt foreign key (party_settlement_id) references party_settlement (id);
