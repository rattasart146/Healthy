package com.example.rattasartpc.healthy.Post;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.rattasartpc.healthy.R;

import java.util.ArrayList;
import java.util.List;

public class PostAdapter extends ArrayAdapter <Post> {
        List<Post> posts = new ArrayList<>();
        Context context;

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

        TextView postId = (TextView) postItem.findViewById(R.id.post_id);
        TextView postTitle =(TextView) postItem.findViewById(R.id.post_title);
        TextView postContent = (TextView) postItem.findViewById(R.id.post_content);

        postId.setText(posts.get(position).getPostId());
        postTitle.setText(posts.get(position).getPostTitle());
        postContent.setText(posts.get(position).getPostContent());

        return postItem;
    }

}
