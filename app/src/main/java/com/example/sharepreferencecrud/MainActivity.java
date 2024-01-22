package com.example.sharepreferencecrud;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    ListView listView;
    Button addBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        intialize();
        intializeLogic();
    }

    public void intialize(){
        listView = findViewById(R.id.listView);
        addBtn = findViewById(R.id.addBtn);
    }

    public void intializeLogic(){
        addBtn.setOnClickListener(View -> {
            Intent i = new Intent(this,AddActivity.class);
            startActivity(i);
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        PostController postController = new PostController(this);
        List<Post> posts = postController.getAllpost();
//        addBtn.setText(String.valueOf(posts.size()));
        CustomAdapter adapter = new CustomAdapter(getApplicationContext(),posts);
        listView.setAdapter(adapter);

    }

    public class CustomAdapter extends BaseAdapter {
        private Context context;
        private List<Post> itemList;
        public CustomAdapter(Context context, List<Post> itemList) {
            this.context = context;
            this.itemList = itemList;
        }

        @Override
        public CharSequence[] getAutofillOptions() {
            return super.getAutofillOptions();
        }

        @Override
        public int getCount() {
            return itemList.size();
        }

        @Override
        public Object getItem(int position) {
            return itemList.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(final int position, View convertView, ViewGroup parent) {
            if (convertView == null) {
                convertView = LayoutInflater.from(context).inflate(R.layout.custom_list_item, parent, false);
            }

            final Post currentItem = (Post) getItem(position);

            TextView titleText = convertView.findViewById(R.id.titleText);
            TextView bodyText = convertView.findViewById(R.id.bodyText);

            titleText.setText(currentItem.getTitle());
            bodyText.setText(String.valueOf(currentItem.getBody()));

            convertView.setOnClickListener( View -> {
                        // Show ID in a Toast message
                        Intent i = new Intent(getApplicationContext(),ShowActivity.class);
                        i.putExtra("id",position);
                        startActivity(i);
                    }
            );
            return convertView;
        }
    }
}