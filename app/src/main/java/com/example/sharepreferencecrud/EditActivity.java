package com.example.sharepreferencecrud;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

public class EditActivity extends AppCompatActivity {

    Button updateBtn;
    TextView titleInput;
    TextView bodyInput;
    PostController postController;
    int postId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);
        intialize();
        intializeLogic();
    }

    public void intialize(){
        titleInput = findViewById(R.id.titleInput);
        bodyInput = findViewById(R.id.bodyInput);
        updateBtn = findViewById(R.id.editBtn);
    }

    public void intializeLogic(){
        postController = new PostController(this);
        postId = getIntent().getIntExtra("id",-1);
        Post post = postController.getPost(postId);
        titleInput.setText(post.getTitle());
        bodyInput.setText(post.getBody());

        updateBtn.setOnClickListener(Viw -> {
            Post updatePost = new Post();
            updatePost.setTitle(titleInput.getText().toString());
            updatePost.setBody(bodyInput.getText().toString());
            postController.updatePost(postId,updatePost);
            finish();
        });
    }
}