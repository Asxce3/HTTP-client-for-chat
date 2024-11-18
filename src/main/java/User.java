import lombok.Data;

@Data
public class User {
    public User () {}
    public User(int id, String username, String password) {
        this.id = id;
        this.username = username;
        this.password = password;
    }


    private int id;
    private String username;
    private String password;
}
