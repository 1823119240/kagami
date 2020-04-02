package org.java.web;

import org.java.dao.VideoDao;
import org.java.entity.fileEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;

import java.util.List;

public class videoController {
    VideoDao dao = new VideoDao();
    @RequestMapping("VideoCata/{url}")
    public String VideoCata(@RequestPart("url") String url, Model model){
        List<fileEntity> list = dao.videoCata();
        model.addAttribute("list",list);
        return "/cartoon/catalogueByOne";
    }
}
