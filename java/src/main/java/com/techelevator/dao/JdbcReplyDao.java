package com.techelevator.dao;

import com.techelevator.model.Reply;
import com.techelevator.model.User;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;

@Component
public class JdbcReplyDao implements ReplyDao {

    private final JdbcTemplate jdbcTemplate;
    UserDao userDao;
    User userTo;
    User userFrom;

    public JdbcReplyDao(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
        this.userDao = new JdbcUserDao(jdbcTemplate);
    }

    @Override
    public Reply getReplyById(int id) {
        String sql = "select reply_id, user_from_id, reply_to_id, post_id from replies where reply_id = ?;";
        SqlRowSet rowSet = jdbcTemplate.queryForRowSet(sql, id);
        Reply reply = new Reply();

        while (rowSet.next()) {
            reply = mapRowToReply(rowSet);
        }

        return reply;
    }

    @Override
    public List<Reply> listAllReplies() {
        String sql = "select * from replies;";
        List<Reply> allReplies = new ArrayList<>();

        SqlRowSet rowSet = jdbcTemplate.queryForRowSet(sql);

        while (rowSet.next()) {
            allReplies.add(mapRowToReply(rowSet));
        }

        return allReplies;
    }

    @Override
    public List<Reply> listRepliesByPost(int postId) {
        String sql = "select * from replies where post_id = ?;";
        List<Reply> allReplies = new ArrayList<>();

        SqlRowSet rowSet = jdbcTemplate.queryForRowSet(sql, postId);

        while (rowSet.next()) {
            allReplies.add(mapRowToReply(rowSet));
        }

        return allReplies;
    }

    @Override
    public List<Reply> listRepliesByUser(int userId) {
        String sql = "select * from replies where user_from_id = ?;";
        List<Reply> allReplies = new ArrayList<>();

        SqlRowSet rowSet = jdbcTemplate.queryForRowSet(sql, userId);

        while (rowSet.next()) {
            allReplies.add(mapRowToReply(rowSet));
        }

        return allReplies;
    }

    @Override
    public Reply reply(Reply newReply) {
        String sql = "insert into replies (user_from_id, reply_to_id, post_id, text, media_link) " +
                "values (?, ?, ?, ?, ?) returning reply_id;";
        Integer newReplyId;

        newReplyId = jdbcTemplate.queryForObject(sql, Integer.class, newReply.getUserFrom(), newReply.getReplyTo(),
                newReply.getPostId(), newReply.getReplyText(), newReply.getMediaLink());
        if (newReplyId == null) {
            throw new NullPointerException();
        }

        newReply.setReplyId(newReplyId);

        return newReply;
    }

    @Override
    public void deleteReply(int id) {
        String sql = "delete from replies where reply_id = ?;";

        jdbcTemplate.update(sql, id);
    }

    private Reply mapRowToReply(SqlRowSet rowSet) {
        Reply reply = new Reply();

        reply.setReplyId(rowSet.getInt("reply_id"));
        reply.setUserFrom(rowSet.getInt("user_from_id"));
        reply.setUsername(userDao.getUserById(reply.getUserFrom()).getUsername());
        reply.setReplyTo(rowSet.getInt("reply_to_id"));
        reply.setUsername(userDao.getUserById(reply.getReplyTo()).getUsername());
        reply.setPostId(rowSet.getInt("post_id"));
        reply.setReplyText(rowSet.getString("text"));
        reply.setMediaLink(rowSet.getString("media_link"));
        if (rowSet.getDate("date_time") != null) {
            reply.setDateTime(rowSet.getDate("date_time").toLocalDate());
        }
        return reply;
    }

}
