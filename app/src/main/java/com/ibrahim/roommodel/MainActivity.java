package com.ibrahim.roommodel;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;


import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.VideoView;

import java.util.List;

import io.reactivex.CompletableObserver;
import io.reactivex.Scheduler;
import io.reactivex.SingleObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private Button insertBt ,getBt;
    private EditText titleEd,bodyEd;
    private  PostsAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_SECURE, WindowManager.LayoutParams.FLAG_SECURE);
        setContentView(R.layout.activity_main);
        initItems();
       final PostsDatabase postsDatabase=PostsDatabase.getInstance(this);
       insertBt.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               postsDatabase.postsDao().insertPost(new Post(new User(1,"Hima"),titleEd.getText().toString(),bodyEd.getText().toString()))
                       .subscribeOn(Schedulers.computation())
                       .subscribe(
                               new CompletableObserver() {
                                   @Override
                                   public void onSubscribe(Disposable d) {

                                   }

                                   @Override
                                   public void onComplete() {

                                   }

                                   @Override
                                   public void onError(Throwable e) {

                                   }
                               }
                       );
           }
       });
   getBt.setOnClickListener(new View.OnClickListener() {
       @Override
       public void onClick(View view) {
           postsDatabase.postsDao().getPosts()
                   .subscribeOn(Schedulers.computation())
                   .observeOn(AndroidSchedulers.mainThread())
                   .subscribe(new SingleObserver<List<Post>>() {
                       @Override
                       public void onSubscribe(Disposable d) {

                       }

                       @Override
                       public void onSuccess(List<Post> posts) {
                         adapter.setPostArrayList(posts);
                         adapter.notifyDataSetChanged();
                       }

                       @Override
                       public void onError(Throwable e) {

                       }
                   });
       }
   });


    }

    private void initItems() {

        getBt= findViewById(R.id.bt_get);
        insertBt=findViewById(R.id.bt_inset);
        bodyEd=findViewById(R.id.et_body);
        titleEd=findViewById(R.id.et_title);
        recyclerView= findViewById(R.id.rv_posts);
        adapter=new PostsAdapter();
        recyclerView.setAdapter(adapter);
    }


}