🦷 PrevDent - Sistema de Gestão Odontológica

Sistema desenvolvido em Spring Boot com Thymeleaf para gerenciar consultas odontológicas, pacientes e dentistas.

📌 Sobre o Projeto

O PrevDent é um sistema web que permite o gerenciamento de consultas odontológicas, facilitando o cadastro de pacientes, dentistas e diagnósticos. Ele utiliza Spring Boot como backend e Thymeleaf para renderizar as páginas no frontend.

O sistema possibilita:
✔ Cadastro e login de usuários✔ Agendamento e listagem de consultas✔ Gestão de dentistas e pacientes✔ Integração segura com JWT para autenticação✔ Interface moderna e responsiva

🛠 Tecnologias Utilizadas

O projeto foi desenvolvido com as seguintes tecnologias:

Back-End:

Java 17

Spring Boot 3.x

Spring MVC

Spring Security (JWT)

Spring Data JPA

Thymeleaf

Hibernate

PostgreSQL/MySQL

Front-End:

HTML5 + Thymeleaf

CSS3 (Bootstrap para estilização)

JavaScript (AJAX para algumas interações)

Infraestrutura:

Docker (para o banco de dados)

OpenFeign (consumo de APIs externas)

Flyway (migração de banco de dados)

🎯 Funcionalidades

🔹 Autenticação & Segurança

Cadastro de usuários com senha criptografada

Login seguro utilizando JWT

Middleware para proteger rotas autenticadas

🔹 Gestão de Consultas

Agendamento de novas consultas

Listagem de consultas com informações detalhadas

Atualização e remoção de consultas

🔹 Gerenciamento de Pacientes & Dentistas

Cadastro de pacientes com CPF, data de nascimento e histórico

Cadastro de dentistas com especializações

Integração de diagnósticos para cada consulta

🚀 Como Rodar o Projeto

1️⃣ Clonar o repositório

2️⃣ Configurar o Banco de Dados

Se estiver usando Docker, inicie o banco de dados:

Caso contrário, crie um banco no PostgreSQL/MySQL e configure no application.properties:

3️⃣ Executar a Aplicação

Acesse o sistema em:➡ http://localhost:8080

📌 Rotas da Aplicação

🔹 Páginas Web (Thymeleaf)

Rota

Descrição

/usuario/cadastrar

Tela de Cadastro

/usuario/login

Tela de Login

/usuario/home

Página Inicial

🔹 Endpoints da API (se necessário)

Método

Rota

Descrição

POST

/consulta/cadastrar

Cadastrar Consulta

GET

/consulta

Listar Consultas

GET

/consulta/{id}

Buscar Consulta

PATCH

/consulta/{id}

Atualizar Consulta

DELETE

/consulta/{id}

Excluir Consulta

🔐 Autenticação com JWT

Após o login, um token JWT é gerado e enviado no cabeçalho das requisições autenticadas.Para testar no Postman ou Insomnia, adicione o token no Header:

🖼 Telas do Sistema

📌 Cadastro de Usuário

📌 Lista de Consultas

📌 Melhorias Futuras

📌 Dashboard com estatísticas de consultas e pacientes

📌 Implementação de WebSockets para notificações em tempo real

📌 Upload de arquivos para anexar exames

👨‍💻 Desenvolvedor

Keven Ike Pereira da Silva🚀 Desenvolvedor de Software | Estudante de ADS na FIAP📌 LinkedIn | 📧 contato: 11932755267
