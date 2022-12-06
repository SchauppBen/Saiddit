package com.techelevator.dao;

import com.techelevator.model.User;
import org.springframework.jdbc.core.JdbcTemplate;

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

    

}
