package Command;

import Handler.HTTP.RequestHandler;
import Handler.HTTP.ResponseHandler;
import Model.Room;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import okhttp3.Response;

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
        System.out.println("Users room : " + res.getUserRooms(response));
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

    public void setHost(String url) {
        this.url = url;
    }
}
