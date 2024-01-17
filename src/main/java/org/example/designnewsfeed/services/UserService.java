package org.example.designnewsfeed.services;

import org.example.designnewsfeed.exceptions.UserNotFoundException;
import org.example.designnewsfeed.models.Post;
import org.example.designnewsfeed.models.User;
import org.springframework.boot.configurationprocessor.json.JSONTokener;

import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class UserService {
    private Map<String, User> users;
    private Map<User, Boolean> loggedInUsers;

    public UserService(){
        this.users = new HashMap<>();
        this.loggedInUsers = new HashMap<>();
    }

    public User signUp(String username){
        if(!users.containsKey(username)){
            User newUser = new User(username);
            users.put(username, newUser);
            System.out.println("User: " + username + " created successfully!");
            return newUser;
        }
        System.out.println("User: " + username + " already exists!");
        return null;
    }

    public void login(String username){
        if(users.containsKey(username)){
            loggedInUsers.put(users.get(username), true);
            System.out.println("User: " + username + " logged in successfully!");
            return;
        }
        throw new UserNotFoundException("User: " + username + " not found! Please Sign Up!");
    }

    public User getCurrentUser(){
        for(User user: loggedInUsers.keySet()){
            if(loggedInUsers.get(user)){
                return user;
            }
        }
        throw new UserNotFoundException("No user logged in!");
    }

    public void logout(String username){
        loggedInUsers.put(users.get(username), false);
        System.out.println("User: " + username + " logged out successfully!");
    }

    public User getuser(String username){
        Optional<User> optionalUser = Optional.of(users.get(username));
        if(optionalUser.isPresent()){
            return optionalUser.get();
        }
        throw new UserNotFoundException("User: " + username + " not found!");
    }

    public void follow(User currentUser, String userNameToFollow){
        User userToFollow = users.get(userNameToFollow);
        if(userToFollow != null){
            currentUser.follow(userToFollow);
            System.out.println("User: " + currentUser.getUsername() + " is now following user: " + userNameToFollow);
        }else{
            throw new UserNotFoundException("User: " + userNameToFollow + " not found!");
        }
    }

    public void displayPost(Post post){
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm a");

        System.out.println("Post id: " + post.getPostId());
        System.out.println("UpVotes: " + post.getUpVotes());
        System.out.println("DownVotes: " + post.getDownVotes());
        System.out.println("User: " + post.getUser().getUsername());
        System.out.println(post.getContent());
        System.out.println(dateFormat.format(post.getTimestamp()));
        System.out.println();
    }

}
