package com.tjnuman.dokan;

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
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.android.gms.auth.api.signin.internal.Storage;
import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.StorageTask;
import com.theartofdev.edmodo.cropper.CropImage;
import com.tjnuman.dokan.Prevalent.Prevalent;

import java.util.HashMap;

import de.hdodenhof.circleimageview.CircleImageView;

public class ProfileActivity extends AppCompatActivity {
    CircleImageView profileImage;
    TextView selectprofile;
    EditText fullName, UserAddress, userPhone;
    Button savebtn;

    DatabaseReference UsersRef;
    private Uri imageUri;
    private String myUrl = " ";
    private StorageReference storageProfilePrictureRef;
    private  String chacker = "";
    private StorageTask uploadTask;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        storageProfilePrictureRef = FirebaseStorage.getInstance().getReference().child("Profile pictures");

        selectprofile = findViewById(R.id.ChangeProfile);
        fullName = findViewById(R.id.Fullname);
        userPhone = findViewById(R.id.phoneNumber);
        UserAddress = findViewById(R.id.address);
        savebtn = findViewById(R.id.savebtn);
        profileImage = findViewById(R.id.uploadImage);


        //userInfoDisplay(fullName, userPhone, UserAddress,profileImage);

        savebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(chacker.equals("clicked"))
                {
                    userInfosaved();
                }
                else{
                    updateOnlyuser();

                }
            }
        });

        selectprofile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                chacker = "clicked";
                CropImage.activity(imageUri)
                        .setAspectRatio(1,1)
                        .start(ProfileActivity.this);
            }
        });
    }

    private void updateOnlyuser() {

        DatabaseReference ref = FirebaseDatabase.getInstance().getReference().child("Users");
        HashMap<String, Object> userMap = new HashMap<>();
        userMap.put("name", fullName.getText().toString());
        userMap.put("phoneOrder", userPhone.getText().toString());
        userMap.put("address", UserAddress.getText().toString());
        userMap.put("image",myUrl);

        ref.child(Prevalent.currentOnlineUser.getPhone()).updateChildren(userMap);
        //progressbar dismiss

        //start activity to go somewhere
        Toast.makeText(ProfileActivity.this, "Profile info saved successfully", Toast.LENGTH_SHORT).show();
    }



    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == CropImage.CROP_IMAGE_ACTIVITY_REQUEST_CODE && resultCode == RESULT_OK && data!=null){
            CropImage.ActivityResult result = CropImage.getActivityResult(data);
            imageUri = result.getUri();
            profileImage.setImageURI(imageUri);
        }
        else {
            Toast.makeText(this, "Error. try again", Toast.LENGTH_SHORT).show();
        }
    }



    private void userInfosaved() {


        if(TextUtils.isEmpty(fullName.getText().toString())){

            Toast.makeText(this, "Name is mandatory", Toast.LENGTH_SHORT).show();
        }
        else if(TextUtils.isEmpty(userPhone.getText().toString())){

            Toast.makeText(this, "phone is mandatory", Toast.LENGTH_SHORT).show();
        }
        else if(TextUtils.isEmpty(UserAddress.getText().toString())){

            Toast.makeText(this, "Address is mandatory", Toast.LENGTH_SHORT).show();
        }
        else if (chacker.equals("clicked")){
            uploadImage();

        }

    }

    private void uploadImage() {
        // progress bar show.

        if(imageUri!=null){

            final StorageReference fileRefg = storageProfilePrictureRef
                    .child(Prevalent.currentOnlineUser.getPhone() + ".jpg");
            uploadTask = fileRefg.putFile(imageUri);
            uploadTask.continueWith(new Continuation() {
                @Override
                public Object then(@NonNull Task task) throws Exception {

                    if(!task.isSuccessful())
                    {
                        throw task.getException();
                    }

                    return fileRefg.getDownloadUrl();
                }
            })

                    .addOnCompleteListener(new OnCompleteListener() {
                        @Override
                        public void onComplete(@NonNull Task task) {

                            if(task.isSuccessful()){
                                Uri downloadUri = (Uri) task.getResult();
                                myUrl = downloadUri.toString();
                                DatabaseReference ref = FirebaseDatabase.getInstance().getReference().child("Users");
                                HashMap<String, Object> userMap = new HashMap<>();
                                userMap.put("name", fullName.getText().toString());
                                userMap.put("phoneOrder", userPhone.getText().toString());
                                userMap.put("address", UserAddress.getText().toString());
                                userMap.put("image",myUrl);

                                //progressbar dismiss

                                //start activity to go somewhere
                                Toast.makeText(ProfileActivity.this, "Profile info saved successfully", Toast.LENGTH_SHORT).show();
                            }
                            else {
                                Toast.makeText(ProfileActivity.this, "Error. Try again", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
        }
        else{
            Toast.makeText(this, "Image is not selected", Toast.LENGTH_SHORT).show();
        }

    }




//    private void userInfoDisplay(EditText fullName, EditText userPhone, EditText UserAddress, CircleImageView profileImage) {
//
//        UsersRef = FirebaseDatabase.getInstance().getReference().child("Users").child(Prevalent.currentOnlineUser.getPhone());
//
//        UsersRef.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot snapshot) {
//                if (snapshot.exists()){
//                    if(snapshot.child("image").exists()){
//
//                        String image = snapshot.child("image").getValue().toString();
//                        String name = snapshot.child("name").getValue().toString();
//                        String phone = snapshot.child("phone").getValue().toString();
//                        String address = snapshot.child("address").getValue().toString();
//
//                        Glide.with(profileImage).load(image).into(profileImage);
//                        fullName.setText(name);
//                        userPhone.setText(phone);
//                        UserAddress.setText(address);
//
//                    }
//                }
//
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError error) {
//
//            }
//        });
//    }
}