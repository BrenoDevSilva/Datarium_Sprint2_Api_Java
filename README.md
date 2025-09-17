# Datarium API

### **Visão Geral**

O **Datarium** é uma plataforma de assessoramento virtual de investimentos, desenvolvida para o desafio da **XP Inc.** e FIAP. Utilizando inteligência artificial explicável (XAI) e análise de perfil, a ferramenta oferece recomendações personalizadas com foco em segurança, transparência e conformidade com a LGPD.

A **API RESTful** foi construída como o back-end do projeto, gerenciando os dados de clientes e ativos, e servindo como a base para a aplicação mobile.

### **Participantes do Grupo**

* **Anna Yagyu** - RM 550360
* **Breno Silva** - RM 99275
* **Danilo Urze** - RM 99465
* **Gabriel Pacheco** - RM 550191

### **Tecnologias Utilizadas**

* **Java 17**: Linguagem de programação para o back-end.
* **Spring Boot**: Framework para a construção da API.
* **Spring Data JPA**: Para a persistência de dados.
* **Maven**: Gerenciador de dependências.
* **Lombok**: Biblioteca para redução de código.
* **Oracle Database**: Banco de dados para a persistência dos dados.

### **Diagramas**

* **Diagrama de Arquitetura em Camadas**
    <img width="922" height="357" alt="image" src="https://github.com/user-attachments/assets/f341329b-0578-496f-b0b5-c4f9b70bac70" />


* **Diagrama de Entidades (ER ou UML)**
    <img width="1258" height="415" alt="image" src="https://github.com/user-attachments/assets/66a2b96f-c9ee-47d3-99ea-5d4e861c4e5d" />


### **Instruções de Configuração e Execução**

1.  **Pré-requisitos**:
    * **Java 17** ou superior instalado e configurado.
    * **Maven** instalado.
    * Acesso ao banco de dados Oracle da FIAP.
    * Cliente HTTP para testes (como Postman ou Insomnia).

2.  **Configuração do Banco de Dados**:
    * Abra o arquivo `src/main/resources/application.properties`.
    * O projeto está configurado para rodar na minha conta pessoal do banco de dados Oracle. Caso queira executá-lo em outra conta, por favor, mude as credenciais (username e password) para as suas.
    * 
        ```properties
        spring.datasource.url=jdbc:oracle:thin:@oracle.fiap.com.br:1521:ORCL
        spring.datasource.username=RM99275
        spring.datasource.password=180105
        ```

3.  **Execução da API**:
    * No terminal, na pasta raiz do projeto, execute o comando:
        ```bash
        mvn spring-boot:run
        ```
    * A API será executada na porta `8080`.

### **Exemplos de Requisições**

A API oferece endpoints completos de CRUD para as entidades `Cliente` e `Ativo`.

#### **Endpoints de Clientes**

* **Criar um novo Cliente (`POST`)**:
    * **URL**: `http://localhost:8080/clientes`
    * **Body (JSON)**:
        ```json
        {
          "nome": "João da Silva",
          "email": "joao.silva@email.com",
          "perfilInvestidor": "CONSERVADOR",
          "objetivo": "LONGO_PRAZO"
        }
        ```
        * **Observação**: Os campos `perfilInvestidor` e `objetivo` devem ser preenchidos com um dos valores definidos na API para que a validação funcione corretamente.
        * **Perfil do Investidor**: `CONSERVADOR`, `MODERADO`, `AGRESSIVO`
        * **Objetivo**: `CURTO_PRAZO`, `MEDIO_PRAZO`, `LONGO_PRAZO`

* **Listar todos os Clientes (`GET`)**:
    * **URL**: `http://localhost:8080/clientes`

* **Atualizar um Cliente (`PUT`)**:
    * **URL**: `http://localhost:8080/clientes/{id}`
    * **Body (JSON)**:
        ```json
        {
          "id": 1,
          "nome": "João da Silva ATUALIZADO",
          "email": "novo.email@email.com",
          "perfilInvestidor": "MODERADO",
          "objetivo": "MEDIO_PRAZO"
        }
        ```
        * **Observação**: Os campos `perfilInvestidor` e `objetivo` devem ser preenchidos com um dos valores definidos na API para que a validação funcione corretamente.
        * **Perfil do Investidor**: `CONSERVADOR`, `MODERADO`, `AGRESSIVO`
        * **Objetivo**: `CURTO_PRAZO`, `MEDIO_PRAZO`, `LONGO_PRAZO`

* **Deletar um Cliente (`DELETE`)**:
    * **URL**: `http://localhost:8080/clientes/{id}`

#### **Endpoints de Ativos**

* **Criar um novo Ativo (`POST`)**:
    * **URL**: `http://localhost:8080/ativos`
    * **Body (JSON)**:
        ```json
        {
          "nome": "Tesouro Direto",
          "tipo": "RENDA_FIXA",
          "valor": 1000.00,
          "descricao": "Ativo de renda fixa."
        }
        ```

* **Listar todos os Ativos (`GET`)**:
    * **URL**: `http://localhost:8080/ativos`

* **Atualizar um Ativo (`PUT`)**:
    * **URL**: `http://localhost:8080/ativos/{id}`
    * **Body (JSON)**:
        ```json
        {
          "id": 1,
          "nome": "Fundo de Ações",
          "tipo": "RENDA_VARIAVEL",
          "valor": 2500.00,
          "descricao": "Fundo de investimentos de ações."
        }
        ```

* **Deletar um Ativo (`DELETE`)**:
    * **URL**: `http://localhost:8080/ativos/{id}`
