import lombok.Getter;
import lombok.Setter;
import okhttp3.*;

import java.io.IOException;

@Getter
@Setter
public class RequestHandler {
    private final MediaType JSON = MediaType.get("application/json; charset=utf-8");
    private OkHttpClient client = new OkHttpClient();

    private String accessToken;
    private String refreshToken;

    public Response auth(String url, String json) {
        RequestBody body = RequestBody.create(json, JSON);
        Request request = new Request.Builder()
                .url(url)
                .post(body)
                .build();
        try (Response response = client.newCall(request).execute()) {
            return response;

        }   catch (IOException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    public Response CheckToken(String URL) {
        Request request = new Request
                .Builder()
                .url(URL)
                .addHeader("accessToken", accessToken)
                .addHeader("refreshToken", refreshToken)
                .build();
        try (Response response = client.newCall(request).execute()) {
            System.out.println(response.code());
            return response;

        }   catch (IOException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    public Response refreshTokens(String URL) {
        Request request = new Request
                .Builder()
                .url(URL + "/refresh")
                .addHeader("refreshToken", refreshToken)
                .build();

        try (Response response = client.newCall(request).execute()) {
            System.out.println(response.code());
            return response;

        }   catch (IOException e) {
            throw new RuntimeException(e.getMessage());
        }
    }


}
