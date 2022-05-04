package Prog3210ProjectChirag.Prog3210.ChiragFinalProject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.finalproject.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class NewRegister extends AppCompatActivity {

    public static final String TAG = "TAG";
    EditText FullName, Email, password,phone;
    Button BtnRegister;
    TextView loginBtn;
    FirebaseAuth _auth;
    ProgressBar progressBar;
    FirebaseFirestore fStore;
    String userID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_register);
        FullName = findViewById(R.id.txtName);
        Email = findViewById(R.id.txtEmail);
        phone = findViewById(R.id.txtPhone);
        password = findViewById(R.id.txtPassword);
        BtnRegister = findViewById(R.id.btnRegister);
        loginBtn = findViewById(R.id.lblLoginUser);

        _auth = FirebaseAuth.getInstance();
        fStore = FirebaseFirestore.getInstance();
        progressBar =  findViewById(R.id.progressBar);

        if(_auth.getCurrentUser() != null){
            startActivity(new Intent(getApplicationContext(), MainActivity.class));
            finish();
        }


        BtnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = Email.getText().toString().trim();
                String passwords =password.getText().toString().trim();
                String fullName = FullName.getText().toString();
                String phoneNumber = phone.getText().toString();
                if(TextUtils.isEmpty(email)){
                    Email.setError("Email is Required");
                    return;
                }
                if(TextUtils.isEmpty(passwords)){
                    password.setError("Password is Required");
                    return;
                }
                if(passwords.length()< 6){
                    password.setError("Password mush be greater or equal to 6 characters");
                    return;
                }

                progressBar.setVisibility(View.VISIBLE);
                //Register the user in firebase

                _auth.createUserWithEmailAndPassword(email,passwords).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            Toast.makeText( NewRegister.this,"User Created",Toast.LENGTH_SHORT).show();
                            userID = _auth.getCurrentUser().getUid();
                            DocumentReference documentReference = fStore.collection("users").document(userID);
                             Map<String,Object> user = new HashMap<>();
                                user.put("fName",fullName);
                                user.put("emailID",email);
                                user.put("phoneNum",phoneNumber);
                                documentReference.set(user).addOnSuccessListener(new OnSuccessListener<Void>() {
                                    @Override
                                    public void onSuccess(Void unused) {
                                        Log.d(TAG,"onSuccess: user profile is created for "+userID);
                                    }
                                }).addOnFailureListener(new OnFailureListener() {
                                    @Override
                                    public void onFailure(@NonNull Exception e) {
                                        Log.d(TAG,"onFailure: " + e.toString());
                                    }
                                });
                             startActivity(new Intent(getApplicationContext(), MainActivity.class));
                        }
                        else
                        {
                            Toast.makeText( NewRegister.this,"Error "+task.getException().getMessage()  ,Toast.LENGTH_SHORT).show();
                            progressBar.setVisibility(View.GONE);
                        }
                    }
                });
            }
        });
        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), LoginActivity.class));
            }
        });
    }
}