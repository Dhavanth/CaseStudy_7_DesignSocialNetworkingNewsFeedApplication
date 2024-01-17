package org.example.designnewsfeed.models;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Getter
public class Post {
    private static Long postCounter = 0L;
    private Long postId;
    private User user;
    private String content;
    private int upVotes;
    private int downVotes;
    private Date timestamp;
    private List<Comment> comments;

    public Post(User user, String content){
        this.postId = ++postCounter;
        this.user = user;
        this.content = content;
        this.upVotes = 0;
        this.downVotes = 0;
        this.timestamp = new Date();
        this.comments = new ArrayList<>();
    }

    public void upVote(){
        upVotes++;
    }

    public void downVote(){
        downVotes++;
    }

    public void addComment(Comment comment){
        comments.add(comment);
    }

    public void setPostId(Long postId) {
        this.postId = postId;
    }


}
