package com.techelevator.dao;

import com.techelevator.model.Post;

import java.util.List;

public interface PostDao {

    List<Post> getPostsForHomePage();

    List<Post> getPostsByForum(String forumName);

    Post createNewPost(Post newPost);

}
