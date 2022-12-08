package com.techelevator.controller;

import com.techelevator.dao.ReplyDao;
import com.techelevator.model.Reply;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping(path = "users/replies/{userId}")
    public List<Reply> getRepliesFromUser(@PathVariable int userId) {
        return dao.listRepliesByUser(userId);
    }

    @GetMapping(path = "posts/replies/{postId}")
    public List<Reply> getRepliesByPost(@PathVariable int postId) {
        return dao.listRepliesByPost(postId);
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
    public void deleteReply(@PathVariable int replyId) {
        try {
            dao.deleteReply(replyId);
        } catch (NullPointerException e) {
            System.out.println(e.getMessage());
        }
    }

}
