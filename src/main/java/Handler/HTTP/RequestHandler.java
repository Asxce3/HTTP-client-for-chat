package Handler.HTTP;

import Model.Tokens;
import lombok.Getter;
import lombok.Setter;
import okhttp3.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

@Getter
@Setter
public class RequestHandler {
    private final static Logger logger = LoggerFactory.getLogger(RequestHandler.class);
    private final MediaType JSON = MediaType.get("application/json; charset=utf-8");

    private OkHttpClient client = new OkHttpClient();

    private Tokens tokens;

    // AUTH REQUESTS
    public Response auth(String host, String json) {
        RequestBody body = RequestBody.create(json, JSON);
        Request request = new Request.Builder()
                .url(host)
                .post(body)
                .build();
        try (Response response = client.newCall(request).execute()) {
            logger.info("Запрос на получение токенов с кодом : " + response.code());
            return response;

        }   catch (IOException e) {
            logger.error(e.getMessage());
            throw new RuntimeException(e.getMessage());
        }
    }

    public Response refreshTokens(String host) {
        Request request = new Request
                .Builder()
                .url(host + "/refresh")
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

//  ROOMS REQUESTS

    public Response getRooms(String host) {
        Request request = new Request.Builder()
                .url(host)
                .addHeader("accessToken", tokens.getAccessToken())
                .addHeader("refreshToken", tokens.getRefreshToken())
                .build();

        try {
            Response response = client.newCall(request).execute();
            logger.info("Запрос на получение списка Room : {}", response.code());
            return response;

        }   catch (IOException e) {
            logger.error(e.getMessage());
            throw new RuntimeException(e.getMessage());
        }
    }

    public Response createRoom(String host, String json) {
        RequestBody body = RequestBody.create(json, JSON);
        Request request = new Request.Builder()
                .url(host)
                .post(body)
                .addHeader("accessToken", tokens.getAccessToken())
                .addHeader("refreshToken", tokens.getRefreshToken())
                .build();
        try (Response response = client.newCall(request).execute()) {
            logger.info("Запрос на создание комнаты с кодом : " + response.code());
            return response;

        }   catch (IOException e) {
            logger.error(e.getMessage());
            throw new RuntimeException(e.getMessage());
        }
    }

    public Response showMessage(String host) {

        Request request = new Request.Builder()
                .url(host)
                .addHeader("accessToken", tokens.getAccessToken())
                .addHeader("refreshToken", tokens.getRefreshToken())
                .build();
        try {
            Response response = client.newCall(request).execute();
            logger.info("Запрос на просмотр сообщения: " + response.code());
            return response;

        }   catch (IOException e) {
            logger.error(e.getMessage());
            throw new RuntimeException(e.getMessage());
        }
    }



}
