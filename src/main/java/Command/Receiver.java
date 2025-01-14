package Command;

import Handler.HTTP.RequestHandler;
import Handler.HTTP.ResponseHandler;
import Model.Message;
import Model.Room;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import okhttp3.Response;

import java.util.List;
import java.util.Scanner;

public class Receiver {
    private final Scanner sc;
    private String url;
    private final ResponseHandler res;
    private final RequestHandler req;

    public Receiver(RequestHandler req, ResponseHandler res, Scanner sc) {
        this.req = req;
        this.res = res;
        this.sc = sc;
    }
    public void getRooms() {
        Response response = req.getRooms(url);
        System.out.println("Users room : " + res.getResponseBody(response));
    }

    public void createRoom() {
        try {
            System.out.println("Введите имя для комнаты: ");
            Room room = new Room();
            room.setRoomName(sc.next());

            String json = new ObjectMapper().writeValueAsString(room);
            Response response = req.createRoom(url, json);
            res.createRoom(response);

        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }

    }

    public void showMessage() {
        System.out.println("Введите id комнаты:");
        String id = sc.next() + "/?";

        System.out.println("Введите messageId:");
        int countMessage = Integer.parseInt(sc.next());


        while (true) {
            String messageId = "messageId=" + countMessage;
            printMessage(url + id + messageId);
            System.out.println("Дальше ?");
            if (sc.next().equals("yes")) {
                countMessage++;
            }   else {
                break;
            }
        }


    }

    public void setUrl(String url) {
        this.url = url;
    }

    private void printMessage(String url) {
        Response response = req.showMessage(url);
        String responseBody = res.getResponseBody(response);
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.registerModule(new JavaTimeModule());
            List<Message> messages = objectMapper.readValue(responseBody, new TypeReference<List<Message>>() {});
            for(Message mes: messages) {
                System.out.println(mes);
            }
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}
