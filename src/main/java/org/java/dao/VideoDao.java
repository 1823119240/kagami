package org.java.dao;

import org.java.entity.fileEntity;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class VideoDao {
    public List<fileEntity> videoCata(){
        File file = new File("D:\\好东西\\视频");
        File[] tempplus = file.listFiles();
        List<fileEntity> list = new ArrayList<fileEntity>();
        for (File f:tempplus
        ) {
            //创建文件对象存储文件信息
            fileEntity ce = new fileEntity();
            if(f.getName().indexOf(".ini")==-1){
                //String name = f.getName().substring(0,f.getName().indexOf("."));
                ce.setFileName(f.getName());
                ce.setFileUrl(f.toString());
                list.add(ce);
            }
            System.out.println(f);
        }
        return list;
    }
}
