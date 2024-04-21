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

USE SGCO;

CREATE TABLE IF NOT EXISTS ItemPedido (
    id INT AUTO_INCREMENT PRIMARY KEY,
    quantidade INT NOT NULL,
    precoTotal DECIMAL(10, 2) NOT NULL,
    produto_id INT,
    compra_id INT,
    FOREIGN KEY (produto_id) REFERENCES Produto(id),
    FOREIGN KEY (compra_id) REFERENCES Compra(id)
);

CREATE TABLE IF NOT EXISTS Usuario (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(255) NOT NULL,
    email VARCHAR(255) NOT NULL UNIQUE,
    senha VARCHAR(255) NOT NULL,
    endereco TEXT,
    admin BOOLEAN DEFAULT FALSE
);

USE SGCO;
CREATE TABLE IF NOT EXISTS Entrega (
    id INT AUTO_INCREMENT PRIMARY KEY,
    endereco TEXT NOT NULL,
    status VARCHAR(255) NOT NULL
);

CREATE TABLE IF NOT EXISTS Pagamento (
    id INT AUTO_INCREMENT PRIMARY KEY,
    tipo VARCHAR(255) NOT NULL,
    status VARCHAR(255) NOT NULL
);

CREATE TABLE IF NOT EXISTS Compra (
    id INT AUTO_INCREMENT PRIMARY KEY,
    data DATE NOT NULL,
    status VARCHAR(255) NOT NULL,
    entrega_id INT,
    pagamento_id INT,
    usuario_id INT,
    FOREIGN KEY (entrega_id) REFERENCES Entrega(id),
    FOREIGN KEY (pagamento_id) REFERENCES Pagamento(id),
    FOREIGN KEY (usuario_id) REFERENCES Usuario(id)
);