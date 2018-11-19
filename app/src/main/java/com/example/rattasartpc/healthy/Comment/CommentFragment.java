package com.example.rattasartpc.healthy.Comment;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;

import com.example.rattasartpc.healthy.MenuFragment;
import com.example.rattasartpc.healthy.Post.Post;
import com.example.rattasartpc.healthy.Post.PostAdapter;
import com.example.rattasartpc.healthy.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class CommentFragment extends Fragment {
    private CommentAdapter commentAdapter;
    private ListView commentList;
    private int CURRENT_POST;
    private ArrayList<Comment> comments = new ArrayList<Comment>();
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_comment,container,false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Button backButton = getView().findViewById(R.id.comment_back);
        commentList = (ListView) getView().findViewById(R.id.comment_list);

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (getFragmentManager().getBackStackEntryCount() != 0) {
                    getFragmentManager().popBackStack("Menu", FragmentManager.POP_BACK_STACK_INCLUSIVE);
                }
            }
        });

        readApi();

    }

    private void readApi() {
        AsyncTask asyncTask = new AsyncTask() {
            @Override
            protected Object doInBackground(Object[] objects) {
                OkHttpClient client = new OkHttpClient();
                Request request = new Request.Builder()
                        .url("https://jsonplaceholder.typicode.com/posts/" + CURRENT_POST + "/comments")
                        .build();
                try {
                    Response response = client.newCall(request).execute();
                    JSONArray array = new JSONArray(response.body().string());
                    for (int i = 0; i < array.length(); i++) {
                        JSONObject object = array.getJSONObject(i);
                        if (object.getInt("postId") == CURRENT_POST) {
                            comments.add(new Comment(
                                    object.getInt("postId"),
                                    object.getInt("id"),
                                    object.getString("name"),
                                    object.getString("email"),
                                    object.getString("body")
                            ));
                        }
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                return null;
            }

            @Override
            protected void onPostExecute(Object o) {
                if (getActivity() != null) {
                    commentAdapter = new CommentAdapter(
                            getActivity(),
                            R.layout.fragment_comment_item,
                            comments
                    );
                }
                commentAdapter.notifyDataSetChanged();
                commentList.setAdapter(commentAdapter);
            }
        }.execute();
    }
}
