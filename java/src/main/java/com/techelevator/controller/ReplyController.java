package com.techelevator.controller;

import com.techelevator.dao.ReplyDao;
import com.techelevator.model.Reply;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Null;

@RestController
@CrossOrigin
public class ReplyController {

    private ReplyDao dao;

    @ResponseStatus(HttpStatus.CREATED)
    @RequestMapping(path = "/forum/posts/replies", method = RequestMethod.POST)
    public Reply reply(@Valid @RequestBody Reply newReply) {
        try {
            return dao.reply(newReply.getUserFrom(), newReply.getReplyTo(), newReply.getReplyText());
        } catch (NullPointerException e) {
            System.out.println(e.getMessage());
        }

        return null;
    }

}
