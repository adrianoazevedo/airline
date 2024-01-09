INSERT INTO usuario (login, senha, data_criacao, data_atualizacao) VALUES
    ('airline', '$2a$12$yxMaPQEhKwVosy3wtP5cnetPokuDU9rZLoH6Euu.UYZPoh52UCgey', now(), now());

INSERT INTO cidade (nome, uf) VALUES
    ('Rio Branco', 'AC'),
    ('Maceió', 'AL'),
    ('Macapá', 'AP'),
    ('Manaus', 'AM'),
    ('Salvador', 'BA'),
    ('Fortaleza', 'CE'),
    ('Vitória', 'ES'),
    ('Goiânia', 'GO'),
    ('São Luís', 'MA'),
    ('Cuiabá', 'MT'),
    ('Campo Grande', 'MS'),
    ('Belo Horizonte', 'MG'),
    ('Belém', 'PA'),
    ('João Pessoa', 'PB'),
    ('Curitiba', 'PR'),
    ('Recife', 'PE'),
    ('Teresina', 'PI'),
    ('Rio de Janeiro', 'RJ'),
    ('Natal', 'RN'),
    ('Porto Alegre', 'RS'),
    ('Porto Velho', 'RO'),
    ('Boa Vista', 'RR'),
    ('Florianópolis', 'SC'),
    ('São Paulo', 'SP'),
    ('Aracaju', 'SE'),
    ('Palmas', 'TO'),
    ('Brasília', 'DF');

INSERT INTO aeroporto (nome, iata, id_cidade) VALUES
	('Aeroporto Internacional de Brasília / Presidente Juscelino Kubitschek', 'BSB', (SELECT id FROM cidade WHERE uf = 'DF')),
	('Aeroporto Internacional de São Paulo / Guarulhos', 'GRU', (SELECT id FROM cidade WHERE uf = 'SP')),
	('Aeroporto Internacional do Rio de Janeiro / Galeão-Antônio Carlos Jobim', 'GIG', (SELECT id FROM cidade WHERE uf = 'RJ')),
	('Aeroporto Internacional de Salvador / Deputado Luis Eduardo Magalhães', 'SSA', (SELECT id FROM cidade WHERE uf = 'BA')),
	('Aeroporto Internacional de Florianópolis / Hercílio Luz', 'FLN', (SELECT id FROM cidade WHERE uf = 'SC')),
	('Aeroporto Internacional de Porto Alegre / Salgado Filho', 'POA', (SELECT id FROM cidade WHERE uf = 'RS')),
	('Aeroporto Internacional do Recife / Guararapes – Gilberto Freyre', 'REC', (SELECT id FROM cidade WHERE uf = 'PE')),
	('Aeroporto Internacional de Curitiba / Afonso Pena', 'CWB', (SELECT id FROM cidade WHERE uf = 'PR')),
	('Aeroporto Internacional de Belém / Val de Cans', 'BEL', (SELECT id FROM cidade WHERE uf = 'PA'));

INSERT INTO voo (numero, id_aeroporto_origem, id_aeroporto_destino, data_hora_embarque, status, data_criacao, data_atualizacao) VALUES 
    ('DPQWXN', (SELECT id FROM aeroporto WHERE iata = 'BSB'), (SELECT id FROM aeroporto WHERE iata = 'POA'), '2024-01-25 15:00:00.000000', 1, '2024-01-09 11:39:51.546365', '2024-01-09 11:39:51.555948'),
    ('AAASSH', (SELECT id FROM aeroporto WHERE iata = 'SSA'), (SELECT id FROM aeroporto WHERE iata = 'GRU'), '2024-01-30 18:00:00.000000', 1, '2024-01-09 11:39:51.546365', '2024-01-09 11:39:51.555948');

INSERT INTO voo_classe (id, id_voo, classe, qtd_assentos, qtd_livres, valor, data_criacao, data_atualizacao) VALUES 
    ('1e58a20b-c420-4146-886f-76c95f5f0f19',(SELECT id FROM voo WHERE numero = 'DPQWXN'), 2, 20, 18, 500.00, '2024-01-09 11:39:51.551570', '2024-01-09 11:39:51.551570'),
    ('1e58a20b-c420-4146-886f-76c95f5f0f20',(SELECT id FROM voo WHERE numero = 'DPQWXN'), 1, 3, 3, 800.00, '2024-01-09 11:39:51.551570', '2024-01-09 11:39:51.551570'),
    ('1e58a20b-c420-4146-886f-76c95f5f0f30',(SELECT id FROM voo WHERE numero = 'AAASSH'), 2, 10, 10, 400.00, '2024-01-09 11:40:51.551570', '2024-01-09 11:40:51.551570');

INSERT INTO comprador (nome, cpf, email, data_nascimento, data_criacao, data_atualizacao) VALUES 
    ('Airton Senna', '123456787', 'senna@gmail.com', '1060-03-21', '2024-01-09 11:40:51.551570', '2024-01-09 11:40:51.551570');

INSERT INTO passageiro (nome, cpf, data_nascimento, data_criacao, data_atualizacao) VALUES 
    ('Allan Prost', '123456789', '1965-01-01', '2024-01-09 11:40:51.551570', '2024-01-09 11:40:51.551570'),
    ('Felipe Massa', '123456788', '1980-01-01', '2024-01-09 11:40:51.551570', '2024-01-09 11:40:51.551570');

INSERT INTO passagem (numero, num_compra, id_comprador, id_passageiro, id_voo_classe, valor_passagem, valor_total, num_bagagem, bag_despachada, status, data_criacao, data_atualizacao) VALUES 
    ('CNRY', 'UUCV', (SELECT id FROM comprador WHERE cpf = '123456787'), (SELECT id FROM passageiro WHERE cpf = '123456789'), '1e58a20b-c420-4146-886f-76c95f5f0f19', 500.00, 550.00, 'DKBF', true, 1, '2024-01-09 11:40:51.551570', '2024-01-09 11:40:51.551570'),
    ('WQDV', 'UUCV', (SELECT id FROM comprador WHERE cpf = '123456787'), (SELECT id FROM passageiro WHERE cpf = '123456788'), '1e58a20b-c420-4146-886f-76c95f5f0f19', 500.00, 500.00, null, false, 1, '2024-01-09 11:40:51.551570', '2024-01-09 11:40:51.551570');







