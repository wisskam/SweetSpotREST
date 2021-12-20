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

INSERT INTO ingredient(name, amount, unit, recipe_id, created_at)
VALUES('ziemniaki', 1.0, 'kg', 1, '2021-11-12 12:00:0')