package android.example.newsapp;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Entertainment extends Fragment{

   ProgressBar bar;
    String api = "7345011732f34fb791d4e0ea93f5bed9";
    ArrayList<ModelClass> modelClassArrayList;
    Adapter adapter;
    String country = "in";
    private RecyclerView recyclerViewOfEntertainment;
    private String category = "entertainment";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View v =  inflater.inflate(R.layout.fragment_entertainment, container, false);

        bar = v.findViewById(R.id.progress_bar6);
        bar.setVisibility(View.VISIBLE);
        recyclerViewOfEntertainment = v.findViewById(R.id.entertain_recycler);
        modelClassArrayList = new ArrayList<>();
        recyclerViewOfEntertainment.setLayoutManager(new LinearLayoutManager(getContext()));
        adapter = new Adapter(getContext(),modelClassArrayList);
        recyclerViewOfEntertainment.setAdapter(adapter);
        findNews();

        return v;
    }

    private void findNews() {
        ApiUtilities.getApiInterface().getCategoryNews(country,category,100,api).enqueue(new Callback<MainNews>() {
            @Override
            public void onResponse(Call<MainNews> call, Response<MainNews> response) {
                if(response.isSuccessful()){
                    bar.setVisibility(View.GONE);
                    modelClassArrayList.addAll(response.body().getArticles());
                    adapter.notifyDataSetChanged();

                }
            }

            @Override
            public void onFailure(Call<MainNews> call, Throwable t) {

                Toast.makeText(getContext(), "Something went wrong", Toast.LENGTH_SHORT).show();

            }
        });

    }
}