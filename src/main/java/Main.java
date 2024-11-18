import okhttp3.Response;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    private static final String URL = "http://localhost:8080"; // в конфиги

    public static void main(String[] args) throws IOException {

        // Сразу выноси в отдельные объекты, в мейне максиммум базовая инициалзиация объектов и передача.

        List<String> roots = Files.readAllLines(Paths.get("src/main/resources/roots.txt")); // в конфиги

        RequestHandler requestHandler = new RequestHandler();
        ResponseHandler responseHandler = new ResponseHandler(requestHandler);
        Form form = new Form();

        String JSON = form.setFormForAuth();
        Response response = requestHandler.auth(URL, JSON);
        responseHandler.setTokens(response);


        // Используй Паттерн Commander (Кстати тут можно будет использовать Хэш-мапы, по крайней мере насколько я могу представлять реализацию в Java)
        while (true) {
            System.out.println("К кому обратиться ? ");
            System.out.println(roots);

            Scanner sc1 = new Scanner(System.in); // Закрывать i/0 кто будет?
            // Не забываем про обработку ошибок!!!

            // Если не одно-то другое, используй else if что бы код лишний раз не проверял ифы. А лучше тут использовать switch-case
            
            if (sc1.nextLine().equals("1")) { // Не исользуй строки для сравнения, используй Enum'ы или константные объекты
                System.out.println("Создать ?");
                System.out.println("Посмотреть список?");

                Scanner sc2 = new Scanner(System.in); // Уверен что нужен ещё один объект сканера? 
                // Разнеси логику в отдельные объекты
                if (sc2.nextLine().equals("1")) {
//                    Вызывает метод создания комнаты
                }
                if (sc2.nextLine().equals("2")) {
//                    Вызывает метод просмотра комнат
                }
            }

            if (sc1.nextLine().equals("2")) {
//                    Вызывает метод просмотра определенной комнаты
            }
        }




    }

}

