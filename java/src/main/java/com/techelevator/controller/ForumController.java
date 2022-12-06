package com.techelevator.controller;

import com.techelevator.dao.ForumDao;
import com.techelevator.model.Forum;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
public class ForumController {

    private ForumDao dao;

    public ForumController(ForumDao dao) {
        this.dao = dao;
    }

    @PostMapping(path = "/")
    public Forum createNewForum(@Valid @RequestBody Forum forum) {
        return dao.createNewForum(forum);
    }

}
