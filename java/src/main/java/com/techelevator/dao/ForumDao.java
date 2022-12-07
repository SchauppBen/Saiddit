package com.techelevator.dao;

import com.techelevator.model.Forum;

public interface ForumDao {

    Forum createNewForum(Forum newForum);

    Forum getForumByName(String forumName);

}
