# API Back-end HiSig

![Java](https://img.shields.io/badge/java-%23ED8B00.svg?style=for-the-badge&logo=openjdk&logoColor=white)
![Spring](https://img.shields.io/badge/spring-%236DB33F.svg?style=for-the-badge&logo=spring&logoColor=white)
[![Licence](https://img.shields.io/github/license/Ileriayo/markdown-badges?style=for-the-badge)](./LICENSE)

Esse projeto é uma API desenvolvida usando **Java, Java Spring, MySQL as the database.** 
Com finalidade de trabalhar como o sistema de recrutamento de estágiarios.

## Tabela de conteúdo

- [Requisitos](#requisitos)
- [Instalação](#instalação)
- [Uso do projeto](#uso-do-projeto)
- [API Endpoints](#api-endpoints)
- [Banco de Dados](#banco-de-dados)
- [Contribuições](#contribuições)

## Requisitos

- Java SDK 17
- Docker Desktop
- Qualquer IDE configurada para Spring boot Java (Recomace usa VSCode ou Spring Boot IDE)

## Instalação

1. Clone o repositório:

```bash
git clone https://github.com/TTeT-Solucoes-Informatica/HiSig-back.git
```

2. Instale dependências com Maven

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


## Contribuições

Contribuições são bem-vindas! Se você encontrar algum problema ou tiver sugestões de melhorias, abra um problema ou envie uma solicitação pull ao repositório.

Ao contribuir para este projeto, siga o estilo de código existente, [commit conventions](https://www.conventionalcommits.org/en/v1.0.0/), e envie suas alterações em um branch separado.