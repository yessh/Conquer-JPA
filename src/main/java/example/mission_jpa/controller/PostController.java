package example.mission_jpa.controller;

import example.mission_jpa.service.PostService;
import example.mission_jpa.service.request.PostRequest;
import example.mission_jpa.service.response.PostResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class PostController {

    private final PostService postService;

    @PostMapping("/users/{userId}/posts")
    public PostResponse createPost(@PathVariable Long userId, @RequestBody PostRequest request) {

        return postService.createPost(userId, request);
    }

    @PutMapping("/posts/{postId}")
    public PostResponse updatePost(@PathVariable Long postId, @RequestBody PostRequest request) {

        return postService.updatePost(postId, request);
    }


    @DeleteMapping("/posts/{postId}")
    public void deletePost(@PathVariable Long postId) {

        postService.deletePost(postId);
    }

    @GetMapping("/posts/{postId}")
    public PostResponse getPost(@PathVariable Long postId) {
        return postService.getPost(postId);
    }

    @GetMapping("/posts")
    public List<PostResponse> getAllPosts() {

        return postService.getAllPost();
    }

    @GetMapping("/users/{userId}/posts")
    public List<PostResponse> getPostsByUserId(@PathVariable Long userId) {
        return postService.getAllPostByUserId(userId);
    }

}
