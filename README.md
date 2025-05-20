# API de Votação Cooperativa

Sistema backend para gerenciamento de pautas e sessões de votação cooperativa, desenvolvido com Spring Boot.

## 🚀 Tecnologias utilizadas
- Java 17
- Spring Boot 3
- Spring Data JPA
- H2 Database (para testes locais)
- Swagger / OpenAPI
- Lombok
- Logback com MDC

## 📦 Como executar o projeto

### 1. Clonar o repositório
```bash
git clone https://github.com/atamanczukfelipe/desafio-votacao.git
cd desafio-votacao
```

### 2. Rodar localmente com Maven Wrapper
```bash
./mvnw spring-boot:run
```

### 3. Acessar o Swagger (documentação da API)
```
http://localhost:8080/swagger-ui/index.html
```

### 4. Acessar o console H2 (opcional)
```
http://localhost:8080/h2-console
```
- JDBC URL: `jdbc:h2:mem:testdb`
- Username: `sa`
- Password: *(em branco)*

## 📘 Endpoints principais
| Método | Rota                            | Descrição                        |
|--------|----------------------------------|----------------------------------|
| POST   | `/api/v1/pautas`                | Criar nova pauta                 |
| POST   | `/api/v1/sessoes`               | Abrir nova sessão de votação     |
| POST   | `/api/v1/votos`                 | Registrar voto (SIM ou NÃO)      |
| GET    | `/api/v1/resultados/{pautaId}`  | Obter resultado da votação       |

## 🧪 Teste completo via Swagger
1. Crie uma pauta com `POST /api/v1/pautas`
2. Abra uma sessão com `POST /api/v1/sessoes`
3. Envie votos com `POST /api/v1/votos`
4. Consulte o resultado com `GET /api/v1/resultados/{pautaId}`

## ⚙️ Logs
- Logs de auditoria com `requestId`, IP, URI, método, user-agent e timestamp são gravados em:
  - `logs/voto-auditoria.log`
- Logs gerais de informações são gravados em:
  - `logs/voto-info.log`

## 🧾 Script de banco
- O projeto carrega automaticamente `schema.sql` com:
  - Criação de tabelas `PAUTA`, `SESSAO_VOTACAO`, `VOTO`
  - Inserções iniciais sem IDs fixos (evita conflitos com AUTO_INCREMENT)

## 🛠️ Configurações YAML
As propriedades estão definidas em `application.yml`, com destaque para:
```yaml
votacao:
  sessao:
    duracao-default-minutos: 1
```
Essa configuração é injetada via `@ConfigurationProperties` para evitar uso de magic numbers.


> Desenvolvido por Felipe Atamanczuk como parte de um desafio técnico ☕
