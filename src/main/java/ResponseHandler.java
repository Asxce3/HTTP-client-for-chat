import okhttp3.Response;

public class ResponseHandler {
    private final Tokens tokens = new Tokens();

    public Tokens setTokens(Response response) {
        if (response.code() == 200) {
            String accessToken = response.header("accessToken");
            String refreshToken = response.header("refreshToken");

            if (accessToken != null && refreshToken != null) {
                tokens.setAccessToken(accessToken);
                tokens.setRefreshToken(refreshToken);
            }

        }   else {
            System.out.println("Произошла ошибка с кодом " + response.code());
        }
        return tokens;
    }

}
