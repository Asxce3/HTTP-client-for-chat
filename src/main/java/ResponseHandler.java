import okhttp3.Response;

public class ResponseHandler {
    private RequestHandler requestHandler;

    public ResponseHandler(RequestHandler requestHandler) {
        this.requestHandler = requestHandler;
    }

    public void setTokens(Response response) {
        if (response.code() == 200) {
            requestHandler.setAccessToken(response.header("accessToken"));
            requestHandler.setRefreshToken(response.header("refreshToken"));
        }   else {
            System.out.println("Произошла ошибка с кодом " + response.code());
        }
    }


}
