package com.example.rattasartpc.healthy.Post;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import com.example.rattasartpc.healthy.Comment.CommentFragment;
import com.example.rattasartpc.healthy.MenuFragment;
import com.example.rattasartpc.healthy.R;
import com.example.rattasartpc.healthy.Weight.Weight;
import com.example.rattasartpc.healthy.Weight.WeightAdapter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;


public class PostFragment extends Fragment {

    private Bundle bundle;
    private CommentFragment obj;
    private FragmentManager fm;
    private FragmentTransaction ft;
    private PostAdapter postAdapter;
    private ArrayList<Post> posts = new ArrayList<Post>();
    private ListView postList;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_post,container,false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Button backButton = getView().findViewById(R.id.post_back);
        postList = (ListView) getView().findViewById(R.id.post_list);

         //posts.add(new Post("BMI","test", "test"));

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (getFragmentManager().getBackStackEntryCount() != 0) {
                    getFragmentManager().popBackStack("Menu", FragmentManager.POP_BACK_STACK_INCLUSIVE);
                }
            }
        });


        AsyncTask asyncTask = new AsyncTask() {
            @Override
            protected Object doInBackground(Object[] objects) {
                OkHttpClient client = new OkHttpClient();
                Request request = new Request.Builder()
                        .url("https://jsonplaceholder.typicode.com/posts")
                        .build();
                try {
                    Response response = client.newCall(request).execute();
                    JSONArray array = new JSONArray(response.body().string());
                    for (int i = 0; i < array.length(); i++) {
                        JSONObject object = array.getJSONObject(i);
                        posts.add(new Post(object.getInt("userId"),
                                object.getInt("id"),
                                object.getString("title"),
                                object.getString("body")
                        ));
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
                if(getActivity() != null) {
                    postAdapter =  new PostAdapter(
                            getActivity(),
                            R.layout.fragment_post_item,
                            posts
                    );
                }
                postList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, final int position, long id) {
                        bundle = new Bundle();
                        bundle.putInt("postId", postAdapter.getItem(position).getPostId());
                        fm = getActivity().getSupportFragmentManager();
                        ft = fm.beginTransaction();
                        obj = new CommentFragment();
                        obj.setArguments(bundle);
                        ft.replace(R.id.main_view, obj).addToBackStack("PostFragment");
                        ft.commit();
                    }
                });
                postAdapter.notifyDataSetChanged();
                postList.setAdapter(postAdapter);
            }
        }.execute();



        postList.setAdapter(postAdapter);

    }

}
