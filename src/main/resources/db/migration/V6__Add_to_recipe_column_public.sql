ALTER TABLE recipe
    ADD COLUMN published BIT(1) DEFAULT 0;

UPDATE recipe
SET published = true
WHERE 1=1;