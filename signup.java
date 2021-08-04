package com.example.vendorproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class signup extends AppCompatActivity implements View.OnClickListener{
    private EditText phoneno, pwd;
    private Button vendorlogin, custlogin;
    boolean b;
    String pass;
    DatabaseReference reff;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        phoneno=(EditText)findViewById(R.id.phone);
        pwd=(EditText)findViewById(R.id.loginpwd);
        vendorlogin=(Button)findViewById(R.id.vendorbtn);
        custlogin=(Button)findViewById(R.id.custbtn);
        vendorlogin.setOnClickListener(this);
        custlogin.setOnClickListener(this);
    }
    @Override
    public void onClick(View view){
        String inputno=phoneno.getText().toString();
        String inputpwd=pwd.getText().toString();
        reff = FirebaseDatabase.getInstance().getReference().child("userinfo").child(inputno);
        validate(inputno, inputpwd, view);
    }
    private void validate(String n, String p, View view)
    {
        reff = FirebaseDatabase.getInstance().getReference().child("userinfo").child(n);
        reff.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                try {
                    pass = snapshot.child("password").getValue().toString();
                    if(p.equals(pass)){
                        Toast.makeText(signup.this,"Login Successful",Toast.LENGTH_SHORT).show();
                        Bundle bundle = new Bundle();
                        bundle.putString("phone", n);
                        if(view.equals(vendorlogin)){
                            Intent i= new Intent(signup.this, vendor.class);
                            i.putExtra("data", bundle);
                            startActivity(i);
                            finish();
                        }
                        else{
                            Intent i= new Intent(signup.this, customer.class);
                            i.putExtra("data", bundle);
                            startActivity(i);
                            finish();
                        }
                    }
                    else if(!p.equals(pass)) {
                        Toast.makeText(signup.this, "Login Failed!", Toast.LENGTH_SHORT).show();
                    }
                }
                catch (Exception e){
                    pass = "null";
                    Toast.makeText(signup.this, "Account does not exist", Toast.LENGTH_SHORT).show();
                    Intent i= new Intent(signup.this, MainActivity.class);
                    startActivity(i);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Intent i= new Intent(signup.this, MainActivity.class);
                startActivity(i);
                Toast.makeText(signup.this, "Database error", Toast.LENGTH_SHORT).show();
            }
        });
    }
}