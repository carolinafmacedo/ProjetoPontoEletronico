CREATE DATABASE pontoeletronico;

CREATE TABLE funcionario (
    id SERIAL PRIMARY KEY,
    nome VARCHAR(100) NOT NULL,
    cargo VARCHAR(50) NOT NULL,
    setor VARCHAR(50) NOT NULL
);

CREATE TABLE registroPonto (
    id SERIAL PRIMARY KEY,
    funcionarioId INT REFERENCES funcionario(id) ON DELETE CASCADE,
    dataHora TIMESTAMP NOT NULL
);

