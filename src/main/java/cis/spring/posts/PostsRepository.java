package cis.spring.posts;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostsRepository extends MongoRepository<Post,String> {
    List<Post> getPostsByPostOwnerUsername(String username);
}
