package org.java.web;

import org.java.dao.CartoonDao;
import org.java.entity.fileEntity;
import org.java.util.getIndex;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Controller
public class cartoonController {
    int maxIndex=0;
    CartoonDao dao = new CartoonDao();

    /**
     * 获取当前漫画所有分集
     * @param url   资源路径
     * @param model
     * @return
     */
    @RequestMapping("/cartoonCata")
    public String cartoonCata(@RequestPart("url") String url, Model model) throws IOException {
        List<fileEntity> list = dao.showCartoon(url);
        String urlIndex=getIndex.loadIndexBycartoon(url);
        model.addAttribute("list",list);
        model.addAttribute("urlIndex",urlIndex);
        return "/cartoon/catalogueByOne";
    }


    /**
     * 初始加载观看漫画
     * @param request
     * @param response
     * @param url   漫画路径
     * @param model
     * @return
     * @throws ServletException
     * @throws IOException
     */
    @RequestMapping("/loadCartoonRead")
    public String cartoonRead( HttpServletRequest request,HttpServletResponse response,@RequestPart("url") String url,Model model) throws ServletException, IOException {
        getIndex.saveIndexBycartoon(url);
        int page = 0;
        String p = getIndex.loadIndexBycartoonPage(url);
        //如果返回的页面值不为空字符串，就使用保存的页面数
        if(!p.equals("")){
            //获取当前页
            page = Integer.valueOf(p);
        }
        //获取总页数
        int maxCount = dao.watchCartoonPageCount(url);
        //获得该文件的上一级路径
        String substring = url.substring(0,url.lastIndexOf("\\"));
        //获得该页文件
        fileEntity fileEntity = dao.watchCartoon(url,page);
        getIndex.saveIndexBycartoonPage(fileEntity.getFileUrl(),page);
        //将文件暂存至项目，用于网页读取
        String s = readFile(fileEntity.getFileUrl(), request, response);
        try {
            //当前页资源
            model.addAttribute("url",s);
        } catch (Exception e) {
            e.printStackTrace();
        }
        //漫画当前页
        model.addAttribute("page",page);
        //当前漫画路径
        model.addAttribute("paramsUrl",substring);
        //漫画当前话路径
        model.addAttribute("paramUrl",url);
        //漫画当前话页数
        model.addAttribute("MaxCount",maxCount);
        return "cartoon/showByOne";
    }



    /**
     * 观看漫画
     * @param request
     * @param response
     * @param url   漫画路径
     * @param page  漫画当前页
     * @param model
     * @return
     * @throws ServletException
     * @throws IOException
     */
    @RequestMapping("/cartoonRead")
    public String cartoonRead( HttpServletRequest request,HttpServletResponse response,@RequestPart("url") String url,@RequestPart("page") int page,Model model) throws ServletException, IOException {

        //获取总页数
        int maxCount = dao.watchCartoonPageCount(url);
        //获得该文件的上一级路径
        String substring = url.substring(0,url.lastIndexOf("\\"));
        //如果当前页小于0，则当前页改为0
        if (page<0){
            //获取上一话
            fileEntity next = dao.getNext(substring, url, "up");
            //获取上一话页数
            page = dao.getNextCount(substring, url);
            maxCount=page+1;
            //将路径传入
            url=next.getFileUrl();
            //保存看到多少话
            getIndex.saveIndexBycartoon(url);
            cartoonRead(request,response,url,page,model);
        };
        //如果当前页大于总页数，则跳转到下一话的第0页
            if (page>=maxCount){
                page=0;
                fileEntity next = dao.getNext(substring, url, "down");
                    url=next.getFileUrl();
                    //保存看到多少话
                    getIndex.saveIndexBycartoon(url);
                    cartoonRead(request,response,url,page,model);
            }
            //获得该页文件
            fileEntity fileEntity = dao.watchCartoon(url,page);
            //记录看到了哪一页
            if(page==maxCount-1){
                getIndex.saveIndexBycartoonPage(fileEntity.getFileUrl(),0);
            }else {
                getIndex.saveIndexBycartoonPage(fileEntity.getFileUrl(),page);
            }
            //将文件暂存至项目，用于网页读取
            String s = readFile(fileEntity.getFileUrl(), request, response);
            try {
                //当前页资源
                model.addAttribute("url",s);
            } catch (Exception e) {
                e.printStackTrace();
            }
            //漫画当前页
            model.addAttribute("page",page);
            //当前漫画路径
            model.addAttribute("paramsUrl",substring);
            //漫画当前话路径
            model.addAttribute("paramUrl",url);
            //漫画当前话页数
            model.addAttribute("MaxCount",maxCount);
            return "cartoon/showByOne";
    }

    /**
     * 获取并设置图片
     *
     * @param filepath 文件本地路径
     * @param request   请求
     * @param response  响应
     * @return
     * @throws ServletException
     * @throws IOException
     */
    private String readFile(String filepath, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //获取临时存放文件夹的位置
        String path = request.getServletContext().getRealPath("temp");
        //文件路径
        InputStream is = new FileInputStream(new File(filepath));
        int i = is.available(); // 得到文件大小
        //创建容器接收输入流数据
        byte[] buffer = new byte[i];
        is.read(buffer); // 读数据
        //生成新文件名称
        String uuid = UUID.randomUUID().toString();
        //拼接新文件名称
        String fileName = uuid + filepath.substring(filepath.lastIndexOf("."));
        //拼接新文件路径
        String newpath = path + "\\" + fileName;
        //创建输出流
        OutputStream os = new FileOutputStream(new File(newpath));
        //写入数据
        os.write(buffer);
        //关闭流
        os.close();
        is.close();
        //返回文件名称  文件已存放进临时文件夹img
        return fileName;
    }


    @RequestMapping("reName")
    public String reName(@RequestParam Map map){

        return "index";
    }
}
