package com.ecoverde.estateagency.service;

import com.ecoverde.estateagency.model.service.UserServiceModel;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface UserService extends UserDetailsService {
    UserServiceModel register(UserServiceModel userServiceModel);

    UserServiceModel findByUsername(String username);

    UserServiceModel editUserProfile(UserServiceModel userServiceModel ,String oldPassword);

    List<UserServiceModel> findAll();

    void userSetRole(String username,String authority);

    void changeUserStatus(String username, String status);

    void usersInit();
}
