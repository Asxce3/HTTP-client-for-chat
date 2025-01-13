package Handler;

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

    public void run() {
        createTokens();

        while (true) {

            System.out.println(conf.getProps());
            System.out.println("К кому обратиться ?");
            String clientChooseRoot = conf.getProps().getProperty(scanner.next());

            if (clientChooseRoot == null) {
                System.out.println("Такого варианта нет!");
            }

        }
    }
    private void createTokens() {
        String JSON = form.setFormForAuth();
        Response response = requestHandler.auth(host, JSON);
        Tokens tokens = responseHandler.checkTokens(response);
        requestHandler.setTokens(tokens);
    }
}

