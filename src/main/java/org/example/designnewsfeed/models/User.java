package org.example.designnewsfeed.models;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Getter
public class User {
    private String username;
    private List<Post> posts;
    private Set<User> following;
    private Set<Post> upVotedPosts;
    private Set<Post> downVotedPosts;

    public User(String username){
        this.username = username;
        this.posts = new ArrayList<>();
        this.following = new HashSet<>();
        this.upVotedPosts = new HashSet<>();
        this.downVotedPosts = new HashSet<>();
    }

    public void addPost(Post post){
        posts.add(post);
    }

    public void follow(User user){
        following.add(user);
    }

    public void upVotePost(Post post){
        upVotedPosts.add(post);
    }

    public void downVotePost(Post post){
        downVotedPosts.add(post);
    }




}
