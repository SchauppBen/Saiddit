package com.techelevator.controller;

import com.techelevator.dao.ForumDao;
import com.techelevator.dao.UserDao;
import com.techelevator.model.Forum;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.Principal;

@RestController
@CrossOrigin
public class ForumController {

    private final ForumDao forumDao;
    private final UserDao userDao;

    public ForumController(ForumDao forumDao, UserDao userDao) {
        this.forumDao = forumDao;
        this.userDao = userDao;
    }

    @PostMapping(path = "/forums/createNewForum")
    public Forum createNewForum(@Valid @RequestBody Forum forum, Principal principal) {
        return forumDao.createNewForum(forum, userDao.findByUsername(principal.getName()));
    }

    @GetMapping(path = "/forums/{forumName}")
    public Forum getForumByName(@PathVariable String forumName) {
        return forumDao.getForumByName(forumName);
    }

}
