create table role (
                      id integer not null auto_increment,
                      created datetime,
                      modified datetime,
                      name varchar(255),
                      deleted datetime,
                      primary key (id)
) engine=InnoDB;
create table user (
                      id integer not null auto_increment,
                      created datetime,
                      modified datetime,
                      email varchar(255),
                      password varchar(255),
                      first_name varchar(255),
                      last_name varchar(255),
    deleted datetime,
    primary key (id)
    ) engine=InnoDB;
create table user_role (
                           user_id integer,
                           role_id integer,
                           primary key (user_id, role_id)
) engine=InnoDB;
alter table user_role add constraint user_role_fk0 foreign key (user_id)
    references user (id);
alter table user_role add constraint user_role_fk1 foreign key (role_id)
    references role (id);
insert into role (created, modified, name)
values (now(), now(), "ROLE_USER");
insert into role (created, modified, name)
values (now(), now(), "ROLE_MODERATOR");
insert into role (created, modified, name)
values (now(), now(), "ROLE_ADMIN");

INSERT INTO user (id, created, modified, email, password, first_name, last_name, deleted) VALUES (1, '2021-12-22 22:20:47', '2021-12-22 22:20:47', 'admin@test.pl', '$2a$10$2Jj.0LCoKK/ENY2BHzKtHuD1TZPPq3Nc6s/5ZL5zl9kO6OLAag9iO', 'John', 'Adminolta', null);
INSERT INTO user (id, created, modified, email, password, first_name, last_name, deleted) VALUES (2, '2021-12-22 22:20:47', '2021-12-22 22:20:47', 'testuser@test.pl', '$2a$10$00q/IpfirpbXAKTW5ofTbuqXS1iOdTBnQydNnsFE06f6GDbCYLhA.', 'Jerry', 'Martinez', null);

insert into user_role (user_id, role_id)
    values (1 , 1);
insert into user_role (user_id, role_id)
   values (2 , 3);