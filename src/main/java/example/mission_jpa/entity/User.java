package example.mission_jpa.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Table(name = "user")
@Entity
@Getter
@ToString
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class User {

    @Id
    private Long id;
    private String username;
    private String email;


    public static User create(String username, String email) {
        User user = new User();
        user.username = username;
        user.email = email;
        return user;
    }

    public void update(String username, String email) {
        this.username = username;
        this.email = email;
    }

}
