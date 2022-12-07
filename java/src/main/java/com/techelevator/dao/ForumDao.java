package com.techelevator.dao;

import com.techelevator.model.Forum;
import com.techelevator.model.User;

public interface ForumDao {

    Forum createNewForum(Forum newForum, User userCreated);

    Forum getForumById(int id);

    Forum getForumByName(String forumName);

}
