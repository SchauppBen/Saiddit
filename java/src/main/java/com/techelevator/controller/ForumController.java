package com.techelevator.controller;

import com.techelevator.dao.ForumDao;
import com.techelevator.dao.UserDao;
import com.techelevator.model.AddModeratorDto;
import com.techelevator.model.Forum;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.security.Principal;
import java.util.List;

@RestController
@CrossOrigin
public class ForumController {

    private final ForumDao forumDao;
    private final UserDao userDao;

    public ForumController(ForumDao forumDao, UserDao userDao) {
        this.forumDao = forumDao;
        this.userDao = userDao;
    }

    @PostMapping(path = "/forums/")
    @ResponseStatus(HttpStatus.CREATED)
    public Forum createNewForum(@Valid @RequestBody Forum forum, Principal principal) {
        if (principal != null) {
            return forumDao.createNewForum(forum, userDao.findByUsername(principal.getName()));
        } else {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED);
        }
    }

    @GetMapping(path = "/forums")
    public List<Forum> getAllForums() {
        return forumDao.getAllForums();
    }

    @GetMapping(path = "/forums/{forumName}")
    public Forum getForumByName(@PathVariable String forumName) {
        return forumDao.getForumByName(forumName);
    }

    @GetMapping(path = "/forums/search/{searchString}")
    public List<Forum> searchForums(@PathVariable String searchString) {
        return forumDao.searchForums(searchString);
    }

    @PutMapping(path = "/forums/{forumName}")
    public Forum updateForum(@PathVariable String forumName, @RequestBody Forum forum) {
        return forumDao.updateForum(forumDao.getForumByName(forumName), forum);
    }

    @GetMapping(path = "/forums/{forumId}/users/{userId}")
    public void addUserToForum(@PathVariable int forumId, @PathVariable int userId) {
        forumDao.addUserToForum(forumId, userId);
    }

    @GetMapping(path = "/forums/{forumId}/users/{userId}/isModerator")
    public boolean isModerator(@PathVariable int forumId, @PathVariable int userId) {
        return forumDao.isModerator(forumId, userId);
    }

    @PutMapping(path = "/forums/addModerator")
    public void addModerator(@RequestBody AddModeratorDto addModeratorDto, Principal principal) {
        forumDao.addModerator(userDao.findByUsername(principal.getName()), addModeratorDto);
    }

    //DOESN'T WORK
//    @DeleteMapping(path = "/forums/{forumId}")
//    @ResponseStatus(HttpStatus.NO_CONTENT)
//    public void deleteForum(@PathVariable int forumId) {
//        forumDao.deleteForum(forumId);
//    }

}
