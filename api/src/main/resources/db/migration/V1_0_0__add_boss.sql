create table boss
(
    id              bigint  not null auto_increment,
    arcane_force    integer not null,
    class           varchar(255),
    death_limit     integer not null,
    entry_max_level integer not null,
    entry_min_level integer not null,
    hp_phase_four   bigint,
    hp_phase_one    bigint,
    hp_phase_three  bigint,
    hp_phase_two    bigint,
    level_          integer,
    name            varchar(255),
    primary key (id)
);
