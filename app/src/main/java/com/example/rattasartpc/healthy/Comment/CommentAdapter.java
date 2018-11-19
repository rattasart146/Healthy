package com.example.rattasartpc.healthy.Comment;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.rattasartpc.healthy.Post.Post;
import com.example.rattasartpc.healthy.R;

import java.util.ArrayList;
import java.util.List;

public class CommentAdapter extends ArrayAdapter<Comment> {

    List<Comment> comments = new ArrayList<>();
    Context context;

    public CommentAdapter(@NonNull Context context, int resource, @NonNull List<Comment> objects) {
        super(context, resource, objects);
        this.context = context;
        this.comments = objects;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View commentItem = LayoutInflater.from(context).inflate(
                R.layout.fragment_comment_item,
                parent,
                false
        );

        TextView commentPostId = commentItem.findViewById(R.id.comment_post_id);
        TextView commentId = commentItem.findViewById(R.id.comment_id);
        TextView commentContent = commentItem.findViewById(R.id.comment_content);
        TextView commentName = commentItem.findViewById(R.id.comment_name);
        TextView commentEmail = commentItem.findViewById(R.id.comment_email);

        commentPostId.setText(comments.get(position).getCommentPostId());
        commentId.setText(comments.get(position).getCommentId());
        commentContent.setText(comments.get(position).getCommentContent());
        commentName.setText(comments.get(position).getCommentName());
        commentEmail.setText(comments.get(position).getCommentEmail());

        return commentItem;
    }
}
