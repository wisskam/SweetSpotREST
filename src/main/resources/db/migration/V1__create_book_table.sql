create table book
(
    id          integer  not null auto_increment,
    created_at  datetime,
    modified_at datetime not null,
    name        varchar(255),
    subtitle    varchar(255),
    description varchar(512),
    image_url    varchar(512),
    primary key (id)
) engine=InnoDB