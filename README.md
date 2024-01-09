## 💻 Sobre o projeto

O App AirLine é um aplicativo para gestão de gestão de voos e compras de passagens. O aplicativo possui funcionalidades que permitam o cadastro de aeroportos, voos, passagens, comprador, passageiros e compra de passagens, inclusive com inform se há despacho de bagagem, gerando aumento de custo no valor total.

---

## ⚙️ Funcionalidades

- [x] Autenticação de gestores
- [x] Listar aeroportos
- [x] Cadastrar, listar, alterar e cancelar voos
- [x] Cadastrar e alterar preços das passagens
- [x] Listar passageiros de um voo
- [x] Comprar passagens
- [x] Obter as passagens compradas pelo CPF do comprador
- [x] Cancelar compra
- [x] Emitir o voucher da passagem
- [x] Emitir etiqueta de bagagem
- [x] Docker com App-Airline, Postgres e PGAdmin.
- [x] Data migrations com versionamento usando o Flyway
- [x] Documentação da API c/ Swagger
- [x] Build do projeto e deploy em produção no Render

---
## 🛠 Aplicação em Produção no Render

- https://app-airline.onrender.com/swagger-ui/index.html

---

## 📄 SpringDoc/Swagger

Implementação da documentação e acessos aos endpoints: 
 - <a href="http://localhost:8080/v3/api-docs">Json</a> / <a href="http://localhost:8080/swagger-ui/index.html">HTML</a> Acesso Local
 - <a href="https://app-airline.onrender.com/v3/api-docs">Json</a> / <a href="https://app-airline.onrender.com/swagger-ui/index.html">HTML</a> Acesso pelo Render (deploy em prod)

---

## 🎨 Modelagem do Banco de Dados

- <a href="https://github.com/adrianoazevedo/airline-new/blob/main/.assets/Model.jpg?raw=true">Model</a>
- tbm na pasta .assets
- PG Admin Local: http://localhost:15432/browser/
- login: airline@gmail.com
- senha: 1234
---

## 🛠 Tecnologias

As seguintes tecnologias foram utilizadas no desenvolvimento da API Rest do projeto:

- **[Java 17](https://www.oracle.com/java)**
- **[Spring Boot 3](https://spring.io/projects/spring-boot)**
- **[Maven](https://maven.apache.org)**
- **[Postgres](https://www.postgresql.org/)**
- **[Docker](https://www.docker.com/)**
- **[Hibernate](https://hibernate.org)**
- **[Flyway](https://flywaydb.org)**
- **[Lombok](https://projectlombok.org)**
- **[Swagger](https://swagger.io)**
- **[Render](https://render.com)**

---

## 📝 Usuário de Teste (gerar token JWT)

- login: airline
- senha: 123456

---

## 📝 Instruções para rodar o projeto

Pelo Docker:
- clonar o projeto
- subir os conteineres com o docker-compose-dev.yaml (usa as variáveis de ambiente do .env-dev)
- aguarde o intelliJ baixar todas as dependências

Por IDE (IntelliJ):
- clonar o projeto
- editar as configurações da aplicação para ver as variáveis de ambiente do arquivo .env
- subir somente os conteineres do postgres-db e pgadmin
- executar a aplicação

Utilização:
- importe o collection do Insomnia da pasta .assets para testar os endpoints ou use o Swagger
- utilize a request efetuar login, com as credenciais acima, para gerar um token JWT
- atualize o token em Authorize (tipo Bearer Token) com o gerado, tanto no Swagger como pelo Insomnia
- tempo de vida do token: 1hs

---

## 📝 Collection Insomia
![Collection](https://github.com/adrianoazevedo/airline-new/blob/main/.assets/Insomnia_2024-01-09.json"Collection")
- baixe para testas o endpoint pelo Insomnia ou use o Swagger

---

