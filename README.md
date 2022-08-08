
   ![Visual Studio Code](https://img.shields.io/badge/Visual%20Studio%20Code-57AFFC.svg?style=for-the-badge&logo=visual-studio-code&logoColor=white)   ![Eclipse](https://img.shields.io/badge/Eclipse-239.svg?style=for-the-badge&logo=Eclipse&logoColor=white) ![Java](https://img.shields.io/badge/java-%23ED8B00.svg?style=for-the-badge&logo=java&logoColor=white) ![Spring](https://img.shields.io/badge/spring-%236DB33F.svg?style=for-the-badge&logo=spring&logoColor=white) ![MySQL](https://img.shields.io/badge/mysql-%2328f.svg?style=for-the-badge&logo=mysql&logoColor=white) ![Angular](https://img.shields.io/badge/angular-%23DD0031.svg?style=for-the-badge&logo=angular&logoColor=white)

<h1 align ="center" >Preservação de Quelônios - Quelofy🐢</h1>

<p align ="center"><img src ="img/logo-quelofy.png" width="700" ></p>



## 🔖  Sobre

<p>O projeto Preservação de Quelônios é uma aplicação de Gerenciamento de Dados que está sendo desenvolvida dentro do curso Full-Stack Java/Angular, no contexto do programa Transforme-se - Serasa Experian. O intuito é colocar em prática todo o conteúdo estudado, e auxiliar o Projeto Pé-de-Pincha através da tecnologia.
O projeto Pé-de-Pincha é um programa de extensão da Universidade Federal do Amazonas que promove a pesquisa, a conservação e o manejo sustentável de quelônios em comunidades ribeirinhas da região amazônica.
O desenvolvimento das funcionalidades foi construído de acordo com a necessidade de melhorar a coleta de dados, feita pelos voluntários do Programa Pé-De-Pincha durante o período de realização das viagens, com objetivo de facilitar o controle do programa.


Projeto Integrador - Squad Amazonas - Digital House Brasil - Programação Full Stack (Java e Angular) - Transforme-se - Serasa Experian</p>

## 💻 Funcionalidades
**Inserção de ciclos** - cadastrar o ciclo que contemplará as três viagens realizadas durante o tempo de execução do projeto,  referente a cada etapa exercida, coleta, eclosão e soltura.

**Inserção de viagens** - cadastrar cada viagem, de acordo com o calendário de programação do projeto e de acordo com a etapa a ser realizada.

**Preenchimento de formulários** - adicionar as informações relevantes que cada etapa do projeto necessita para fazer o controle das fases de coleta, eclosão e soltura. 

**Inserção de usuários** - cadastrar as pessoas voluntárias do projeto, para que tenham acesso ao sistema de preenchimento dos formulários.

**Geração de relatórios** - gerar relatórios em pdf, com os dados de cada fase do projeto, facilitando assim, a manipulação individual das informações relevantes de cada etapa.

## 📊 Diagrama de classe
<p align ="center"><img src = "img/diagrama-de-classe.png"  ></p>

## 📋 Pré-requisitos
Para executar o projeto, será necessário instalar os seguintes programas:
* **JDK 11**
* **Spring Boot**
* **Angular**
* **Eclipse**
* **VS Code**
* **MySQL**

## 🔧 Começando

Para execução do projeto na máquina local, clone o repositório da API e também o repositório do front-end, que se encontra [aqui]( https://github.com/NaaraMarinheiro/Preservacao-de-Quelonios-front), em um repositório de sua preferência.

Para clonar este repositório:

* cd "repositorio de sua preferencia"
* git clone https://github.com/YacciRocha/Preservacao-de-Quelonios.git

Para clonar o repositório do front-end:

* cd "repositorio de sua preferencia"
* git clone https://github.com/NaaraMarinheiro/Preservacao-de-Quelonios-front.git


## 🛠️ Construção

Siga as instruções:

* Abra o código da API na sua IDE de preferência.

* Para que o projeto rode com sucesso, é necessário executar o comando clean e install do maven, devido ao uso da lib do mapstruct para o mapeamento. 
 
```
mvn clean install
```

 * O comando irá baixar todas as dependências do projeto e criar um diretório target com os artefatos construídos, que incluem o arquivo jar do projeto. Além disso, serão executados os testes unitários, e se algum falhar, o Maven exibirá essa informação no console.*

 * Em seguida já é possível rodar a aplicação spring, através do comando: 
 
 ```
 Run As: Spring Boot App.
 ```

* Com a API em execução, já é possível fazer testes de funcionamento via postman. 

*Para ter acesso ao sistema completo, é necessário também subir a aplicação do Angular, para isso, siga as seguintes instruções:*

* Abra o código angular em sua IDE de preferência.

* No console do node, rode o comando npm install, para gerar o arquivo de node_modules e assim, o aplicativo funcionar corretamente.
```
npm install
```
 * Em seguida, rode o comando ng serve para subir a aplicação, o sistema rodará na porta 4200.
```
ng serve
```
 
* Para ter acesso, abra o caminho **localhost:4200** no browser. 


## 🚀 Tecnologias utilizadas

O projeto foi desenvolvido utilizando as seguintes tecnologias:

|    Tecnologia     	|                    Back-end                  	|   Tecnologia   	|                     Front-end                   	|
|:-----------------:	|:--------------------------------------------:	|:--------------:	|:-----------------------------------------------:	|
| **Java**          	| Linguagem de programação (versão 11)         	| **Typescript** 	| Linguagem de programação                        	|
| **Spring**        	| O framework web usado (versão 2.7.0)         	| **Angular**    	| Framework Javascript usado (versão 14)          	|
| **Mapstruct**     	| Mapeamento do back-end (versão 1.5)          	| **HTML5**      	| Desenvolvimento do front-end                    	|
| **Lombok**        	| Lib de Java (versão 1.18)                    	| **CSS3**       	| Desenvolvimento do front-end                    	|
| **JasperReports** 	| Api externa - geração de pdf (versão 6.19.1) 	| **Bootstrap**  	| Framework do front-end ( versão 5)              	|
| **MySQL**         	| Banco de dados (versão 8.0)                  	| **Axios**      	| Cliente http baseado em promessas (versão 0.27) 	|
| **Postman**       	| API Client para testes (9.27)                	| **Chart.js**   	| Lib de gráficos (versão 3.8)                    	|
| **Swagger**       	| Documentação da API                          	| **Toastr-ngx** 	| Exibição de notificações (versão 15)            	|
|                   	|                                              	| **Figma**      	| Editor de prototipagem                          	|

## ✒️ Autoras
* **Naara Marinheiro** - [Github](https://github.com/NaaraMarinheiro)  | [Linkedin ](https://www.linkedin.com/in/naaramarinheiro/)
* **Pamela Guerra** - [Github ](https://github.com/Pam-Guerra)  |  [Linkedin  ](https://www.linkedin.com/in/pamela-guerra-a3a29b230/)
* **Viviane Mayumi** - [Github](https://github.com/VivianeMayumi) |  [Linkedin](https://www.linkedin.com/in/viviane-mayumi-ogusko-saitou-67465418a/)
* **Yacci Rocha** -  [Github](https://github.com/YacciRocha) |  [Linkedin](https://www.linkedin.com/in/yacci-da-rocha-sousa-9b0342149/)
