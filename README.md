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

- `GET http://localhost:9090/api/dono-restaurante/ids`: Lista todos os donos de restaurante ordenados pelo ID de forma crescente.
- `GET http://localhost:9090/api/dono-restaurante/nome/{nome}`: Busca o dono do restaurante pelo nome desejado.
- `GET http://localhost:9090/api/dono-restaurante/nomes`: Lista todos os donos de restaurante ordenados pelo nome de forma crescente.
- `POST http://localhost:9090/api/dono-restaurante`: Cria um novo dono de restaurante.
- `PUT http://localhost:9090/api/dono-restaurante/{id}`: Atualiza um dono de restaurante existente informando o ID. Mas, os campos atualizados serão somente os campos informados na requisição.
- `DELETE http://localhost:9090/api/dono-restaurante/{id}`: Exclui um dono de restaurante.

- `GET http://localhost:9090/api/cliente-restaurante/ids`: Lista todos os clientes do restaurante ordenados pelo ID de forma crescente.
- `GET http://localhost:9090/api/cliente-restaurante/nome/{nome}`: Busca o cliente do restaurante pelo nome desejado.
- `GET http://localhost:9090/api/cliente-restaurante/nomes`: Lista todos os clientes do restaurante ordenados pelo nome de forma crescente.
- `POST http://localhost:9090/api/cliente-restaurante`: Cria um novo cliente do restaurante.
- `PUT http://localhost:9090/api/cliente-restaurante/{id}`: Atualiza um cliente do restaurante existente informando o ID. Mas, os campos atualizados serão somente os campos informados na requisição.
- `DELETE http://localhost:9090/api/cliente-restaurante/{id}`: Exclui um cliente do restaurante.
## Tecnologias utilizadas

- Java
- Spring Boot
- Maven
- Docker
- MySQL