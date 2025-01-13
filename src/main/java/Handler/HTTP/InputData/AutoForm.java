package Handler.HTTP.InputData;

import Model.User;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Scanner;

public class AutoForm implements InputUserData {
    private final Logger logger = LoggerFactory.getLogger(AutoForm.class);
    private final ObjectMapper objectMapper = new ObjectMapper();
    private final User user = new User();
    private Scanner scanner;

    public AutoForm(Scanner scanner) {
        this.scanner = scanner;
    }

    public String setFormForAuth()  {
        logger.info("Заполнение формы для авторизации");
        try {
            System.out.println("Введите username : ");
            String username = scanner.next();
            System.out.println("Введите password : ");
            String password = scanner.next();

            user.setUsername(username);
            user.setPassword(password);
            return objectMapper.writeValueAsString(user);

        }   catch (JsonProcessingException e) {
            logger.error(e.getMessage());
            throw new RuntimeException(e.getMessage());
        }
    }


}
