package com.zlw.server;

import com.zlw.pojo.Relation;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.List;

@Mapper
@Repository
public interface RelationServer {
    //    增加人际关系
    int addRelation(Relation relation);

    //    删除人际关系
    int deleteRelation(int id);

    //    查看个人的人际关系
    List<Relation> queryRelation(String username);

    //    通过id查询关系

    Relation queryRelationbyid(int id);

    //    修改人际关系
    int updateRelation(Relation relation);
}
