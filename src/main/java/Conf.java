import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;

import java.nio.charset.StandardCharsets;
import java.util.Properties;

@Getter
@Setter
@ToString
public class Conf {
    private final Logger logger = LoggerFactory.getLogger(Form.class);

    private final Properties props = new Properties();
    private static final String CONFIG_PATH = "src/main/resources/config.properties";

    public Conf() {
        try(Reader reader = new InputStreamReader(new FileInputStream(CONFIG_PATH), StandardCharsets.UTF_8)) {
            props.load(reader);
            logger.info("Загрузка конфиг файла");

        }   catch (IOException e) {
            logger.error(e.getMessage());
            e.printStackTrace();
        }
    }

}
