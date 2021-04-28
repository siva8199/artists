 package com.example.artist;

 import android.content.ContentResolver;
 import android.content.Intent;
 import android.net.Uri;
 import android.os.Bundle;
 import android.text.TextUtils;
 import android.util.Log;
 import android.view.LayoutInflater;
 import android.view.View;
 import android.webkit.MimeTypeMap;
 import android.widget.Button;
 import android.widget.EditText;
 import android.widget.ImageView;
 import android.widget.ProgressBar;
 import android.widget.TextView;
 import android.widget.Toast;

 import androidx.annotation.NonNull;
 import androidx.annotation.Nullable;
 import androidx.appcompat.app.AlertDialog;
 import androidx.appcompat.app.AppCompatActivity;

 import com.google.android.gms.tasks.Continuation;
 import com.google.android.gms.tasks.OnCompleteListener;
 import com.google.android.gms.tasks.OnFailureListener;
 import com.google.android.gms.tasks.OnSuccessListener;
 import com.google.android.gms.tasks.Task;
 import com.google.firebase.auth.FirebaseAuth;
 import com.google.firebase.auth.FirebaseUser;
 import com.google.firebase.database.DatabaseReference;
 import com.google.firebase.database.FirebaseDatabase;
 import com.google.firebase.firestore.DocumentReference;
 import com.google.firebase.firestore.FirebaseFirestore;
 import com.google.firebase.storage.FirebaseStorage;
 import com.google.firebase.storage.StorageReference;
 import com.google.firebase.storage.UploadTask;
 import com.squareup.picasso.Picasso;

 import java.util.HashMap;
 import java.util.Map;



 public class profile extends AppCompatActivity {

    EditText etname,etprofession,etlocation,etemail,etgender,etsocial;
    Button button ;
    TextView diabuttom;
    ImageView imageview;
    ProgressBar progressBar;
    Uri imageUri;
    UploadTask uploadTask;
    StorageReference storageReference;
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference databaseReference;
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    DocumentReference documentReference;
    private  static final int PICK_IMAGE = 1;
    AllUserMmber memeber;
    String currentUserID;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        diabuttom = findViewById(R.id.diabtn);
        memeber = new AllUserMmber();
        imageview = findViewById(R.id.iv_cp);
        etname = findViewById(R.id.et_name_cp);
        etprofession = findViewById(R.id.et_prof_cp);
        etlocation = findViewById(R.id.et_loc_cp);
        etemail = findViewById(R.id.et_email_cp);
        etgender = findViewById(R.id.et_gen_cp);
        etsocial = findViewById(R.id.et_ph_cp);
        button = findViewById(R.id.btn_cp);
        progressBar = (ProgressBar) findViewById(R.id.progressbar_cp);

       FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
       currentUserID = user.getUid();

       documentReference = db.collection("user").document(currentUserID);
       storageReference = FirebaseStorage.getInstance().getReference("Profile Images");
       
       databaseReference = database.getReference("ALL Users");
       
       button.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View view) {
             uploadData();
          }
       });
      imageview.setOnClickListener(new View.OnClickListener() {
         @Override
         public void onClick(View view) {
            Intent intent = new Intent();
            intent.setType("image/*");
            intent.setAction(Intent.ACTION_GET_CONTENT);
            startActivityForResult(intent,PICK_IMAGE);
         }
      });

      diabuttom.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View v) {
                    showDialog();
          }


      });
    }

     private void showDialog() {

         LayoutInflater inflater = LayoutInflater.from(this);
         View view = inflater.inflate(R.layout.infolay,null);

         Button accebtn = view.findViewById(R.id.okbtn);

         accebtn.setOnClickListener(new View.OnClickListener() {
             private String TAG;

             @Override
             public void onClick(View v) {
                 Log.e(TAG, "onClick: ok button");
             }
         });


         AlertDialog alertDialog  = new AlertDialog.Builder(this)
                 .setView(view)
                 .create();

         alertDialog.show();

     }

     @Override
     protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
         super.onActivityResult(requestCode, resultCode, data);

         try {

             if(requestCode == PICK_IMAGE || resultCode == RESULT_OK || data != null || data.getData() != null){

                 imageUri = data.getData();
                 Picasso.get().load(imageUri).into(imageview);

             }

         }catch (Exception e){
             Toast.makeText(this, "Error"+e, Toast.LENGTH_SHORT).show();
         }


     }


     private String getFileExt(Uri uri){
         ContentResolver contentResolver = getContentResolver();
         MimeTypeMap mimeTypeMap = MimeTypeMap.getSingleton();
         return mimeTypeMap.getExtensionFromMimeType((contentResolver.getType(uri)));

     }

     public void uploadData() {

            String name = etname.getText().toString();
            String prof = etprofession.getText().toString();
            String loca = etlocation.getText().toString();
            String email = etemail.getText().toString();
            String gende = etgender.getText().toString();
            String sco =etsocial.getText().toString();


            if(!TextUtils.isEmpty(name) || !TextUtils.isEmpty(prof) || !TextUtils.isEmpty(loca) || !TextUtils.isEmpty(email) ||
                    !TextUtils.isEmpty(gende) || !TextUtils.isEmpty(sco) || imageUri != null ){

                progressBar.setVisibility(View.VISIBLE);
                final  StorageReference reference = storageReference.child(System.currentTimeMillis()+"."+getFileExt(imageUri));
                uploadTask = reference.putFile(imageUri);

                Task<Uri> uriTask = uploadTask.continueWithTask(new Continuation<UploadTask.TaskSnapshot, Task<Uri>>() {
                    @Override
                    public Task<Uri> then(@NonNull Task<UploadTask.TaskSnapshot> task) throws Exception {
                        if (!task.isSuccessful()){
                            throw task.getException();
                        }
                        return reference.getDownloadUrl();
                    }
                }).addOnCompleteListener(new OnCompleteListener<Uri>() {
                    @Override
                    public void onComplete(@NonNull Task<Uri> task) {

                        if (task.isSuccessful()){
                            Uri downloadUri = task.getResult();

                            Map<String,String> profile = new HashMap<>();
                            profile.put("name",name);
                            profile.put("Profession",prof);
                            profile.put("Location",loca);
                            profile.put("Email",email);
                            profile.put("gender",gende);
                            profile.put("Social",sco);
                            profile.put("uid",currentUserID);
                            profile.put("url",downloadUri.toString());
                            profile.put("privacy","public");

                            memeber.setName(name);
                            memeber.setProf(prof);
                            memeber.setLocation(loca);
                            memeber.setEmail(email);
                            memeber.setGender(gende);
                            memeber.setSco(sco);
                            memeber.setUid(currentUserID);
                            memeber.setUrl(downloadUri.toString());

                            databaseReference.child(currentUserID).setValue(memeber);

                            db.collection("users")
                                    .add(profile)
                                    .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                                        @Override
                                        public void onSuccess(DocumentReference documentReference) {
                                            progressBar.setVisibility(View.VISIBLE);
                                            Toast.makeText(profile.this, "Profile Created", Toast.LENGTH_SHORT).show();

                                        }
                                    })


                                    .addOnFailureListener(new OnFailureListener() {
                                        @Override
                                        public void onFailure(@NonNull Exception e) {
                                            progressBar.setVisibility(View.INVISIBLE);
                                            Toast.makeText(profile.this, "Profile Created", Toast.LENGTH_SHORT).show();
                                        }
                                    });


                        }
                    }
                });


            }else {
                Toast.makeText(this, "Please fill all the fields", Toast.LENGTH_SHORT).show();
            }


     }
 }