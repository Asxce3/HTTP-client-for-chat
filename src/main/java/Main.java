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
    private static final String URL = "http://localhost:8080";

    public static void main(String[] args) throws IOException {
        List<String> roots = Files.readAllLines(Paths.get("src/main/resources/roots.txt"));

        RequestHandler requestHandler = new RequestHandler();
        ResponseHandler responseHandler = new ResponseHandler(requestHandler);
        Form form = new Form();

        String JSON = form.setFormForAuth();
        Response response = requestHandler.auth(URL, JSON);
        responseHandler.setTokens(response);



        while (true) {
            System.out.println("К кому обратиться ? ");
            System.out.println(roots);

            Scanner sc1 = new Scanner(System.in);

            if (sc1.nextLine().equals("1")) {
                System.out.println("Создать ?");
                System.out.println("Посмотреть список?");

                Scanner sc2 = new Scanner(System.in);
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

