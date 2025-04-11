SET  foreign_key_checks = 0;
DELETE FROM nationality;
DELETE FROM genre;
DELETE FROM author;
DELETE FROM book;

SET foreign_key_checks = 1;

ALTER TABLE nationality AUTO_INCREMENT = 1;
ALTER TABLE genre AUTO_INCREMENT = 1;
ALTER TABLE author AUTO_INCREMENT = 1;
ALTER TABLE book AUTO_INCREMENT = 1;

INSERT INTO nationality (name, enabled, created_at, updated_at)
VALUES
    ('Brasil', TRUE, NOW(), NOW()),
    ('Espanha', TRUE, NOW(), NOW()),
    ('Reino Unido', TRUE, NOW(), NOW()),
    ('Estados Unidos', TRUE, NOW(), NOW());

INSERT INTO genre (name, enabled, created_at, updated_at)
VALUES
    ('Fantasía', TRUE, NOW(), NOW()),
    ('Ciencia Ficción', TRUE, NOW(), NOW()),
    ('Distopía', TRUE, NOW(), NOW()),
    ('Drama', TRUE, NOW(), NOW()),
    ('Romance', TRUE, NOW(), NOW());

INSERT INTO author (name, enabled, nationality_id, created_at, updated_at)
VALUES
    ('Paulo Coelho', TRUE, 1, NOW(), NOW()), -- Brasil
    ('Miguel de Cervantes', TRUE, 2, NOW(), NOW()), -- Espanha
    ('J.K. Rowling', TRUE, 3, NOW(), NOW()), -- Reino Unido
    ('Isaac Asimov', TRUE, 4, NOW(), NOW()); -- Estados Unidos

INSERT INTO book (title, description, year, genre_id, author_id, photo_url, enabled, created_at, updated_at)
VALUES
    ('El alquimista', 'Un viaje espiritual de descubrimiento personal.', 1988, 5, 1, 'https://example.com/el_alquimista.jpg', TRUE, NOW(), NOW()), -- Romance
    ('Don Quijote', 'Una novela clásica sobre un caballero y su escudero.', 1605, 4, 2, 'https://example.com/don_quijote.jpg', TRUE, NOW(), NOW()), -- Drama
    ('Harry Potter y la piedra filosofal', 'Un niño descubre que es un mago.', 1997, 1, 3, 'https://example.com/harry_potter.jpg', TRUE, NOW(), NOW()), -- Fantasía
    ('Fundación', 'Una obra maestra de la ciencia ficción.', 1951, 2, 4, 'https://example.com/fundacion.jpg', TRUE, NOW(), NOW()); -- Ciencia Ficción