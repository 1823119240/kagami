package org.java.web;

import org.java.dao.CartoonDao;
import org.java.dao.VideoDao;
import org.java.entity.fileEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
public class indexController {
    @RequestMapping("/open/{msg}")
    public String open(@PathVariable("msg") String msg, Model model){
        if(msg.equals("1")){
            CartoonDao dao = new CartoonDao();
            List<fileEntity> list = dao.showCatatoon();
            model.addAttribute("list",list);
            return "/cartoon/catalogue";
        }
        if(msg.equals("2")){
            VideoDao dao = new VideoDao();
            List<fileEntity> list = dao.videoCata();
            model.addAttribute("list",list);
            return "/video/catalogue";
        }
        if(msg.equals("3")){
            return "/music/catalogue";
        }
        if(msg.equals("4")){
            return "/picture/catalogue";
        }
        else{
            return "/text/catalogue";
        }
    }


}
