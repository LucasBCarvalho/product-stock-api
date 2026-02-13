# 🏭 Armazem API

API RESTful para gerenciamento de armazém, desenvolvida com Spring Boot. Sistema completo para controle de produtos, matérias-primas e suas relações.

## 📋 Índice

- Sobre o Projeto
- Tecnologias
- Arquitetura
- Funcionalidade
- Pré-requisitos
- Instalação
- Configuração
- Uso da API
- Endpoints
- Estrutura do Banco de Dados
- Estrutura do Projeto

## 🎯 Sobre o Projeto

A **Armazém API** é uma solução robusta para gerenciamento de estoque de produtos e matérias-primas. O sistema permite:
- Cadastro e gerenciamento de produtos
- Cadastro e gerenciamento de matérias-primas
- Relacionamento entre produtos e suas matérias-primas componentes
- Controle de quantidades e especificações
## 🚀 Tecnologias
- **Java** - Linguagem de programação
- **Spring Boot** - Framework para desenvolvimento de aplicações
- **Spring Data JPA** - Persistência de dados
- **Maven** - Gerenciamento de dependências
- **H2/PostgreSQL/MySQL** - Banco de dados (configurável)
- **Lombok** - Redução de código boilerplate
## 🏗️ Arquitetura

O projeto segue uma arquitetura em camadas (layered architecture):

```
┌─────────────────────────────────────┐
│         Controller Layer            │  ← REST Controllers
├─────────────────────────────────────┤
│         Service Layer               │  ← Business Logic
├─────────────────────────────────────┤
│         Repository Layer            │  ← Data Access
├─────────────────────────────────────┤
│         Entity Layer                │  ← Domain Models
└─────────────────────────────────────┘
```
### Padrões Utilizados

- **DTO (Data Transfer Object)** - Para transferência de dados entre camadas
- **Repository Pattern** - Para acesso a dados
- **RESTful API** - Para comunicação cliente-servidor
- **MVC (Model-View-Controller)** - Separação de responsabilidades

## ✨ Funcionalidades

### Produtos

- ✅ Criar novo produto
- ✅ Listar todos os produtos
- ✅ Atualizar informações do produto
- ✅ Deletar produto
### Matérias-Primas

- ✅ Criar nova matéria-prima
- ✅ Listar todas as matérias-primas
- ✅ Atualizar informações da matéria-prima
- ✅ Deletar matéria-prima
### Relação Produto-Matéria-Prima

- ✅ Associar matérias-primas a produtos
- ✅ Definir quantidades necessárias
- ✅ Gerenciar composição de produtos

## 📦 Pré-requisitos

- Java JDK 17 ou superior
- Maven 3.6 ou superior
- IDE de sua preferência (IntelliJ IDEA, Eclipse, VS Code)
- Git

## 🔧 Instalação

1. **Clone o repositório**

```bash
git clone https://github.com/seu-usuario/armazem-api.git
cd armazem-api
```

2. **Instale as dependências**

```bash
mvn clean install
```

3. **Execute a aplicação**

```bash
mvn spring-boot:run
```

A API estará disponível em `http://localhost:8080`
## ⚙️ Configuração

### application.properties

```properties
# Configuração do Banco de Dados
spring.datasource.url=jdbc:postgresql://localhost:5432/armazem  
spring.datasource.username=nomeDoBanco  
spring.datasource.password=Senha
```
## 📡 Uso da API

A API segue os princípios REST e utiliza os métodos HTTP padrão:

- **GET** - Recuperar recursos
- **POST** - Criar novos recursos
- **PUT** - Atualizar recursos existentes
- **DELETE** - Remover recursos
### Base URL

```
http://localhost:8080/
```

## 🛣️ Endpoints

### Produtos

| Método | Endpoint        | Descrição               |
| ------ | --------------- | ----------------------- |
| GET    | `/product`      | Lista todos os produtos |
| POST   | `/product`      | Cria novo produto       |
| PUT    | `/product/{id}` | Atualiza produto        |
| DELETE | `/product/{id}` | Remove produto          |
### Matérias-Primas

| Método | Endpoint             | Descrição                      |
| ------ | -------------------- | ------------------------------ |
| GET    | `/raw-material`      | Lista todas as matérias-primas |
| POST   | `/raw-material`      | Cria nova matéria-prima        |
| PUT    | `/raw-material/{id}` | Atualiza matéria-prima         |
| DELETE | `/raw-material/{id}` | Remove matéria-prima           |
### Relação Produto-Matéria-Prima

| Método | Endpoint                     | Descrição               |
| ------ | ---------------------------- | ----------------------- |
| GET    | `/product-raw-material`      | Lista todas as relações |
| GET    | `/product-raw-material/{id}` | Busca relação por ID    |
| POST   | `/product-raw-material`      | Cria nova relação       |
| PUT    | `/product-raw-material/{id}` | Atualiza relação        |
| DELETE | `/product-raw-material/{id}` | Remove relação          |
## 🗄️ Estrutura do Banco de Dados

### DDL (Data Definition Language)

#### Tabela: products

```sql
CREATE TABLE products (
    id serial4 NOT NULL,
    name varchar(255) NOT NULL,
    price numeric(10, 2) NOT NULL,
    code varchar(55) NULL,
    CONSTRAINT products_pkey PRIMARY KEY (id)
);
```

**Campos:**

- `id` - Identificador único do produto (auto-incremento)
- `name` - Nome do produto
- `price` - Preço do produto (com 2 casas decimais)
- `code` - Código/SKU do produto (opcional)

#### Tabela: raw_materials

