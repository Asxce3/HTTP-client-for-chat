import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.Scanner;

public class Form {
    private Scanner sc = new Scanner(System.in); // Память не резиновая, i/o штуки надо закрывать
    private ObjectMapper objectMapper = new ObjectMapper();
    private User user = new User();

    public String setFormForAuth()  {
        try { // используй try-with-resources
            System.out.println("Введите username : ");
            String username = sc.next();
            System.out.println("Введите password : ");
            String password = sc.next();

            user.setUsername(username);
            user.setPassword(password);

            return objectMapper.writeValueAsString(user);

        }   catch (JsonProcessingException e) {
            // Используй сразу нормальные интсрументы логирования
            throw new RuntimeException(e.getMessage()); // ??? Пометка для меня, углубиться в передачу StackTrace 
        }
    }
}
