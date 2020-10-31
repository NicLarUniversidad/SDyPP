package org.handler;

import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.Channel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.google.gson.Gson;
import com.rabbitmq.client.Consumer;
import com.rabbitmq.client.DefaultConsumer;

public class RabbitMQHandler{

    private static Logger logger;

    public static void init(){
        logger = LoggerFactory.getLogger(Logger.class);
    }

    public void publicarMensaje(Object obj, String cola){
        logger.debug("Recibido: \n\tObjeto: " + obj.toString() + "\n\tCola: " + cola);
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");
        Gson gson = new Gson();
        String message = gson.toJson(obj);
        try (Connection connection = factory.newConnection();
            Channel channel = connection.createChannel()) {
            channel.queueDeclare(cola, false, false, false, null);
            channel.basicPublish("", cola, null, message.getBytes());
            logger.debug("Objeto publicado exitosamente");
        }
        catch (Exception e){
            logger.warn("No se ha podido enviar el mensaje: " + message);
            logger.error(e.getLocalizedMessage());
        }
    }

    public void consumirMensaje(String cola){
        boolean autoAck = false;
        channel.basicConsume(queueName, autoAck, "myConsumerTag",
            new DefaultConsumer(channel) {
            @Override
            public void handleDelivery(String consumerTag,
                                        Envelope envelope,
                                        AMQP.BasicProperties properties,
                                        byte[] body)
                throws IOException
            {
                String routingKey = envelope.getRoutingKey();
                String contentType = properties.getContentType();
                long deliveryTag = envelope.getDeliveryTag();
                // (process the message components here ...)
                channel.basicAck(deliveryTag, false);
            }
        });
        
    }

    public void leerMensaje(String cola){
        
    }

    public void leerTodosLosMensajes(String cola){
        
    }
}