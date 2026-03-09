# 📚 LiterAlura — Catálogo de Livros com Java e Spring Boot

Projeto desenvolvido como parte do desafio **Literalura** da Alura, com o objetivo de consumir uma API externa de livros, persistir os dados em um banco relacional e permitir consultas através de um menu interativo no terminal.

## 🚀 Tecnologias Utilizadas

* **Java**
* **Spring Boot**
* **Spring Data JPA**
* **Hibernate**
* **PostgreSQL**
* **Maven**
* **API Gutendex**

## 📖 Sobre o Projeto

O LiterAlura é uma aplicação backend que realiza o consumo da API pública **Gutendex**, obtendo informações sobre livros disponíveis no Project Gutenberg.

Os dados retornados pela API são convertidos para objetos Java e armazenados em um banco de dados PostgreSQL utilizando **JPA/Hibernate**.

O sistema permite realizar diversas consultas através de um menu interativo no terminal.

## ⚙️ Funcionalidades

O sistema possui as seguintes funcionalidades:

1. **Buscar livro pelo título**

   * Consulta diretamente a API Gutendex
   * Salva o livro e o autor no banco de dados

2. **Listar livros registrados**

   * Exibe todos os livros armazenados no banco

3. **Listar autores**

   * Mostra todos os autores cadastrados

4. **Listar autores vivos em determinado ano**

   * Permite consultar autores que estavam vivos em um ano específico

5. **Listar livros por idioma**

   * Permite buscar livros registrados por idioma

## 🗄 Estrutura do Projeto

```
literalura
│
├── dto
│   ├── DadosAutor
│   ├── DadosLivro
│   └── DadosResposta
│
├── model
│   ├── Autor
│   └── Livro
│
├── repository
│   ├── AutorRepository
│   └── LivroRepository
│
├── service
│   ├── ConsumoAPI
│   └── ConverteDados
│
├── principal
│   └── Principal
│
└── LiteraluraApplication
```

## 🔗 API Utilizada

Os dados dos livros são obtidos através da API:

https://gutendex.com

A API fornece informações sobre livros disponíveis no **Project Gutenberg**, incluindo:

* título
* autor
* idioma
* número de downloads

## 💾 Banco de Dados

O projeto utiliza **PostgreSQL** para persistência dos dados, com mapeamento realizado através do **Spring Data JPA** e **Hibernate**.

Relacionamento implementado:

```
Autor (1) ---- (N) Livro
```

Um autor pode possuir vários livros registrados no sistema.

## ▶️ Como Executar o Projeto

1. Clone o repositório

```
git clone https://github.com/seu-usuario/literalura.git
```

2. Configure o banco PostgreSQL no arquivo:

```
application.properties
```

Exemplo:

```
spring.datasource.url=jdbc:postgresql://localhost:5432/literalura
spring.datasource.username=postgres
spring.datasource.password=sua_senha
```

3. Execute o projeto

```
mvn spring-boot:run
```

4. Utilize o menu no terminal para interagir com o sistema.

## 🎯 Objetivo do Projeto

Este projeto foi desenvolvido para praticar:

* Consumo de **APIs REST**
* Conversão de **JSON para objetos Java**
* Persistência de dados com **JPA/Hibernate**
* Integração com **PostgreSQL**
* Estruturação de aplicações **Spring Boot**

## 👨‍💻 Autor

**Matheus Mariano**

🔗 LinkedIn
https://www.linkedin.com/in/matheus-s-mariano

🔗 GitHub
https://github.com/MatheusSMariano-Dev
