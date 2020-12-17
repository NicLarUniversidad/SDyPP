package org.example;

import java.io.IOException;
import java.rmi.Remote;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.concurrent.TimeoutException;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Servidor {

    public static int port = 13000;
    public static final String user = "user";
    public static final String password = "bitnami";
    public static final String ip = "localhost";
    public static final int CANT_WORKER = 10;
    public static final int CANT_CORTES = 1500;
    public static ConnectionFactory queueConnectionFactory;
    public static Connection queueConnection;
    public static Channel queueChannel;
    public static String queueTrabajos = "queueTrabajos";
    public static String queueEnProceso = "queueEnProceso";
    public static String queueTerminados = "queueTerminados";

    public static void main(String[] args) throws IOException, TimeoutException {
        queueConnectionFactory = new ConnectionFactory();
        queueConnectionFactory.setUsername(user);
        queueConnectionFactory.setPassword(password);
        queueConnection = queueConnectionFactory.newConnection();
        queueChannel = queueConnection.createChannel();
        queueChannel.queueDeclare(queueTrabajos, true, false, false, null);
        queueChannel.queueDeclare(queueEnProceso, true, false, false, null);
        queueChannel.queueDeclare(queueTerminados, true, false, false, null);
        IServicioRemoto servicioRemoto = new ImagenService();
        Remote remote = UnicastRemoteObject.exportObject(servicioRemoto,0);
        Registry registry = LocateRegistry.createRegistry(port);
        registry.rebind("Tarea", remote);
    }
}


