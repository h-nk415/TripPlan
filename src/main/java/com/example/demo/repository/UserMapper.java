package com.example.demo.repository;


import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.example.demo.entity.User;

@Mapper
public interface UserMapper {
    void insertUser(User user);

    User selectUserById(@Param("id") Integer id);

    void updateUser(User user);

    void deleteUserById(@Param("id") Integer id);
    
    User findByEmail(@Param("email") String email);


}
