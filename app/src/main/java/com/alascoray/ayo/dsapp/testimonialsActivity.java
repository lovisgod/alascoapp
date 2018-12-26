package com.alascoray.ayo.dsapp;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Timer;
import java.util.TimerTask;

public class testimonialsActivity extends AppCompatActivity {

    FloatingActionButton button;
    RecyclerView comments;
    DatabaseReference ref;
    ProgressDialog progress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_testimonials);
        comments =(RecyclerView)findViewById(R.id.mRecycler);
        comments.setHasFixedSize(true);
        comments.setLayoutManager(new LinearLayoutManager(this));
        ref = FirebaseDatabase.getInstance().getReference().child("Comments");
        ref.keepSynced(true);


        button = (FloatingActionButton) findViewById(R.id.add_comment);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(testimonialsActivity.this, addCommentActivity.class);
                startActivity(intent);
                finish();
            }
        });
        progress=new ProgressDialog(this);
        progress.setMessage("Downloading Testimonials! please make sure your internet connection is up");
        progress.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
        progress.setIndeterminate(true);
        progress.setProgress(0);
        progress.show();

        new Timer().schedule(new TimerTask() {
            public void run() {
                progress.dismiss();
            }
        }, 7000);
    }

    @Override
    protected void onStart() {
        super.onStart();

     /**   Query query = FirebaseDatabase.getInstance()
                .getReference()
                .child("Comments");**/

        FirebaseRecyclerOptions<list_item> options =
                new FirebaseRecyclerOptions.Builder<list_item>()
                        .setQuery(ref, list_item.class)
                        .build();
        FirebaseRecyclerAdapter<list_item, commentViewHolder>firebaseRecyclerAdapter= new FirebaseRecyclerAdapter<list_item, commentViewHolder>(options) {
            @Override
            protected void onBindViewHolder(@NonNull commentViewHolder holder, int position, @NonNull list_item model) {
                holder.textViewName.setText(model.getName());
                holder.textViewDesc.setText(model.getDesc());
            }

            @Override
            public commentViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
                View v = LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.listmodel,parent, false);
                return new commentViewHolder(v);
            }
        };
        firebaseRecyclerAdapter.startListening();
        comments.setAdapter(firebaseRecyclerAdapter);
    }



    public class commentViewHolder extends RecyclerView.ViewHolder {
        private TextView textViewName;
        private TextView textViewDesc;

        public commentViewHolder(View itemView) {
            super(itemView);

            textViewName= (TextView)itemView.findViewById(R.id.textViewName);
            textViewDesc = (TextView)itemView.findViewById(R.id.textViewDesc);
        }
    }
}

