package Handler;

import Command.*;
import Config.Conf;
import Handler.HTTP.InputData.InputUserData;
import Handler.HTTP.InputData.NoAutoForm;
import Handler.HTTP.RequestHandler;
import Handler.HTTP.ResponseHandler;
import Model.Tokens;
import okhttp3.Response;

import java.util.*;


public class MainHandler {
    private final Conf conf = new Conf();

    private final RequestHandler requestHandler = new RequestHandler();
    private final ResponseHandler responseHandler = new ResponseHandler();
    private final InputUserData form = new NoAutoForm(conf);

    private final Scanner scanner = new Scanner(System.in);

    private final String host = conf.getProps().getProperty("host");
    private final Map<String, Command> CommandMap = new HashMap<>();

    public void run() {
        int codeTokens = createTokens();
        if(codeTokens != 200 ) {
            System.out.println("Не удалось полученить токены!");
            return;
        }

        while (true) {

            System.out.println(conf.getProps());
            System.out.println("К кому обратиться ?");

            String userEndPoint = scanner.next();

            Receiver receiver = new Receiver(requestHandler, responseHandler, scanner);
            initializationCommandMap(receiver);

            String userChooseUrl = conf.getProps().getProperty(userEndPoint);

            if(CommandMap.get(userEndPoint) != null && userChooseUrl != null) {
                receiver.setUrl(host + userChooseUrl);
                CommandMap.get(userEndPoint).execute();
            }   else {
                System.out.println("Такого вариант нет !");
            }
        }
    }
    private int createTokens() {
        String JSON = form.setFormForAuth();
        Response response = requestHandler.auth(host, JSON);
        if(response.code() == 200) {
            Tokens tokens = responseHandler.checkTokens(response);
            requestHandler.setTokens(tokens);
        }
        return response.code();

    }

    private void initializationCommandMap(Receiver receiver) {
        Command getRooms = new CommandGetRooms(receiver);
        Command createCommand = new CommandCreateRoom(receiver);
        Command showMessage = new CommandShowMessage(receiver);
        CommandMap.put("get_rooms", getRooms);
        CommandMap.put("post_rooms", createCommand);
        CommandMap.put("current_room", showMessage);
    }
}

