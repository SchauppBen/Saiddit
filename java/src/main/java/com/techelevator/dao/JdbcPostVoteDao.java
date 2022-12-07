package com.techelevator.dao;

import com.techelevator.model.PostVote;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

@Component
public class JdbcPostVoteDao implements PostVoteDao {

    JdbcTemplate jdbcTemplate;

    public JdbcPostVoteDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public void upvotePost(int userId, int postId) {
        String sql = "INSERT INTO post_votes (user_id, post_id, is_upvote) VALUES (?, ?, true)";
        jdbcTemplate.update(sql, userId, postId);
    }

    @Override
    public void downvotePost(int userId, int postId) {
        String sql = "INSERT INTO post_votes (user_id, post_id, is_upvote) VALUES (?, ?, false)";
        jdbcTemplate.update(sql, userId, postId);
    }
}
