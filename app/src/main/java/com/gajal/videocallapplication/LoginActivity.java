package com.gajal.videocallapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.gajal.videocallapplication.databinding.ActivityLoginBinding;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.FirebaseFirestore;

import Model.User;

public class LoginActivity extends AppCompatActivity {
ActivityLoginBinding binding;
private FirebaseAuth firebaseAuth;
FirebaseFirestore firebaseFirestore;
    GoogleSignInClient mGoogleSignInClient;
    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityLoginBinding.inflate(getLayoutInflater());
                setContentView(binding.getRoot());
                firebaseAuth=FirebaseAuth.getInstance();
                firebaseFirestore=FirebaseFirestore.getInstance();
             //   firebaseDatabase=FirebaseDatabase.getInstance();
        progressDialog=new ProgressDialog(LoginActivity.this);
        progressDialog.setTitle("CREATING ACCOUNT...");
        progressDialog.setMessage("we are creating your account");

        GoogleSignInOptions gso=new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString((R.string.default_web_client_id))).requestEmail().build();

mGoogleSignInClient = GoogleSignIn.getClient(this,gso);
binding.signup.setOnClickListener(new View.OnClickListener() {
    @Override

    public void onClick(View view) {
        progressDialog.show();
        User user=new User(binding.username.getText().toString(),binding.email.getText().toString(),binding.password.getText().toString());
        firebaseAuth.createUserWithEmailAndPassword(binding.email.getText().toString(),binding.password.getText().toString()).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                progressDialog.dismiss();

                if(task.isSuccessful()){

                    Toast.makeText(LoginActivity.this, "account created", Toast.LENGTH_SHORT).show();
//                    User user=new User(binding.username.getText().toString(),binding.email.getText().toString(),binding.password.getText().toString());
//
//
//                    firebaseDatabase.getReference().child("Users").child(id).setValue(user);
//                    Intent intent=new Intent(LoginActivity.this,MainActivity.class);
//                    startActivity(intent);
              firebaseFirestore.collection("Users").document().set(user).addOnSuccessListener(new OnSuccessListener<Void>() {
                  @Override
                  public void onSuccess(Void unused) {
Intent intent=new Intent(LoginActivity.this,DashBoardActivity.class);
startActivity(intent);
                  }
              });
                }
                else
                {
                    Toast.makeText(LoginActivity.this, task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                }

            }
        });

    }
});
//        binding.alreadyhaveaccount.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intent=new Intent(LoginActivity.this,SignInActivity.class);
//                startActivity(intent);
//            }
//        });
    }

    }
