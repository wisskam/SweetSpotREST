CREATE TABLE ingredient
(
    id         INT AUTO_INCREMENT NOT NULL,
    name       VARCHAR(255)       NULL,
    amount     DOUBLE             NULL,
    unit       VARCHAR(255)       NULL,
    recipe_id  INT                NULL,
    created_at datetime           NULL,
    CONSTRAINT pk_ingredient PRIMARY KEY (id)
);

ALTER TABLE ingredient
    ADD CONSTRAINT uc_ingredient_id UNIQUE (id);

ALTER TABLE ingredient
    ADD CONSTRAINT FK_INGREDIENT_ON_RECIPE FOREIGN KEY (recipe_id) REFERENCES recipe (id);


INSERT INTO ingredient(name, amount, unit, recipe_id, created_at) VALUES('mięso mielone', 1.0, 'kg', 1, '2021-11-12 12:00:0');
INSERT INTO ingredient(name, amount, unit, recipe_id, created_at) VALUES('kapusta', 1.0, 'szt', 1, '2021-11-12 12:00:0');
INSERT INTO ingredient(name, amount, unit, recipe_id, created_at) VALUES('majeranek', 1.0, 'szt', 1, '2021-11-12 12:00:0');
INSERT INTO ingredient(name, amount, unit, recipe_id, created_at) VALUES('sól', 1.0, 'szt', 1, '2021-11-12 12:00:0');
INSERT INTO ingredient(name, amount, unit, recipe_id, created_at) VALUES('pieprz', 1.0, 'szt', 1, '2021-11-12 12:00:0');

INSERT INTO ingredient(name, amount, unit, recipe_id, created_at) VALUES('jabłka', 2.0, 'kg', 2, '2021-11-12 12:00:0');
INSERT INTO ingredient(name, amount, unit, recipe_id, created_at) VALUES('mąka', 1.0, 'kg', 2, '2021-11-12 12:00:0');
INSERT INTO ingredient(name, amount, unit, recipe_id, created_at) VALUES('drożdze', 1.0, 'szt', 2, '2021-11-12 12:00:0');
INSERT INTO ingredient(name, amount, unit, recipe_id, created_at) VALUES('jajka', 2.0, 'szt', 2, '2021-11-12 12:00:0');
INSERT INTO ingredient(name, amount, unit, recipe_id, created_at) VALUES('mleko', 0.25, 'l', 2, '2021-11-12 12:00:0');
INSERT INTO ingredient(name, amount, unit, recipe_id, created_at) VALUES('cukier', 0.25, 'kg', 2, '2021-11-12 12:00:0');

INSERT INTO ingredient(name, amount, unit, recipe_id, created_at) VALUES('mąka', 1.0, 'kg', 3, '2021-11-12 12:00:0');
INSERT INTO ingredient(name, amount, unit, recipe_id, created_at) VALUES('mięso mielone', 1.0, 'kg', 3, '2021-11-12 12:00:0');
INSERT INTO ingredient(name, amount, unit, recipe_id, created_at) VALUES('woda', 1.0, 'l', 3, '2021-11-12 12:00:0');

INSERT INTO ingredient(name, amount, unit, recipe_id, created_at) VALUES('mąka', 1.0, 'kg', 4, '2021-11-12 12:00:0');
INSERT INTO ingredient(name, amount, unit, recipe_id, created_at) VALUES('cukier wanilinowy', 0.1, 'kg', 4, '2021-11-12 12:00:0');
INSERT INTO ingredient(name, amount, unit, recipe_id, created_at) VALUES('mleko', 0.25, 'l', 4, '2021-11-12 12:00:0');
INSERT INTO ingredient(name, amount, unit, recipe_id, created_at) VALUES('jajko', 1.0, 'szt', 4, '2021-11-12 12:00:0');

