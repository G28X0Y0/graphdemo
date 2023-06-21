package com.graphdemo.graphql.controller;

import com.graphdemo.graphql.dao.AuthorDao;
import com.graphdemo.graphql.dao.PostDao;
import com.graphdemo.graphql.model.Author;
import com.graphdemo.graphql.model.Post;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.graphql.data.method.annotation.SchemaMapping;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
public class PostController {

    @Autowired
    private final PostDao postDao;

    @Autowired
    private final AuthorDao authorDao;

    public PostController(PostDao postDao, AuthorDao authorDao) {
        this.postDao = postDao;
        this.authorDao = authorDao;
    }

    @QueryMapping
//    @RequestMapping(path = "/graphql", method = RequestMethod.POST)
    public List<Post> recentPosts(@Argument Integer count, @Argument Integer offset) {
        count = 1;
        offset = 1;
        return postDao.getRecentPosts(count, offset);
    }

    @SchemaMapping
    public Author author(Post post) {
        return authorDao.getAuthor(post.getAuthorId());
    }

    @SchemaMapping(typeName="Post", field="first_author")
    public Author getFirstAuthor(Post post) {
        return authorDao.getAuthor(post.getAuthorId());
    }

    @MutationMapping
//    @RequestMapping(path = "/graphql/createPost", method = RequestMethod.POST)
    public Post createPost(@RequestBody @Argument String title, @Argument String text,
                           @Argument String category, @Argument String authorId) {
        Post post = new Post();
        post.setId(UUID.randomUUID().toString());
        post.setTitle(title);
        post.setText(text);
        post.setCategory(category);
        post.setAuthorId(authorId);
        postDao.savePost(post);
        postDao.createPost(title, text, category, authorId);

        return post;
    }

}