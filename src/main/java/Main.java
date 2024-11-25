import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

public class Main {
    private static final Logger logger = LoggerFactory.getLogger(Main.class);

    public static void main(String[] args) {
        logger.info("App start");
        MainHandler mainHandler = new MainHandler();
        mainHandler.run();
        logger.info("App finish!");

//        while (true) {
//            System.out.println("К кому обратиться ? ");
//            System.out.println(roots);
//
//            Scanner sc1 = new Scanner(System.in);
//
//            if (sc1.nextLine().equals("1")) {
//                System.out.println("Создать ?");
//                System.out.println("Посмотреть список?");
//
//                Scanner sc2 = new Scanner(System.in);
//                if (sc2.nextLine().equals("1")) {
////                    Вызывает метод создания комнаты
//                }
//                if (sc2.nextLine().equals("2")) {
////                    Вызывает метод просмотра комнат
//                }
//            }
//
//            if (sc1.nextLine().equals("2")) {
////                    Вызывает метод просмотра определенной комнаты
//            }
//        }

    }

}

