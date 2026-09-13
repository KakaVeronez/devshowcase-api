# DevShowcase API 🚀

API RESTful desenvolvida em Java com Spring Boot para gerenciamento e exibição de portfólios de desenvolvedores, seus projetos, tecnologias e feedbacks.

## 🛠️ Tecnologias Utilizadas

* **Java 17 / 21**
* **Spring Boot**
* **Spring Data JPA**
* **H2 Database** (In-Memory Database)
* **Maven**
* **Bean Validation** (`jakarta.validation`)

## 📐 Arquitetura

O projeto foi estruturado seguindo o padrão de arquitetura em 4 camadas:
src/main/java/com/devshowcase/api/
├── controller/    # Camada de endpoints REST
├── dto/           # Objetos de transferência de dados (DTOs) com validações
├── model/         # Entidades de domínio JPA
└── repository/    # Interfaces de persistência de dados
---

## 🔗 Endpoints da API

### Profiles (`/profiles`)
* `POST /profiles` - Cadastra um novo perfil
* `GET /profiles` - Lista todos os perfis
* `GET /profiles/{id}` - Busca perfil por ID
* `PUT /profiles/{id}` - Atualiza um perfil existente
* `DELETE /profiles/{id}` - Remove um perfil

### Technologies (`/technologies`)
* `POST /technologies` - Cadastra uma nova tecnologia
* `GET /technologies` - Lista todas as tecnologias cadastrais

### Projects (`/projects`)
* `POST /projects` - Cadastra um projeto (vinculado a um perfil e tecnologias)
* `GET /projects` - Lista todos os projetos cadastrados
* `GET /projects/{id}` - Busca projeto por ID
* `PUT /projects/{id}` - Atualiza dados do projeto
* `DELETE /projects/{id}` - Remove um projeto

### Feedbacks (`/projects/{projectId}/feedbacks`)
* `POST /projects/{projectId}/feedbacks` - Adiciona feedback a um projeto específico

---

## 🛢️ Banco de Dados (H2 Console)

Enquanto a aplicação estiver em execução, o painel do H2 pode ser acessado em:
* **URL:** `http://localhost:8080/h2-console`
* **JDBC URL:** `jdbc:h2:mem:devshowcasedb`
* **User:** `sa`
* **Password:** *(em branco)*

---

## 👥 Integrantes do Grupo

* Carlos Alexandre Silva Veronez
* Fabio Alves Camelo
* Lia Raquel Rodrigues de Sousa
* Wallison Nunes da Costa