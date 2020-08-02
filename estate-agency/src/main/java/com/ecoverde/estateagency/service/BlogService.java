package com.ecoverde.estateagency.service;

import com.ecoverde.estateagency.model.service.BlogCommentServiceModel;
import com.ecoverde.estateagency.model.service.BlogServiceModel;

import java.util.Set;

public interface BlogService {
    void blogsInit();

    BlogServiceModel addBlog(BlogServiceModel blogServiceModel);

    Set<BlogServiceModel> findAll();

    BlogServiceModel findByTitle(String title);

    void changeBlogStatus(String title, String status);

    void addBlogComment(String blogTitle, String blogCommentTitle);

}
