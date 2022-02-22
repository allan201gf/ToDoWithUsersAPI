# ToDoWithUsersAPI

Projeto de uma API para gerenciamento de tarefas.<br/>

Para facilitar os testes da API, ela foi desenvolvida utilizando o banco de dados H2, 
um banco em memória que não há necessidade de executar um banco SQL fora da aplicação. 
Em contrapartida, toda vez que o sistema é reiniciado, todos os dados são perdidos.
<br>
A qualquer momento o banco pode ser alterado refaturando o arquivo abaixo.
> resources/application.properties

A documentação dos endpoints é feita via Swagger no link abaixo e algumas informações podem ser consultadas neste mesmo arquivo.

Para acessar os endpoints que necessitam de autenticação é necessário incluir o token no swagger.

> http://localhost:8080/swagger-ui.html

## Versão e IDE de desenvolvimento
O projeto foi desenvolvido utilizando a IDE IntelliJ utilizando o Java em sua versão 11.

## Build do Projeto
Para rodar o projeto não é necessário configurações extras, apenas clonar o projeto e fazer o build (requer java +11).<br>

> resources/application.properties

## Features

### Registro e Login de usuários
#### Cadastro do usuário
Para cadastrar um usuário devemos bater no endpoint:

~~~
POST /api/user/v1
~~~
Dados no body:
~~~
{
  "email": "string",
  "name": "string",
  "password": "string"
}
~~~

#### Login do usuário
~~~
POST /api/user/v1/login
~~~
Dados no body:
~~~
{
    "email": "email@email.com",
    "password": "senhateste"
}
~~~
Após efetuar o login o sistema retornará o Bearer Token que deve ser enviado no header em todas as requisições que requerem o usuário logado.

### Dados do usuário logado
Este endpoint retorna os dados do usuário que está logado.
> Para utilizar este endpoint o usuário deve estar logado
~~~
GET  /api/user/v1/getloggeduser
~~~

### Deletar usuário logado
Utilize este endpoint para deletar o usuário que está logado.
> Para utilizar este endpoint o usuário deve estar logado
~~~
DELETE  /api/user/v1/delete
~~~

### ToDo
#### Criação de tarefa

> Para utilizar este endpoint o usuário deve estar logado
~~~
POST  /api/ingredient/v1
~~~
Dados no body:
~~~
{
  "description": "string",
  "priority": "string" (HIGH, MEDIUM, LOW)
}
~~~

#### Listagem das tarefas

> Para utilizar este endpoint o usuário deve estar logado

Exibe apenas as tarefas do usuário logado.<br>

caso "orderByPriority" seja enviada como "false" a lista retornará as tarefas na ordem que foram adicionadas.<br>
Se for enviada como "true" a lista retornará por ondem de prioridade (HIGH -> MEDIUM -> LOW)
~~~
GET  api/todo/v1/all?orderByPriority=true
~~~

#### Edição de tarefa

> Para utilizar este endpoint o usuário deve estar logado

Edita apenas tarefas do usuário logado.<br>

~~~
PUT  /api/todo/v1/alter?id={id da tarefa}
~~~
Dados no body:<br>
Será alterado apenas os campos que forem enviados na requisição.
~~~
{
  "description": "string",
  "priority": "string" (HIGH, MEDIUM, LOW)
}
~~~

#### Deletar tarefa

> Para utilizar este endpoint o usuário deve estar logado

Excluí apenas tarefas do usuário logado.<br>

~~~
PUT  /api/todo/v1/alter?id={id da tarefa}
~~~

#### Alterar status da tarefa

> Para utilizar este endpoint o usuário deve estar logado

Altera o status somente as tarefas do usuário logado.<br>

~~~
GET  api/todo/v1/setstatus?id={id da tarefa}&status={status: "FINISHED" / "PENDING"}
~~~


## 🛠️ Desenvolvido com

* [IntelliJ](http://www.dropwizard.io/1.0.2/docs/) - A IDE do ❤️
* [Maven](https://maven.apache.org/) - Gerenciador de dependências
* [SpringBoot](https://start.spring.io/) - Framework para aplicações web
* [H2 DataBase](https://www.h2database.com/html/main.html) - Banco de dados em memória
* [Lombok](https://projectlombok.org/) - Facilitar a criação de construtores
* [Swagger](https://swagger.io/tools/open-source/open-source-integrations/) - Listagem endpoints da API
* [Spring Security](https://spring.io/projects/spring-security) - Autenticação de usuários
* [Token JWT](https://github.com/jwtk/jjwt) - Criação do token de autenticação de usuários

---
⌨️ com ❤️ por [Allan Garcia Ferreira](https://github.com/allan201gf) 