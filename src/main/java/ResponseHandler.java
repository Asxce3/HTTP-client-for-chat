import okhttp3.Response;

public class ResponseHandler {
    // Похорошему их надо будет отвязать друг от друга, но сделаем это позже. Пока идей нет
    private RequestHandler requestHandler;

    public ResponseHandler(RequestHandler requestHandler) {
        this.requestHandler = requestHandler;
    }

    public void setTokens(Response response) {
        if (response.code() == 200) {
            // Не забываем проверять, мы никогда и никому не доверяем! Вот будет баг, вернут 200, а токенов нет, вот клиент охуеет
            requestHandler.setAccessToken(response.header("accessToken"));
            requestHandler.setRefreshToken(response.header("refreshToken"));
        }   else {
            System.out.println("Произошла ошибка с кодом " + response.code()); 
        }
    }


}
