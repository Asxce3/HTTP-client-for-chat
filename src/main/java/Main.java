import Handler.MainHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

public class Main {
    private final static Logger logger = LoggerFactory.getLogger(Main.class);
    public static void main(String[] args) throws IOException {
        logger.info("Приложение запущено!");
        MainHandler mainHandler = new MainHandler();
        mainHandler.run();
        logger.info("Приложение финишировало!");
    }
}
