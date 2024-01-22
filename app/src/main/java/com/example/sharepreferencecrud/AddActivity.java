package com.example.sharepreferencecrud;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class AddActivity extends AppCompatActivity {

    TextView titleInput;
    TextView bodyInput;
    TextView postBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);
        intialize();
        intializeLogic();
    }

    public void intialize(){
        titleInput = findViewById(R.id.titleInput);
        bodyInput = findViewById(R.id.bodyInput);
        postBtn = findViewById(R.id.postBtn);
    }

    public  void  intializeLogic(){
        postBtn.setOnClickListener(View -> {
            Post post = new Post();
            post.setTitle(titleInput.getText().toString());
            post.setBody(bodyInput.getText().toString());
            PostController postController = new PostController(this);
            postController.createPost(post);
            finish();
        });
    }
}