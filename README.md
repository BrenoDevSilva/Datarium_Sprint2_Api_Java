# Datarium API

---

### **Visão Geral**

O **Datarium API** é o componente de back-end (servidor) do projeto Datarium, uma plataforma de assessoramento virtual de investimentos desenvolvida como parte do desafio da **XP Inc.** e FIAP.

Esta aplicação é uma API RESTful construída em Java com o framework Spring Boot. Ela é responsável por:

* Gerenciar a lógica de negócio da plataforma.
* Realizar operações de CRUD (Create, Read, Update, Delete) para clientes e seus ativos financeiros.
* Autenticar usuários.
* Comunicar-se com um banco de dados Oracle (fornecido pela FIAP) para persistência dos dados.
* Priorizar a cibersegurança e a conformidade com a LGPD.

### **Participantes do Grupo**

* **Anna Yagyu** - RM 550360
* **Breno Silva** - RM 99275
* **Danilo Urze** - RM 99465
* **Gabriel Pacheco** - RM 550191

---

### **Tecnologias Utilizadas**

* **Linguagem:** Java 17
* **Framework:** Spring Boot (incluindo Spring Data JPA, Spring Web, Spring Validation)
* **Gerenciador de Dependências:** Maven
* **Banco de Dados:** Oracle Database
* **Testes de Segurança:** GitHub Actions com CodeQL e OWASP ZAP

---

### **Diagramas**

