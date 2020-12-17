package org.example;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.concurrent.TimeUnit;
import com.rabbitmq.client.MessageProperties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ImagenService implements IServicioRemoto {

    static Logger log = LoggerFactory.getLogger(ImagenService.class);

    @Override
    public Imagen getImagen(Imagen imagen) throws RemoteException {
        Date initTime = new Date();
        try {
            Servidor.queueChannel.queuePurge(Servidor.queueTrabajos);
            Servidor.queueChannel.queuePurge(Servidor.queueTerminados);
            Servidor.queueChannel.queuePurge(Servidor.queueEnProceso);
            BufferedImage bufferedImage = imagen.getImage();
            Cortador imgHandler = new Cortador(bufferedImage, Servidor.CANT_CORTES);
            boolean hayMasMensajes = true;
            ArrayList<Imagen> imagenesCortadas = imgHandler.cortar();
            ArrayList<BufferedImage> buffImgOrdenada = new ArrayList<BufferedImage>();
            for (Imagen imgCutted : imagenesCortadas) {
                Servidor.queueChannel.basicPublish("", Servidor.queueTrabajos, MessageProperties.PERSISTENT_TEXT_PLAIN, Imagen.imagenToByteArr(imgCutted));
            }
            log.info("Cola queueTrabajos: " + (int) Servidor.queueChannel.messageCount(Servidor.queueTrabajos));
            instantiateWorkers();
            int cortes = (int) Math.pow(((int) Math.sqrt(Servidor.CANT_CORTES)), 2);
            while (hayMasMensajes) {
                int terminados = (int) Servidor.queueChannel.messageCount(Servidor.queueTerminados);
                if (terminados == cortes) {//Cant Cortes Real
                    hayMasMensajes = false;
                }
            }
            ArrayList<Imagen> resultado = new ArrayList<Imagen>();
            synchronized (Servidor.queueConnection) {
                for (int i = 0; i < imagenesCortadas.size(); i++) {
                    byte[] data = Servidor.queueChannel.basicGet(Servidor.queueTerminados, false).getBody();
                    resultado.add(Imagen.ByteArrToImagenObj(data));
                }
            }
            Collections.sort(resultado, new Comparator<Imagen>() {
                @Override
                public int compare(Imagen o1, Imagen o2) {
                    return Integer.compare(o1.getNroImage(), o2.getNroImage());
                }
            });

            for (Imagen i : resultado) {
                buffImgOrdenada.add(i.getImage());
            }
            BufferedImage result = imgHandler.unirImagen(buffImgOrdenada);
            Date endTime = new Date();
            Servidor.queueChannel.queueDelete(Servidor.queueTrabajos);
            Servidor.queueChannel.queueDelete(Servidor.queueTerminados);
            Servidor.queueChannel.queueDelete(Servidor.queueEnProceso);
            Servidor.queueChannel.queueDeclare(Servidor.queueTrabajos, true, false, false, null);
            Servidor.queueChannel.queueDeclare(Servidor.queueEnProceso, true, false, false, null);
            Servidor.queueChannel.queueDeclare(Servidor.queueTerminados, true, false, false, null);

            log.info("Inicio:" + initTime.toString());
            log.info("Fin:" + endTime.toString());
            long diffInMillies = Math.abs(endTime.getTime() - initTime.getTime());
            long diff = TimeUnit.MILLISECONDS.convert(diffInMillies, TimeUnit.MILLISECONDS);
            log.info("Tiempo:" + diff + "milisegundos");

            return new Imagen(result);
        } catch (Exception e) {
            return null;
        }
    }

    private void instantiateWorkers() {
        for (int i = 0; i < Servidor.CANT_WORKER; i++) {
            Worker worker = new Worker(Servidor.queueChannel, Servidor.queueConnection);
            Thread thWorker = new Thread(worker);
            thWorker.start();
        }
    }
}
