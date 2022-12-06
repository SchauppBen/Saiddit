package com.techelevator.dao;

import com.techelevator.model.Post;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

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
