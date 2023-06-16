package com.graphdemo.graphdemo.controller;

import com.graphdemo.graphdemo.dao.PostDao;
import com.graphdemo.graphdemo.model.Author;
import com.graphdemo.graphdemo.model.Post;
import org.springframework.graphql.data.method.annotation.SchemaMapping;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class AuthorController {

    private final PostDao postDao;

    public AuthorController(PostDao postDao) {
        this.postDao = postDao;
    }

    @SchemaMapping
    public List<Post> posts(Author author) {
        return postDao.getAuthorPosts(author.getId());
    }
}