CREATE TABLE author (
    id BIGINT NOT NULL AUTO_INCREMENT,
    name VARCHAR(150) NOT NULL,
    enabled BOOLEAN DEFAULT TRUE,
    nationality_id BIGINT NOT NULL,
    created_at DATETIME NOT NULL,
    updated_at DATETIME NOT NULL,
    PRIMARY KEY (id),
    CONSTRAINT fk_author_nationality FOREIGN KEY (nationality_id)
        REFERENCES nationality(id)

);
