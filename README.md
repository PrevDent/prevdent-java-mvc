# 🦷 PrevDent - Sistema de Gestão Odontológica

# Aluno	RM

#### Keven Ike Pereira da Silva	553215

#### Vitor Cruz dos Santos  553621

#### José Ribeiro dos Santos Neto 553844

Link do Video [video](https://youtu.be/BfRq7Z8UPuY)

Sistema desenvolvido em **Spring Boot** com **Thymeleaf** para gerenciar consultas odontológicas, pacientes e dentistas.

## 📌 Sobre o Projeto  
O **PrevDent** é um sistema web que facilita o gerenciamento de consultas odontológicas, permitindo o cadastro de pacientes, dentistas e diagnósticos. O sistema utiliza **Spring Boot** no backend e **Thymeleaf** no frontend para uma experiência dinâmica e eficiente.

### 🚀 Principais Funcionalidades  
✅ **Cadastro e login de usuários**  
✅ **Agendamento e listagem de consultas**  
✅ **Gestão de dentistas e pacientes**  
✅ **Integração segura com JWT para autenticação**  
✅ **Interface moderna e responsiva**  

---

## 🛠 Tecnologias Utilizadas

### 🔹 Back-End  
- Java 17  

- Spring MVC  
- Spring Security (JWT)  
- Spring Data JPA  
- Thymeleaf  

### 🔹 Front-End  
- HTML5 + Thymeleaf  
- CSS3 

### 🔹 Infraestrutura  
- OpenFeign (para integração com APIs externas)  

---

## 🎯 Como Rodar o Projeto  

### 1️⃣ Clone o repositório principal  
Para executar este projeto corretamente, é necessário rodá-lo em conjunto com a **aplicação PrevDent**.  

🔗 **Link do GitHub:** [PrevDent](https://github.com/PrevDent/prevdentjava)  

Clone o repositório da aplicação principal:  


2️⃣ Configuração das Requisições
Para visualizar a listagem de pacientes na página Home, você deve realizar requisições na aplicação [PrevDent](https://github.com/PrevDent/prevdentjava).

O repositório já contém um arquivo atualizado com todas as requisições necessárias para uso no Postman.

3️⃣ Executando a Aplicação Spring MVC
Inicie a aplicação localmente e utilize as seguintes URLs para acessar:

Cadastro de Usuário: http://localhost:8081/usuario/cadastro

Login de Usuário: http://localhost:8081/usuario/login


## Deploy na nuvem

### 1 -> Clone o repositório principal  na maquina
### 2 -> Use o Intellj e baixe o plugin **Azure Toolkit for Intellj**
### 3 -> Faça login na sua conta Azure 
### 4 -> Selecione o Web App no dropdrown da extenção 
### 5 -> Clique com o botão direito e clique em Create
### 6 -> Insira o nome do seu web app
### 7 -> Em "Platform" selecione "Windows-Java 17-Java SE"
### 8 -> Ao criar, vá nos "Web Apps", botão direito e selecione "Deploy"
### 8 -> No modal que que irá abrir, só selecionar "Run"
### 9 -> Ao finalizar o deploy, apenas clicar na url e abrir no browser

O back-end está neste link https://prevdent-java.azurewebsites.net/
O front-end está nesse link: https://prevdent-mvc.azurewebsites.net/usuario/cadastro

Para verificar requisições e itens no banco, usar as colections do postman que se encontram no projeto.





