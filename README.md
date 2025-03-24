# Tech-Challenge - FIAP

## Pré-requisitos

- Java 17
- Maven
- Docker
- Docker Compose

## Configuração

1. Clone o repositório:
    ```sh
    git clone https://github.com/fabiopinheirobatista/tech-challenge-fiap.git
    cd techchallenge
    ```
2. Construa e inicie os containers Docker:
    ```sh
    docker-compose up --build 
    ```
   OU
   ```sh
    docker-compose up -d 
    ```

## Execução

1. Para executar a aplicação, use o comando:
    ```sh
    mvn spring-boot:run
    ```

2. A aplicação estará disponível na porta `9090`

## Portas

- **API local**: `9090`
- **Banco de Dados (MySQL)**: `3307`

## Acesso ao BD

1. Pode ser utilizado o DBeaver ou qualquer outra ferramenta de sua preferência.
2. Utilize as credenciais localizadas no arquivo application.properties

## Endpoints

Claro! Aqui estão todos os endpoints listados no seu arquivo Postman, organizados por categoria:

**Cliente:**

* `POST http://localhost:9090/api/cliente-restaurante/cadastrar` (Salvar Cliente)
* `GET http://localhost:9090/api/cliente-restaurante/buscar-todos` (Pesquisar Todos os Clientes)
* `PUT http://localhost:9090/api/cliente-restaurante/atualizar/{id}` (Atualizar Cliente) - Exemplo: `http://localhost:9090/api/cliente-restaurante/atualizar/3`
* `GET http://localhost:9090/api/cliente-restaurante/listar/{id}` (Pesquisar Cliente Único) - Exemplo: `http://localhost:9090/api/cliente-restaurante/listar/1`
* `DELETE http://localhost:9090/api/cliente-restaurante/deletar/{id}` (Excluir Cliente) - Exemplo: `http://localhost:9090/api/cliente-restaurante/deletar/4`

**Dono:**

* `POST http://localhost:9090/api/donos-restaurante/cadastrar` (Criar Dono)
* `GET http://localhost:9090/api/donos-restaurante/listar-todos` (Buscar Donos)
* `GET http://localhost:9090/api/donos-restaurante/listar/{id}` (Buscar Dono por ID) - Exemplo: `http://localhost:9090/api/donos-restaurante/listar/3`
* `PUT http://localhost:9090/api/donos-restaurante/atualizar/{id}` (Atualizar Dono) - Exemplo: `http://localhost:9090/api/donos-restaurante/atualizar/4`
* `PUT http://localhost:9090/api/donos-restaurante/alterar-senha` (Alterar Senha do Dono)
* `DELETE http://localhost:9090/api/donos-restaurante/excluir/{id}` (Deletar Dono) - Exemplo: `http://localhost:9090/api/donos-restaurante/excluir/1`

**Restaurante:**

* `POST http://localhost:9090/api/restaurante/cadastrar` (Criar Restaurante)
* `GET http://localhost:9090/api/restaurante/listar-todos` (Buscar Restaurantes)
* `GET http://localhost:9090/api/restaurante/listar/{id}` (Buscar Restaurante por ID) - Exemplo: `http://localhost:9090/api/restaurante/listar/3`
* `PUT http://localhost:9090/api/restaurante/atualizar/{id}` (Atualizar Restaurante) - Exemplo: `http://localhost:9090/api/restaurante/atualizar/3`
* `DELETE http://localhost:9090/api/restaurante/{id}` (Deletar Restaurante) - Exemplo: `http://localhost:9090/api/restaurante/3`

**Cardápio (Itens Cardápio):**

* `POST http://localhost:9090/api/itens-cardapio/cadastrar` (Criar Cardápio/Item)
* `GET http://localhost:9090/api/itens-cardapio/listar-todos` (Buscar Itens do Cardápio)
* `GET http://localhost:9090/api/itens-cardapio/listar/{id}` (Buscar Item do Cardápio por ID) - Exemplo: `http://localhost:9090/api/itens-cardapio/listar/1`
* `PUT http://localhost:9090/api/itens-cardapio/atualizar/{id}` (Atualizar Item do Cardápio) - Exemplo: `http://localhost:9090/api/itens-cardapio/atualizar/4`
* `DELETE http://localhost:9090/api/itens-cardapio/deletar/{id}` (Deletar Item do Cardápio) - Exemplo: `http://localhost:9090/api/itens-cardapio/deletar/4`

## Tecnologias utilizadas

- Java
- Spring Boot
- Maven
- Docker
- MySQL
