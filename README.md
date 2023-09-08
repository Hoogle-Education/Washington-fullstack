# e-commerce Model

## Condições

Um sistema de e-commerce que possui times na índia, especialistas em interface gráfica, desenvolvedores seniores no Brasil, especialista em banco de dados, Spring e servidores. A parte de interface gráfica deve ser desenvolvida de forma independente da parte de infraestrutura (frameworks, servidores de aplicação e banco de dados). O banco de dados, por exemplo, pode ser migrado futuramente de um banco de dados relacional para um banco de dados orientado a documentos. Então, diante deste cenário, quais tecnologias e metodologias você utilizaria e por quê? A justificativa sobre a escolha de cada tecnologia pode ser breve. 

# Proposta De Solução

## Metodologia

Metodologia a ser utilizada: Scrum

Vantagens de usar **Scrum:**

* Equipes interdisciplinares
* Auto-Organização das demandas
* Mais adaptado ao modelo de lançamentos, ligados aos sprints e que condiz com a proposta do site.

## Dependências para o backend

Para o backend em Java as tecnologias utilizadas seriam:

* [Spring Web](https://docs.spring.io/spring-boot/docs/3.0.1/reference/htmlsingle/#web)

Essa dependência permite que criemos a REST API para ser consumida pelo nosso frontend.

* Alguma dependência que permita conexão com o banco não relacional escolhido, caso seja o MongoDb por exemplo: **Spring Data MongoDb**

* Alguma dependência que permita conexão com o banco não relacional escolhido, caso seja o MongoDb por exemplo: **Postgre SQL Driver**

* [Validation](https://docs.spring.io/spring-boot/docs/3.0.1/reference/htmlsingle/#io.validation)

Irá validar os dados passados para nossa api. Irá retornar possível erros para o client.

* [Java Mail Sender](https://docs.spring.io/spring-boot/docs/3.0.1/reference/htmlsingle/#io.email)

Gerencia o envio de emails através do Spring. Será usado pois em um e-commerce fazemos a verificação de uma nova conta através de token.

* [Spring Data JPA](https://docs.spring.io/spring-data/jpa/docs/current/reference/html/)

Irá fazer a conversão entre os objetos vindo de uma tabela(modelo relacional), para o modelo de objetos.

* [Spring Security](https://docs.spring.io/spring-security/reference/index.html)

Realiza a segurança dos endpoints.

## Para o frontend

* React como tecnologia frontend, além de html, css e javascript para a parte estática do front.
* Através do React poderemos consumir nossa api e adicionar os dados corretos para o e-commerce, login, logout, compras e afins.
* Através de uma tecnologia no frontend poderemos criar páginas melhores pois elas funcionarão como SPA(Single Page Application)
* Também se faz válido o uso de bootstrap para estilização de componentes da página com alguns valores pré-configurados.

## Estratégia para migração

Para migrar o modelo dos bancos podemos usar a seguinte estratégia:

1. Criar um modelo de entidades baseado no relacional, dentro do pacote `models`, ele terá anotações adequadas para que possa ser capturado do banco.
2. Criar um `repository` para este modelo, de acordo com o modelo relacional, assim poderemos capturar todos os dados em forma de objeto dentro do programa.
3. Criar um modelo de entidades baseado em documentos, com os mesmos atributos que o modelo relacional, porém com as anotações condizentes ao modelo de documentos.
4. Criar um construtor dentro desta entidade capaz de receber como argumento uma entidade em sua versão relacional, desta forma passaremos cada um dos atributos ao construir uma no modelo de documentos. Exemplo:

chamada:
```java
  List<EntityDocument> entidades = repository
    .findAll()
    .stream()
    .map(EntityDocument::new)
    .collect(Collectors.toList());
```

construtor
```java
  public EntityDocument(EntityRelational entidadeRelacional) {
    this.atributo1 = entidadeRelacional.atributo1;
    this.atributo2 = entidadeRelacional.atributo2;
    this.atributo3 = entidadeRelacional.atributo3;
    this.atributo4 = entidadeRelacional.atributo4;
    //...
  }
```