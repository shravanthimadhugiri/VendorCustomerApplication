package com.example.vendorproject;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    private EditText username, pwd, pc, ph;
    private Button signup, login;
    DatabaseReference reff;
    userinfo info;
    String regex="^(?=.*[A-Za-z])(?=.*\\d)(?=.*[@$!%*#?&])[A-Za-z\\d@$!%*#?&]{8,}$";
    String regexph="^[2-9]{2}[0-9]{8}$";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        username=(EditText)findViewById(R.id.name);
        ph=(EditText)findViewById(R.id.phoneno);
        pc=(EditText)findViewById(R.id.pincode);
        pwd=(EditText)findViewById(R.id.pwd);
        signup=(Button)findViewById(R.id.signup);
        login=(Button)findViewById(R.id.loginbtn);
        signup.setOnClickListener(this);
        login.setOnClickListener(this);
        info = new userinfo();
        reff = FirebaseDatabase.getInstance().getReference().child("userinfo");
    }
    @Override
    public void onClick(View view) {
        if(view.equals(signup)) {
            String name = username.getText().toString();
            String pass = pwd.getText().toString();
            Long phone = Long.parseLong(ph.getText().toString());
            Long pin = Long.parseLong(pc.getText().toString());
            if (!(name.equals("") && pass.equals("") && phone.equals("") && pin.equals("")))
            {
                if (validate(pass) && validateph(Long.toString(phone)))
                {

                    info.setName(name);
                    info.setPassword(pass);
                    //info.setPh(phone);
                    info.setPincode(pin);
                    reff.child(Long.toString(phone)).setValue(info);
                    Toast.makeText(getBaseContext(), "Successfully signed up", Toast.LENGTH_LONG).show();
                    Intent i = new Intent(this, signup.class);
                    startActivity(i);

                }
                else if(!validate(pass))
                {
                    Toast.makeText(getBaseContext(), "Invalid Password", Toast.LENGTH_LONG).show();
                }
                else if(!validateph(Long.toString(phone)))
                {
                    Toast.makeText(getBaseContext(), "Invalid Phone Number", Toast.LENGTH_LONG).show();
                }
            }
            else
            {
                Toast.makeText(getBaseContext(), "Enter details", Toast.LENGTH_LONG).show();
            }
        }
        else{
            Intent i = new Intent(this, signup.class);
            startActivity(i);
        }
    }
    public boolean validate(String p)
    {
        Pattern pat= Pattern.compile(regex);
        Matcher m=pat.matcher(p);
        return m.matches();
    }
    public boolean validateph(String p)
    {
        Pattern pat= Pattern.compile(regexph);
        Matcher m=pat.matcher(p);
        return m.matches();
    }
}