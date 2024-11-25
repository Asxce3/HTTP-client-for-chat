import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Scanner;

public class Form {
    private final Logger logger = LoggerFactory.getLogger(Form.class);
    private final ObjectMapper objectMapper = new ObjectMapper();
    private final User user = new User();

    public String setFormForAuth()  {
        logger.info("Заполнение формы для авторизации");
        try (Scanner sc = new Scanner(System.in)) {
            System.out.println("Введите username : ");
            String username = sc.next();
            System.out.println("Введите password : ");
            String password = sc.next();

            user.setUsername(username);
            user.setPassword(password);
            return objectMapper.writeValueAsString(user);

        }   catch (JsonProcessingException e) {
            logger.error(e.getMessage());
            throw new RuntimeException(e.getMessage());
        }
    }
}
