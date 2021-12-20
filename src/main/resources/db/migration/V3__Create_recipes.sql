CREATE TABLE recipe
(
    id            INT AUTO_INCREMENT NOT NULL,
    name          VARCHAR(255)       NULL,
    `description` VARCHAR(255)       NULL,
    steps         LONGTEXT           NULL,
    image_url     VARCHAR(255)       NULL,
    created_at    datetime           NULL,
    modified_at   datetime           NULL,
    book_id       INT                NULL,
    CONSTRAINT pk_recipe PRIMARY KEY (id)
);

ALTER TABLE recipe
    ADD CONSTRAINT uc_recipe_id UNIQUE (id);

ALTER TABLE recipe
    ADD CONSTRAINT FK_RECIPE_ON_BOOK FOREIGN KEY (book_id) REFERENCES book (id);

INSERT INTO recipe(name, description, steps, image_url, created_at, modified_at, book_id)
VALUES ('Pierogi', 'Jak u mamy :)', '1. Idz do mamy 2. Poproś o pierogi',
        'https://ocdn.eu/pulscms-transforms/1/Et5k9kpTURBXy8zYTg1NGQ5YTUwNmQ3MGUzNWVmM2E2N2FmOGJiNWM2Yy5qcGeTlQMAzKLNFEDNC2STBc0DFM0BvJMJpjZiZjgyYgaBoTAB/pierogi-z-kapusta.jpg',
        '2021-12-12 14:22:11', '2021-12-12 14:22:11', 1)