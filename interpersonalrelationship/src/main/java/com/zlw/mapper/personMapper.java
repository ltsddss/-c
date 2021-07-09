package com.zlw.mapper;

import com.zlw.pojo.person;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface personMapper {

    //    增加用户
    int addUser(person person);

    //    注销用户
    int deleteUser(String username);

    //    查找用户（通过username）
    person queryUser(String username);

    //    更改用户信息
    int updateUser(person person);

}
