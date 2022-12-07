package com.techelevator.dao;

import com.techelevator.model.Reply;
import com.techelevator.model.User;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;

import javax.sql.DataSource;

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
        SqlRowSet queryResults = jdbcTemplate.queryForRowSet(sql, id);
        Reply reply = new Reply();

        while (queryResults.next()) {
            reply = mapRowToReply(queryResults);
        }

        return reply;
    }

    @Override
    public Reply reply(int replyFromId, int replyToId, String replyText) {
        String sql = "insert into replies (reply_id, user_from_id, reply_to_id, post_id, text, media_link, date_time) " +
                "values (?, ?, ?, ?, ?, ?, ?) returning reply_id;";
        Integer newReplyId;

        newReplyId = jdbcTemplate.queryForObject(sql, Integer.class, userDao.getUserById(replyToId), replyText);
        if (newReplyId == null) {
            throw new NullPointerException();
        }

        Reply newReply = getReplyById(newReplyId);

        return newReply;
    }

    private Reply mapRowToReply(SqlRowSet queryResults) {
        Reply reply = new Reply();

        reply.setReplyId(queryResults.getInt("reply_id"));
        reply.setUserFrom(queryResults.getInt("user_from_id"));
        reply.setReplyTo(queryResults.getInt("reply_to_id"));
        reply.setPostId(queryResults.getInt("post_id"));
        reply.setReplyText(queryResults.getString("text"));
        reply.setMediaLink(queryResults.getString("media_link"));
        if (queryResults.getDate("date_time") != null) {
            reply.setDateTime(queryResults.getDate("date_time").toLocalDate());
        }
        return reply;
    }

}
