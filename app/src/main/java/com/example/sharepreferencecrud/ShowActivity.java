package com.example.sharepreferencecrud;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

public class ShowActivity extends AppCompatActivity {

    TextView title;
    TextView body;
    Button editBtn;
    Button deleteBtn;
    PostController postController;
    int postId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show);
        intialize();
        intializeLogic();
    }

    public  void intialize(){
        title = findViewById(R.id.titleText);
        body = findViewById(R.id.bodyText);
        editBtn = findViewById(R.id.postButton);
        deleteBtn = findViewById(R.id.deleteBtn);
    }

    public void intializeLogic(){
        postId = getIntent().getIntExtra("id",-1);
        editBtn.setOnClickListener(View -> {
            Intent i = new Intent(this,EditActivity.class);
            i.putExtra("id",getIntent().getIntExtra("id",-1));
            startActivity(i);
        });

        deleteBtn.setOnClickListener(View -> {
            postController.deletePost(postId);
            finish();
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        postController = new PostController(this);
        Post post = postController.getPost(postId);
        title.setText(post.getTitle());
        body.setText(post.getBody());
    }
}