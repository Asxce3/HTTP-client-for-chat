package Model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class Tokens {
    private String accessToken;
    private String refreshToken;
}
