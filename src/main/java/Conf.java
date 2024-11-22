import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.*;

import java.nio.charset.StandardCharsets;
import java.util.Properties;

@Getter
@Setter
@ToString
public class Conf {
    private final Properties props = new Properties();
    private static final String CONFIG_PATH = "src/main/resources/config.properties";

    public Conf() {
        try(Reader reader = new InputStreamReader(new FileInputStream(CONFIG_PATH), StandardCharsets.UTF_8)) {
            props.load(reader);
        }   catch (IOException e) {
            e.printStackTrace();
        }
    }

}
