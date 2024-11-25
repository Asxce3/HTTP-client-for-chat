import lombok.Getter;
import lombok.Setter;
import okhttp3.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

@Getter
@Setter
public class RequestHandler {
    private final static Logger logger = LoggerFactory.getLogger(MainHandler.class);
    private final MediaType JSON = MediaType.get("application/json; charset=utf-8");

    private OkHttpClient client = new OkHttpClient();

    private Tokens tokens;

    public Response auth(String url, String json) {
        RequestBody body = RequestBody.create(json, JSON);
        Request request = new Request.Builder()
                .url(url)
                .post(body)
                .build();
        try (Response response = client.newCall(request).execute()) {
            logger.info("Запрос на получение токенов с кодом : {}", response.code());
            return response;

        }   catch (IOException e) {
            logger.error(e.getMessage());
            throw new RuntimeException(e.getMessage());
        }
    }

    public Response checkJWT(String url) {
        Request request = new Request
                .Builder()
                .url(url)
                .addHeader("accessToken", tokens.getAccessToken())
                .addHeader("refreshToken", tokens.getRefreshToken())
                .build();
        try (Response response = client.newCall(request).execute()) {
            logger.info("Запрос на проверку JWT с кодом : {}", response.code());
            return response;

        }   catch (IOException e) {
            logger.error(e.getMessage());
            throw new RuntimeException(e.getMessage());
        }
    }

    public Response refreshTokens(String url) {
        Request request = new Request
                .Builder()
                .url(url + "/refresh")
                .addHeader("refreshToken", tokens.getRefreshToken())
                .build();

        try (Response response = client.newCall(request).execute()) {
            logger.info("Запрос на замену токенов с кодом : {}", response.code());
            return response;

        }   catch (IOException e) {
            logger.error(e.getMessage());
            throw new RuntimeException(e.getMessage());
        }
    }

}
