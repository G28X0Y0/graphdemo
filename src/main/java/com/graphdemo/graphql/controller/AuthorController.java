package com.graphdemo.graphql.controller;

import com.graphdemo.graphql.dao.PostDao;
import com.graphdemo.graphql.model.Author;
import com.graphdemo.graphql.model.Post;
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