package com.example.ayo.dsapp;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.support.v4.graphics.drawable.RoundedBitmapDrawable;
import android.support.v4.graphics.drawable.RoundedBitmapDrawableFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class landingActivity extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_landing);


        ImageView profile = (ImageView)findViewById(R.id.logo);
        ImageView imageOne = (ImageView)findViewById(R.id.imageView);
        ImageView imageTwo = (ImageView)findViewById(R.id.imageView2);
        ImageView imageThree = (ImageView)findViewById(R.id.imageView3);
        Bitmap bitmapProfile = BitmapFactory.decodeResource(getResources(), R.drawable.pageone);
        Bitmap bitmapImageTwo = BitmapFactory.decodeResource(getResources(),R.drawable.aim_account);
        Bitmap bitmapImageThree = BitmapFactory.decodeResource(getResources(),R.drawable.product_six);
        RoundedBitmapDrawable mDrawable = RoundedBitmapDrawableFactory.create(getResources(), bitmapProfile);
        RoundedBitmapDrawable imageTwoDrawable = RoundedBitmapDrawableFactory.create(getResources(), bitmapImageTwo);
        RoundedBitmapDrawable imageThreeDrawable = RoundedBitmapDrawableFactory.create(getResources(), bitmapImageThree);
        mDrawable.setCircular(true);
        imageTwoDrawable.setCircular(true);
        imageThreeDrawable.setCircular(true);

        profile.setImageDrawable(mDrawable);
        imageTwo.setImageDrawable(imageTwoDrawable);
        imageThree.setImageDrawable(imageThreeDrawable);

        Button loginButton = (Button)findViewById(R.id.login);
        Button testimonial = (Button)findViewById(R.id.testimonials);
        Button account_link = (Button)findViewById(R.id.account_linking);

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentLogin = new Intent(Intent.ACTION_VIEW, Uri.parse("https://dtc.aimglobalinc.com/aimglobalinc/login"));
                startActivity(intentLogin);
            }
        });

        testimonial.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intentTestimonials = new Intent(landingActivity.this, testimonialsActivity.class);
                startActivity(intentTestimonials);
            }
        });

        account_link.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentAccount = new Intent(landingActivity.this, accountActivity.class);
                startActivity(intentAccount);
            }
        });


    }
}
