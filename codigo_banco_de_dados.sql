CREATE DATABASE IF NOT EXISTS SGCO
    CHARACTER SET utf8mb4
    COLLATE utf8mb4_unicode_ci;

USE SGCO;

CREATE TABLE IF NOT EXISTS Produto (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(255) NOT NULL,
    descricao TEXT,
    preco DECIMAL(10, 2) NOT NULL,
    quantidadeEmEstoque INT NOT NULL
);