package com.blogspot.addictioncodes.news;

/**
 * Created by hp on 7/18/2017.
 */

public class news {
    private String st;
    private String st1,img,da,t,url;
    public news(String st,String ss,String img,String date,String Time,String url){
        this.st=st;
        this.st1=ss;
        this.img=img;
        this.da=date;
        this.t=Time;
        this.url=url;
    }
    public String getSt(){return this.st;}
    public String getSt1(){return this.st1;}
    public String getImg(){return this.img;}
    public String getDa(){return this.da;}
    public String getT(){return this.t;}
    public String getUrl(){return this.url;}
}
