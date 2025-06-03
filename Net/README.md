
# 🌊 Flood Alert - API

API desenvolvida em .NET para a plataforma **Flood Alert**, uma solução criada para o desafio da **Global Solution 2025 da FIAP**, com o objetivo de auxiliar na gestão de enchentes urbanas.

## 🚀 Tecnologias Utilizadas

- .NET 8
- ASP.NET Core Web API
- Entity Framework Core
- Banco de Dados Oracle
- Swagger (Documentação Interativa)
- CORS Habilitado
- RESTful API

## 🗺️ Funcionalidades da API

- 🔔 **Alertas:** Gerenciamento de alertas de enchentes.
- 🚩 **Áreas de Risco:** Cadastro e monitoramento de zonas de risco.
- 🏠 **Abrigos:** Consulta de abrigos disponíveis e status de ocupação.
- 🚨 **Ocorrências:** Registro de incidentes e problemas na cidade.

## 🔗 Endpoints Principais

| Entidade       | Endpoint Base               |
|----------------|------------------------------|
| Alertas        | `/api/alerts`               |
| Áreas de Risco | `/api/dangerareas`          |
| Abrigos        | `/api/shelters`             |
| Ocorrências    | `/api/incidents`            |

## 📦 Instalação e Execução Local

### Pré-requisitos:
- .NET 8 SDK instalado
- Banco de dados Oracle ativo
- Visual Studio, Rider ou VSCode
- Docker (opcional)

### 1️⃣ Clone o repositório

```bash
git clone https://github.com/seu-usuario/FloodAlert-API.git
cd FloodAlert-API
```

### 2️⃣ Configure a conexão com o banco Oracle

Edite o arquivo `appsettings.json`:

```json
{
  "ConnectionStrings": {
    "Oracle": "User Id=seu_usuario;Password=sua_senha;Data Source=seu_servidor"
  }
}
```

### 3️⃣ Execute as migrações (Criação automática das tabelas)

```bash
dotnet ef database update
```

### 4️⃣ Rode o projeto

```bash
dotnet run
```

A API estará disponível em:

```
https://localhost:7076
```

## 📑 Documentação Interativa (Swagger)

Acesse:

```
https://localhost:7076/swagger
```

## 🗂️ Estrutura do Projeto

```
GlobalSolution/
├── Controllers/
├── Models/
├── Data/ (Contexto)
├── Migrations/
├── Program.cs
├── appsettings.json
```

## 🚩 Observações Importantes

- Ative as regras de CORS para permitir o consumo via mobile e web.
- Certifique-se de que sua API esteja acessível na rede local ou na nuvem para integração com o App Mobile.

## 👥 Contribuidores

- Nicolas Barutti
- Nome do seu grupo
- FIAP - 2TDS - Global Solution 2025

## 📜 Licença

Este projeto é desenvolvido para fins acadêmicos no contexto do Global Solution da FIAP.
