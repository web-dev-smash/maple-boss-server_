alter table boss
    add constraint uq_class_name unique (name, class);
