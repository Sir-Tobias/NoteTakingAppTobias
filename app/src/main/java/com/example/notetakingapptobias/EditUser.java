package com.example.notetakingapptobias;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class EditUser extends AppCompatActivity implements View.OnClickListener{
    private EditText fullnameUpdate, phonenoUpdate;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_user);

        //Initializing the update details
        fullnameUpdate = (EditText) findViewById(R.id.uFullname);
        phonenoUpdate = (EditText) findViewById(R.id.uPhoneNo);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.updateUser:
                updateUser();
                break;
            case R.id.logout:
                mAuth.signOut();
                finish();
                startActivity(new Intent(EditUser.this, com.example.notetakingapptobias.MainActivity.class));
        }

    }
    private void updateUser() {
        String fullname = fullnameUpdate.getText().toString().trim();
        String phoneNo = phonenoUpdate.getText().toString().trim();
        DatabaseReference fireDB = FirebaseDatabase.getInstance().getReference("Users").child(FirebaseAuth.getInstance().getCurrentUser().getUid());

        User userObj = new User(fullname, phoneNo); //updade the whole node
        fireDB.setValue(userObj).addOnSuccessListener(new OnSuccessListener<Void>()
        {
            @Override
            public void onSuccess(Void aVoid) {      // Write was successful!
                Toast.makeText(EditUser.this, "Update succesful", Toast.LENGTH_LONG).show();
            }}).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {// Write failed
                Toast.makeText(EditUser.this, "Update failed", Toast.LENGTH_LONG).show();
            }
        });
    }
}