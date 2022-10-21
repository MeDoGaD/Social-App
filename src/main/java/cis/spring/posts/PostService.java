package cis.spring.posts;

import cis.spring.errors.ConflictException;
import cis.spring.errors.NotFoundException;
import cis.spring.users.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class PostService {

    @Autowired
    private PostsRepository postsRepository;

    public void savePost(Post post)
    {
        postsRepository.save(post);
    }

    public List<Post> getAllPosts(){
        return postsRepository.findAll();
    }

    public List<Post>getAllCurrentUSerPosts(User user){
       return postsRepository.getPostsByPostOwnerUsername(user.getEmail());
    }

    public void IncrementPostViews(String id){
        try {
            Post p=getByID(id);
            int num=p.getNum_of_views();
            p.setNum_of_views(++num);
            savePost(p);
        }catch (Exception e){
            throw new ConflictException("There is an error !!");
        }
    }
    public Post getByID(String id){
        try {
            Post p= postsRepository.findById(id).get();
            return p;
        }catch (NoSuchElementException ex){
            throw new NotFoundException(String.format("No post with the id [%s] was found in your posts",id));
        }
    }
}
