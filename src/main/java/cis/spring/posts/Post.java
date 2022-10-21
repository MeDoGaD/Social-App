package cis.spring.posts;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotNull;
import java.util.Date;

@Document(collection ="posts" )
public class Post  {
    @Id
    private String id;
    @NotNull(message = "The post content is empty !!")
    private String text;
    @NotNull
    @JsonFormat(shape = JsonFormat.Shape.STRING,pattern ="dd/MM/yyyy >> hh:mm:ss" )
    private Date date;
    @NotNull
    private int num_of_views;
    private String postOwnerUsername;

    public Post() {
        this.num_of_views =0;
        this.date=new Date();
    }

    public Post(String id,String text) {
        this();
        this.id=id;
        this.text = text;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
       this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public int getNum_of_views() {
        return num_of_views;
    }

    public void setNum_of_views(int num_of_views) {
        this.num_of_views = num_of_views;
    }

    public String getPostOwnerUsername() {
        return postOwnerUsername;
    }

    public void setPostOwnerUsername(String postOwnerUsername) {
        this.postOwnerUsername = postOwnerUsername;
    }
}
