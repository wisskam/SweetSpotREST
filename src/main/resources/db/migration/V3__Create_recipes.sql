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

INSERT INTO recipe (id, name, description, steps, image_url, created_at, modified_at, book_id) VALUES (1, 'Gołąbki', 'Mmmm', 'Kapusta <br>Mielone<br>test', 'https://praktykulinarni.com/wp-content/uploads/2019/08/golabki-soczewica-ziemniaki.jpg', '2021-12-28 21:26:58', '2021-12-28 21:26:58', 2);
INSERT INTO recipe (id, name, description, steps, image_url, created_at, modified_at, book_id) VALUES (2, 'Jabłecznik', 'Lub szarlotka jak kto woli :) Wyjątkowo pyszna w jeśienne wieczory, idelanie pasuje do kawy czy herbaty w towarzystwie. Prosty przepis bez zbędnego pierdolenia <3', 'Obrać i pokroić japka<br>Wymieszać mąke, cukier i jajca<br>Rozciapciać ciasto na blaszce<br>Rozciapciać japka na cieście<br>Dodać ciasta<br>Piec na oko, bo ja nie wiem', 'https://d3iamf8ydd24h9.cloudfront.net/pictures/articles/2018/07/88438-v-900x556.jpg', '2021-12-28 21:34:37', '2021-12-28 21:34:37', 4);
INSERT INTO recipe (id, name, description, steps, image_url, created_at, modified_at, book_id) VALUES (3, 'Pierogi', 'Najlepsze pierogi pod słońcem', 'Przygotować ciasto<br>Podsmażyć mięso z cebulą<br>Rozwałkować ciasto i podzielić na małe kółka<br>Nadziać ciasto miesem i zalepić<br>Gotować we wrzącej wodzie 10 min po wypłynięciu', 'https://ocdn.eu/pulscms-transforms/1/Et5k9kpTURBXy8zYTg1NGQ5YTUwNmQ3MGUzNWVmM2E2N2FmOGJiNWM2Yy5qcGeTlQMAzKLNFEDNC2STBc0DFM0BvJMJpjZiZjgyYgaBoTAB/pierogi-z-kapusta.jpg', '2021-12-29 00:32:44', '2021-12-29 00:32:44', 3);
INSERT INTO recipe (id, name, description, steps, image_url, created_at, modified_at, book_id) VALUES (4, 'Naleśniki', 'Pyszna sprawa na śniadanie obiad i kolacje :)', 'Usmaż naleśniki', 'https://www.mojegotowanie.pl/media/cache/big/uploads/media/recipe/0002/13/nalesniki-fit.jpeg', '2021-12-29 00:34:06', '2021-12-29 00:34:06', 1);