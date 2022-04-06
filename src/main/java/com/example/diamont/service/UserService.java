package com.example.diamont.service;

import com.example.diamont.domain.User;
import com.example.diamont.domain.dto.UserDto;

import java.util.List;

public interface UserService {

    User finById(Integer id);
    List<User> findAll();
    User create(UserDto obj);
    User update(UserDto obj);
    void delete(Integer id);

}
