
<p align="center" width="100%">
    <img width="50%" src="https://blog.b2bstack.com.br/wp-content/uploads/2023/02/whatsapp-business-api-neu-01.webp"> 
</p>

<h3 align="center">
  # 🚀 Sistema de Envio de Mensagens via WhatsApp 📲
</h3>

<p align="center">

  <img alt="License: MIT" src="https://img.shields.io/badge/license-MIT-%2304D361">
  <img alt="Language: Java" src="https://img.shields.io/badge/language-java-green">
  <img alt="Version: 1.0" src="https://img.shields.io/badge/version-1.0-yellowgreen">

</p>

Este projeto é uma aplicação Spring Boot que permite o envio automatizado de mensagens via WhatsApp utilizando a API do Twilio. Ele inclui funcionalidades para upload de arquivos CSV com informações de clientes, personalização de mensagens, envio de mensagens e atualização do status das mensagens.

## 📋 Sumário

- [📚 Tecnologias Utilizadas](#-tecnologias-utilizadas)
- [🏗️ Arquitetura](#-arquitetura)
- [🔧 Configuração do Ambiente](#-configuração-do-ambiente)
- [🔑 Configuração do Twilio](#-configuração-do-twilio)
- [📝 Uso](#-uso)
- [🔌 API Endpoints](#-api-endpoints)
- [💾 Estrutura do Banco de Dados](#-estrutura-do-banco-de-dados)
- [🔒 Considerações de Segurança](#-considerações-de-segurança)
- [🤝 Contribuição](#-contribuição)

## 📚 Tecnologias Utilizadas

- Java com Spring Boot
- MySQL (utilizando Docker)
- Twilio API para envio de mensagens via WhatsApp
- Apache Kafka para processamento assíncrono de mensagens
- Spring Security para autenticação e controle de acesso
- OpenCSV para processamento de arquivos CSV

## 🏗️ Arquitetura

A aplicação está estruturada em camadas, seguindo a arquitetura de microsserviços:

1. **Controller**: Recebe e processa as requisições HTTP.
2. **Service**: Contém a lógica de negócios.
3. **Repository**: Interage com o banco de dados.
4. **Model**: Representa as entidades do banco de dados.

![Arquitetura](https://miro.medium.com/v2/resize:fit:800/format:webp/1*Kn16sMS4mtn4a-PyGnlI_A.png)

## 🔧 Configuração do Ambiente

### Pré-requisitos

- JDK 11+
- Maven 3+
- Docker
- Conta no Twilio

### Passos para Configuração

1. **Clonar o Repositório**

    ```bash
    git clone https://github.com/seu_usuario/seu_repositorio.git
    cd seu_repositorio
    ```

2. **Configurar o Banco de Dados**

    - Certifique-se de que o Docker está instalado e em execução.
    - Suba um container MySQL com o seguinte comando:

    ```bash
    docker run --name mysql-container -e MYSQL_ROOT_PASSWORD=root -e MYSQL_DATABASE=message_system -p 3306:3306 -d mysql:latest
    ```

3. **Configurar as Dependências do Maven**

    - Navegue até o diretório do projeto e execute:

    ```bash
    mvn clean install
    ```

4. **Configurar o `application.properties`**

    - No diretório `src/main/resources`, edite o arquivo `application.properties` com as suas configurações:

    ```properties
    # Configurações do Banco de Dados
    spring.datasource.url=jdbc:mysql://localhost:3306/message_system
    spring.datasource.username=root
    spring.datasource.password=root
    spring.jpa.hibernate.ddl-auto=update

    # Credenciais do Twilio
    twilio.account-sid=YOUR_TWILIO_ACCOUNT_SID
    twilio.auth-token=YOUR_TWILIO_AUTH_TOKEN
    twilio.whatsapp-number=whatsapp:+14155238886
    ```

## 🔑 Configuração do Twilio

1. **Criar Conta no Twilio**

    - Vá para [Twilio](https://www.twilio.com) e crie uma conta.
    - Obtenha seu `Account SID` e `Auth Token` do [Console do Twilio](https://www.twilio.com/console).

2. **Configurar um Número Twilio para WhatsApp**

    - No console do Twilio, configure um número Twilio para enviar mensagens via WhatsApp.

## 📝 Uso

1. **Subir a Aplicação**

    - No diretório do projeto, execute:

    ```bash
    mvn spring-boot:run
    ```

2. **Endpoints**

    - **Upload de Arquivo CSV**: Para fazer upload de um arquivo CSV com informações de clientes.
    - **Envio de Mensagens**: Enviar mensagens via WhatsApp para os clientes listados no CSV.

## 🔌 API Endpoints

### Upload de Arquivo CSV

- **Endpoint**: `/api/csv/upload`
- **Método**: `POST`
- **Descrição**: Faz o upload de um arquivo CSV com informações de clientes.
- **Parâmetros**:
    - `file`: Arquivo CSV contendo nome, telefone e email dos clientes.

### Envio de Mensagens via WhatsApp

- **Endpoint**: `/api/whatsapp/send`
- **Método**: `POST`
- **Descrição**: Envia uma mensagem via WhatsApp para o cliente especificado.
- **Parâmetros**:
    - `to`: Número de telefone do destinatário.
    - `message`: Mensagem a ser enviada.

### Exemplo de Requisição para Envio de Mensagens

```bash
curl -X POST "http://localhost:8080/api/whatsapp/send" -d "to=+123456789" -d "message=Hello, this is a test message!"
