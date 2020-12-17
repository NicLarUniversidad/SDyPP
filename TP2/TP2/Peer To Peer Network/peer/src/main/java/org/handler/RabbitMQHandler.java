package org.handler;

import com.rabbitmq.client.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.google.gson.Gson;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeoutException;

public class RabbitMQHandler{

    private static Logger logger;
    private static Channel queueChannel;
    private static Connection queueConnection;
    public static String ip = "localhost";
    public static String user = "user";
    public static String password = "bitnami";
    private static ConnectionFactory queueConnectionFactory;

    public static void init() throws IOException, TimeoutException {
        logger = LoggerFactory.getLogger(Logger.class); 
        queueConnectionFactory = new ConnectionFactory();
        queueConnectionFactory.setHost(ip);
        queueConnectionFactory.setUsername(user);
        queueConnectionFactory.setPassword(password);
        queueConnection = queueConnectionFactory.newConnection();
        queueChannel = queueConnection.createChannel();
    }

    public static void publicarMensaje(Object obj, String cola) throws IOException {
        Gson gson = new Gson();
        String message = gson.toJson(obj);
        logger.debug("Recibido: \n\tObjeto: " + message);
        logger.debug("Recibido: \n\tCola: " + cola);
        logger.debug("Recibido: \n\tObjeto: " + message+ "\n\tCola: " + cola);
        queueChannel.basicPublish("", cola, MessageProperties.PERSISTENT_TEXT_PLAIN, message.getBytes());
        /*ConnectionFactory factory = new ConnectionFactory();
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
        }*/
    }

    public String consumirMensaje(String cola) throws IOException {
        GetResponse gr = queueChannel.basicGet("some.queue", false);
        queueChannel.basicNack(gr.getEnvelope().getDeliveryTag(), true, true);
        return new String(gr.getBody());
        /*boolean autoAck = false;
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
        });*/
    }

    public static String leerMensaje(String cola) throws IOException {
        if (queueChannel == null || cola == null) {
            return "";
        }
        GetResponse res = queueChannel.basicGet(cola, true);
        if (res == null) {
            return "";
        }
        return new String(res.getBody());
    }

    public static List<String> leerTodosLosMensajes(String cola) throws IOException {
        List<String> res = new ArrayList<String>();
        res.add(leerMensaje(cola));
        return res;
    }
}