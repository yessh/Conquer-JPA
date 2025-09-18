package example.mission_jpa.service.response;

import example.mission_jpa.entity.User;
import lombok.Getter;

@Getter
public class UserResponse {

    private Long id;
    private String username;
    private String email;


    public static UserResponse from(User user) {
        UserResponse userResponse = new UserResponse();
        userResponse.id = user.getId();
        userResponse.username = user.getUsername();
        userResponse.email = user.getEmail();
        return userResponse;
    }
}
