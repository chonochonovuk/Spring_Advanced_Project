package com.ecoverde.estateagency.service.impl;

import com.ecoverde.estateagency.model.entity.BlogComment;
import com.ecoverde.estateagency.model.service.BlogCommentServiceModel;
import com.ecoverde.estateagency.repositories.BlogCommentRepository;
import com.ecoverde.estateagency.service.BlogCommentService;
import com.ecoverde.estateagency.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
public class BlogCommentServiceImpl implements BlogCommentService {
    private final BlogCommentRepository blogCommentRepository;
    private final UserService userService;
    private final ModelMapper modelMapper;

    public BlogCommentServiceImpl(BlogCommentRepository blogCommentRepository, UserService userService, ModelMapper modelMapper) {
        this.blogCommentRepository = blogCommentRepository;
        this.userService = userService;
        this.modelMapper = modelMapper;
    }

    @Override
    public BlogCommentServiceModel addBlogComment(BlogCommentServiceModel blogCommentServiceModel) {
        blogCommentServiceModel.setAuthor(this.userService.findByUsername(blogCommentServiceModel.getAuthor().getUsername()));
        BlogComment blogComment = this.modelMapper.map(blogCommentServiceModel,BlogComment.class);
        if (this.findByTitle(blogCommentServiceModel.getTitle()) == null){
            this.blogCommentRepository.saveAndFlush(blogComment);
        }
        return this.modelMapper.map(blogComment,BlogCommentServiceModel.class);
    }

    @Override
    public BlogCommentServiceModel findByTitle(String title) {
        return this.blogCommentRepository.findByTitle(title).map(blogComment -> this.modelMapper.map(blogComment,BlogCommentServiceModel.class))
                .orElse(null);
    }

}
