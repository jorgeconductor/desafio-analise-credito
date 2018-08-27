## *Tutorial de Instalação*


### *Tecnologias utilizadas e seus links para download*
* Hibernate
* AngularJS
* Bootstrap
* [NPM](https://www.npmjs.com/package/npm)
* Bower
* [Java 1.8](http://www.oracle.com/technetwork/pt/java/javase/downloads/jdk8-downloads-2133151.html)
* MySQL
* Spring Boot
* [Maven](https://maven.apache.org/download.cgi)
* Servidor MySQL local rodando (Easy PHP DEVSERVER, XAMPP ou similar)

### *Construindo o projeto*
1. Tenha certeza que o NPM, Java 1.8, Maven e o servidor MySQL estão instalando e funcionando apropriadamente em sua máquina local
2. Crie um banco de dados MySQL no seu servidor local e sete no arquivo ```application.properties``` na pasta ```desafio-analise-credito\src\main\resources``` a url do caminho do banco de dados, seu usuário e senha de acesso ao banco
   - Exemplo: ```spring.datasource.url=jdbc:mysql://localhost/conductordb```
```spring.datasource.username=root```
```spring.datasource.password=```
3. Tenha certeza que seu servidor MySQL (podendo ser no Easy PHP ou qualquer outra plataforma que crie um servidor MySQL local) está rodando localmente e com banco de dados criado
4. Faça o clone do projeto para a sua máquina local: ```git clone https://github.com/jorgeconductor/desafio-analise-credito.git``` e vá para a branch ```desafio_analise_credito```
5. Navegue até a pasta raiz do projeto ```desafio-analise-credito```
6. Tenha certeza que tem o NPM instalado em sua máquina e está na variável de ambiente do sistema
7. Instale o Bower usando o comando NPM via linha de comando: ```npm install -g bower```
8. Execute via linha de comando ainda na pasta raiz do projeto: ```bower install```
9. Tenha certeza que contém o Maven instalado em sua máquina ou em sua IDE, caso queira usar um para rodar o código
10. Execute o comando na pasta raiz do projeto via CMD: ```mvn clean install -DskipTests``` o -DskipTests é para pular os testes enquanto instalando, caso queira executar os testes remova o ```-DskipTests```
   - Pode executar o maven usando sua IDE, ao invés

### *Como executar o projeto*
* Execute o comando abaixo na raiz do projeto ```desafio-analise-credito```:
```mvn spring-boot:run```
* Acesse sua porta local com o /conductor/ como base, exemplo:
```http://127.0.0.1:8080/conductor/```

### *Screenshots*

![tela login](https://i.imgur.com/go2gu69.png)

![página de portadores](https://i.imgur.com/pIK6U12.png)

![página de análise](https://i.imgur.com/0EiDKCd.png)

![página de análise - limitado](https://i.imgur.com/eJBEAvU.png)
