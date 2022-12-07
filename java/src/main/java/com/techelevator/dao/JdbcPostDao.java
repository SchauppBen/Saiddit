package com.techelevator.dao;

import com.techelevator.model.Post;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class JdbcPostDao implements PostDao {

    private final JdbcTemplate jdbcTemplate;

    public JdbcPostDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }


    @Override
    public List<Post> getPostsForHomePage() {
        List<Post> getPosts = new ArrayList<>();

        String sql = "SELECT * FROM posts;";
        SqlRowSet rowSet = jdbcTemplate.queryForRowSet(sql);

        while(rowSet.next()) {
            getPosts.add(mapRowToPost(rowSet));
        }

        return getPosts;
    }

    @Override
    public List<Post> getPostsByForum(String forumName) {
        List<Post> getPosts = new ArrayList<>();

        String sql = "SELECT post_id, user_id, forum_id, title, text, media_link, date_time FROM posts JOIN forums USING (forum_id) WHERE forums.name = ?;";

        SqlRowSet rowSet = jdbcTemplate.queryForRowSet(sql, forumName);
        while(rowSet.next()) {
            getPosts.add(mapRowToPost(rowSet));
        }
        return getPosts;
    }

    @Override
    public Post createNewPost(Post newPost) {
        String sql = "";
        Integer postId = null;
        if (newPost.getText() == null && newPost.getMediaLink() == null) {
            sql = "INSERT INTO posts (user_id, forum_id, title) VALUES (?, ?, ?) RETURNING post_id";
            postId = jdbcTemplate.queryForObject(sql, Integer.class, newPost.getUserId(), newPost.getForumId(),
                    newPost.getTitle());
        } else if (newPost.getText() == null) {
            sql = "INSERT INTO posts (user_id, forum_id, title, media_link) VALUES (?, ?, ?, ?) RETURNING post_id;";
            postId = jdbcTemplate.queryForObject(sql, Integer.class, newPost.getUserId(), newPost.getForumId(),
                    newPost.getTitle(), newPost.getMediaLink());
        } else if (newPost.getMediaLink() == null) {
            sql = "INSERT INTO posts (user_id, forum_id, title, text) VALUES (?, ?, ?, ?) RETURNING post_id;";
            postId = jdbcTemplate.queryForObject(sql, Integer.class, newPost.getUserId(), newPost.getForumId(),
                    newPost.getTitle(), newPost.getText());
        } else {
            sql = "INSERT INTO posts (user_id, forum_id, title, text) VALUES (?, ?, ?, ?, ?) RETURNING post_id;";
            postId = jdbcTemplate.queryForObject(sql, Integer.class, newPost.getUserId(), newPost.getForumId()
                    , newPost.getTitle(), newPost.getText(), newPost.getMediaLink());
        }
        if (postId != null) {
            newPost.setPostId(postId);
        } else {
            return null;
        }
        return newPost;
    }

    private Post mapRowToPost(SqlRowSet rowSet) {
        Post post = new Post();
        post.setPostId(rowSet.getInt("post_id"));
        post.setUserId(rowSet.getInt("user_id"));
        post.setForumId(rowSet.getInt("forum_id"));
        post.setTitle(rowSet.getString("title"));
        post.setText(rowSet.getString("text"));
        post.setMediaLink(rowSet.getString("media_link"));
        if (rowSet.getDate("date_time") != null) {
            post.setDateTime(rowSet.getDate("date_time").toLocalDate());
        }
        return post;
    }
}
