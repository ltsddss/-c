package com.zlw.server;

import com.zlw.mapper.personMapper;
import com.zlw.pojo.person;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public class PersonServerImpl implements PersonServer {

    @Autowired
    private personMapper personMapper;

    @Override
    public int addUser(person person) {
        return personMapper.addUser(person);
    }

    @Override
    public int deleteUser(String username) {
        return personMapper.deleteUser(username);
    }

    @Override
    public person queryUser(String username) {
        return personMapper.queryUser(username);
    }

    @Override
    public int updateUser(person person) {
        return personMapper.updateUser(person);
    }
}
