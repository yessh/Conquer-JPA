package example.mission_jpa.service.response;

import example.mission_jpa.entity.User;
import lombok.Getter;

import java.util.List;

@Getter
public class UserListResponse {

    List<User> users;

    public static UserListResponse of(List<User> users) {
        UserListResponse response = new UserListResponse();
        response.users = users;
        return response;
    }
}
