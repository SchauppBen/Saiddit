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

    @Override
    public Forum getForumByName(String forumName) {
        String sql = "SELECT * FROM forums WHERE name = ?";
        SqlRowSet rowSet = jdbcTemplate.queryForRowSet(sql, forumName);
        if (rowSet.next()) {
            return mapRowSetToForum(rowSet);
        }
        return null;
    }

    public Forum mapRowSetToForum(SqlRowSet rowSet) {
        Forum forum = new Forum();
        forum.setForumId(rowSet.getInt("forum_id"));
        forum.setName(rowSet.getString("name"));
        forum.setDescription(rowSet.getString("description"));
        if (rowSet.getDate("time_created") != null) {
            forum.setDateCreated(rowSet.getDate("time_created").toLocalDate());
        }
        return forum;
    }
}
