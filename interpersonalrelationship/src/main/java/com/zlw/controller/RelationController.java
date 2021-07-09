package com.zlw.controller;

import com.zlw.pojo.person;
import com.zlw.pojo.Relation;
import com.zlw.server.RelationServerImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class RelationController {

    @Autowired
    private RelationServerImpl relationServer;

    @RequestMapping("/toqueryRelation")
    public String Relationship(HttpSession session, Model model){
        person person=(com.zlw.pojo.person) session.getAttribute("LoginUser");

        List<Relation> list=relationServer.queryRelation(person.getName());

        model.addAttribute("msglist",list);

        return "views/showRelation";
    }

    @RequestMapping("/toAdd")
    public String toaddrelation(){
        return "views/addRelation";
    }

    @RequestMapping("/addRelation")
    public String addrelation(String sid,String relation,Model model,HttpSession session){
        person p=(person) session.getAttribute("LoginUser");

        Relation relations=new Relation();

        relations.setMid(relation);

        relations.setMid(p.getName());

        relations.setSid(sid);

        relations.setRelation(relation);

        int i=relationServer.addRelation(relations);

        System.out.println(relations);

        if(i==0){
            model.addAttribute("msg","关系添加失败");

        }
        else {

            List<Relation> list=relationServer.queryRelation(p.getName());

            model.addAttribute("msglist",list);
            model.addAttribute("msg","关系添加成功");
        }


        return "views/showRelation";
    }

    @RequestMapping("/deleterelation")
    public String deleterelation(@RequestParam("id") int id,Model model,HttpSession session){
        int i=relationServer.deleteRelation(id);

        person p=(person) session.getAttribute("LoginUser");

        if(i==1){
            List<Relation> list=relationServer.queryRelation(p.getName());

            model.addAttribute("msglist",list);
            model.addAttribute("msg","删除成功");
        }
        else {

            model.addAttribute("msg","删除失败");
        }
        return "views/showRelation";
    }

    @RequestMapping("/toupdaterelation")
    public String toupdaterelation(@RequestParam("id") int id,Model model){

        Relation r= relationServer.queryRelationbyid(id);

        model.addAttribute("msg",r);

        return "views/updaterelation";
    }

    @RequestMapping("/updaterelation")
    public String updaterelation(int relationID,String sid,String relation,HttpSession session,Model model){

        person p=(person) session.getAttribute("LoginUser");

        Relation relation1=new Relation();

        relation1.setId(relationID);

        relation1.setMid(p.getName());

        relation1.setSid(sid);

        relation1.setRelation(relation);

        int i=relationServer.updateRelation(relation1);

        if(i==1){
            List<Relation> list=relationServer.queryRelation(p.getName());

            model.addAttribute("msglist",list);
            model.addAttribute("msg","修改成功");
            return "views/showRelation";
        }
        else {
            model.addAttribute("msg","修改失败");
            return "views/updaterelation";
        }

    }
}
