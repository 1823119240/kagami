package org.java.dao;

import org.java.entity.fileEntity;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

//漫画类
public class CartoonDao {
    /**
     * 查询到所有的漫画
     * @return 漫画集合
     */
    public List<fileEntity> showCatatoon(){
        //创建文件对象，获取该文件夹下目录
        File file = new File("D:\\好东西\\漫画");
        File[] tempplus = file.listFiles();
        //创建集合存储漫画名以及漫画路径
        List<fileEntity> list = new ArrayList<fileEntity>();
        for (File f:tempplus
             ) {
            //创建漫画对象存储漫画信息
            list.add(new fileEntity(f.getName(),f.toString()));
        }
        return list;
        }

    /**
     * 获取该漫画下所有话
     * @return 该漫画分集合集
     */
        public List<fileEntity> showCartoon(String str){
            File file = new File(str);
            File[] tempplus = file.listFiles();
            List<fileEntity> list = new ArrayList<>();
            for (File f:tempplus
                 ) {
                if(!f.getName().equals("Index.txt")){
                    list.add(new fileEntity(f.getName(),f.toString()));
                }
            }

            return list;
            }

    /**
     *
     * @param url 资源路径
     * @param page 漫画页数
     * @return
     */
    public fileEntity watchCartoon(String url,int page){
            File file = new File(url);
            File[] tempplus = file.listFiles();
            fileEntity fileEntity;
                fileEntity = new fileEntity(tempplus[page].getName(),tempplus[page].toString());
            return fileEntity;
        }

    /**
     * 查询当前漫画多少页
     * @param str 资源路径
     * @return
     */
    public int watchCartoonPageCount(String str){
        File file = new File(str);
        File[] tempplus = file.listFiles();
        List list = new ArrayList();
        for (File f: tempplus
             ) {
            if(f.getName().equals("Index.txt"))
                return tempplus.length-1;
        }
        return tempplus.length;
    }
    //获得下一话路径
    public fileEntity getNext(String paramurl,String url,String conf){
        File file = new File(paramurl);
        File[] files = file.listFiles();
        fileEntity fileEntity = new fileEntity();
        try {
            for (int i = 0; i < files.length; i++) {
                if(files[i].toString().equals(url)){
                    if(i>0 && conf.equals("up")){
                        fileEntity.setFileUrl(files[i-1].toString());
                        fileEntity.setFileName(files[i-1].getName());
                    }else if(i<files.length-1 && conf.equals("down") && !files[i+1].getName().equals("Index.txt")){
                        fileEntity.setFileUrl(files[i+1].toString());
                        fileEntity.setFileName(files[i+1].getName());
                    }else{
                        fileEntity.setFileUrl(files[i].toString());
                        fileEntity.setFileName(files[i].getName());
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return fileEntity;
    }
    //获取上一话最大页数
    public int getNextCount(String paramurl,String url){
        //获取漫画文件夹
        File file = new File(paramurl);
        //获取该漫画所有话文件夹
        File[] files = file.listFiles();
        //创建实体类
        fileEntity fileEntity = new fileEntity();
        try {
            //循环找到该话的上一话
            for (int i = 0; i < files.length; i++) {
                //如果找到该话
                if(files[i].toString().equals(url)){
                    if(i>0){
                        fileEntity.setFileUrl(files[i-1].toString());
                        fileEntity.setFileName(files[i-1].getName());
                        //获取当前话的文件集合
                        File[] fs = new File(fileEntity.getFileUrl()).listFiles();
                        for (File f: fs) {
                            if(f.getName().equals("Index.txt")){
                                return fs.length-2;
                            }

                        }
                        return fs.length-1;
                    }else{
                        fileEntity.setFileUrl(files[i].toString());
                        fileEntity.setFileName(files[i].getName());
                        //获取当前话的文件集合
                        File[] fs = new File(fileEntity.getFileUrl()).listFiles();
                        for (File f: fs) {
                            if(f.getName().equals("Index.txt")){
                                return fs.length-2;
                            }
                        }
                        return fs.length-1;
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

}
