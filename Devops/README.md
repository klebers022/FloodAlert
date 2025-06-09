# 🚀 FloodAlert - DevOps Infraestrutura com Docker

Este repositório contém os arquivos de configuração necessários para a criação da infraestrutura da **API FloodAlert**, utilizando `Docker`, `Docker Compose` e um banco de dados Oracle Express.

---

# Link do vídeo demonstrando o Projeto

🔗 ["YouTube"] (https://youtu.be/rhAE8zvpNX8)

---

## 📦 Estrutura

- **Dockerfile**: Define o processo de build e publicação da aplicação .NET 9.0.
- **docker-compose.yml**: Orquestra a aplicação e o banco de dados Oracle XE.

---

## 🛠️ Tecnologias

- ASP.NET 9.0 (SDK e Runtime)
- Oracle Database XE 21c
- Docker / Docker Compose

---

## 📁 Estrutura dos Containers

### API (`api`)

- Build da pasta: `./Net/GlobalSolution`
- Porta exposta: `5149`
- Variável de conexão com o Oracle definida via `environment`

### Banco de Dados (`oracle`)

- Imagem: `container-registry.oracle.com/database/express:21.3.0-xe`
- Porta exposta: `1521`
- Senha padrão: `oracle`
- Character set: `AL32UTF8`

---

## ▶️ Como Executar

> Certifique-se de ter o Docker e Docker Compose instalados.

```bash
# Clone o repositório
git clone https://github.com/klebers022/floodalert.git
cd FloodAlert
cd Devops

# Suba os containers
docker-compose up --build
```

---

## 🌐 API no Docker Hub

A imagem da API está publicada em:

🔗 [`lucasrainha/floodalert-api`](https://hub.docker.com/r/lucasrainha/floodalert-api)

Você pode utilizar diretamente com:

```bash
docker pull lucasrainha/floodalert-api
```

---

## 🧪 Testes de Conectividade

- API: [http://localhost:5149/swagger](http://localhost:5149/swagger)
- Oracle: Verifique conectividade pela porta `1521` com as credenciais `system/oracle` e SID `XEPDB1`.

---

## 🧠 Sobre o Projeto

O **FloodAlert** é uma solução integrada para mapeamento, alerta e resposta a eventos de enchentes. Esta configuração DevOps automatiza o ambiente da API e banco de dados para facilitar o desenvolvimento, testes e deploy.

---

## 👥 Contribuidores

- Kleber da Silva RM- 557887
- Nicolas Barutti RM- 554944
- Lucas Rainha RM- 558471

---

## 📄 Licença

MIT © 2025 — Projeto Global Solution FIAP
