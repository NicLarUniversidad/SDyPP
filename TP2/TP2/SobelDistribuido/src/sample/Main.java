package sample;


import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.time.LocalTime;

public class Main  {

    Logger log = LoggerFactory.getLogger(Main.class);

    public static void main(String[] args) throws IOException {
        String filePath = args[0];
        LocalTime initTime = LocalTime.now();
        BufferedImage imagen;
        File file = null;
        file = new File(filePath);
        imagen = ImageIO.read(file);
        log.info("Calculando imagen (Sin cortes): "+file.getName());

        Sobel filtro = new Sobel(imagen);

        BufferedImage resultado = filtro.aplicarFiltro();

        LocalTime endTime = LocalTime.now();
        log.info("Init time:"+initTime.toString());
        log.info("End time:"+endTime.toString());
        log.info("Delta time:"+Duration.between(initTime, endTime));

        // write image
        try {
            file = new File("image_singleThread_sinCorte_sobel.png");
            ImageIO.write(resultado, "png", file);
        } catch (IOException e) {
            System.err.println(e);
        }

    } catch (IOException e) {
        e.printStackTrace();
    }
    }
}
