DROP TABLE IF EXISTS usuario CASCADE;
DROP TABLE IF EXISTS livros CASCADE;
DROP TABLE IF EXISTS filmes_series CASCADE;

CREATE TABLE usuario (
     id_user SERIAL PRIMARY KEY,
     nome VARCHAR(150),
     email VARCHAR(100),
     dt_nascimento DATE,
     cpf VARCHAR(15),
     senha VARCHAR(12)
);

CREATE TABLE livros (
    id_livro SERIAL PRIMARY KEY,
    nome VARCHAR(150),
    autor VARCHAR(150),
    dt_inicio DATE,
    dt_final DATE,
    num_paginas INTEGER,
    genero VARCHAR(50),
    id_user INTEGER,
    FOREIGN KEY (id_user) REFERENCES usuario(id_user) ON DELETE CASCADE
);

CREATE TABLE filmes_series (
   id_programa SERIAL PRIMARY KEY,
   nome VARCHAR(150),
   isMovie BOOLEAN,
   num_temporadas INTEGER,
   genero VARCHAR(50),
   pais_origem VARCHAR(150),
   id_user INTEGER,
   FOREIGN KEY (id_user) REFERENCES usuario(id_user) ON DELETE CASCADE
);

INSERT INTO usuario (nome, email, dt_nascimento, cpf, senha) VALUES
     ('Julia Jaeger', 'julia.jaeger@gmail.com', '1995-03-22', '123.456.789-01', 'julia123'),
     ('Daniela Cremonese', 'daniela.cremonese@gmail.com', '1990-08-11', '234.567.890-12', 'dani456'),
     ('Julio Cezar', 'julio.cezar@gmail.com', '1988-06-03', '345.678.901-23', 'julio789'),
     ('Lucas Jaeger', 'lucas.jaeger@gmail.com', '1999-11-19', '456.789.012-34', 'lucas321'),
     ('Xaden Riorson', 'xaden.riorson@gmail.com', '2001-02-27', '567.890.123-45', 'xaden2024');

INSERT INTO livros (nome, autor, dt_inicio, dt_final, num_paginas, genero, id_user) VALUES
    ('Príncipe Cruel', 'Holly Black', '2024-01-05', '2024-01-20', 385, 'Fantasia', 1),
    ('A Empregada', 'Freida McFadden', '2024-02-10', '2024-02-18', 290, 'Suspense', 2),
    ('Cavalo de Troia', 'J.J. Benítez', '2024-03-01', '2024-04-01', 600, 'Ficção Histórica', 3),
    ('O Homem de Giz', 'C.J. Tudor', '2024-04-10', '2024-04-25', 320, 'Mistério', 4),
    ('Quarta Asa', 'Rebecca Yarros', '2024-05-02', '2024-05-21', 528, 'Fantasia', 5);


INSERT INTO filmes_series (nome, isMovie, num_temporadas, genero, pais_origem, id_user) VALUES
    ('Oppenheimer', TRUE, NULL, 'Drama', 'EUA', 1),
    ('Dark', FALSE, 3, 'Ficção Científica', 'Alemanha', 2),
    ('Breaking Bad', FALSE, 5, 'Crime', 'EUA', 3),
    ('Interstellar', TRUE, NULL, 'Ficção Científica', 'EUA', 4),
    ('Avatar: O Caminho da Água', TRUE, NULL, 'Aventura', 'EUA', 5);
