package com.example.ayo.dsapp;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v4.graphics.drawable.RoundedBitmapDrawable;
import android.support.v4.graphics.drawable.RoundedBitmapDrawableFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

public class accountActivity extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account);

        ImageView profile = (ImageView)findViewById(R.id.logo);
        Bitmap bitmapProfile = BitmapFactory.decodeResource(getResources(), R.drawable.pageone);
        RoundedBitmapDrawable mDrawable = RoundedBitmapDrawableFactory.create(getResources(), bitmapProfile);
        mDrawable.setCircular(true);

        profile.setImageDrawable(mDrawable);

        final EditText fullName = (EditText)findViewById(R.id.fullname);
        final EditText id_no = (EditText)findViewById(R.id.id_number);
        final EditText acc_no = (EditText)findViewById(R.id.acc_no);
        final EditText bank_name = (EditText)findViewById(R.id.bank_name);
        final EditText  mobile_no= (EditText)findViewById(R.id.mobile_no);

        /** String full_name = fullName.getText().toString();
        final String idNo = id_no.getText().toString();
        String accNo = acc_no.getText().toString();
        String bankName = bank_name.getText().toString();
        String mobileNo = mobile_no.getText().toString();
         final String text = full_name + " :" + "\n" + idNo+ " :" + "\n" + accNo  + " :" + "\n" + bankName + " :" + "\n" + mobileNo+ " :" ;**/

        Button button= (Button) findViewById(R.id.submit);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent emailIntent = new Intent(Intent.ACTION_SEND);
// The intent does not have a URI, so declare the "text/plain" MIME type
                emailIntent.setType("plain/text");
                emailIntent.putExtra(Intent.EXTRA_EMAIL, new String[] {"ofondear@yahoo.com"}); // recipients
                emailIntent.putExtra(Intent.EXTRA_SUBJECT, "ACCOUNT ACTIVATION");
                emailIntent.putExtra(Intent.EXTRA_TEXT,   "FULL NAME : " + fullName.getText().toString() + "\n\n" +  "ID NUMBER : " +  id_no.getText().toString() + "\n\n" + "ACCOUNT NUMBER : "  +  acc_no.getText().toString()
                        + "\n\n"  + "BANK NAME : " + bank_name.getText().toString()  + "\n\n" + "MOBILE NUMBER : " +  mobile_no.getText().toString() );

                startActivity(emailIntent);
            }
        });
    }
}
