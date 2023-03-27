CREATE TABLE IF NOT EXISTS ENDERECO (
IDENDERECO INTEGER PRIMARY KEY AUTOINCREMENT,
CEP VARCHAR(10), 
LOGRADOURO VARCHAR(100),
NUMERO VARCHAR(10),
COMPLEMENTO VARCHAR(100),
REFERENCIA VARCHAR(100),
BAIRRO VARCHAR(100),
LOCALIDADE VARCHAR(20),
UF VARCHAR(2),
PAIS VARCHAR(100));

CREATE TABLE IF NOT EXISTS ESTABELECIMENTO(
ID INTEGER PRIMARY KEY AUTOINCREMENT, 
NOME VARCHAR(100), 
CNPJ VARCHAR(15),
TELEFONE VARCHAR(12),
TELEFONE2 VARCHAR(12),
EMAIL VARCHAR(50),
HORARIOFUNCIONAMENTO VARCHAR(30),
ABERTO VARCHAR(7), 
IMAGEMESTABELECIMENTO VARCHAR(100), 
FATURAMENTO INTEGER, 
CARDAPIO INTEGER, 
ENDERECO INTEGER, 
AVALIACAO INTEGER, 
MESAS INTEGER, 
CATEGORIAESTABELECIMENTO VARCHAR(50),
FOREIGN KEY (FATURAMENTO) REFERENCES FATURAMENTO(IDFATURAMENTO),
FOREIGN KEY (CARDAPIO) REFERENCES CARDAPIO(IDCARDAPIO),
FOREIGN KEY (ENDERECO) REFERENCES ENDERECO(IDENDERECO),
FOREIGN KEY (AVALIACAO) REFERENCES AVALIACAO(ID),
FOREIGN KEY (MESAS) REFERENCES MESAS(IDMESAS));

CREATE TABLE IF NOT EXISTS FATURAMENTO (
IDFATURAMENTO INTEGER PRIMARY KEY AUTOINCREMENT,
NOME VARCHAR(50),
TAXA DOUBLE);

CREATE TABLE IF NOT EXISTS CARDAPIO (
IDCARDAPIO INTEGER PRIMARY KEY AUTOINCREMENT,
LISTA_PRATOS INTEGER,
LISTA_BEBIDAS INTEGER,
ID_ESTABELECIMENTO INTEGER);

CREATE TABLE IF NOT EXISTS AVALIACAO (
ID INTEGER PRIMARY KEY AUTOINCREMENT,
AVALIACAO DOUBLE,
ID_ESTABELECIMENTO INTEGER);

CREATE TABLE IF NOT EXISTS MESAS (
IDMESAS INTEGER PRIMARY KEY AUTOINCREMENT,
NUMMESAS INTEGER,
CAPACIDADE_PESSOAS INTEGER,
ID_ESTABELECIMENTO INTEGER);

CREATE TABLE IF NOT EXISTS PRATOS (
IDPRATO INTEGER PRIMARY KEY AUTOINCREMENT,
NOME VARCHAR(100),
TIPOPRATO VARCHAR(50),
INGREDIENTES VARCHAR(200),
VALOR DOUBLE,
IMAGEM STRING(100),
IDAVALPRATO INTEGER,
IDCARDAPIO INTEGER,
FOREIGN KEY (IDCARDAPIO) REFERENCES CARDAPIO(IDCARDAPIO),
FOREIGN KEY (IDAVALPRATO) REFERENCES AVALPRATO(IDAVALPRATO));

CREATE TABLE IF NOT EXISTS AVALPRATO (
IDAVALPRATO INTEGER PRIMARY KEY AUTOINCREMENT,
AVALIACAO INTEGER,
IDPRATO INTEGER);

CREATE TABLE IF NOT EXISTS BEBIDAS (
IDBEBIDA INTEGER PRIMARY KEY AUTOINCREMENT,
NOME VARCHAR(50),
TIPOBEBIDA VARCHAR(30),
VALOR DOUBLE,
IMAGEM VARCHAR(100),
IDCARDAPIO INTEGER,
FOREIGN KEY (IDCARDAPIO) REFERENCES CARDAPIO (IDCARDAPIO));


