CREATE TABLE autor (
  id INT NOT NULL AUTO_INCREMENT,
  nome VARCHAR,
  nascimento DATE,
  nacionalidade VARCHAR,
  PRIMARY KEY (id)
);

INSERT INTO autor (nacionalidade, nascimento, nome) VALUES ('Brasileiro', to_date('01-09-1994', 'dd-mm-yyyy'), 'José Aparecido');
INSERT INTO autor (nacionalidade, nascimento, nome) VALUES ('Brasileiro', to_date('18-03-1999', 'dd-mm-yyyy'), 'João Rocha');
INSERT INTO autor (nacionalidade, nascimento, nome) VALUES ('Brasileiro', to_date('31-07-1981', 'dd-mm-yyyy'), 'Agnaldo Perreira');

CREATE TABLE livro (
  id INT NOT NULL AUTO_INCREMENT,
  nome VARCHAR,
  publicacao DATE,
  editora VARCHAR,
  resumo VARCHAR,
  id_autor INT,
  PRIMARY KEY (id),
  FOREIGN KEY (id_autor) REFERENCES autor(id)
);

INSERT INTO livro 
    (nome, publicacao, editora, resumo, id_autor) 
  VALUES 
    ('Back-end Java', to_date('13-08-2006', 'dd-mm-yyyy'), 'Casa do Código', 'Microsserviços, Spring Boot e Kubernetes', 1);
    
INSERT INTO livro 
    (nome, publicacao, editora, resumo, id_autor) 
  VALUES 
    ('Spring Boot', to_date('04-07-2008', 'dd-mm-yyyy'), 'Casa do Código', 'Acelere o desenvolvimento de microsserviços', 1);
    
INSERT INTO livro 
    (nome, publicacao, editora, resumo, id_autor) 
  VALUES 
    ('Segurança', to_date('12-12-2012', 'dd-mm-yyyy'), 'Casa do Código', 'Em aplicações web', 2);

CREATE TABLE comentario (
  id INT NOT NULL AUTO_INCREMENT,
  texto VARCHAR,
  usuario VARCHAR,
  data DATE,
  id_livro INT,
  PRIMARY KEY (id),
  FOREIGN KEY (id_livro) REFERENCES livro(id)
);

INSERT INTO comentario
    (texto, usuario, data, id_livro)
  VALUES
    ('Ótimo livro!!', 'Fulano', to_date('01-07-2019','dd-mm-yyyy'), 1);