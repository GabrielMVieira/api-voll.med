# Api Rest | Voll.med

![GitHub repo size](https://img.shields.io/github/repo-size/iuricode/README-template?style=for-the-badge)
![GitHub language count](https://img.shields.io/github/languages/count/iuricode/README-template?style=for-the-badge)

![Java](https://img.shields.io/badge/java-%23ED8B00.svg?style=for-the-badge&logo=openjdk&logoColor=white)
![Spring](https://img.shields.io/badge/spring-%236DB33F.svg?style=for-the-badge&logo=spring&logoColor=white)
![JWT](https://img.shields.io/badge/JWT-black?style=for-the-badge&logo=JSON%20web%20tokens)

> Este projeto é uma API construída usando Java, Spring, Flyway, MySql como banco de dados, SpringDoc para documentar a Api, Maven para gerenciar dependências e build do projeto, Spring Security e JWT para controle de autenticação.

## 🚀 Instalação

Para instalar o Voll.med, siga estas etapas:

1. Clone o repositório:

```bash
git clone https://github.com/GabrielMVieira/api-voll.med.git
```

2. Instale as dependências com o maven.

3. Instale [MySql](https://www.mysql.com/)

## ☕ Uso

Para usar siga estas etapas:

1. Iniciar a aplicação com o Maven
2. A API estará acessível em http://localhost:8080

## Banco de dados
O projeto utiliza [MySql](https://www.mysql.com/) como banco de dados. As migrações de banco de dados necessárias são gerenciadas usando Flyway.


## API Endpoints
A API fornece os seguintes endpoints:

Autenticação: 
```markdown
POST /login - Realiza login na aplicação e recebe um token JWT para autenticação.
```

Consultas:
```markdown
POST /consultas - Agenda uma nova consulta.

DELETE /consultas - Cancela uma consulta previamente agendada.
```

Médicos:
```markdown
GET /medico - Retorna uma lista paginada de médicos cadastrados.

GET /medico/{id} - Retorna os detalhes de um médico específico.

POST /medico - Cadastra um novo médico.

PUT /medico - Atualiza os dados cadastrais de um médico existente.

DELETE /medico/{id} - Exclui um médico do sistema.
```

Pacientes:
```markdown
GET /paciente - Retorna uma lista paginada de pacientes cadastrados.

GET /paciente/{id} - Retorna os detalhes de um paciente específico.

POST /paciente - Cadastra um novo paciente.

PUT /paciente - Atualiza os dados cadastrais de um paciente existente.

DELETE /paciente/{id} - Exclui um paciente do sistema.
```

## Autenticação

A autenticação na API é gerenciada pelo Spring Security em conjunto com tokens JWT (JSON Web Token).
A classe TokenService é responsável por gerar e validar tokens JWT, a classe SecurityConfiguration configura o Spring Security para habilitar a autenticação stateless e definir os endpoints públicos e protegidos, enquanto a classe SecurityFilter implementa a lógica de filtragem para extrair e validar tokens JWT das requisições HTTP.

## 📫 Contribuindo

Contribuições são bem-vindas! Se você encontrar algum problema ou tiver sugestões de melhorias, por favor abra uma issue ou envie um pull request para o repositório.

Ao contribuir para este projeto, por favor siga o estilo de código existente, [convenções de commit](https://www.conventionalcommits.org/en/v1.0.0/), e envie suas alterações em um branch separado.
