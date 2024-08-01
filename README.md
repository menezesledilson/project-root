# TrilhaBackEndJR-JUN15

## Descrição
O projeto consiste em uma API RESTful em Java para gerenciar tarefas, implementando funcionalidades CRUD (Create, Read, Update, Delete), autenticação de usuário e armazenamento de registros em um banco de dados. Esse projeto faz parte da trilha de Backend da Código Certo.


## Funcionalidades

1. **Registro e Login de Usuários**
   - **Registro de Usuário:** Permite que novos usuários se registrem no sistema.
   - **Login de Usuário:** Permite que usuários registrados façam login no sistema usando credenciais válidas.

2. **CRUD de Tarefa**
   - **Criar Tarefa:** Adiciona novas tarefas ao sistema.
   - **Ler Tarefas:** Recupera e lista todas as tarefas armazenadas.
   - **Localizar Tarefa:** Recupera tarefa por id.
   - **Atualizar Tarefa:** Atualiza informações de tarefas existentes.
   - **Excluir Tarefa:** Remove tarefas do sistema.
  
## Tecnologias

- **Java:** Linguagem de programação utilizada no projeto.
- **Spring Boot:** Framework para desenvolvimento.
- **PostgreSQL:** Banco de dados utilizado para armazenar registro.
- **Render:** Plataforma para hospedagem do projeto.
- **JWT (JSON Web Token):** Para autenticação e gerenciamento de sessões de usuário.

## Endpoints

- Utilize o Postman para os testes.
- No repositório, há um payload que pode ser importado para o Postman e utilizado.
- Insira os endpoints no campo da ferramenta. 
    https://project-root-jtpp.onrender.com

### Usuários (registro e login)

- **Registro de Usuário**
  - **Método:** `POST`
  - **URL:** `https://project-root-jtpp.onrender.com/auth/register`
  - **Corpo da Requisição:** 
    ```json
    {
     "login": "string",
     "email": "string",
     "password": "string",
     "role": "ADMIN"
    }
    ```
- **Login de Usuário**
  - **Método:** `POST`
  - **URL:** `https://project-root-jtpp.onrender.com/auth/login`
  - **Corpo da Requisição:** 
    ```json
    {
     "login": "string",
     "password": "string"
    }
   
    Resposta: No JSON contém um token JWT se as   credenciais forem válidas.
    ```
### Tarefas (CRUD)
- **Criar Tarefa**
  - **Método:** `POST`
  - **URL:** `https://project-root-jtpp.onrender.com/task/create
  - **Corpo da Requisição:** 
    ```json
    {
      "description": "pagar internet",
      "status": "não concluido"
    }
    ```   
- **Lista todas as Tarefas**
  - **Método:** `GET`
  - **URL:** `https://project-root-jtpp.onrender.com/task/all`
  - **Resposta:** retorna um JSON com todas as tarefas.

- **Localizar por nome / Localizar por ID**
  - **Método:** `GET`
  - **URL:** `https://project-root-jtpp.onrender.com/task/{id}`
  - **Respota:**retorna um JSON com a tarefa


- **Atualizar Tarefa**
  - **Método:** `PUT`
  - **URL:** `https://project-root-jtpp.onrender.com/task/update/{id}`
  - **Corpo da Requisição:** 
    ```json
    {
      "description": "pagar internet",
      "status": "concluido"
    }
    ```
- **Excluir Tarefa**
  - **Método:** `DELETE`
  - **URL:** `https://project-root-jtpp.onrender.com/task/delete/{id}`

- **SWAGGER**
  - **URL:** https://project-root-jtpp.onrender.com/swagger-ui/index.html


 

