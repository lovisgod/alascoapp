package com.example.ayo.dsapp;

import android.content.ContentResolver;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Handler;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.squareup.picasso.Picasso;

public class addCommentActivity extends AppCompatActivity {

    EditText customerName;
    EditText customerComment;
    Button submmitCom;
    DatabaseReference databaseRef;
    StorageReference storageReference;
    String id;
   // Button fromCamera;
    //Button fromGallery;
    FloatingActionButton choose;
    ImageView customerImage;
    Bitmap imageBitmap;
   // ProgressBar mProgress;

    static final int REQUEST_IMAGE_CAPTURE = 1;
    static final int PICK_IMAGE_REQUEST = 1;

    Uri imageUri;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_comment);

        customerImage=(ImageView)findViewById(R.id.logo);
       // fromCamera = (Button)findViewById(R.id.cammera);
       // fromGallery = (Button)findViewById(R.id.gallery);
        choose= (FloatingActionButton)findViewById(R.id.add_image);
        customerName = (EditText)findViewById(R.id.name);
        customerComment =(EditText)findViewById(R.id.comment);
        submmitCom = (Button)findViewById(R.id.submit);
        databaseRef = FirebaseDatabase.getInstance().getReference("Comments");
        storageReference = FirebaseStorage.getInstance().getReference("storages");
         id = databaseRef.push().getKey();
        //mProgress = (ProgressBar)findViewById(R.id.progress);

        choose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                pickPictureIntent();
               /** if(fromCamera.getVisibility()==View.VISIBLE){
                    fromCamera.setVisibility(View.INVISIBLE);
                }else{
                    fromCamera.setVisibility(View.VISIBLE);
                }
                if(fromGallery.getVisibility()==View.VISIBLE){
                    fromGallery.setVisibility(View.INVISIBLE);
                }else{
                    fromGallery.setVisibility(View.VISIBLE);
                }**/

            }
        });


        submmitCom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                changeTexts();
            }
        });

       /** fromCamera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                takePictureIntent();
            }
        });

        fromGallery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pickPictureIntent();
            }
        });**/
    }

    private void changeTexts() {
        String nameCustom = customerName.getText().toString().trim();
        String commentCustom = customerComment.getText().toString().trim();


        if(!TextUtils.isEmpty(commentCustom)){

           // String id = databaseRef.push().getKey();
            list_itemAdd commenttt = new list_itemAdd(id, commentCustom, nameCustom);
            databaseRef.child(id).setValue(commenttt);
            Intent intent = new Intent(addCommentActivity.this, testimonialsActivity.class);
            startActivity(intent);
            Toast.makeText(this,"comment added",Toast.LENGTH_SHORT).show();
            finish();


            if(imageUri !=null){

                StorageReference fileREference = storageReference.child
                        (System.currentTimeMillis()+ "."+ getFileExtension(imageUri));
                fileREference.putFile(imageUri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                        String imagee = taskSnapshot.getDownloadUrl().toString();
                      // String id = databaseRef.push().getKey();
                        list_itemImage upload = new list_itemImage(id,imagee);
                        databaseRef.child(id).setValue(upload);
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(addCommentActivity.this,e.getMessage(),
                                Toast.LENGTH_SHORT).show();
                    }
                }).addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onProgress(UploadTask.TaskSnapshot taskSnapshot) {

                    }
                });

            }else{
                Toast.makeText(this, "you have not selected any file",Toast.LENGTH_SHORT).show();
            }

        }else {
            Toast.makeText(this, "comment cannot be empty", Toast.LENGTH_LONG).show();
        }


    }

    /**private void takePictureIntent() {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
            startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
        }
    }**/

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
       /** if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
            Bundle extras = data.getExtras();
            imageBitmap = (Bitmap) extras.get("data");
            customerImage.setImageBitmap(imageBitmap);
        }**/

        if(requestCode==PICK_IMAGE_REQUEST && resultCode==RESULT_OK && data !=null && data.getData() !=null){
            imageUri = data.getData();
            Picasso.with(this).load(imageUri).into(customerImage);
        }
    }


    private void pickPictureIntent() {
        Intent pickPictureIntent = new Intent();
        pickPictureIntent.setType("image/*");
        pickPictureIntent.setAction(Intent.ACTION_GET_CONTENT);
       startActivityForResult(pickPictureIntent,PICK_IMAGE_REQUEST);

    }
//this get the file extension of the image
    private String getFileExtension(Uri uri){
        ContentResolver cr = getContentResolver();
        MimeTypeMap mime = MimeTypeMap.getSingleton();
        return mime.getMimeTypeFromExtension(cr.getType(uri));
    }


}
