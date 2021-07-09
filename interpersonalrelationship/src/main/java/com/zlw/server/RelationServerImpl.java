package com.zlw.server;

import com.zlw.mapper.relationMapper;
import com.zlw.pojo.Relation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RelationServerImpl implements RelationServer {

    @Autowired
    private relationMapper relationMapper;

    @Override
    public int addRelation(Relation relation) {
        return relationMapper.addRelation(relation);
    }

    @Override
    public int deleteRelation(int id) {
        return relationMapper.deleteRelation(id);
    }

    @Override
    public List<Relation> queryRelation(String username) {
        return relationMapper.queryRelation(username);
    }

    @Override
    public Relation queryRelationbyid(int id) {
        return relationMapper.queryRelationbyid(id);
    }

    @Override
    public int updateRelation(Relation relation) {
        return relationMapper.updateRelation(relation);
    }


}
