ALTER TABLE book
    ADD COLUMN user_id int,
    ADD CONSTRAINT FK_BOOK_ON_USER FOREIGN KEY (user_id) REFERENCES user (id);

UPDATE book
SET user_id = 1