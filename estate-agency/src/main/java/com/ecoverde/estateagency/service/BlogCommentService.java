package com.ecoverde.estateagency.service;

import com.ecoverde.estateagency.model.service.BlogCommentServiceModel;
import com.ecoverde.estateagency.model.service.BlogServiceModel;

import java.util.List;

public interface BlogCommentService {
    BlogCommentServiceModel addBlogComment(BlogCommentServiceModel blogCommentServiceModel);
    BlogCommentServiceModel findByTitle(String title);
}
