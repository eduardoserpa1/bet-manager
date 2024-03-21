# Bet Manager
Página web construída com [React.js](https://react.dev/learn) no frontend e [Spring](https://spring.io/) com [Postgres](https://www.postgresql.org/download/) no backend para gerenciar sorteios.
## como executar a aplicação:
Primeiramente, para executarmos essa aplicação será necessário instalar alguns programas que são fundamentais para o funcionamento das tennologias:

### > Postgres

A aplicação foi desenvolvida em cima de um esquema de banco de dados relacional do utilizando Postgres.

Embora seja extremamente simples o modelo desenvolvido para esse problema, não é necessário criar nenhuma tabela de forma manual, já que o [Spring JPA] já faz esse trabalho.

O unico requisito nesse caso é ter o Postgres instalado no seu computador, como também (no meu caso), instalar o programa [Pgamin4](https://www.pgadmin.org/download/), no qual é um SGBD que facilita muito a configuração e criação dos bancos de dados relacionais do Postgres.

Quando instalado, será necessário apenas criar uma instãncia de banco de dados para que o JPA faça todo o restante do trabalho

Para a instancia do banco de dados, utilize as seguintes credenciais

```
database-name: bet-manager
hostname/address: 127.0.0.1
port: 5432
username: postgres
password: 1234
```

Com isso, ao iniciar a aplicação Spring, o código ira criar 3 tabelas (bm_sortition, bm_user, bm_bet), necessárias para o funcionamento correto e persistencia de dados da aplicação.

### > Java (openjdk 21)

O [Java](https://www.oracle.com/java/technologies/javase/jdk21-archive-downloads.html) será necessário para conseguir utilizar o [Maven](https://maven.apache.org/) e executar código no framework Spring

Caso esteja utilizando uma IDE como [IntelliJ](https://www.jetbrains.com/pt-br/idea/) (IDE na qual a aplicação foi desenvolvida), apenas será necessário abrir o diretório './betmanager-api' e executar os seguintes comandos

```
mvn install
mvn spring-boot:run
```

Com isso, as dependências necessárias para o correto funcionamento do código serão instaladas.


### > Node (React.js)

Para conseguir executar com correto funcionamento a parte visual da aplicação, será necessário ter o [Node.js](https://nodejs.org/en/download/current) instalado na máquina, pois com ele iremos subir uma aplicação React para consumir os dados da API Spring.

Após instalar o Node, no diretório './betmanager-front' execute os seguintes comandos.

```
npm install
npm start
```

Com isso, o node irá instalar as dependências necessárias para o correto funcionamento da aplicação React, assim como no Spring anteriormente.

### > Utilização

Após as etapas anteriores, teremos 2 processos sendo executados em paralelo, sendo um deles o font-end (http://localhost:3000/), e outro o back-end (http://localhost:8080).

A API Spring, na qual fica disponível na porta 8080 do servidor local da máquina, pode também ser acessada pelo navegador ou Postman.

Porém, por ser muito trabalhoso o teste manual, foram elaborados testes unitários que cobrem praticamente toda aplicação, para visualizar os testes é sugerido que utilize ou o InteliJ, ou o seguinte comando no diretório './betmanager-api'

```
mvn test
```

Por fim, após seguir todos os passos descritos, é possível acessar a aplicação, acesse a url 'http://localhost:3000/' no seu navegador.

