package com.example.chalaan;

import android.content.SharedPreferences;

import android.os.Bundle;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class About extends AppCompatActivity {

    RecyclerView NewsRecyclerview;
    NewsAdapter newsAdapter;
    List<NewsItem> mData;
    FloatingActionButton fabSwitcher;
    boolean isDark = false;
    ConstraintLayout rootLayout;
    EditText searchInput ;
    CharSequence search="";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // let's make this activity on full screen

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_about);

        // hide the action bar

        getSupportActionBar().hide();



        // ini view

        fabSwitcher = findViewById(R.id.fab_switcher);
        rootLayout = findViewById(R.id.root_layout);
        searchInput = findViewById(R.id.search_input);
        NewsRecyclerview = findViewById(R.id.news_rv);
        mData = new ArrayList<>();

        // load theme state

        isDark = getThemeStatePref();
        if(isDark) {
            // dark theme is on

            searchInput.setBackgroundResource(R.drawable.search_input_dark_style);
            rootLayout.setBackgroundColor(getResources().getColor(R.color.black));

        }
        else
        {
            // light theme is on
            searchInput.setBackgroundResource(R.drawable.search_input_style);
            rootLayout.setBackgroundColor(getResources().getColor(R.color.white));

        }



        // fill list news with data
        // just for testing purpose i will fill the news list with random data
        // you may get your data from an api / firebase or sqlite database ...
        mData.add(new NewsItem("Shri Shekhar Channe, IAS","Transport Commissioner ,Contact: 22614721, 22614722,Email:transport.commr-mh@gov.in","6 july 1994",R.drawable.user));
        mData.add(new NewsItem("Shri S.B. Sahasrabudhe","Additional Transport Commissioner,Contact: 22614723,Email:adtc.tpt-mh@gov.in","6 july 1994",R.drawable.circul6));
        mData.add(new NewsItem("Shri A.N. Bhalchandra ","Joint Transport Commissioner (addl. charge),Contact: 22614724,Email:jttc.tpt-mh@gov.in","6 july 1994",R.drawable.uservoyager));
        mData.add(new NewsItem("Shri Jitendra Patil","Dy. Transport Commissioner (Admin),Contact: 22614726","6 july 1994",R.drawable.useillust));
        mData.add(new NewsItem("Shri A.N. Bhalchandra","Dy. Transport Commissioner (E-I),Contact: 22614727,Email:dytcenf1.tpt-mh@gov.in","6 july 1994",R.drawable.circul6));
        mData.add(new NewsItem("Shri Purushottam Nikam","Dy. Transport Commissioner (E-II),Contact: 22615075,Email:dytcenf2.tpt-mh@gov.in","6 july 1994",R.drawable.user));
        mData.add(new NewsItem("Shri Rajendra Kadam","Dy. Transport Commissioner (Insp.),Contact: 22617103,Email:dytcinspect.tpt-mh@gov.in","6 july 1994",R.drawable.user));
        mData.add(new NewsItem("Shri S.M. Chavan","Dy. Transport Commissioner (Comp.) (addl. charge),Contact: 22614723,Email:dytccomp.tpt-mh@gov.in","6 july 1994",R.drawable.circul6));
        mData.add(new NewsItem("Shri Sudhir Nakadi ","Dy. Commissioner (Accounts),Contact: 22616498,Email:cao.tpt-mh@gov.in","6 july 1994",R.drawable.uservoyager));
        mData.add(new NewsItem("Shri Pradip Shinde","Asst. Commissioner of Police (Vigilance) (addl. charge),Contact: 22642223,Email:acpvig.tpt-mh@gov.in","6 july 1994",R.drawable.useillust));


        // adapter ini and setup

        newsAdapter = new NewsAdapter(this,mData,isDark);
        NewsRecyclerview.setAdapter(newsAdapter);
        NewsRecyclerview.setLayoutManager(new LinearLayoutManager(this));


        fabSwitcher.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                isDark = !isDark ;
                if (isDark) {

                    rootLayout.setBackgroundColor(getResources().getColor(R.color.black));
                    searchInput.setBackgroundResource(R.drawable.search_input_dark_style);

                }
                else {
                    rootLayout.setBackgroundColor(getResources().getColor(R.color.white));
                    searchInput.setBackgroundResource(R.drawable.search_input_style);
                }

                newsAdapter = new NewsAdapter(getApplicationContext(),mData,isDark);
                if (!search.toString().isEmpty()){

                    newsAdapter.getFilter().filter(search);

                }
                NewsRecyclerview.setAdapter(newsAdapter);
                saveThemeStatePref(isDark);




            }
        });



        searchInput.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {


                newsAdapter.getFilter().filter(s);
                search = s;


            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });



    }

    private void saveThemeStatePref(boolean isDark) {

        SharedPreferences pref = getApplicationContext().getSharedPreferences("myPref",MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        editor.putBoolean("isDark",isDark);
        editor.commit();
    }

    private boolean getThemeStatePref () {

        SharedPreferences pref = getApplicationContext().getSharedPreferences("myPref",MODE_PRIVATE);
        boolean isDark = pref.getBoolean("isDark",false) ;
        return isDark;

    }



}
