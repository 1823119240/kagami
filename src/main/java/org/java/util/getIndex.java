package org.java.util;

import java.io.*;

public class getIndex {
    public static void main(String[] args) throws IOException {
        //saveIndexBycartoonPage("D:\\好东西\\漫画\\堕落的珈百璃\\51\\000.jpg");
        loadIndexBycartoonPage("D:\\好东西\\漫画\\堕落的珈百璃\\51");
    }
    //确定漫画看到哪一话
    public static String loadIndexBycartoon(String url) throws IOException {
            File f = new File(url+"\\Index.txt");
            if(f.exists()){
                FileInputStream fls = new FileInputStream(f);
                InputStreamReader isr = new InputStreamReader(fls);
                BufferedReader br = new BufferedReader(isr);
                String urlIndex = br.readLine();
                return urlIndex;
            }else{
                return "";
            }
    }

    //记录漫画看到哪一话
    public static void saveIndexBycartoon(String url) throws IOException {
        FileOutputStream fileOutputStream = new FileOutputStream(url.substring(0,url.lastIndexOf("\\"))+"\\Index.txt");
        OutputStreamWriter osw = new OutputStreamWriter(fileOutputStream);
        BufferedWriter bw = new BufferedWriter(osw);
        bw.write(url);
        bw.close();
        osw.close();
        fileOutputStream.close();
    }



    //确定看到漫画的那一话的哪一页
    public static String loadIndexBycartoonPage(String url) throws IOException {
        File f = new File(url+"\\Index.txt");
        if(f.exists()){
            FileInputStream fls = new FileInputStream(f);
            InputStreamReader isr = new InputStreamReader(fls);
            BufferedReader br = new BufferedReader(isr);
            String urlIndex = br.readLine();
            return urlIndex;
        }else{
            return "";
        }
    }

    //记录漫画看到哪一话的哪一页
    public static void saveIndexBycartoonPage(String url,Integer Index) throws IOException {
        FileOutputStream fileOutputStream = new FileOutputStream(url.substring(0,url.lastIndexOf("\\"))+"\\Index.txt");
        OutputStreamWriter osw = new OutputStreamWriter(fileOutputStream);
        BufferedWriter bw = new BufferedWriter(osw);
        bw.write(Index.toString());
        bw.close();
        osw.close();
        fileOutputStream.close();
    }

}
