package com.example.sharepreferencecrud;

import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class PostController {
    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;
    private Context context;
    private List<HashMap<String,String>> postList;
    public PostController(Context context){
        this.context = context;
        sharedPreferences = context.getSharedPreferences("data", context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
        postList = new ArrayList<>();
        if(sharedPreferences.contains("posts")){
            String jsonString = sharedPreferences.getString("posts","");
            // Convert JSON to ArrayList
            Gson gson = new Gson();
            Type type = new TypeToken<ArrayList<HashMap<String,String>>>(){}.getType();
            postList = gson.fromJson(jsonString, type);
        }
    }

    public void createPost(Post post){
        postList.add(post.toHashMap());
        saveData();
    }

    public List<Post> getAllpost(){
        List<Post> totalPost = new ArrayList<>();
        for (HashMap<String,String> hashMap: postList ) {
            totalPost.add(new Post().toPost(hashMap));
        }
        return totalPost;
    }

    public Post getPost(int position){
        HashMap<String,String> hashMap = postList.get(position);
        return new Post().toPost(hashMap);
    }

    public void updatePost(int position, Post post){
        HashMap<String,String> hashMap = post.toHashMap();
        postList.set(position,hashMap);
        saveData();
    }

    public void deletePost(int position){
        postList.remove(position);
        saveData();
    }

    public void saveData(){
        Gson gson = new Gson();
        String jsonString = gson.toJson(postList);
        editor.putString("posts",jsonString);
        editor.commit();
    }


}
