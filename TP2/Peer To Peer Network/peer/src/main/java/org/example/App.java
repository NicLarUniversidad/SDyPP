package org.example;

import java.io.IOException;
import org.handler.RabbitMQHandler;

public class App 
{
    public static void main( String[] args )
    {
        RabbitMQHandler r = new RabbitMQHandler();
        RabbitMQHandler.init();
        r.publicarMensaje("pepe","pepe");
    }
}
