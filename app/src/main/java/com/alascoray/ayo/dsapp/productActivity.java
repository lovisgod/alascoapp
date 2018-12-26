package com.alascoray.ayo.dsapp;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.graphics.drawable.RoundedBitmapDrawable;
import android.support.v4.graphics.drawable.RoundedBitmapDrawableFactory;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;

import java.util.Timer;
import java.util.TimerTask;

public class productActivity extends AppCompatActivity {


    Timer timer;
    int page = 1;
    ViewPager viewPager;
    AdView adView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product);
        viewPager = findViewById(R.id.view_pager);

        ImageAdapter adapter = new ImageAdapter(this);
        viewPager.setAdapter(adapter);

        timer = new Timer(); // At this line a new Thread will be created
        timer.scheduleAtFixedRate(new RemindTask(), 2000, 2000);


        ImageView logo= (ImageView)findViewById(R.id.logo);
        FloatingActionButton gotomain = (FloatingActionButton) findViewById(R.id.cont);

        Bitmap bitmaplogo = BitmapFactory.decodeResource(getResources(), R.drawable.pageone);
        RoundedBitmapDrawable oneDrawable = RoundedBitmapDrawableFactory.create(getResources(), bitmaplogo);
        oneDrawable.setCircular(true);


        logo.setImageDrawable(oneDrawable);


        gotomain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(productActivity.this, landingActivity.class);
                startActivity(intent);
            }
        });

        // Add mobile ads
        MobileAds.initialize(this, "ca-app-pub-3940256099942544~3347511713");
        adView = (AdView) findViewById(R.id.banner);
        AdRequest adRequest = new AdRequest.Builder().build();
        adView.loadAd(adRequest);

    }

    class RemindTask extends TimerTask {

        @Override
        public void run() {

            // As the TimerTask run on a seprate thread from UI thread we have
            // to call runOnUiThread to do work on UI thread.
            runOnUiThread(new Runnable() {
                public void run() {

                    if (page > 4) { // In my case the number of pages are 5
                        timer.cancel();
                        return;
                    } else {
                        viewPager.setCurrentItem(page++);
                    }

                }
            });

        }


    }
}
