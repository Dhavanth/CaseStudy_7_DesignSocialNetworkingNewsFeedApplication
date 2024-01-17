package org.example.designnewsfeed;

import org.example.designnewsfeed.controllers.NewsFeedController;
import org.example.designnewsfeed.services.PostService;
import org.example.designnewsfeed.services.UserService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DesignNewsFeedApplication {

    public static void main(String[] args) {
        UserService userService = new UserService();
        PostService postService = new PostService();

        NewsFeedController newsFeedController = new NewsFeedController(userService, postService);

        newsFeedController.signUp("lucious");
        newsFeedController.signUp("albus");
        newsFeedController.signUp("tom");

        newsFeedController.login("tom");

        newsFeedController.createPost("I am going to be the darkest dark wizard of all time");
        newsFeedController.createPost("I am lord Voldemort btw 3:)");

        newsFeedController.login("lucious");
        newsFeedController.upVotePost("001");
        newsFeedController.follow("tom");
        newsFeedController.replyToPost("001", "I am with you dark lord!");

        newsFeedController.login("albus");
        newsFeedController.createPost("Happiness can be found, even in the darkest of times, if one only remembers to turn on the light");
        newsFeedController.follow("tom");
        newsFeedController.downVotePost("001");
        newsFeedController.downVotePost("002");
        newsFeedController.replyToPost("002", "LOL!!");

        newsFeedController.showNewsFeed();


        SpringApplication.run(DesignNewsFeedApplication.class, args);
    }

}
