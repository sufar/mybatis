package com.vorise.mybatis.mapper;

import com.vorise.mybatis.entity.User;

public interface UserMapper {
    User selectById(Integer id);
}
