# AppVendas

## Descrição

Este projeto implementa uma aplicação de vendas simples utilizando a arquitetura MVC (Model-View-Controller) com Java (versão 17) e a modelagem arquitetural baseada no **C4 Model**. O objetivo é demonstrar uma estrutura organizada para desenvolvimento de sistemas com o Spring Boot, garantindo separação de responsabilidades e facilidade de manutenção.

## Arquitetura do Projeto

O projeto segue o modelo **MVC**, onde:

- **Model**: Representa a estrutura de dados (entidades do sistema);
- **View**: Não é implementada nesta aplicação, pois o foco está na API REST;
- **Controller**: Responsável por receber as requisições HTTP e acionar os serviços;
- **Service**: Implementa a lógica de negócio e manipula os dados do sistema;
- **Repository**: Gerencia o acesso ao banco de dados.

Além disso, a aplicação utiliza a ferramenta **PlantUML** para documentação da arquitetura por meio do **C4 Model**, com diagramas nos seguintes níveis:

1. **Contexto** (Visão geral do sistema);
2. **Contêiner** (Componentes principais);
3. **Componentes** (Detalhamento das funcionalidades);
4. **Código** (Implementação).

## Estrutura de Pastas

```
📦 src/docs
    ┗ 📂 diagrams (Diagramas do C4 model usando PlantUML)
       ┣ 📜 01-context.puml  (Nível 1 - Contexto)
       ┣ 📜 02-container.puml (Nível 2 - Contêiner)
       ┣ 📜 03-component.puml (Nível 3 - Componentes)
       ┗ 📜 04-code.puml (Nível 4 - Código)

📦 src/main/java/com/xpeducacao/georgy/appvendas
    ┣ 📂 controller (Camada de controle - Recebe requisições)
    ┃  ┗ 📜 ProductController.java
    ┣ 📂 service (Camada de serviço - Regras de negócio)
    ┃  ┗ 📜 ProductService.java
    ┣ 📂 repository (Camada de persistência - Acesso ao banco)
    ┃  ┗ 📜 ProductRepository.java
    ┣ 📂 model (Camada de modelo - Representação da entidade)
    ┃  ┗ 📜 Product.java
    ┗ 📜 AppvendasApplication.java (Classe principal do Spring Boot)
```

## Como Rodar o Projeto

### 1. Clonar o Repositório

```sh
 git clone https://github.com/seu-usuario/appvendas.git
 cd appvendas
```

### 2. Configurar o Banco de Dados

O projeto utiliza um banco de dados H2 (em memória) por padrão. Para usar outro banco, configure o **application.properties**.

### 3. Construir e Executar a Aplicação

#### obs.: antes de rodar a aplicação, certifique-se de que o Java 17 é o default no seu sistema

```sh
 ./mvnw spring-boot:run
```

Ou, se preferir:

```sh
 mvn clean install
 java -jar target/appvendas-0.0.1-SNAPSHOT.jar
```

A API ficará disponível em [**http://localhost:8080/api/v1/products**](http://localhost:8080/api/v1/products).

## Testando a API com cURL

### Listar todos os produtos

```sh
curl -X GET http://localhost:8080/api/v1/products/
```

### Buscar um produto por ID

```sh
curl -X GET http://localhost:8080/api/v1/products/1
```

### Criar um novo produto

```sh
curl -X POST http://localhost:8080/api/v1/products \
     -H "Content-Type: application/json" \
     -d '{"name": "Notebook", "price": 2999.90}'
```

### Atualizar um produto

```sh
curl -X PUT http://localhost:8080/api/v1/products/1 \
     -H "Content-Type: application/json" \
     -d '{"name": "Notebook Gamer", "price": 4999.90}'
```

### Excluir um produto

```sh
curl -X DELETE http://localhost:8080/api/v1/products/1
```

### Contar o número total de produtos

```sh
curl -X GET http://localhost:8080/api/v1/products/count
```
