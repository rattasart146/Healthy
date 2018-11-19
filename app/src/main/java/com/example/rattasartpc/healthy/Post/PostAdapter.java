package com.example.rattasartpc.healthy.Post;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.rattasartpc.healthy.Comment.Comment;
import com.example.rattasartpc.healthy.R;

import java.util.ArrayList;
import java.util.List;

public class PostAdapter extends ArrayAdapter <Post> {
        List<Post> posts = new ArrayList<>();
        Context context;
        String _content, _title;
        int _id;

    public PostAdapter(@NonNull Context context, int resource, @NonNull List<Post> objects) {
        super(context, resource, objects);
        this.context = context;
        this.posts = objects;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View postItem = LayoutInflater.from(context).inflate(
                R.layout.fragment_post_item,
                parent,
                false
        );

        TextView postId = postItem.findViewById(R.id.post_id);
        TextView postTitle = postItem.findViewById(R.id.post_title);
        TextView postContent = postItem.findViewById(R.id.post_content);

        Post row = posts.get(position);

        _content = row.getPostContent();
        _id = row.getPostId();
        _title = row.getPostTitle();

        postId.setText(Integer.toString(_id));
        postTitle.setText(_title);
        postContent.setText(_content);

        Log.d("checkId", "getView: " + _id);
        Log.d("checkTitle", "getView: " + _title);
        Log.d("checkContent", "getView: " + _content);
        return postItem;
    }

}
