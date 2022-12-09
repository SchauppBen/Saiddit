package com.techelevator.dao;

import com.techelevator.model.AddModeratorDto;
import com.techelevator.model.Forum;
import com.techelevator.model.User;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;

@Component
public class JdbcForumDao implements ForumDao {

    private final JdbcTemplate jdbcTemplate;

    public JdbcForumDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Forum createNewForum(Forum newForum, User userCreated) {
        String sql = "INSERT INTO forums (name, description) VALUES (?, ?) RETURNING forum_id;";
        Integer forumId = jdbcTemplate.queryForObject(sql, Integer.class, newForum.getName(), newForum.getDescription());
        if (forumId != null) {
            newForum.setForumId(forumId);
        } else {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
        sql = "INSERT INTO forums_users (forum_id, user_id, is_moderator) VALUES (?, ?, true);";
        jdbcTemplate.update(sql, newForum.getForumId(), userCreated.getId());
        return newForum;
    }

    @Override
    public List<Forum> getAllForums() {
        List<Forum> allForums = new ArrayList<>();

        String sql = "SELECT * FROM forums ORDER BY name;";
        SqlRowSet rowSet = jdbcTemplate.queryForRowSet(sql);
        while(rowSet.next()) {
            allForums.add(mapRowSetToForum(rowSet));
        }
        return allForums;
    }

    @Override
    public Forum getForumById(int id) {
        String sql = "SELECT * FROM forums WHERE forum_id = ?";
        SqlRowSet rowSet = jdbcTemplate.queryForRowSet(sql, id);
        if (rowSet.next()) {
            return mapRowSetToForum(rowSet);
        }
        return null;
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

    @Override
    public boolean isModerator(int forumId, int userId) {
        String sql = "SELECT is_moderator FROM forums_users WHERE forum_id = ? AND user_id = ?;";
        try {
            SqlRowSet rowSet = jdbcTemplate.queryForRowSet(sql, forumId, userId);
            if (rowSet.next()) {
                return rowSet.getBoolean("is_moderator");
            }
        } catch(NullPointerException e) {
            System.out.println(e.getMessage());
            return false;
        }
        return false;
    }

    @Override
    public void addModerator(User userAddingMod, AddModeratorDto addModeratorDto) {
        if (isModerator(addModeratorDto.getForumId(), userAddingMod.getId())) {
            String sql = "UPDATE forums_users SET is_moderator = true WHERE forum_id = ? AND user_id = ?";
            jdbcTemplate.update(sql, addModeratorDto.getForumId(), addModeratorDto.getUserId());
        } else {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN);
        }
    }

//    @Override
//    public void deleteForum(int forumId) {
//        String sql = "DELETE FROM posts WHERE forum_id = ?;";
//        jdbcTemplate.update(sql, forumId);
//        sql = "DELETE FROM forums_users WHERE forum_id = ?;";
//        jdbcTemplate.update(sql, forumId);
//        sql = "DELETE FROM forums WHERE forum_id = ?;";
//    }
    // NOT WORKING DO THIS LATER IF WE HAVE TIME

    @Override
    public void addUserToForum(int forumId, int userId) {
        String sql = "INSERT INTO forums_users (forum_id, user_id, is_moderator) VALUES (?, ?, false)";
        jdbcTemplate.update(sql, forumId, userId);
    }

    @Override
    public List<Forum> searchForums(String searchString) {
        List<Forum> forums = new ArrayList<>();
        String sql = "SELECT * FROM forums WHERE name ILIKE ?";
        SqlRowSet rowSet = jdbcTemplate.queryForRowSet(sql, "%" + searchString + "%");
        while(rowSet.next()) {
            forums.add(mapRowSetToForum(rowSet));
        }
        return forums;
    }

    @Override
    public Forum updateForum(Forum oldForum, Forum newForum) {
        if (oldForum.getForumId() != newForum.getForumId() && !oldForum.getName().equals(newForum.getName())) {
            return oldForum;
        }
        String sql = "UPDATE forums SET description = ? WHERE forum_id = ?";
        jdbcTemplate.update(sql, newForum.getDescription(), oldForum.getForumId());
        return newForum;
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
