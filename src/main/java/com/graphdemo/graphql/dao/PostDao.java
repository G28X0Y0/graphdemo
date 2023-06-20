package com.graphdemo.graphql.dao;

import com.graphdemo.graphql.model.Post;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

public class PostDao {
    private final List<Post> posts;

    public PostDao(List<Post> posts) {
        this.posts = posts;
    }

    public List<Post> getRecentPosts(int count, int offset) {
        return posts.stream()
                .skip(offset)
                .limit(count)
                .collect(Collectors.toList());
    }

    public List<Post> getAuthorPosts(String author) {
        return posts.stream()
                .filter(post -> author.equals(post.getAuthorId()))
                .collect(Collectors.toList());
    }

    public void savePost(Post post) {
        posts.add(post);
    }

    public void deletePost(Post post) {
        posts.remove(post);
    }

    public Post createPost(String title, String text, String category, String authorId) {
        Post post = new Post();
        post.setId(UUID.randomUUID().toString());
        post.setTitle(title);
        post.setText(text);
        post.setCategory(category);
        post.setAuthorId(authorId);
        posts.add(post);
        return post;
    }

}
