package com.example.sharepreferencecrud;

import java.util.HashMap;

public class Post {

    private String title;
    private String body;
    private HashMap<String,String> hashMap;

    public void setTitle(String title){
        this.title = title;
    }
    public void setBody(String body){
        this.body = body;
    }

    public String getTitle(){
        return title;
    }

    public String getBody(){
        return body;
    }

    public HashMap<String,String> toHashMap(){
        hashMap = new HashMap<>();
        hashMap.put("title",title);
        hashMap.put("body",body);
        return hashMap;
    }

    public Post toPost(HashMap<String,String> hashMap){
        title = hashMap.get("title");
        body = hashMap.get("body");
        return  this;
    }

}
