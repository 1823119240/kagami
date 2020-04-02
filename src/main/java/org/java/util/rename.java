package org.java.util;

import java.io.*;
import java.nio.Buffer;

//改文件名用工具类
public class rename {



    public static void main(String[] args) {
        try {
            //examineFlv("C:\\Users\\zhangjunjie\\Videos\\BiliBiliDownload",".flv");
            getJson("C:\\Users\\zhangjunjie\\Videos\\BiliBiliDownload","D:\\好东西\\视频\\");
        } catch (Exception e) {
            e.printStackTrace();
        }
        //re00("D:\\好东西\\漫画");
/*        try {
            showSongName();
        } catch (IOException e) {

            System.out.println(e.toString());
            e.printStackTrace();
        }*/
    }

    /**
     * 检查文件中是否有包含该字符串
     * @param url 文件夹路径
     * @param str 文件名所包含的字符串
     */
    public static void examineFlv(String url,String str){
        File file = new File(url);
        File[] files = file.listFiles();
        for (File f: files
             ) {
            if(f.isFile()){
                if(f.getName().indexOf(str)!=-1){
                    System.out.println(f.toString());
                }
            }
            if(f.isDirectory()){
                examineFlv(f.toString(),str);
            }
        }
    }

    /**
     * 将文件夹中的文件移动到另外一个文件夹，并更改名称
     * @param url       原文件夹路径
     * @param newUrl    新文件夹路径
     * @throws IOException
     */
    public static void getJson(String url,String newUrl) throws IOException {
        //part
        //创建文件对象
        File file = new File(url);
        //视频
        File[] files = file.listFiles();
        //遍历视频
        for (File f: files
             ) {
            //记录该视频中有多少分p
            int count = 0;
            //分p
            File[] files1 = f.listFiles();
            //遍历分p，查看该视频下有多少分p
            for (File f1: files1
                 ) {
                if(f1.isDirectory())count+=1;
            }

            if(/*false*/count==1){
                String title="";
                for (File f2: files1
                     ) {
                    if(f2.getName().equals("info.json")){
                        FileInputStream in = new FileInputStream(f2);
                        InputStreamReader inr = new InputStreamReader(in);
                        BufferedReader br = new BufferedReader(inr);
                        title = br.readLine();
                        br.close();
                        inr.close();
                        in.close();
                        title=title.substring(title.indexOf("title")+8,title.length());
                        title=newUrl+title.substring(0,title.indexOf("\""))+".mp4";
                    }
                }
                for (File f3: files1
                     ) {
                    if(f3.isDirectory()){
                        File[] files2 = f3.listFiles();
                        for (File f4: files2
                             ) {
                            if(f4.getName().indexOf(".mp4")!=-1){
                                FileInputStream in = new FileInputStream(f4);
                                FileOutputStream out = new FileOutputStream(new File(title));
                                System.out.println("开始复制："+title.substring(title.lastIndexOf("\\"),title.length()));
                                byte[] b = new byte[1024];
                                int n=0;
                                while((n=in.read(b))!=-1){
                                    out.write(b,0,n);
                                }
                                out.close();
                                in.close();
                                System.out.print("\t复制完成");
                            }
                        }
                    }

                }
            }
            if(count>1){
                String title="";
                File fileItem = null;
                for (File f2: files1
                ) {
                    if(f2.getName().equals("info.json")){
                        FileInputStream in = new FileInputStream(f2);
                        InputStreamReader inr = new InputStreamReader(in);
                        BufferedReader br = new BufferedReader(inr);
                        title = br.readLine();
                        br.close();
                        inr.close();
                        in.close();
                        title=title.substring(title.indexOf("title")+8,title.length());
                        //判断newUrl结尾有没有\
                        if(newUrl.lastIndexOf("\\")!=newUrl.length()-2)
                        title=newUrl+title.substring(0,title.indexOf("\""));
                        else
                            title=newUrl+"\\"+title.substring(0,title.indexOf("\""));
                        System.out.println(title);
                        //创建多p视频文件夹
                        fileItem = new File(title);


                    }

                }
                for (File f3:files1
                     ) {
                    if(f3.isDirectory()){
                        String  name ="";
                        File[] files2 = f3.listFiles();
                        for (File f3_1: files2
                        ) {
                            if(f3_1.getName().equals("info.json")){
                                FileInputStream in = new FileInputStream(f3_1);
                                InputStreamReader inr = new InputStreamReader(in);
                                BufferedReader br = new BufferedReader(inr);
                                name = br.readLine();
                                br.close();
                                inr.close();
                                in.close();
                                name=name.substring(name.indexOf("title")+8,name.length());
                                name=name.substring(0,name.indexOf("\""));
                            }
                        }
                        for (File f3_1: files2
                        ) {
                            if(f3_1.getName().indexOf(".mp4")!=-1 && name!=""){
                                File newFileItem = new File(fileItem.toString());
                                newFileItem.mkdir();
                                FileInputStream in = new FileInputStream(f3_1);
                                FileOutputStream out = new FileOutputStream(newFileItem+"\\"+name+".mp4");
                                byte[] b = new byte[1024];
                                int n=0;
                                while((n=in.read(b))!=-1){
                                   out.write(b,0,n);
                                System.out.print("开始复制："+name); }
                            out.close();
                            in.close();
                                System.out.println("\t复制完成");


                            }
                        }
                    }
                }

            }

        }
    }
    private static void re00(String url) {
        File file = new File(url);
        File[] files = file.listFiles();
        int Max =7;
        for (File f: files
             ) {
            if(f.isFile()){
               if(f.getName().length()<Max){
                   if(f.getName().length()-Max==-1) {
                       System.out.println(file+"\\0" + f.getName());
                       f.renameTo(new File(file+"\\0" + f.getName()));
                   }
                   if(f.getName().length()-Max==-2){
                       f.renameTo(new File(file+"\\00" + f.getName()));
                   }
               }
            }
                if (f.isDirectory()){
                    //System.out.println(f);
                    re00(f.toString());

                }
                }

            }

    /**
     * 获取所有歌名
     * @throws IOException
     */
    public static void showSongName() throws IOException {
            //
            FileOutputStream fileOutputStream = new FileOutputStream("D:\\好东西\\songName.txt");
            OutputStreamWriter osw = new OutputStreamWriter(fileOutputStream);
            BufferedWriter bw = new BufferedWriter(osw);
            StringBuffer sb = new StringBuffer();
            File file = new  File("D:\\好东西\\歌曲");
            File[] files = file.listFiles();
            for (File f: files
                 ) {
                if (f.getName().indexOf(".mp3")!=-1){
                    int begin = f.getName().indexOf("-");
                    int end = f.getName().lastIndexOf(".");
                    if(begin!=-1 && end!=-1 ){
                        String str0 ="\t"+  f.getName()+"\r\n";
                        String str =f.getName().substring(begin,end+1);
                        if(str.indexOf("-")!=-1)str=str.substring(str.indexOf("-")+1,str.length()-1);
                        else if(str.indexOf("- ")!=-1)str=str.substring(str.indexOf("- ")+2,str.length()-1);
                        if(str.indexOf("[mqms2]")!=-1)str=str.substring(0,str.indexOf("[mqms2]"));
                        str = "歌名\t"+str+"\r\n";
                        String str2 ="歌手名\t"+ f.getName().substring(0,begin)+"\r\n\r\n";
                        sb.append(str0);
                        sb.append(str);
                        sb.append(str2);
                    }

                }
            }
            bw.write(sb.toString());
            bw.close();
            osw.close();
            fileOutputStream.close();
        }

    }