* **Diagrama de Arquitetura em Camadas**
  
    ![Diagrama de Arquitetura](https://github.com/user-attachments/assets/f341329b-0578-496f-b0b5-c4f9b70bac70)

* **Diagrama de Entidades (Modelo Relacional)**
  
    ![Diagrama de Entidades](https://github.com/user-attachments/assets/d1387427-0232-4f80-bb4d-b2b093177e14)

---

### **Instruções de Configuração e Execução**

**1. Pré-requisitos:**

* JDK 17 ou superior instalado e configurado (verifique com `java -version`).
* Maven instalado (verifique com `mvn -version`).
* Acesso ao banco de dados Oracle da FIAP.
* Um cliente HTTP para testar os endpoints (como Postman, Insomnia ou `curl`).

**2. Configuração do Banco de Dados:**

* As credenciais de acesso ao banco de dados estão no arquivo `src/main/resources/application.properties`.
* O projeto está pré-configurado com credenciais específicas (`RM99500`). Se precisar usar outra conta do Oracle FIAP, **atualize** as propriedades `spring.datasource.username` e `spring.datasource.password` neste arquivo com suas credenciais.

    ```properties
    spring.datasource.url=jdbc:oracle:thin:@oracle.fiap.com.br:1521:ORCL
    spring.datasource.username=RM99500
    spring.datasource.password=100504
    ```

**3. Execução da API:**

* Abra um terminal na pasta raiz do projeto (onde o arquivo `pom.xml` está localizado).
* Execute o comando Maven para iniciar a aplicação Spring Boot:

    ```bash
    mvn spring-boot:run
    ```
* Aguarde a inicialização. A API estará rodando localmente na porta `8080` (ex: `http://localhost:8080`) e pronta para receber requisições.

---

### **Endpoints da API**

A API expõe os seguintes endpoints RESTful para gerenciar clientes e seus ativos:

#### **Clientes (`/clientes`)**

* **`POST /clientes`**
    * **Descrição:** Cadastra um novo cliente.
    * **Corpo da Requisição (JSON):**
        ```json
        {
          "nome": "Nome Completo",
          "email": "email@exemplo.com",
          "senha": "senhaforte123",
          "perfilInvestidor": "MODERADO", // CONSERVADOR, MODERADO ou AGRESSIVO
          "objetivo": "LONGO_PRAZO" // CURTO_PRAZO, MEDIO_PRAZO ou LONGO_PRAZO
        }
        ```
    * **Resposta (Sucesso):** `201 Created` com os dados do cliente criado.

* **`POST /clientes/login`**
    * **Descrição:** Autentica um cliente existente.
    * **Corpo da Requisição (JSON):**
        ```json
        {
          "email": "email@exemplo.com",
          "senha": "senhaforte123"
        }
        ```
    * **Resposta (Sucesso):** `200 OK` com os dados do cliente logado.
    * **Resposta (Falha):** `401 Unauthorized`.

* **`GET /clientes/{id}`**
    * **Descrição:** Busca um cliente pelo seu ID.
    * **Parâmetro de URL:** `{id}` - O ID numérico do cliente.
    * **Exemplo:** `GET http://localhost:8080/clientes/1`
    * **Resposta (Sucesso):** `200 OK` com os dados do cliente.
    * **Resposta (Não encontrado):** `404 Not Found`.

* **`GET /clientes`**
    * **Descrição:** Retorna uma lista de todos os clientes cadastrados.
    * **Exemplo:** `GET http://localhost:8080/clientes`
    * **Resposta (Sucesso):** `200 OK` com um array de objetos Cliente.

* **`PUT /clientes/{id}`**
    * **Descrição:** Atualiza os dados de um cliente existente.
    * **Parâmetro de URL:** `{id}` - O ID numérico do cliente a ser atualizado.
    * **Corpo da Requisição (JSON):** (Incluir todos os campos que deseja atualizar, **o ID no corpo não é necessário se já estiver na URL**)
        ```json
        {
          "nome": "Nome Atualizado",
          "email": "email@exemplo.com", // Pode ser o mesmo ou um novo email
          "senha": "novasenha456",
          "perfilInvestidor": "AGRESSIVO",
          "objetivo": "CURTO_PRAZO"
        }
        ```
    * **Resposta (Sucesso):** `200 OK` com os dados do cliente atualizado.
    * **Resposta (Não encontrado):** `404 Not Found`.

* **`DELETE /clientes/{id}`**
    * **Descrição:** Remove um cliente pelo seu ID.
    * **Parâmetro de URL:** `{id}` - O ID numérico do cliente.
    * **Exemplo:** `DELETE http://localhost:8080/clientes/1`
    * **Resposta (Sucesso):** `204 No Content`.

#### **Ativos (`/ativos`)**

* **`POST /ativos`**
    * **Descrição:** Adiciona um novo ativo ao portfólio de um cliente.
    * **Corpo da Requisição (JSON):**
        ```json
        {
          "nome": "Nome do Ativo (ex: CDB XPTO)",
          "tipo": "RENDA_FIXA", // RENDA_FIXA, RENDA_VARIAVEL, FUNDO, etc. (ajustar conforme modelo Ativo.java)
          "valor": 1500.75, // Valor numérico
          "descricao": "Descrição opcional do ativo.",
          "cliente": {
            "id": 1 // ID do cliente ao qual este ativo pertence
          }
        }
        ```
    * **Resposta (Sucesso):** `201 Created` com os dados do ativo criado.

* **`GET /ativos/cliente/{clienteId}`**
    * **Descrição:** Retorna todos os ativos pertencentes a um cliente específico.
    * **Parâmetro de URL:** `{clienteId}` - O ID numérico do cliente.
    * **Exemplo:** `GET http://localhost:8080/ativos/cliente/1`
    * **Resposta (Sucesso):** `200 OK` com um array de objetos Ativo.

* **`GET /ativos/{id}`**
    * **Descrição:** Busca um ativo pelo seu próprio ID.
    * **Parâmetro de URL:** `{id}` - O ID numérico do ativo.
    * **Exemplo:** `GET http://localhost:8080/ativos/5`
    * **Resposta (Sucesso):** `200 OK` com os dados do ativo.
    * **Resposta (Não encontrado):** `404 Not Found`.

* **`GET /ativos`**
    * **Descrição:** Retorna uma lista de todos os ativos cadastrados (independente do cliente).
    * **Exemplo:** `GET http://localhost:8080/ativos`
    * **Resposta (Sucesso):** `200 OK` com um array de objetos Ativo.

* **`PUT /ativos/{id}`**
    * **Descrição:** Atualiza os dados de um ativo existente.
    * **Parâmetro de URL:** `{id}` - O ID numérico do ativo a ser atualizado.
    * **Corpo da Requisição (JSON):** (Incluir os campos que deseja atualizar, **não precisa enviar o `cliente` aqui**)
        ```json
        {
          "nome": "Nome do Ativo Atualizado",
          "tipo": "RENDA_VARIAVEL",
          "valor": 1600.00,
          "descricao": "Descrição atualizada."
        }
        ```
    * **Resposta (Sucesso):** `200 OK` com os dados do ativo atualizado.
    * **Resposta (Não encontrado):** `404 Not Found`.

* **`DELETE /ativos/{id}`**
    * **Descrição:** Remove um ativo pelo seu ID.
    * **Parâmetro de URL:** `{id}` - O ID numérico do ativo.
    * **Exemplo:** `DELETE http://localhost:8080/ativos/5`
    * **Resposta (Sucesso):** `204 No Content`.

---

### **Cibersegurança do Projeto**

Para garantir a segurança da aplicação, implementamos um fluxo de trabalho automatizado utilizando **GitHub Actions**. Este fluxo realiza:

1.  **Análise Estática de Segurança (SAST):** Utiliza o **CodeQL** para varrer o código-fonte em busca de vulnerabilidades comuns (como SQL Injection, XSS, etc.) a cada `push` ou `pull request`.
2.  **Análise Dinâmica de Segurança (DAST):** Utiliza o **OWASP ZAP** para testar a aplicação em execução contra vulnerabilidades conhecidas.

O arquivo de configuração do fluxo de trabalho pode ser encontrado em `.github/workflows/codeql.yml`.

---

**Nota Importante:** Esta API está sendo utilizada como back-end para o projeto mobile Datarium (desenvolvido em React Native com Expo). Certifique-se de que esta API esteja em execução para que o aplicativo mob
ile funcione corretamente. Acesse o aplicativo Mobile [nesse repositório](https://github.com/BrenoDevSilva/Datarium_Sprint2_Mobile).
