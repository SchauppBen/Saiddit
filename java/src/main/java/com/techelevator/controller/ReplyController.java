package com.techelevator.controller;

import com.techelevator.dao.ReplyDao;
import com.techelevator.model.Reply;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import javax.validation.constraints.Null;
import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin
public class ReplyController {

    private final ReplyDao dao;

    public ReplyController(ReplyDao dao) {
        this.dao = dao;
    }

    @GetMapping(path = "/users/replies/{userId}")
    public List<Reply> getRepliesFromUser(@PathVariable("userId") int id) {
        return dao.listRepliesByUser(id);
    }

    @GetMapping(path = "/posts/replies/{postId}")
    public List<Reply> getRepliesByPost(@PathVariable("postId") int id) {
        return dao.listRepliesByPost(id);
    }

    @RequestMapping(path = "/replies", method = RequestMethod.GET)
    public List<Reply> listAllReplies() {
        return dao.listAllReplies();
    }

    @ResponseStatus(HttpStatus.CREATED)
    @RequestMapping(path = "/replies", method = RequestMethod.POST)
    public Reply reply(@Valid @RequestBody Reply newReply) {
        try {
            return dao.reply(newReply);
        } catch (NullPointerException e) {
            System.out.println(e.getMessage());
        }

        return null;
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @RequestMapping(path = "/replies/{replyId}", method = RequestMethod.DELETE)
    public void deleteReply(@PathVariable("replyId") int id) {
        try {
            dao.deleteReply(id);
        } catch (NullPointerException e) {
            System.out.println(e.getMessage());
        }
    }

}
