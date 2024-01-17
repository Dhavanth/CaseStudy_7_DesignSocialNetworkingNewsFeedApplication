package org.example.designnewsfeed.models;

public class Comment extends Post{

    private static Long commentCounter = 0L;

    public Comment(User user, String content, Post parentPost){
        super(user, content);
        this.setPostId(++commentCounter);
        parentPost.addComment(this);
    }
}
