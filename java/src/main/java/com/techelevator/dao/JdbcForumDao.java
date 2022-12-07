package com.techelevator.dao;

import com.techelevator.model.Forum;
import org.springframework.stereotype.Component;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;

@Component
public class JdbcForumDao implements ForumDao {

    private final JdbcTemplate jdbcTemplate;

    public JdbcForumDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Forum createNewForum(Forum newForum) {
        String sql = "INSERT INTO forums (name, description) VALUES (?, ?) RETURNING forum_id;";
        Integer forumId = jdbcTemplate.queryForObject(sql, Integer.class, newForum.getName(), newForum.getDescription());
        if (forumId != null) {
            newForum.setForumId(forumId);
        } else {
            return null;
        }
        return newForum;
    }
}
