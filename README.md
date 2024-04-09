# API Back-end

![Java](https://img.shields.io/badge/java-%23ED8B00.svg?style=for-the-badge&logo=openjdk&logoColor=white)
![Spring](https://img.shields.io/badge/spring-%236DB33F.svg?style=for-the-badge&logo=spring&logoColor=white)
[![Licence](https://img.shields.io/github/license/Ileriayo/markdown-badges?style=for-the-badge)](./LICENSE)

Esse projeto é uma API desenvolvida usando **Java, Java Spring, MySQL.** 
Com finalidade de trabalhar com o sistema de divulgação de vagas.

## Tabela de conteúdo

- [Requisitos](#requisitos)
- [Instalação](#instalação)
- [Uso do projeto](#uso-do-projeto)
- [API Endpoints](#api-endpoints)
- [Banco de Dados](#banco-de-dados)

## Requisitos

- Java SDK 17
- Docker Desktop
- Qualquer IDE configurada para Spring boot Java (Recomace usa VSCode ou Spring Boot IDE)

## Instalação

## Uso do projeto

1. Start a aplicação com Maven
2. A API pode ser acessada em http://localhost:8080


## API Endpoints
A API prover uma série de endpoints documentados usando **SWAGGER UI** acesse todos os endpoints disponíveis e suas respectivas especificações, seguindo as seguintes etapas.
1. Start a aplicação com Maven
2. A documentação completa estara disponivel em http://localhost:8080/swagger-ui/index.html#/

## Banco de Dados
O projeto utiliza [MySQL Database](https://dev.mysql.com/doc/) como banco de dados e [Docker](https://hub.docker.com/_/mysql) para subir o banco de dados em container docker.

### Criando o container
Para a criação do contêiner para o projeto, execute o seguinte comando na raiz do projeto:
```docker
docker-compose up -d
```
