import amqp from 'amqplib';
import dotenv from 'dotenv';

dotenv.config({ path: '../.env' });

const exchange = 'order-exchange';
const queue = 'order-created';

const messages = [
  {
    codigoPedido: 1001,
    codigoCliente: 1,
    itens: [
      { produto: "lápis", quantidade: 100, preco: 1.10 },
      { produto: "caderno", quantidade: 10, preco: 1.00 }
    ]
  },
  {
    codigoPedido: 1002,
    codigoCliente: 2,
    itens: [
      { produto: "caneta", quantidade: 50, preco: 2.50 }
    ]
  },
  {
    codigoPedido: 1003,
    codigoCliente: 3,
    itens: [
      { produto: "borracha", quantidade: 200, preco: 0.50 },
      { produto: "caderno", quantidade: 5, preco: 1.00 }
    ]
  },
  {
    codigoPedido: 1004,
    codigoCliente: 1,
    itens: [
      { produto: "lapiseira", quantidade: 20, preco: 3.50 }
    ]
  }
];

async function seed() {
  const connection = await amqp.connect({
    protocol: 'amqp',
    hostname: process.env.RABBITMQ_HOST,
    port: parseInt(process.env.RABBITMQ_PORT, 10),
    username: process.env.RABBITMQ_USER,
    password: process.env.RABBITMQ_PASSWORD
  });

  const channel = await connection.createChannel();
  await channel.assertExchange(exchange, 'direct', { durable: true });
  await channel.assertQueue(queue, { durable: true });
  await channel.bindQueue(queue, exchange, queue);

  for (const msg of messages) {
    channel.publish(exchange, queue, Buffer.from(JSON.stringify(msg)), { persistent: true });
    console.log(`Message sent: ${msg}`);
  }

  await channel.close();
  await connection.close();
  console.log('All messages published!');
}

seed().catch(console.error);
