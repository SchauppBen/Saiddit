package com.techelevator.dao;

import com.techelevator.model.Reply;

public interface ReplyDao {

    Reply reply(int replyToId, int replyFromId, String replyText);

    Reply getReplyById(int id);
}
