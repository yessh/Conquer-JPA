package example.mission_jpa.controller;

import example.mission_jpa.service.UserService;
import example.mission_jpa.service.request.UserRequest;
import example.mission_jpa.service.response.UserResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class UserController {

    private final UserService userService;

    @GetMapping("/users/{userId}")
    public UserResponse getUser(@PathVariable Long userId) {

        return userService.getUser(userId);
    }

    @GetMapping("/users/all")
    public List<UserResponse> getUsers() {
        return userService.getAllUsers();
    }

    @PostMapping("/users")
    public UserResponse createUser(@RequestBody UserRequest request) {
        return userService.createUser(request);
    }

    @PutMapping("/users/{userId}")
    public UserResponse updateUser(@PathVariable Long userId, @RequestBody UserRequest request) {
        return userService.updateUser(userId, request);
    }


    @DeleteMapping("/users/{userId}")
    public void deleteUser(@PathVariable Long userId) {
        userService.deleteUser(userId);
    }
}