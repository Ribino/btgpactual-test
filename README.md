# 🚀 BTG - Processar Pedido e Gerar Relátorio (Teste Técnico)

Este projeto contém a solução do desafio técnico, utilizando **Docker Compose** para orquestrar os serviços necessários.

---

## 🔧 Como executar

1. Clone este repositório.
2. Certifique-se de ter instalado:
    - [Docker](https://docs.docker.com/get-docker/)
    - [Docker Compose](https://docs.docker.com/compose/)
3. Execute o comando abaixo para subir o ambiente:
   ```bash
   docker-compose --env-file .env up --build
   ```
4. A aplicação estará disponível em:
   ```
   http://localhost:8081
   ```

---

## 📌 Endpoints disponíveis

### 📦 Orders
- **Obter valor total de um pedido específico**
    - `GET /api/public/order/{codigoDoPedido}/totalAmount`
    - Exemplo:
      ```
      http://localhost:8081/api/public/order/1001/totalAmount
      ```
    - Pedidos de teste: **1001, 1002, 1003, 1004**

- **Obter valor total de todos os pedidos**
    - `GET /api/public/order/totalAmount`

---

### 👤 Clients
- **Contar pedidos de um cliente**
    - `GET /api/public/order/clients/{codigoDoCliente}/orders/count`
    - Exemplo:
      ```
      http://localhost:8081/api/public/order/clients/1/orders/count
      ```
    - Clientes de teste: **1, 2, 3**

- **Contar pedidos de todos os clientes**
    - `GET /api/public/order/clients/orders/count`

- **Listar todos os pedidos de todos os clientes**
    - `GET /api/public/order/clients/orders`

- **Listar pedidos de um cliente específico**
    - `GET /api/public/order/clients/{codigoDoCliente}/orders`
    - Exemplo:
      ```
      http://localhost:8081/api/public/order/clients/2/orders
      ```

---

## 📄 Paginação

Os endpoints que retornam listas suportam **paginação**.
Utilize os parâmetros opcionais:

- `page` → número da página (inicia em 0, caso não informado o padrão é 0)
- `size` → quantidade de registros por página (caso não informado o padrão é 10)

### Exemplo:
```
http://localhost:8081/api/public/order/clients/1/orders?page=0&size=5
```

### Observação:
O único endpoint que não suporta ***paginação*** é o de listar todos os pedidos de todos os clientes.

---

## 🛠 Tecnologias utilizadas
- Java (Spring Boot)
- Node (Gerador de Mensagens)
- PostgreSQL
- RabbitMQ
- Docker & Docker Compose

---

## 📖 Observações
- O arquivo **`.env`** já está no repositório para facilitar a execução.
- Todos os endpoints são **públicos** (sem autenticação).  
