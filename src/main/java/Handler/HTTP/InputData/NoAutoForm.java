package Handler.HTTP.InputData;

import Config.Conf;
import Model.User;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Scanner;

public class NoAutoForm implements InputUserData {
    private final Logger logger = LoggerFactory.getLogger(NoAutoForm.class);
    private final ObjectMapper objectMapper = new ObjectMapper();
    private final User user = new User();
    private Conf conf;

    public NoAutoForm(Conf conf) {
        this.conf = conf;
    }

    public String setFormForAuth()  {
        logger.info("Заполнение формы для авторизации");
        try {
            System.out.println("Введите username : ");
            user.setUsername(conf.getProps().getProperty("username"));
            System.out.println("Введите password : ");
            user.setPassword(conf.getProps().getProperty("password"));
            return objectMapper.writeValueAsString(user);

        }   catch (JsonProcessingException e) {
            logger.error(e.getMessage());
            throw new RuntimeException(e.getMessage());
        }
    }

    public void setScanner(Scanner scanner) {
    }
}
