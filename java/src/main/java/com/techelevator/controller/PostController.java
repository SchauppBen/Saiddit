package com.techelevator.controller;

import com.techelevator.dao.PostDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PostController {

    @Autowired
    private PostDao dao;



}
