package org.example.designnewsfeed.services;

import org.example.designnewsfeed.exceptions.PostNotFoundException;
import org.example.designnewsfeed.models.Comment;
import org.example.designnewsfeed.models.Post;
import org.example.designnewsfeed.models.User;

import java.util.ArrayList;
import java.util.List;

public class PostService {
    private List<Post> allPosts;

    public PostService(){
        this.allPosts = new ArrayList<>();
    }

    public void createPost(User user, String content){
        Post post = new Post(user, content);
        user.addPost(post);
        addPost(post);
        System.out.println("Post created!");
    }

    public void addPost(Post post){
        allPosts.add(post);
    }

    public Post getPostById(Long postId){
        for(Post post: allPosts){
            if(post.getPostId().equals(postId)){
                return post;
            }
        }
        throw new PostNotFoundException("Post with id: " + postId + " not found!");
    }

    public List<Post> getAllPosts(){
        return allPosts;
    }

    public void replyToPost(User currentUser, String postIdStr, String content){
        Long postId = Long.parseLong(postIdStr);
        Post parentPost = getPostById(postId);
        if(currentUser != null && parentPost != null){
            Comment comment = new Comment(currentUser,content,parentPost);
            parentPost.addComment(comment);
            System.out.println(currentUser.getUsername() + " replied to the post " + postIdStr + ".");
        }else{
            throw new PostNotFoundException("Either User or Post not found!");
        }
    }

    public void upVotePost(User currentUser, String postidStr){
        Long postId = Long.parseLong(postidStr);
        Post postToUpvote = getPostById(postId);
        if(currentUser != null && postToUpvote != null){
            postToUpvote.upVote();
            currentUser.upVotePost(postToUpvote);
            System.out.println("Upvoted the post : " + postidStr + ".");
        }else{
            throw new PostNotFoundException("Either User or Post not found!");
        }
    }

    public void downVotePost(User currentUser, String postidStr){
        Long postId = Long.parseLong(postidStr);
        Post postToDownVote = getPostById(postId);
        if(currentUser != null && postToDownVote != null){
            postToDownVote.downVote();
            currentUser.downVotePost(postToDownVote);
            System.out.println("Downvoted the post : " + postidStr + ".");
        }else{
            throw new PostNotFoundException("Either User or Post not found!");
        }
    }




}
