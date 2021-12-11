create table book
(
    id          integer  not null auto_increment,
    created_at  datetime,
    modified_at datetime not null,
    name        varchar(255),
    primary key (id)
) engine=InnoDB