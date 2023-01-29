package android.example.newsapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;

import com.google.android.material.tabs.TabItem;
import com.google.android.material.tabs.TabLayout;

import java.time.chrono.ThaiBuddhistChronology;

public class MainActivity extends AppCompatActivity {

    TabLayout tabLayout;
    TabItem home,sport,science,technology,health,entertain;
    PageAdapter pageAdapter;
    Toolbar toolbar;
    String api = "7345011732f34fb791d4e0ea93f5bed9";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        tabLayout = findViewById(R.id.tab_layout);
        home = findViewById(R.id.home);
        sport = findViewById(R.id.sports);
        science = findViewById(R.id.science);
        technology = findViewById(R.id.technology);
        health = findViewById(R.id.health);
        entertain = findViewById(R.id.entertainment);
        ViewPager viewPager = findViewById(R.id.frag_contain);

        pageAdapter = new PageAdapter(getSupportFragmentManager(),6);
        viewPager.setAdapter(pageAdapter);

        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
                if(tab.getPosition()==0||tab.getPosition()==1||tab.getPosition()==2||tab.getPosition()==3||tab.getPosition()==4||tab.getPosition()==5){
                    pageAdapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));

    }
}