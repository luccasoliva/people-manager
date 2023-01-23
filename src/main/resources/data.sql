INSERT INTO PESSOA (DATA_NASCIMENTO, NOME) VALUES ('2000-01-02', 'Ana');
INSERT INTO PESSOA (DATA_NASCIMENTO, NOME) VALUES ('2000-03-04', 'Tiago');
INSERT INTO PESSOA (DATA_NASCIMENTO, NOME) VALUES ('2000-05-06', 'Lucas');
INSERT INTO PESSOA (DATA_NASCIMENTO, NOME) VALUES ('2000-07-08', 'Maria');

INSERT INTO ENDERECO (CEP, CIDADE, IS_PRINCIPAL, LOGRADOURO, NUMERO, PESSOA_ID) VALUES ('12345678', 'São Paulo', FALSE, 'Rua 1', '1', 1);
INSERT INTO ENDERECO (CEP, CIDADE, IS_PRINCIPAL, LOGRADOURO, NUMERO, PESSOA_ID) VALUES ('12345678', 'São Paulo', FALSE, 'Rua 2', '2', 1);
INSERT INTO ENDERECO (CEP, CIDADE, IS_PRINCIPAL, LOGRADOURO, NUMERO, PESSOA_ID) VALUES ('12345678', 'São Paulo', TRUE, 'Rua 3', '3', 1);

INSERT INTO ENDERECO (CEP, CIDADE, IS_PRINCIPAL, LOGRADOURO, NUMERO, PESSOA_ID) VALUES ('12345678', 'Rio de Janeiro', FALSE, 'Rua 11', '11', 2);
INSERT INTO ENDERECO (CEP, CIDADE, IS_PRINCIPAL, LOGRADOURO, NUMERO, PESSOA_ID) VALUES ('12345678', 'Rio de Janeiro', FALSE, 'Rua 22', '22', 2);
INSERT INTO ENDERECO (CEP, CIDADE, IS_PRINCIPAL, LOGRADOURO, NUMERO, PESSOA_ID) VALUES ('12345678', 'Rio de Janeiro', TRUE, 'Rua 33', '33', 2);

INSERT INTO ENDERECO (CEP, CIDADE, IS_PRINCIPAL, LOGRADOURO, NUMERO, PESSOA_ID) VALUES ('12345678', 'Fortaleza', FALSE, 'Rua 111', '111', 3);
INSERT INTO ENDERECO (CEP, CIDADE, IS_PRINCIPAL, LOGRADOURO, NUMERO, PESSOA_ID) VALUES ('12345678', 'Fortaleza', FALSE, 'Rua 222', '222', 3);
INSERT INTO ENDERECO (CEP, CIDADE, IS_PRINCIPAL, LOGRADOURO, NUMERO, PESSOA_ID) VALUES ('12345678', 'Fortaleza', TRUE, 'Rua 333', '333', 3);

INSERT INTO ENDERECO (CEP, CIDADE, IS_PRINCIPAL, LOGRADOURO, NUMERO, PESSOA_ID) VALUES ('12345678', 'Brasília', FALSE, 'Rua 1111', '1111', 4);
INSERT INTO ENDERECO (CEP, CIDADE, IS_PRINCIPAL, LOGRADOURO, NUMERO, PESSOA_ID) VALUES ('12345678', 'Brasília', FALSE, 'Rua 2222', '2222', 4);
INSERT INTO ENDERECO (CEP, CIDADE, IS_PRINCIPAL, LOGRADOURO, NUMERO, PESSOA_ID) VALUES ('12345678', 'Brasília', TRUE, 'Rua 3333', '3333', 4);
