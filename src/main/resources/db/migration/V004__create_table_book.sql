CREATE TABLE book (
    id BIGINT NOT NULL AUTO_INCREMENT,
    title VARCHAR(150) NOT NULL,
    description TEXT NOT NULL,
    year INT NOT NULL,
    genre_id BIGINT NOT NULL,
    author_id BIGINT NOT NULL,
    photo_url VARCHAR(100),
    enabled BOOLEAN DEFAULT TRUE,
    created_at DATETIME NOT NULL,
    updated_at DATETIME NOT NULL,
    PRIMARY KEY (id),
    CONSTRAINT fk_book_genre FOREIGN KEY (genre_id)
        REFERENCES genre(id),
    CONSTRAINT fk_book_author FOREIGN KEY (author_id)
        REFERENCES author(id)

);
