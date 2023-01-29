package android.example.newsapp;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class Adapter extends RecyclerView.Adapter<Adapter.ViewHolder> {

    Context context;
    ArrayList<ModelClass> modelClassArrayList;

    public Adapter(Context context,ArrayList<ModelClass> modelClassArrayList) {
        this.context = context;
        this.modelClassArrayList = modelClassArrayList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.layout_item,null,false);
        return new ViewHolder(view);
    }
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context,WebView.class);
                intent.putExtra("url",modelClassArrayList.get(position).getUrl());
                context.startActivity(intent);
            }
        });
        holder.time.setText("Published At:-"+modelClassArrayList.get(position).getPublishedAt());
        holder.author.setText(modelClassArrayList.get(position).getAuthor());
        holder.content.setText(modelClassArrayList.get(position).getDescription());
        holder.heading.setText(modelClassArrayList.get(position).getTitle());

        Picasso.get().load(modelClassArrayList.get(position).getUrlToImage()).into(holder.newsImage);

    }

    @Override
    public int getItemCount() {
        return modelClassArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView heading,content,author,time;
        ImageView newsImage;
        CardView cardView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            heading = itemView.findViewById(R.id.main_heading);
            content = itemView.findViewById(R.id.content);
            author = itemView.findViewById(R.id.author_name);
            time = itemView.findViewById(R.id.time);
            newsImage = itemView.findViewById(R.id.news_image);
            cardView = itemView.findViewById(R.id.card_view);
        }
    }

}
