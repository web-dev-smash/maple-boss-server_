alter table user
    add cert_code_sent_at datetime(6) null;

alter table user
    change create_at created_at datetime(6) null;