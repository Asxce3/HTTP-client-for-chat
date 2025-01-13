package Handler.HTTP;

import Model.Tokens;
import com.fasterxml.jackson.databind.ObjectMapper;
import okhttp3.Response;
import okhttp3.ResponseBody;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;


public class ResponseHandler {
    private final static Logger logger = LoggerFactory.getLogger(ResponseHandler.class);
    private final Tokens tokens = new Tokens();
    private final ObjectMapper objectMapper = new ObjectMapper();

    public Tokens checkTokens(Response response) {
        if (response.code() == 200) {
            String accessToken = response.header("accessToken");
            String refreshToken = response.header("refreshToken");
            if (accessToken != null && refreshToken != null) {
                tokens.setAccessToken(accessToken);
                tokens.setRefreshToken(refreshToken);
            }

        }   else {
            logger.error("Произошла ошибка с кодом: {}", response.code());
        }
        return tokens;
    }

    public String resRooms(Response response) {
        try {
            if (checkTokens(response).getAccessToken() != null && response.body() != null) {
                ResponseBody responseBody = response.body();
                return responseBody.string();
            }
            return null;

        } catch (IOException e) {
            logger.error("Ошибка при получении списка Room");
            throw new RuntimeException(e.getMessage());
//            Ручное закрыте объекта response
        } finally {
            response.close();
        }

    }

    public void createRoom(Response response) {
        int code = response.code();
        if (checkTokens(response).getAccessToken() != null && code == 200) {
            logger.info("Response код по создание комнаты: {}", response.code());
        }   else {
            logger.error("Произошла ошибка при создании комнаты с кодом: {}", code);
        }
        response.close();

    }

}
