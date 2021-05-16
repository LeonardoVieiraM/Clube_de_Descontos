CREATE TABLE Assinatura (
 	id SERIAL NOT NULL,
 	nome VARCHAR(60) NOT NULL,	
	PRIMARY KEY (id)
);

CREATE TABLE Cliente (
 	id SERIAL NOT NULL,
 	email VARCHAR(100) NOT NULL,
 	nome VARCHAR(120) NOT NULL,
 	nascimento DATE NOT NULL,
 	senha VARCHAR(100) NOT NULL,
 	id_assinatura int NOT NULL,
 	PRIMARY KEY (id),
	UNIQUE (email),
	FOREIGN KEY (id_assinatura) REFERENCES Assinatura (id)
);
 
CREATE TABLE Loja (
 	id SERIAL NOT NULL,
 	email VARCHAR(100) NOT NULL,
 	nome VARCHAR(120) NOT NULL,
 	site VARCHAR(120) NOT NULL,
 	senha VARCHAR(100) NOT NULL,
 	PRIMARY KEY (id),
	UNIQUE (email)
);

CREATE TABLE Cupom (
	id SERIAL NOT NULL,
	codigo VARCHAR(30) NOT NULL,
	desconto float NOT NULL,
	estoque int NOT NULL,
	id_loja int NOT NULL,
	id_assinatura int NOT NULL,
	PRIMARY KEY (id),
    UNIQUE (codigo),
	FOREIGN KEY (id_loja) REFERENCES Loja (id),
	FOREIGN KEY (id_assinatura) REFERENCES Assinatura (id)
);

CREATE TABLE Historico (
	id_cliente int NOT NULL,
	id_codigo int NOT NULL,
	FOREIGN KEY (id_cliente) REFERENCES Cliente (id),
	FOREIGN KEY (id_codigo) REFERENCES Cupom (id),
    CONSTRAINT pk_cliente_cupom PRIMARY KEY (id_cliente,id_codigo)
);
