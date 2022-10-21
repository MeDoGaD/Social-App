package cis.spring.posts;

import cis.spring.BaseController;
import cis.spring.users.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(value = {"/api/v1/posts"})
public class PostController extends BaseController {
    @Autowired
    private PostService postService;

    @PostMapping(value = {"","/"})
    public void savePost(@Valid @RequestBody Post post){
        post.setPostOwnerUsername(getCurrentUser().getEmail());
        postService.savePost(post);
    }

    @GetMapping(value = {"/{id}"})
    public ResponseEntity<Post> getPostById(@PathVariable String id){
        Post res=postService.getByID(id);
        return new ResponseEntity<>(res,HttpStatus.OK);
    }

    @GetMapping(value = {"/view/{id}"})
    public ResponseEntity<String> IncrementPostViews(@PathVariable String id){
        postService.IncrementPostViews(id);
        return new ResponseEntity<>("Views Incremented",HttpStatus.OK);
    }

    @GetMapping(value = {"","/"})
    public ResponseEntity<List<Post>> getAllCurrentUserPosts(){
        User user = getCurrentUser();
        List<Post> posts=postService.getAllCurrentUSerPosts(user);
        return new ResponseEntity<>(posts,HttpStatus.OK);
    }


}
