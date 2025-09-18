package example.mission_jpa.service;

import example.mission_jpa.entity.Post;
import example.mission_jpa.entity.User;
import example.mission_jpa.repository.PostRepository;
import example.mission_jpa.repository.UserRepository;
import example.mission_jpa.service.request.PostRequest;
import example.mission_jpa.service.response.PostResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PostService {

    private final UserRepository userRepository;
    private final PostRepository postRepository;

    @Transactional
    public PostResponse createPost(Long userId, PostRequest request) {

        User user = userRepository.findById(userId).orElseThrow(() -> new IllegalArgumentException("User not found"));
        Post post = Post.create(request.getTitle(), request.getContent(), user);

        return PostResponse.from(postRepository.save(post));
    }


    @Transactional
    public PostResponse updatePost(Long id, PostRequest request) {

        Post post = postRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Post not found"));
        post.update(request.getTitle(), request.getContent());

        return PostResponse.from(post);
    }


    @Transactional
    public void deletePost(Long id) {

        Post post = postRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Post not found"));
        postRepository.delete(post);
    }


    @Transactional(readOnly = true)
    public PostResponse getPost(Long id) {

        Post post = postRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Post not found"));

        return PostResponse.from(post);
    }

    @Transactional(readOnly = true)
    public List<PostResponse> getAllPost() {

        List<PostResponse> posts = postRepository.findAll().stream()
                .map(PostResponse::from)
                .toList();

        return posts;
    }
}
