package example.mission_jpa.controller;

import example.mission_jpa.service.PostService;
import example.mission_jpa.service.request.PostRequest;
import example.mission_jpa.service.response.PostResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/posts")
public class PostController {

    private final PostService postService;

    @PostMapping("/")
    public PostResponse createPost(@RequestBody PostRequest request) {

        return postService.createPost(request);
    }

    @PutMapping("/{postId}")
    public PostResponse updatePost(@PathVariable Long postId, @RequestBody PostRequest request) {

        return postService.updatePost(postId, request);
    }


    @DeleteMapping("/{postId}")
    public void deletePost(@PathVariable Long postId) {

        postService.deletePost(postId);
    }

    @GetMapping("/{postId}")
    public PostResponse getPost(@PathVariable Long postId) {
        return postService.getPost(postId);
    }

    @GetMapping("")
    public List<PostResponse> getAllPosts() {

        return postService.getAllPost();
    }

}
