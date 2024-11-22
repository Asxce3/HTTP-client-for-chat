import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.Scanner;

public class Form {
    private final ObjectMapper objectMapper = new ObjectMapper();
    private final User user = new User();

    public String setFormForAuth()  {
        try (Scanner sc = new Scanner(System.in)) {
            System.out.println("Введите username : ");
            String username = sc.next();
            System.out.println("Введите password : ");
            String password = sc.next();

            user.setUsername(username);
            user.setPassword(password);
            return objectMapper.writeValueAsString(user);

        }   catch (JsonProcessingException e) {

            throw new RuntimeException(e.getMessage());
        }
    }
}
