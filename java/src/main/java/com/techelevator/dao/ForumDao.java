package com.techelevator.dao;

import com.techelevator.model.Forum;
import com.techelevator.model.User;

import java.util.List;

public interface ForumDao {

    Forum createNewForum(Forum newForum, User userCreated);

    Forum getForumById(int id);

    void addUserToForum(int forumId, int userId);

    List<Forum> searchForums(String searchString);

    Forum updateForum(Forum oldForum, Forum newForum);

    Forum getForumByName(String forumName);

}
