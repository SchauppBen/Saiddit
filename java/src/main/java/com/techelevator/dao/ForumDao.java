package com.techelevator.dao;

import com.techelevator.model.Forum;
import com.techelevator.model.User;

public interface ForumDao {

    Forum createNewForum(Forum newForum, User userCreated);

    Forum getForumById(int id);

    void addUserToForum(int forumId, int userId);


    Forum getForumByName(String forumName);

}
