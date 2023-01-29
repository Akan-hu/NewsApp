package android.example.newsapp;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.Toast;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeFragment extends Fragment {


    ProgressBar bar;

    String api = "7345011732f34fb791d4e0ea93f5bed9";
    ArrayList<ModelClass> modelClassArrayList;
    Adapter adapter;
    String country = "in";
    private RecyclerView recyclerViewOfHome;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_home, container, false);

       //progress = v.findViewById(R.id.my_relative);
        bar = v.findViewById(R.id.progress_bar);


        bar.setVisibility(View.VISIBLE);
        recyclerViewOfHome = v.findViewById(R.id.home_recycler);
        modelClassArrayList = new ArrayList<>();
        recyclerViewOfHome.setLayoutManager(new LinearLayoutManager(getContext()));
        adapter = new Adapter(getContext(),modelClassArrayList);
        recyclerViewOfHome.setAdapter(adapter);
        findNews();

        return v;
    }

    private void findNews() {
        ApiUtilities.getApiInterface().getNews(country,100,api).enqueue(new Callback<MainNews>() {
            @Override
            public void onResponse(Call<MainNews> call, Response<MainNews> response) {
                if(response.isSuccessful())
                {

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