package com.ibrahim.roommodel;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class PostsAdapter extends RecyclerView.Adapter<PostsAdapter.PostsViewHolder> {
    private List<Post> postArrayList = new ArrayList<>();

    @NonNull
    @Override
    public PostsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new PostsViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.postitem, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull PostsViewHolder holder, int position) {
           holder.tvBody.setText(postArrayList.get(position).getBody());
        holder.tvTittle.setText(postArrayList.get(position).getTitle());
    }

    @Override
    public int getItemCount() {
        return postArrayList.size();
    }
 public void setPostArrayList(List<Post> posts){
        this.postArrayList=posts;
        notifyDataSetChanged();
 }
    class PostsViewHolder extends RecyclerView.ViewHolder {
        private TextView tvTittle, tvBody;

        public PostsViewHolder(@NonNull View itemView) {

            super(itemView);
            tvTittle=itemView.findViewById(R.id.tv_title);
            tvBody=itemView.findViewById(R.id.tv_body);
        }
    }
}
