package com.blogspot.addictioncodes.news;

import android.content.Context;
import android.support.v4.content.AsyncTaskLoader;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

/**
 * Created by hp on 7/18/2017.
 */

class NewsLoader extends AsyncTaskLoader<ArrayList<news>> {
    private String url;
    public NewsLoader(Context context, String usgs) {
        super(context);
        this.url=usgs;
    }
    @Override
    protected void onStartLoading() {
        forceLoad();
    }
    @Override
    public ArrayList<news> loadInBackground() {
        //TODO: Implement this method
        return getList(getJsonResponseString(url));
    }
    private String getJsonResponseString(String urlJ){
        String st="";
        try{
            URL ur=new URL(urlJ);
            HttpURLConnection uc=(HttpURLConnection) ur.openConnection();
            uc.setRequestMethod("GET");
            uc.setReadTimeout(10000);
            uc.setConnectTimeout(150000);
            uc.connect();
            BufferedReader br=new BufferedReader(new InputStreamReader(uc.getInputStream()));
            String temp=br.readLine();
            st+=temp;
            while ((temp=br.readLine())!=null){
                st+=temp;
            }
        }catch (MalformedURLException m){
            Log.e("getJsonResponseString","MalformedUrlException");
        }catch (IOException e){
            Log.e("getJsonResponseString","IOException");
        }
        return st;
    }
    private ArrayList<news> getList(String operate){
        ArrayList<news> list=new ArrayList<news>();
        String date,Time;
        try{
            JSONObject root=new JSONObject(operate);
            JSONArray articles=root.getJSONArray("articles");
            for(int i=0;i<articles.length();i++){
                JSONObject details=(JSONObject) articles.get(i);
               String title=details.getString("title");
                String description=details.getString("description");
                String Imageurl=details.getString("urlToImage");
                String d=details.getString("publishedAt");
                try{
                date=d.substring(0,10);}
                catch(Exception e){
                    date="";
                }
                try{Time=d.substring(11,16);}
                catch(Exception e){
                    Time="";
                }
                String ur=details.getString("url");
                list.add(new news(title,description,Imageurl,date,Time,ur));
            }
        }catch (JSONException j){
        }
        return list;
    }
}