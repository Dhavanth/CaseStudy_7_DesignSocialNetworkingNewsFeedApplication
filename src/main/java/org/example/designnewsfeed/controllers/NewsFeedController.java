package org.example.designnewsfeed.controllers;

import org.example.designnewsfeed.models.Post;
import org.example.designnewsfeed.models.User;
import org.example.designnewsfeed.services.PostService;
import org.example.designnewsfeed.services.UserService;

import java.util.Comparator;
import java.util.List;

public class NewsFeedController {
    private UserService userService;
    private PostService postService;

    private User loggedInUser;

    public NewsFeedController(UserService userService, PostService postService){
        this.userService = userService;
        this.postService = postService;
    }

    public void signUp(String username){
        userService.signUp(username);
        System.out.println();
    }

    public void  login(String username){
        userService.login(username);
        loggedInUser = userService.getuser(username);
        System.out.println();
    }

    public void logout(String username){
        userService.logout(username);
        System.out.println();
    }

//    public void getCurrentUser(){
//        loggedInUser = userService.getCurrentUser();
//    }

    public void createPost(String content){
        if(loggedInUser != null){
            postService.createPost(loggedInUser, content);
        }else{
            throw new RuntimeException("No user logged in!");
        }
        System.out.println();

    }

    public void follow(String usernameToFollow){
        userService.follow(loggedInUser, usernameToFollow);
        System.out.println();
    }

    public void replyToPost(String postIdStr, String content){
        postService.replyToPost(loggedInUser, postIdStr, content);
        System.out.println();
    }

    public void upVotePost(String postIdStr){
        postService.upVotePost(loggedInUser, postIdStr);
        System.out.println();
    }

    public void downVotePost(String postIdStr){
        postService.downVotePost(loggedInUser, postIdStr);
        System.out.println();
    }

    public void showNewsFeed(){
        if(loggedInUser != null){
            List<Post> listOfPosts = postService.getAllPosts();
            listOfPosts.sort(Comparator.comparing(Post :: getTimestamp).reversed());
            for(Post eachPost: listOfPosts){
                userService.displayPost(eachPost);
            }
        }else{
            System.out.println("User not logged in!");
        }
        System.out.println();
    }
}
