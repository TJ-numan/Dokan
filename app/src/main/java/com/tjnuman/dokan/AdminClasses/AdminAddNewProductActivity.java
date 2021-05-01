package com.tjnuman.dokan.AdminClasses;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.airbnb.lottie.LottieAnimationView;
import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.tjnuman.dokan.R;
import com.tjnuman.dokan.UserClasses.AllProductFromCatagory;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;

public class AdminAddNewProductActivity extends AppCompatActivity {
    String CatagoryName, pdescription,pname,pprice, savecurrentdate, savecurrenttime,productRandomKey, downloadImageUrl;
    ImageView addNewProductimage;
    Button addButton;
    EditText productName,productPrice,getProductDescription;
    static final int GalleryPic = 1;
    Uri imageUri;
    LottieAnimationView loadingbar;
    StorageReference productImageref;
    DatabaseReference ProductsRef;
    String parentDB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_add_new_product2);

        loadingbar = findViewById(R.id.loadingbaranimation);
        CatagoryName = getIntent().getExtras().get("Catagory").toString();
        productImageref = FirebaseStorage.getInstance().getReference().child("Product Images");
        ProductsRef = FirebaseDatabase.getInstance().getReference().child("Products");
        Toast.makeText(this, CatagoryName, Toast.LENGTH_SHORT).show();
        addButton = findViewById(R.id.addbtn);
        addNewProductimage = findViewById(R.id.addimage);
        productName = findViewById(R.id.productname);
        getProductDescription = findViewById(R.id.productdescription);
        productPrice = findViewById(R.id.productprice);
        parentDB = getIntent().getStringExtra("ParentDB");

        addNewProductimage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                opengallery();
            }
        });

        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                validateProductData();
            }
        });

    }

    private void validateProductData() {

        pname = productName.getText().toString();
        pdescription = getProductDescription.getText().toString();
        pprice = productPrice.getText().toString();

        if (imageUri == null){
            Toast.makeText(this, "Please add a image first", Toast.LENGTH_LONG).show();
        }
        else if (TextUtils.isEmpty(pname)){
            Toast.makeText(this, "Please add the product name", Toast.LENGTH_LONG).show();
        }
        else if (TextUtils.isEmpty(pdescription)){

            Toast.makeText(this, "Please add the product description", Toast.LENGTH_LONG).show();
        }
        else if (TextUtils.isEmpty(pprice)){

            Toast.makeText(this, "Please add the product price", Toast.LENGTH_LONG).show();
        }
        else
        {
            storeproductinformation();
        }

    }

    private void storeproductinformation() {

        loadingbar.setVisibility(View.VISIBLE);

        Calendar calendar = Calendar.getInstance();

        SimpleDateFormat currentDate = new SimpleDateFormat("MMM dd, yyyy");
        savecurrentdate = currentDate.format(calendar.getTime());

        SimpleDateFormat currentTime = new SimpleDateFormat("HH:mm:ss a");
        savecurrenttime = currentTime.format(calendar.getTime());

        productRandomKey = savecurrentdate+savecurrenttime;

        final StorageReference filepath = productImageref.child(imageUri.getLastPathSegment() + productRandomKey + ".jpg");
        final UploadTask uploadTask = filepath.putFile(imageUri);

        uploadTask.addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {

                String message = e.toString();
                Toast.makeText(AdminAddNewProductActivity.this, "Error: " + message, Toast.LENGTH_LONG).show();
                loadingbar.setVisibility(View.GONE);


            }
        }).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                Toast.makeText(AdminAddNewProductActivity.this, "Image uploaded successfuly", Toast.LENGTH_LONG).show();
                Task<Uri> uriTask = uploadTask.continueWithTask(new Continuation<UploadTask.TaskSnapshot, Task<Uri>>() {
                    @Override
                    public Task<Uri> then(@NonNull Task<UploadTask.TaskSnapshot> task) throws Exception {
                        if(!task.isSuccessful())
                        {
                          throw task.getException();
                        }
                        downloadImageUrl = filepath.getDownloadUrl().toString();
                        return filepath.getDownloadUrl();
                    }
                }).addOnCompleteListener(new OnCompleteListener<Uri>() {
                    @Override
                    public void onComplete(@NonNull Task<Uri> task) {
                       if(task.isSuccessful()){
                           downloadImageUrl = task.getResult().toString();
                           Toast.makeText(AdminAddNewProductActivity.this, "got the product image Url successfully", Toast.LENGTH_LONG).show();
                           SaveProductInfoToDatabase();
                           loadingbar.setVisibility(View.GONE);
                       }
                    }
                });
            }
        });

    }

    private void SaveProductInfoToDatabase() {
        HashMap<String,Object> productMap = new HashMap<>();
        productMap.put("pid", productRandomKey);
        productMap.put("date", savecurrentdate);
        productMap.put("time", savecurrenttime);
        productMap.put("description", pdescription);
        productMap.put("image", downloadImageUrl);
        productMap.put("category",CatagoryName);
        productMap.put("price", pprice);
        productMap.put("pname", pname);

        ProductsRef.child(productRandomKey).updateChildren(productMap)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if(task.isSuccessful()){

                            Toast.makeText(AdminAddNewProductActivity.this, "Product added successfully", Toast.LENGTH_LONG).show();
                            Intent intent = new Intent(AdminAddNewProductActivity.this, AllProductFromCatagory.class);
                            intent.putExtra("Catagory",CatagoryName);
                            intent.putExtra("ParentDB",parentDB);
                            startActivity(intent);
                            loadingbar.setVisibility(View.GONE);
                        }
                        else{

                            String errormessage = task.getException().toString();
                            Toast.makeText(AdminAddNewProductActivity.this, "Error:"+errormessage, Toast.LENGTH_SHORT).show();
                            loadingbar.setVisibility(View.GONE);
                        }
                    }
                });
    }

    private void opengallery() {
        Intent galleryintent = new Intent();
        galleryintent.setAction(Intent.ACTION_GET_CONTENT);
        galleryintent.setType("image/*");
        startActivityForResult(galleryintent,GalleryPic);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == GalleryPic &&  resultCode == RESULT_OK && data!=null){
            imageUri = data.getData();
            addNewProductimage.setImageURI(imageUri);

        }

    }
}