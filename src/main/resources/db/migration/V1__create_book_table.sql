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
) engine=InnoDB;

INSERT INTO book (id, created_at, modified_at, name, subtitle, description, image_url)
    VALUES (1, '2021-12-12 14:22:11', '2021-12-12 14:25:41', 'Przepisy śniadaniowe', '', 'Pyszne kanapki, pasty i proste dania', 'https://cdn.vuetifyjs.com/images/cards/cooking.png');
INSERT INTO book (id, created_at, modified_at, name, subtitle, description, image_url)
    VALUES (2, '2021-12-12 14:22:11', '2021-12-12 14:30:52', 'Dania obiadowe', 'Kotleciki!', 'Dania dla 2-4 osób', 'https://esmmweighless.com/wp-content/uploads/2019/12/Carolyn-Cooking-Blog.jpg');
INSERT INTO book (id, created_at, modified_at, name, subtitle, description, image_url)
    VALUES (3, '2021-12-12 14:35:11', '2021-12-12 14:36:25', 'Przekąski', 'Idealne na imprezę!', '', 'https://poprostupycha.com.pl/wp-content/uploads/2021/09/tortilla_4.jpg');
INSERT INTO book (id, created_at, modified_at, name, subtitle, description, image_url)
    VALUES (4, '2021-12-12 20:35:43', '2021-12-12 20:35:43', 'Desery', 'Placuszki i naleśniki', null, 'https://cdn.vuetifyjs.com/images/cards/cooking.png');