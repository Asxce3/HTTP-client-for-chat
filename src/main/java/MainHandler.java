import okhttp3.Response;


public class MainHandler {
    private final RequestHandler requestHandler = new RequestHandler();
    private final ResponseHandler responseHandler = new ResponseHandler();
    private final Form form = new Form();
    private final Conf conf = new Conf();

    public void run() {
        String JSON = form.setFormForAuth();
        Response response = requestHandler.auth(conf.getProps().getProperty("url"), JSON);
        Tokens tokens = responseHandler.checkTokens(response);
        requestHandler.setTokens(tokens);
        requestHandler.checkJWT(conf.getProps().getProperty("url"));
    }


}
