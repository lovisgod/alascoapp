package com.example.ayo.dsapp;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Handler;
import android.support.v4.graphics.drawable.RoundedBitmapDrawable;
import android.support.v4.graphics.drawable.RoundedBitmapDrawableFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Handler handler = new Handler();


        ImageView profile = (ImageView)findViewById(R.id.imageIntro);
        Bitmap bitmapProfile = BitmapFactory.decodeResource(getResources(), R.drawable.pageone);
        RoundedBitmapDrawable mDrawable = RoundedBitmapDrawableFactory.create(getResources(), bitmapProfile);
        mDrawable.setCircular(true);

        profile.setImageDrawable(mDrawable);


        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent=new Intent(MainActivity.this,productActivity.class);
                startActivity(intent);
                finish();
            }
        },4000);
    }
}
