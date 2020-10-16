package socket.server.input;

import java.util.InputMismatchException;
import java.util.Scanner;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Keyboard {
    protected char lastKey;
    protected boolean isKeyPressed = false;
    protected Scanner scanner;
    private Logger log;

    public Keyboard() {
        scanner = new Scanner(System.in);
        this.log = LoggerFactory.getLogger(this.getClass());
    }

    public boolean isKeyPressed(char key){
        isKeyPressed = scanner.hasNext();
        if (isKeyPressed) {
            try{
                lastKey = scanner.nextLine().charAt(0);
                log.debug("Se ha presionado la tecla " + lastKey);
            }
            catch (InputMismatchException e){
                log.error(e.getLocalizedMessage());
            }
            catch (StringIndexOutOfBoundsException e){
                log.error(e.getLocalizedMessage());
            }
        }
        return isKeyPressed && (key == lastKey);
    }
}
