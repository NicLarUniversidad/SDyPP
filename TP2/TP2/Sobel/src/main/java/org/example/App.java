package org.example;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.concurrent.TimeUnit;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.imageio.ImageIO;

public class App {

    static Logger log = LoggerFactory.getLogger(App.class);

    public static void main(String[] args) throws IOException {
        String filePath = "";
        if (args.length != 0){
            filePath = args[0];
        } else {
            filePath = "prueba.jpg";
        }
        Date inicio = new Date();
        BufferedImage imagen;
        File file = null;
        file = new File(filePath);
        imagen = ImageIO.read(file);
        log.info("Calculando imagen (Sin cortes): " + file.getName());

        Sobel filtro = new Sobel(imagen);

        BufferedImage resultado = filtro.aplicarFiltro();

        Date fin = new Date();
        log.info("Inicio:" + inicio.toString());
        log.info("End time:" + fin.toString());
        long diffInMillies = Math.abs(fin.getTime() - inicio.getTime());
        long diff = TimeUnit.MILLISECONDS.convert(diffInMillies, TimeUnit.MILLISECONDS);
        log.info("Delta time:" + diff);
        file = new File("resultado.png");
        ImageIO.write(resultado, "png", file);

    }
}


