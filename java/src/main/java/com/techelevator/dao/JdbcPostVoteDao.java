package com.techelevator.dao;

import com.techelevator.model.PostVote;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class JdbcPostVoteDao implements PostVoteDao {

    JdbcTemplate jdbcTemplate;

    public JdbcPostVoteDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public void upVotePost(PostVote postVote) {
        String sql = "INSERT INTO post_votes (user_id, post_id, is_upvote) VALUES (?, ?, true)";
        jdbcTemplate.update(sql, postVote.getUserId(), postVote.getPostId());
    }

    @Override
    public void downVotePost(PostVote postVote) {
        String sql = "INSERT INTO post_votes (user_id, post_id, is_upvote) VALUES (?, ?, false)";
        jdbcTemplate.update(sql, postVote.getUserId(), postVote.getPostId());
    }

    @Override
    public List<PostVote> getAllPostVotes() {
        List<PostVote> allPostVotes = new ArrayList<>();

        String sql = "SELECT * FROM post_votes;";

        SqlRowSet rowSet = jdbcTemplate.queryForRowSet(sql);

        while(rowSet.next()) {
            allPostVotes.add(mapRowSetToPostVote(rowSet));
        }
        return allPostVotes;
    }

    @Override
    public List<PostVote> getPostVotesByUser(int userId) {
        List<PostVote> postVotes = new ArrayList<>();

        String sql = "SELECT * FROM post_votes WHERE user_id = ?";

        SqlRowSet rowSet = jdbcTemplate.queryForRowSet(sql, userId);

        while(rowSet.next()) {
            postVotes.add(mapRowSetToPostVote(rowSet));
        }
        return postVotes;
    }

    @Override
    public List<PostVote> getPostVotesByPost(int postId) {
        List<PostVote> postVotes = new ArrayList<>();

        String sql = "SELECT * FROM post_votes WHERE post_id = ?";

        SqlRowSet rowSet = jdbcTemplate.queryForRowSet(sql, postId);

        while(rowSet.next()) {
            postVotes.add(mapRowSetToPostVote(rowSet));
        }
        return postVotes;
    }

    @Override
    public List<PostVote> getUpVotesByUser(int userId) {
        List<PostVote> postVotes = new ArrayList<>();

        String sql = "SELECT * FROM post_votes WHERE user_id = ? AND is_upvote = true";

        SqlRowSet rowSet = jdbcTemplate.queryForRowSet(sql, userId);

        while(rowSet.next()) {
            postVotes.add(mapRowSetToPostVote(rowSet));
        }
        return postVotes;
    }

    @Override
    public List<PostVote> getDownVotesByUser(int userId) {
        List<PostVote> postVotes = new ArrayList<>();

        String sql = "SELECT * FROM post_votes WHERE user_id = ? AND is_upvote = false";

        SqlRowSet rowSet = jdbcTemplate.queryForRowSet(sql, userId);

        while(rowSet.next()) {
            postVotes.add(mapRowSetToPostVote(rowSet));
        }
        return postVotes;
    }

    @Override
    public List<PostVote> getUpVotesByPost(int postId) {
        List<PostVote> postVotes = new ArrayList<>();

        String sql = "SELECT * FROM post_votes WHERE post_id = ? AND is_upvote = true";

        SqlRowSet rowSet = jdbcTemplate.queryForRowSet(sql, postId);

        while(rowSet.next()) {
            postVotes.add(mapRowSetToPostVote(rowSet));
        }
        return postVotes;
    }

    @Override
    public List<PostVote> getDownVotesByPost(int postId) {
        List<PostVote> postVotes = new ArrayList<>();

        String sql = "SELECT * FROM post_votes WHERE post_id = ? AND is_upvote = false";

        SqlRowSet rowSet = jdbcTemplate.queryForRowSet(sql, postId);

        while(rowSet.next()) {
            postVotes.add(mapRowSetToPostVote(rowSet));
        }
        return postVotes;
    }

    private PostVote mapRowSetToPostVote(SqlRowSet rowSet) {
        PostVote postVote = new PostVote();
        postVote.setPostId(rowSet.getInt("post_id"));
        postVote.setUserId(rowSet.getInt("user_id"));
        postVote.setUpvote(rowSet.getBoolean("is_moderator"));
        if (rowSet.getDate("time_date") != null) {
            postVote.setTimeDate(rowSet.getDate("time_date").toLocalDate());
        }
        return postVote;
    }
}
