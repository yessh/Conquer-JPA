package example.mission_jpa.service;

import example.mission_jpa.entity.User;
import example.mission_jpa.repository.UserRepository;
import example.mission_jpa.service.request.UserRequest;
import example.mission_jpa.service.response.UserListResponse;
import example.mission_jpa.service.response.UserResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;


    @Transactional
    public UserResponse createUser(UserRequest request) {
        if (userRepository.existsByUsername(request.getUsername())) {
            throw new IllegalArgumentException("Username already exist");
        }

        if (userRepository.existsByEmail(request.getEmail())) {
            throw new IllegalArgumentException("Email already exist");
        }

        User user = User.create(
                request.getUsername(),
                request.getEmail()
        );

        return UserResponse.from(userRepository.save(user));
    }



    @Transactional
    public UserResponse updateUser(Long id, UserRequest request) {
        User user = userRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("User not found"));

        userRepository.findByEmail(request.getEmail()).ifPresent(u -> {
            if (!u.getId().equals(id)) {
                throw new IllegalArgumentException("Email already exist");
            }
        });


        user.update(request.getUsername(), request.getEmail());
        return UserResponse.from(user);
    }




    @Transactional
    public void deleteUser(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow();

        userRepository.delete(user);
    }


    @Transactional(readOnly = true)
    public UserResponse getUser(Long id) {
        User user = userRepository.findById(id).orElseThrow();

        return UserResponse.from(user);
    }

    @Transactional(readOnly = true)
    public List<UserResponse> getAllUsers() {
        List<UserResponse> users = userRepository.findAll().stream()
                .map(UserResponse::from)
                .toList();

        return users;
    }


}