```sql
CREATE TABLE raw_materials (
    id bigserial NOT NULL,
    name varchar(255) NOT NULL,
    code varchar(100) NOT NULL,
    stock_quantity numeric(10, 2) NOT NULL,
    CONSTRAINT raw_materials_pkey PRIMARY KEY (id)
);
```

**Campos:**

- `id` - Identificador único da matéria-prima (auto-incremento)
- `name` - Nome da matéria-prima
- `code` - Código de identificação da matéria-prima
- `stock_quantity` - Quantidade em estoque (com 2 casas decimais)

#### Tabela: product_raw_materials

```sql
CREATE TABLE product_raw_materials (
    id bigserial NOT NULL,
    product_id int8 NOT NULL,
    raw_material_id int8 NOT NULL,
    quantity_needed numeric(10, 2) NOT NULL,
    CONSTRAINT product_raw_materials_pkey PRIMARY KEY (id)
);

-- Foreign Keys
ALTER TABLE product_raw_materials 
    ADD CONSTRAINT product_raw_materials_product_id_fkey 
    FOREIGN KEY (product_id) 
    REFERENCES public.products(id) 
    ON DELETE CASCADE;

ALTER TABLE product_raw_materials 
    ADD CONSTRAINT product_raw_materials_raw_material_id_fkey 
    FOREIGN KEY (raw_material_id) 
    REFERENCES public.raw_materials(id) 
    ON DELETE CASCADE;
```

**Campos:**

- `id` - Identificador único da relação (auto-incremento)
- `product_id` - ID do produto (chave estrangeira)
- `raw_material_id` - ID da matéria-prima (chave estrangeira)
- `quantity_needed` - Quantidade necessária de matéria-prima para o produto

### Relacionamentos

```
products (1) ────────── (*) product_raw_materials
                              │
raw_materials (1) ─────────── (*)
```

- Um **produto** pode ter **várias matérias-primas**
- Uma **matéria-prima** pode ser usada em **vários produtos**
- A tabela `product_raw_materials` é uma **tabela associativa** (many-to-many)
- **ON DELETE CASCADE**: Ao deletar um produto ou matéria-prima, as relações são automaticamente removidas

### Diagrama ER

```
┌─────────────────┐
│    PRODUCTS     │
├─────────────────┤
│ PK id           │
│    name         │
│    price        │
│    code         │
└────────┬────────┘
         │
         │ 1
         │
         │ *
┌────────┴──────────────────┐
│ PRODUCT_RAW_MATERIALS     │
├───────────────────────────┤
│ PK id                     │
│ FK product_id             │
│ FK raw_material_id        │
│    quantity_needed        │
└────────┬──────────────────┘
         │
         │ *
         │
         │ 1
┌────────┴────────┐
│ RAW_MATERIALS   │
├─────────────────┤
│ PK id           │
│    name         │
│    code         │
│    stock_qty    │
└─────────────────┘
```
### Dados de Exemplo

```sql
-- Inserir produtos
INSERT INTO products (name, price, code) VALUES
    ('Cadeira de Escritório', 599.90, 'PROD001'),
    ('Mesa Executiva', 899.90, 'PROD002'),
    ('Estante Modular', 450.00, 'PROD003');

-- Inserir matérias-primas
INSERT INTO raw_materials (name, code, stock_quantity) VALUES
    ('Aço Carbono SAE 1020', 'MAT001', 1000.00),
    ('MDF 15mm', 'MAT002', 500.00),
    ('Espuma D28', 'MAT003', 200.00),
    ('Tecido Couro Sintético', 'MAT004', 150.00),
    ('Parafuso M6', 'MAT005', 5000.00);

-- Inserir relações produto-matéria-prima
INSERT INTO product_raw_materials (product_id, raw_material_id, quantity_needed) VALUES
    (1, 1, 5.50),  -- Cadeira usa 5.5kg de aço
    (1, 3, 2.00),  -- Cadeira usa 2kg de espuma
    (1, 4, 1.50),  -- Cadeira usa 1.5m de tecido
    (2, 2, 15.00), -- Mesa usa 15kg de MDF
    (2, 5, 20.00), -- Mesa usa 20 parafusos
    (3, 2, 25.00), -- Estante usa 25kg de MDF
    (3, 5, 40.00); -- Estante usa 40 parafusos
```

## 📁 Estrutura do Projeto

```
armazem/
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   └── com/
│   │   │       └── example/
│   │   │           └── armazem/
│   │   │               ├── controller/
│   │   │               │   ├── ProductController.java
│   │   │               │   ├── ProductRawMaterialController.java
│   │   │               │   └── RawMaterialController.java
│   │   │               ├── product/
│   │   │               │   ├── Product.java
│   │   │               │   ├── ProductRepository.java
│   │   │               │   ├── ProductRequestDTO.java
│   │   │               │   └── ProductResponseDTO.java
│   │   │               ├── productrawmaterial/
│   │   │               │   ├── ProductRawMaterial.java
│   │   │               │   ├── ProductRawMaterialRepository.java
│   │   │               │   ├── ProductRawMaterialRequestDTO.java
│   │   │               │   └── ProductRawMaterialResponseDTO.java
│   │   │               ├── rawmaterial/
│   │   │               │   ├── RawMaterial.java
│   │   │               │   ├── RawMaterialRepository.java
│   │   │               │   ├── RawMaterialRequestDTO.java
│   │   │               │   └── RawMaterialResponseDTO.java
│   │   │               └── ArmazemApplication.java
│   │   └── resources/
│   │       ├── application.properties
│   │       └── application-prod.properties
│   └── test/
│       └── java/
│           └── com/
│               └── example/
│                   └── armazem/
│                       └── ArmazemApplicationTests.java
├── .gitignore
├── mvnw
├── mvnw.cmd
├── pom.xml
└── README.md
```
