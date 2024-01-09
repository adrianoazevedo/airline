CREATE EXTENSION IF NOT EXISTS "uuid-ossp";

CREATE TABLE IF NOT EXISTS cidade (
    id UUID PRIMARY KEY DEFAULT uuid_generate_v4(),
    nome VARCHAR(100) NOT NULL,
    uf VARCHAR(2) NOT NULL
);

CREATE TABLE IF NOT EXISTS aeroporto (
    id UUID PRIMARY KEY DEFAULT uuid_generate_v4(),
    nome VARCHAR(250) NOT NULL,
    iata VARCHAR(5) UNIQUE NOT NULL,
    id_cidade UUID NOT NULL,
    CONSTRAINT "FK_AEROPORTO_ID_CIDADE" FOREIGN KEY (id_cidade) REFERENCES cidade (id)
);

CREATE TABLE IF NOT EXISTS passageiro (
    id UUID PRIMARY KEY DEFAULT uuid_generate_v4(),
    nome VARCHAR(50) NOT NULL,
    cpf VARCHAR(11) UNIQUE NOT NULL,
    data_nascimento DATE NOT NULL,
    data_criacao TIMESTAMP NOT NULL,
    data_atualizacao TIMESTAMP NOT NULL
);

CREATE TABLE IF NOT EXISTS comprador (
    id UUID PRIMARY KEY DEFAULT uuid_generate_v4(),
    nome VARCHAR(50) NOT NULL,
    cpf VARCHAR(11) UNIQUE NOT NULL,
    email VARCHAR(100) not null,
    data_nascimento DATE NOT NULL,
    data_criacao TIMESTAMP NOT NULL,
    data_atualizacao TIMESTAMP NOT NULL
);

CREATE TABLE IF NOT EXISTS usuario (
    id UUID PRIMARY KEY DEFAULT uuid_generate_v4(),
    login VARCHAR(50) UNIQUE NOT NULL,
    senha VARCHAR(255) not null,
    data_criacao TIMESTAMP NOT NULL,
    data_atualizacao TIMESTAMP NOT NULL
);

CREATE TABLE IF NOT EXISTS voo (
    id UUID PRIMARY KEY DEFAULT uuid_generate_v4(),
    numero VARCHAR(50) UNIQUE NOT NULL,
    id_aeroporto_origem UUID NOT NULL,
    id_aeroporto_destino UUID NOT NULL,
    data_hora_embarque TIMESTAMP NOT NULL,
    status INT NOT NULL,
    data_criacao TIMESTAMP NOT NULL,
    data_atualizacao TIMESTAMP NOT NULL,
    CONSTRAINT "FK_VOO_ID_AEROPORTO_ORIGEM" FOREIGN KEY (id_aeroporto_origem) REFERENCES aeroporto (id),
    CONSTRAINT "FK_VOO_ID_AEROPORTO_DESTINO" FOREIGN KEY (id_aeroporto_destino) REFERENCES aeroporto (id)
);

COMMENT ON COLUMN voo.status IS '1-ATIVO, 2-INATIVO';

CREATE TABLE IF NOT EXISTS voo_classe (
    id UUID PRIMARY KEY DEFAULT uuid_generate_v4(),
    id_voo UUID NOT NULL,
    classe INT NOT NULL,
    qtd_assentos INT NOT NULL,
    qtd_livres INT NOT NULL,
    valor DECIMAL(10,2) NOT NULL,
    data_criacao TIMESTAMP NOT NULL,
    data_atualizacao TIMESTAMP NOT NULL,
    CONSTRAINT "FK_VOO_CLASSE_ID_VOO" FOREIGN KEY (id_voo) REFERENCES voo (id)
);

COMMENT ON COLUMN voo_classe.classe IS '1-EXECUTIVA, 2-ECONOMICA';

CREATE TABLE IF NOT EXISTS passagem (
    id UUID PRIMARY KEY DEFAULT uuid_generate_v4(),
    numero VARCHAR(50) NOT NULL,
    num_compra VARCHAR(50) NOT NULL,
    id_comprador UUID NOT NULL,
    id_passageiro UUID NOT NULL,
    id_voo_classe UUID NOT NULL,
    valor_passagem DECIMAL(10,2) NOT NULL,
    valor_total DECIMAL(10,2) NOT NULL,
    num_bagagem VARCHAR(50) NULL,
    bag_despachada BOOLEAN,
    status INT NOT NULL,
    data_criacao TIMESTAMP NOT NULL,
    data_atualizacao TIMESTAMP NOT NULL,
    CONSTRAINT "FK_PASSAGEM_ID_COMPRADOR" FOREIGN KEY (id_comprador) REFERENCES comprador (id),
    CONSTRAINT "FK_PASSAGEM_ID_PASSAGEIRO" FOREIGN KEY (id_passageiro) REFERENCES passageiro (id),
    CONSTRAINT "FK_PASSAGEM_ID_VOO_CLASSE" FOREIGN KEY (id_voo_classe) REFERENCES voo_classe (id)
);

COMMENT ON COLUMN passagem.status IS '1-ATIVO, 2-INATIVO';